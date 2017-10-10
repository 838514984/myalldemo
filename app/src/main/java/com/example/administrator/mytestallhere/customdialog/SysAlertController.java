package com.example.administrator.mytestallhere.customdialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.CursorAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.administrator.mytestallhere.R;

import java.lang.ref.WeakReference;

public class SysAlertController {
	private static final int MATCH_PARENT = LinearLayout.LayoutParams.FILL_PARENT;

	protected final Context mContext;
	private final DialogInterface mDialogInterface;
	private final Window mWindow;

	private CharSequence mTitle;

	private CharSequence mMessage;

	private ListView mListView;

	private View mView;

	private int mViewSpacingLeft;

	private int mViewSpacingTop;

	private int mViewSpacingRight;

	private int mViewSpacingBottom;

	private boolean mViewSpacingSpecified = false;

	private Button mButtonPositive;

	private CharSequence mButtonPositiveText;

	private Message mButtonPositiveMessage;

	private Button mButtonNegative;

	private CharSequence mButtonNegativeText;

	private Message mButtonNegativeMessage;

	private Button mButtonNeutral;

	private CharSequence mButtonNeutralText;

	private Message mButtonNeutralMessage;

	private ScrollView mScrollView;

	private int mIconId = 0; // 为0默认隐藏图标，为-1则显示

	private Drawable mIcon;

	private ImageView mIconView;

	private TextView mTitleView;

	private View titleDivider;

	private TextView mMessageView;

	private View mCustomTitleView;

	protected boolean mForceInverseBackground;

	private ListAdapter mAdapter;

	private int mCheckedItem = -1;

	private Handler mHandler;

	public static int m_MyAlertContentViewId = R.layout.dialog_layout; // 默认的对话框布局ID

	View.OnClickListener mButtonHandler = new View.OnClickListener() {
		public void onClick(View v) {
			Message m = null;
			if (v == mButtonPositive && mButtonPositiveMessage != null) {
				m = Message.obtain(mButtonPositiveMessage);
			} else if (v == mButtonNegative && mButtonNegativeMessage != null) {
				m = Message.obtain(mButtonNegativeMessage);
			} else if (v == mButtonNeutral && mButtonNeutralMessage != null) {
				m = Message.obtain(mButtonNeutralMessage);
			}
			if (m != null) {
				m.sendToTarget();
			}

			// Post a message so we dismiss after the above handlers are
			// executed
			mHandler.obtainMessage(ButtonHandler.MSG_DISMISS_DIALOG, mDialogInterface).sendToTarget();
		}
	};

	private static final class ButtonHandler extends Handler {
		// Button clicks have Message.what as the BUTTON{1,2,3} constant
		private static final int MSG_DISMISS_DIALOG = 1;

		private WeakReference<DialogInterface> mDialog;

		public ButtonHandler(DialogInterface dialog) {
			mDialog = new WeakReference<DialogInterface>(dialog);
		}

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {

				case DialogInterface.BUTTON_POSITIVE:
				case DialogInterface.BUTTON_NEGATIVE:
				case DialogInterface.BUTTON_NEUTRAL:
					((DialogInterface.OnClickListener) msg.obj).onClick(mDialog.get(), msg.what);
					break;

				case MSG_DISMISS_DIALOG:
					((DialogInterface) msg.obj).dismiss();
			}
		}
	}

	public SysAlertController(Context context, DialogInterface di, Window window) {
		mContext = context;
		mDialogInterface = di;
		mWindow = window;
		mHandler = new ButtonHandler(di);
	}

	static boolean canTextInput(View v) {//是否可输入文本
		if (v.onCheckIsTextEditor()) {
			return true;
		}

		if (!(v instanceof ViewGroup)) {
			return false;
		}

		ViewGroup vg = (ViewGroup) v;
		int i = vg.getChildCount();
		while (i > 0) {
			i--;
			v = vg.getChildAt(i);
			if (canTextInput(v)) {
				return true;
			}
		}
		return false;
	}

	public void installContent() {
		/* We use a custom title so never request a window title */
		mWindow.requestFeature(Window.FEATURE_NO_TITLE);

		if (mView == null || !canTextInput(mView)) {
			mWindow.setFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM, WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
		}
		mWindow.setContentView(m_MyAlertContentViewId);
		setupView();
	}

	public void setTitle(CharSequence title) {
		mTitle = title;
		if (mTitleView != null) {
			mTitleView.setText(title);
		}
	}

	/**
	 * @see AlertDialog.Builder#setCustomTitle(View)
	 */
	public void setCustomTitle(View customTitleView) {
		mCustomTitleView = customTitleView;
	}

	public void setMessage(CharSequence message) {
		mMessage = message;
		if (mMessageView != null) {
			mMessageView.setText(message);
			mMessageView.setMovementMethod(LinkMovementMethod.getInstance());
		}
	}

	/**
	 * Set the view to display in the dialog.
	 */
	public void setView(View view) {
		mView = view;
		mViewSpacingSpecified = false;
	}

	/**
	 * Set the view to display in the dialog along with the spacing around that view(Builder的对应方法属于hide，此方法不会调用到)
	 */
	public void setView(View view, int viewSpacingLeft, int viewSpacingTop, int viewSpacingRight, int viewSpacingBottom) {
		mView = view;
		mViewSpacingSpecified = true;
		mViewSpacingLeft = viewSpacingLeft;
		mViewSpacingTop = viewSpacingTop;
		mViewSpacingRight = viewSpacingRight;
		mViewSpacingBottom = viewSpacingBottom;
	}

	/**
	 * Sets a click listener or a message to be sent when the button is clicked.
	 * You only need to pass one of {@code listener} or {@code msg}.
	 * @param whichButton
	 *            Which button, can be one of
	 *            {@link DialogInterface#BUTTON_POSITIVE},
	 *            {@link DialogInterface#BUTTON_NEGATIVE}, or
	 *            {@link DialogInterface#BUTTON_NEUTRAL}
	 * @param text The text to display in positive button.
	 * @param listener The {@link DialogInterface.OnClickListener} to use.
	 * @param msg The {@link Message} to be sent when clicked.
	 */
	public void setButton(int whichButton, CharSequence text, DialogInterface.OnClickListener listener, Message msg) {

		if (msg == null && listener != null) {
			msg = mHandler.obtainMessage(whichButton, listener);
		}

		switch (whichButton) {

			case DialogInterface.BUTTON_POSITIVE:
				mButtonPositiveText = text;
				mButtonPositiveMessage = msg;
				break;

			case DialogInterface.BUTTON_NEGATIVE:
				mButtonNegativeText = text;
				mButtonNegativeMessage = msg;
				break;

			case DialogInterface.BUTTON_NEUTRAL:
				mButtonNeutralText = text;
				mButtonNeutralMessage = msg;
				break;

			default:
				throw new IllegalArgumentException("Button does not exist");
		}
	}

	/**
	 * Set resId to 0 if you don't want an icon.
	 * @param resId the resourceId of the drawable to use as the icon or 0 if you don't want an icon.
	 */
	public void setIcon(int resId) {
		mIconId = resId;
		if (mIconView != null) {
			if (resId > 0) {
				mIconView.setImageResource(mIconId);
			} else if (resId == 0) {
				mIconView.setVisibility(View.GONE);
			}
		}
	}

	public void setIcon(Drawable icon) {
		mIcon = icon;
		if ((mIconView != null) && (mIcon != null)) {
			mIconView.setImageDrawable(icon);
		}
	}

	public void setInverseBackgroundForced(boolean forceInverseBackground) {
		mForceInverseBackground = forceInverseBackground;
	}

	public ListView getListView() {
		return mListView;
	}

	public Button getButton(int whichButton) {
		switch (whichButton) {
			case DialogInterface.BUTTON_POSITIVE:
				return mButtonPositive;
			case DialogInterface.BUTTON_NEGATIVE:
				return mButtonNegative;
			case DialogInterface.BUTTON_NEUTRAL:
				return mButtonNeutral;
			default:
				return null;
		}
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		return mScrollView != null && mScrollView.executeKeyEvent(event);
	}

	public boolean onKeyUp(int keyCode, KeyEvent event) {
		return mScrollView != null && mScrollView.executeKeyEvent(event);
	}

	private void setupView() {
		LinearLayout contentPanel = (LinearLayout) mWindow.findViewById(R.id.contentPanel);
		setupContent(contentPanel);
		boolean hasButtons = setupButtons();

		LinearLayout topPanel = (LinearLayout) mWindow.findViewById(R.id.topPanel);
		TypedArray a = mContext.obtainStyledAttributes(null, R.styleable.HerilyAlertDialog, R.style.alertDialogStyle, 0);

		boolean hasTitle = setupTitle(topPanel);

		View buttonPanel = mWindow.findViewById(R.id.buttonPanel);
		if (!hasButtons) {//无按钮、隐藏按钮布局和按钮上部分割线布局
			buttonPanel.setVisibility(View.GONE);
			mWindow.findViewById(R.id.btnUpDivider).setVisibility(View.GONE);
		}

		FrameLayout customPanel = null;
		if (mView != null) {
			customPanel = (FrameLayout) mWindow.findViewById(R.id.customPanel);
			FrameLayout custom = (FrameLayout) mWindow.findViewById(R.id.custom);
			custom.addView(mView, new LayoutParams(MATCH_PARENT, MATCH_PARENT));
			if (mViewSpacingSpecified) {
				custom.setPadding(mViewSpacingLeft, mViewSpacingTop, mViewSpacingRight, mViewSpacingBottom);
			}
			if (mListView != null) {
				((LinearLayout.LayoutParams) customPanel.getLayoutParams()).weight = 0;
			}
		} else {
			mWindow.findViewById(R.id.customPanel).setVisibility(View.GONE);
		}

		/*
		 * Only display the divider if we have a title and a custom view or a message.
		 */
//		if (hasTitle && ((mMessage != null) || (mView != null))) {
//			View divider = mWindow.findViewById(R.id.titleDivider);
//			divider.setVisibility(View.VISIBLE);
//		}
		/*
		 *modify: only display the divider if we have a title! 
		 */
		/*if (hasTitle) {
			View divider = mWindow.findViewById(R.id.titleDivider);
			divider.setVisibility(View.VISIBLE);
		}*/

		setBackground(topPanel, contentPanel, customPanel, hasButtons, a, hasTitle, buttonPanel);
		a.recycle();
	}

	private boolean setupTitle(LinearLayout topPanel) {
		boolean hasTitle = true;

		if (mCustomTitleView != null) {//有自定义title则隐藏默认的title
			// Add the custom title view directly to the topPanel layout
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

			topPanel.addView(mCustomTitleView, 1, lp);

			// Hide the title template
			View titleTemplate = mWindow.findViewById(R.id.title_template);
			titleTemplate.setVisibility(View.GONE);
		} else {
			final boolean hasTextTitle = !TextUtils.isEmpty(mTitle);

			mIconView = (ImageView) mWindow.findViewById(R.id.icon);//若不需要ICON图标，注释此代码即可
			if (hasTextTitle) {
				/* Display the title if a title is supplied, else hide it */
				mTitleView = (TextView) mWindow.findViewById(R.id.alertTitle);
				titleDivider =  mWindow.findViewById(R.id.title_divider);
				titleDivider.setVisibility(View.VISIBLE);
				mTitleView.setText(mTitle);
				//mIconView.setImageResource(R.drawable.ic_dialog_menu_generic);
				
				/*
				 * Do this last so that if the user has supplied any icons we use them instead of the default ones. If the user has specified 0 then make it disappear.
				 */
				if(mIconView != null)
                if (mIconId > 0) {
                    mIconView.setImageResource(mIconId);
                } else if (mIcon != null) {
                    mIconView.setImageDrawable(mIcon);
                } else if (mIconId == 0) {
                    /* Apply the padding from the icon to ensure the title is aligned correctly.
                     */
//                  mTitleView.setPadding(mIconView.getPaddingLeft(),mIconView.getPaddingTop(),mIconView.getPaddingRight(),mIconView.getPaddingBottom());
                    mIconView.setVisibility(View.GONE);
                }
			} else {
				// Hide the title template
				View titleTemplate = mWindow.findViewById(R.id.title_template);
				titleTemplate.setVisibility(View.GONE);
//				mIconView.setVisibility(View.GONE);
				hasTitle = false;
			}
		}
		return hasTitle;
	}

	private void setupContent(LinearLayout contentPanel) {
		mScrollView = (ScrollView) mWindow.findViewById(R.id.scrollView);
		mScrollView.setFocusable(false);

		// Special case for users that only want to display a String
		mMessageView = (TextView) mWindow.findViewById(R.id.message);
		if (mMessageView == null) {
			return;
		}

		if (mMessage != null) {//对话框如果设置有Message则会忽略设置的列表
			mMessageView.setText(mMessage);
			mMessageView.setMovementMethod(LinkMovementMethod.getInstance());
		} else {
			mMessageView.setVisibility(View.GONE);
			mScrollView.removeView(mMessageView);

			if (mListView != null) {
				contentPanel.removeView(mWindow.findViewById(R.id.scrollView));
				contentPanel.addView(mListView, new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT));
				contentPanel.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, 0, 1.0f));
			} else {
				contentPanel.setVisibility(View.GONE);
			}
		}
	}

	private boolean setupButtons() {
		int BIT_BUTTON_POSITIVE = 1;
		int BIT_BUTTON_NEGATIVE = 2;
		int BIT_BUTTON_NEUTRAL = 4;
		int whichButtons = 0;

		mButtonPositive = (Button) mWindow.findViewById(R.id.mButtonPositive);
		mButtonPositive.setOnClickListener(mButtonHandler);

		if (TextUtils.isEmpty(mButtonPositiveText)) {
			mButtonPositive.setVisibility(View.GONE);
		} else {
			mButtonPositive.setText(mButtonPositiveText);
			mButtonPositive.setVisibility(View.VISIBLE);
			whichButtons = whichButtons | BIT_BUTTON_POSITIVE;
		}

		mButtonNegative = (Button) mWindow.findViewById(R.id.mButtonNegative);
		mButtonNegative.setOnClickListener(mButtonHandler);

		if (TextUtils.isEmpty(mButtonNegativeText)) {
			mButtonNegative.setVisibility(View.GONE);
		} else {
			mButtonNegative.setText(mButtonNegativeText);
			mButtonNegative.setVisibility(View.VISIBLE);

			whichButtons = whichButtons | BIT_BUTTON_NEGATIVE;
		}

		mButtonNeutral = (Button) mWindow.findViewById(R.id.mButtonNeutral);
		mButtonNeutral.setOnClickListener(mButtonHandler);

		if (TextUtils.isEmpty(mButtonNeutralText)) {
			mButtonNeutral.setVisibility(View.GONE);
		} else {
			mButtonNeutral.setText(mButtonNeutralText);
			mButtonNeutral.setVisibility(View.VISIBLE);

			whichButtons = whichButtons | BIT_BUTTON_NEUTRAL;
		}

		/*
		 * If we only have 1 button it should be centered on the layout and
		 * expand to fill 50% of the available space.(自定义不需要此方式，注释掉)
		 */
		if (whichButtons == BIT_BUTTON_POSITIVE) {
			centerButton(mButtonPositive);
		} else if (whichButtons == BIT_BUTTON_NEGATIVE) {
			centerButton(mButtonNeutral);
		} else if (whichButtons == BIT_BUTTON_NEUTRAL) {
			centerButton(mButtonNeutral);
		}
		
		//TODO ...控制按钮间的竖向分割线 用whichButtons的位运算来判断存在哪几个按钮
		switch (whichButtons) {//通常情况只使用两个按钮，其中一个是POSITIVE
			case 7://Positive、Neutral、Negative
				mWindow.findViewById(R.id.btn_middle_divider).setVisibility(View.VISIBLE);
				mWindow.findViewById(R.id.btn_middle_divider2).setVisibility(View.VISIBLE);
				mButtonPositive.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.herily_alertex_dlg_btn_left_drawable));
				mButtonNeutral.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.herily_alertex_dlg_btn_center_drawable));
				mButtonNegative.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.herily_alertex_dlg_btn_right_bdrawable));
				break;
			case 5://Positive、Neutral
				mWindow.findViewById(R.id.btn_middle_divider).setVisibility(View.VISIBLE);
				mWindow.findViewById(R.id.btn_middle_divider2).setVisibility(View.GONE);
				mButtonPositive.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.herily_alertex_dlg_btn_left_drawable));
				mButtonNeutral.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.herily_alertex_dlg_btn_right_bdrawable));
				break;
			case 3://Positive、Negative
				mWindow.findViewById(R.id.btn_middle_divider).setVisibility(View.VISIBLE);
				mWindow.findViewById(R.id.btn_middle_divider2).setVisibility(View.GONE);
				mButtonPositive.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.herily_alertex_dlg_btn_left_drawable));
				mButtonNegative.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.herily_alertex_dlg_btn_right_bdrawable));
				break;
			case 6://Neutral、Negative
				mWindow.findViewById(R.id.btn_middle_divider).setVisibility(View.GONE);
				mWindow.findViewById(R.id.btn_middle_divider2).setVisibility(View.VISIBLE);
				mButtonNeutral.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.herily_alertex_dlg_btn_left_drawable));
				mButtonNegative.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.herily_alertex_dlg_btn_right_bdrawable));
				break;
			case 1://Positive	[一个按钮则隐藏竖向分割线]
				mWindow.findViewById(R.id.btn_middle_divider).setVisibility(View.GONE);
				mWindow.findViewById(R.id.btn_middle_divider).setVisibility(View.GONE);
				mButtonPositive.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.herily_alertex_dlg_btn_singlebtn_drawable));
				break;
			case 2://Negative
				mWindow.findViewById(R.id.btn_middle_divider).setVisibility(View.GONE);
				mWindow.findViewById(R.id.btn_middle_divider).setVisibility(View.GONE);
				mButtonNegative.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.herily_alertex_dlg_btn_singlebtn_drawable));
				break;
			case 4://Neutral
				mWindow.findViewById(R.id.btn_middle_divider).setVisibility(View.GONE);
				mWindow.findViewById(R.id.btn_middle_divider).setVisibility(View.GONE);
				mButtonNeutral.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.herily_alertex_dlg_btn_singlebtn_drawable));
				break;
			default://没有按钮
				break;
		}
		
		return whichButtons != 0;
	}

	private void centerButton(Button button) {
		//当只有一个按钮时应当让按钮宽度占一半并居中(自定义不需要此方法，注释掉)
//		LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) button.getLayoutParams();
//		params.gravity = Gravity.CENTER_HORIZONTAL;
//		params.weight = 0.5f;
//		button.setLayoutParams(params);
//		View leftSpacer = mWindow.findViewById(R.id.leftSpacer);
//		leftSpacer.setVisibility(View.VISIBLE);
//		View rightSpacer = mWindow.findViewById(R.id.rightSpacer);
//		rightSpacer.setVisibility(View.VISIBLE);
	}

	private void setBackground(LinearLayout topPanel, LinearLayout contentPanel, View customPanel, boolean hasButtons, TypedArray a, boolean hasTitle, View buttonPanel) {
		//dialog方框背景
//		//取消背景
//		int fullDark = 0;
//		int topDark = 0;
//		int centerDark = 0;
//		int bottomDark = 0;
//		int fullBright = 0;
//		int topBright = 0;
//		int centerBright = 0;
//		int bottomBright = 0;
//		int bottomMedium = 0;
		
		/* Get all the different background required */
		//自定义的背景
//		int fullDark = R.drawable.herily_alertex_dlg_bg_full_dark;
//		int topDark = R.drawable.herily_alertex_dlg_bg_top_dark;
//		int centerDark = R.drawable.herily_alertex_dlg_bg_center_dark;
//		int bottomDark = R.drawable.herily_alertex_dlg_bg_bottom_dark;
//		int fullBright = R.drawable.herily_alertex_dlg_bg_full_dark;
//		int topBright = R.drawable.herily_alertex_dlg_bg_top_dark;
//		int centerBright = R.drawable.herily_alertex_dlg_bg_center_dark;
//		int bottomBright = R.drawable.herily_alertex_dlg_bg_bottom_dark;
//		int bottomMedium = R.drawable.herily_alertex_dlg_bg_bottom_dark;

		//系统默认的背景
		int fullDark = a.getResourceId(R.styleable.HerilyAlertDialog_fullDark, R.drawable.herily_alertex_dlg_bg_full_dark);
		int topDark = a.getResourceId(R.styleable.HerilyAlertDialog_topDark, R.drawable.herily_alertex_dlg_bg_top_dark);
		int centerDark = a.getResourceId(R.styleable.HerilyAlertDialog_centerDark, R.drawable.herily_alertex_dlg_bg_center_dark);
		int bottomDark = a.getResourceId(R.styleable.HerilyAlertDialog_bottomDark, R.drawable.herily_alertex_dlg_bg_bottom_dark);
		int fullBright = a.getResourceId(R.styleable.HerilyAlertDialog_fullBright, R.drawable.herily_alertex_dlg_bg_full_dark);
		int topBright = a.getResourceId(R.styleable.HerilyAlertDialog_topBright, R.drawable.herily_alertex_dlg_bg_top_dark);
		int centerBright = a.getResourceId(R.styleable.HerilyAlertDialog_centerBright, R.drawable.herily_alertex_dlg_bg_center_dark);
		int bottomBright = a.getResourceId(R.styleable.HerilyAlertDialog_bottomBright, R.drawable.herily_alertex_dlg_bg_bottom_dark);
		int bottomMedium = a.getResourceId(R.styleable.HerilyAlertDialog_bottomMedium, R.drawable.herily_alertex_dlg_bg_bottom_dark);

		/*
		 * We now set the background of all of the sections of the alert. First
		 * collect together each section that is being displayed along with
		 * whether it is on a light or dark background, then run through them
		 * setting their backgrounds. This is complicated because we need to
		 * correctly use the full, top, middle, and bottom graphics depending on
		 * how many views they are and where they appear.
		 */

		View[] views = new View[4];
		boolean[] light = new boolean[4];
		View lastView = null;
		boolean lastLight = false;

		int pos = 0;
		if (hasTitle) {
			views[pos] = topPanel;
			light[pos] = true;
			pos++;
		}

		/*
		 * The contentPanel displays either a custom text message or a ListView.
		 * If it's text we should use the dark background for ListView we should
		 * use the light background. If neither are there the contentPanel will
		 * be hidden so set it as null.
		 */
		views[pos] = (contentPanel.getVisibility() == View.GONE) ? null : contentPanel;
		light[pos] = true;// mListView != null;
		pos++;
		if (customPanel != null) {
			views[pos] = customPanel;
			light[pos] = true;// mForceInverseBackground;
			pos++;
		}
		if (hasButtons) {
			views[pos] = buttonPanel;
			light[pos] = true;
		}

		boolean setView = false;
		for (pos = 0; pos < views.length; pos++) {
			View v = views[pos];
			if (v == null) {
				continue;
			}
			if (lastView != null) {
				if (!setView) {
					lastView.setBackgroundResource(lastLight ? topBright : topDark);
				} else {
					lastView.setBackgroundResource(lastLight ? centerBright : centerDark);
				}
				setView = true;
			}
			lastView = v;
			lastLight = light[pos];
		}

		if (lastView != null) {
			if (setView) {

				/*
				 * ListViews will use the Bright background but buttons use the
				 * Medium background.
				 */
				lastView.setBackgroundResource(lastLight ? (hasButtons ? bottomMedium : bottomBright) : bottomDark);
			} else {
				lastView.setBackgroundResource(lastLight ? fullBright : fullDark);
			}
		}

		/*
		 * TODO: uncomment section below. The logic for this should be if it's a
		 * Contextual menu being displayed AND only a Cancel button is shown
		 * then do this.
		 */
		// if (hasButtons && (mListView != null)) {
		/*
		 * Yet another *special* case. If there is a ListView with buttons don't
		 * put the buttons on the bottom but instead put them in the footer of
		 * the ListView this will allow more items to be displayed.
		 */

		/*
		 * contentPanel.setBackgroundResource(bottomBright);
		 * buttonPanel.setBackgroundResource(centerMedium); 
		 * ViewGroup parent = (ViewGroup) mWindow.findViewById(R.id.parentPanel);
		 * parent.removeView(buttonPanel); 
		 * AbsListView.LayoutParams params = new AbsListView.LayoutParams( AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.MATCH_PARENT);
		 * buttonPanel.setLayoutParams(params);
		 * mListView.addFooterView(buttonPanel);
		 */
		// }

		if ((mListView != null) && (mAdapter != null)) {
			mListView.setAdapter(mAdapter);
			if (mCheckedItem > -1) {
				mListView.setItemChecked(mCheckedItem, true);
				mListView.setSelection(mCheckedItem);
			}
		}
	}

	public static class RecycleListView extends ListView {
		boolean mRecycleOnMeasure = true;

		public RecycleListView(Context context) {
			super(context);
		}

		public RecycleListView(Context context, AttributeSet attrs) {
			super(context, attrs);
		}

		public RecycleListView(Context context, AttributeSet attrs, int defStyle) {
			super(context, attrs, defStyle);
		}

		protected boolean recycleOnMeasure() {
			return mRecycleOnMeasure;
		}
	}

	public static class AlertParams {
		public final Context mContext;
		public final LayoutInflater mInflater;

		public int mIconId = -1;
		public Drawable mIcon;
		public CharSequence mTitle;
		public View mCustomTitleView;
		public CharSequence mMessage;
		public CharSequence mPositiveButtonText;
		public DialogInterface.OnClickListener mPositiveButtonListener;
		public CharSequence mNegativeButtonText;
		public DialogInterface.OnClickListener mNegativeButtonListener;
		public CharSequence mNeutralButtonText;
		public DialogInterface.OnClickListener mNeutralButtonListener;
		public boolean mCancelable;
		public DialogInterface.OnCancelListener mOnCancelListener;
		public DialogInterface.OnKeyListener mOnKeyListener;
		public CharSequence[] mItems;
		public ListAdapter mAdapter;
		public DialogInterface.OnClickListener mOnClickListener;
		public View mView;
		public int mViewSpacingLeft;
		public int mViewSpacingTop;
		public int mViewSpacingRight;
		public int mViewSpacingBottom;
		public boolean mViewSpacingSpecified = false;
		public boolean[] mCheckedItems;
		public boolean mIsMultiChoice;
		public boolean mIsSingleChoice;
		public int mCheckedItem = -1;
		public DialogInterface.OnMultiChoiceClickListener mOnCheckboxClickListener;
		public Cursor mCursor;
		public String mLabelColumn;
		public String mIsCheckedColumn;
		public boolean mForceInverseBackground;
		public AdapterView.OnItemSelectedListener mOnItemSelectedListener;
		public OnPrepareListViewListener mOnPrepareListViewListener;
		public boolean mRecycleOnMeasure = true;

		/**
		 * Interface definition for a callback to be invoked before the ListView
		 * will be bound to an adapter.
		 */
		public interface OnPrepareListViewListener {
			/**
			 * Called before the ListView is bound to an adapter.
			 * @param listView The ListView that will be shown in the dialog.
			 */
			void onPrepareListView(ListView listView);
		}

		public AlertParams(Context context) {
			mContext = context;
			mCancelable = true;
			mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		public void apply(SysAlertController dialog) {
			if (mCustomTitleView != null) {//自定义和默认的title优先取自定义
				dialog.setCustomTitle(mCustomTitleView);
			} else {
				if (mTitle != null) {
					dialog.setTitle(mTitle);
				}
				if (mIcon != null) {//自定义不需要图标，注释
					dialog.setIcon(mIcon);
				}
				if (mIconId >= 0) {
					dialog.setIcon(mIconId);
				}
			}
			if (mMessage != null) {
				dialog.setMessage(mMessage);
			}
			
			if (mPositiveButtonText != null) {
				dialog.setButton(DialogInterface.BUTTON_POSITIVE, mPositiveButtonText, mPositiveButtonListener, null);
			}
			if (mNegativeButtonText != null) {
				dialog.setButton(DialogInterface.BUTTON_NEGATIVE, mNegativeButtonText, mNegativeButtonListener, null);
			}
			if (mNeutralButtonText != null) {
				dialog.setButton(DialogInterface.BUTTON_NEUTRAL, mNeutralButtonText, mNeutralButtonListener, null);
			}
			
			if (mForceInverseBackground) {
				dialog.setInverseBackgroundForced(true);
			}
			// For a list, the client can either supply an array of items or an adapter or a cursor
			if ((mItems != null) || (mCursor != null) || (mAdapter != null)) {
				createListView(dialog);
			}
			if (mView != null) {
				if (mViewSpacingSpecified) {
					dialog.setView(mView, mViewSpacingLeft, mViewSpacingTop, mViewSpacingRight, mViewSpacingBottom);
				} else {
					dialog.setView(mView);
				}
			}
			
			/*
			  dialog.setCancelable(mCancelable);
			  dialog.setOnCancelListener(mOnCancelListener); if (mOnKeyListener
			  != null) { dialog.setOnKeyListener(mOnKeyListener); }
			 */
		}

		private void createListView(final SysAlertController dialog) {
			final RecycleListView listView = (RecycleListView) mInflater.inflate(R.layout.dlg_select, null);
			ListAdapter adapter;

			if (mIsMultiChoice) {
				if (mCursor == null) {
					adapter = new ArrayAdapter<CharSequence>(mContext, R.layout.dlg_select_multichoice, android.R.id.text1, mItems) {
						@Override
						public View getView(int position, View convertView, ViewGroup parent) {
							View view = super.getView(position, convertView, parent);
							if (mCheckedItems != null) {
								boolean isItemChecked = mCheckedItems[position];
								if (isItemChecked) {
									listView.setItemChecked(position, true);
								}
							}
							return view;
						}
					};
				} else {
					adapter = new CursorAdapter(mContext, mCursor, false) {
						private final int mLabelIndex;
						private final int mIsCheckedIndex;

						{
							final Cursor cursor = getCursor();
							mLabelIndex = cursor.getColumnIndexOrThrow(mLabelColumn);
							mIsCheckedIndex = cursor.getColumnIndexOrThrow(mIsCheckedColumn);
						}

						@Override
						public void bindView(View view, Context context, Cursor cursor) {
							CheckedTextView text = (CheckedTextView) view.findViewById(android.R.id.text1);
							text.setText(cursor.getString(mLabelIndex));
							listView.setItemChecked(cursor.getPosition(), cursor.getInt(mIsCheckedIndex) == 1);
						}

						@Override
						public View newView(Context context, Cursor cursor, ViewGroup parent) {
							return mInflater.inflate(R.layout.dlg_select_multichoice, parent, false);
						}

					};
				}
			} else {
				int layout = mIsSingleChoice ? R.layout.dlg_select_singlechoice : R.layout.dlg_select_item;
				if (mCursor == null) {
					adapter = (mAdapter != null) ? mAdapter : new ArrayAdapter<CharSequence>(mContext, layout, android.R.id.text1, mItems);
				} else {
					adapter = new SimpleCursorAdapter(mContext, layout, mCursor, new String[] { mLabelColumn }, new int[] { android.R.id.text1 });
				}
			}

			if (mOnPrepareListViewListener != null) {
				mOnPrepareListViewListener.onPrepareListView(listView);
			}

			/*
			 * Don't directly set the adapter on the ListView as we might want to add a footer to the ListView later.
			 */
			dialog.mAdapter = adapter;
			dialog.mCheckedItem = mCheckedItem;

			if (mOnClickListener != null) {
				listView.setOnItemClickListener(new OnItemClickListener() {
					@SuppressWarnings("rawtypes")
					public void onItemClick(AdapterView parent, View v, int position, long id) {
						mOnClickListener.onClick(dialog.mDialogInterface, position);
						if (!mIsSingleChoice) {
							dialog.mDialogInterface.dismiss();
						}
					}
				});
			} else if (mOnCheckboxClickListener != null) {
				listView.setOnItemClickListener(new OnItemClickListener() {
					@SuppressWarnings("rawtypes")
					public void onItemClick(AdapterView parent, View v, int position, long id) {
						if (mCheckedItems != null) {
							mCheckedItems[position] = listView.isItemChecked(position);
						}
						mOnCheckboxClickListener.onClick(dialog.mDialogInterface, position, listView.isItemChecked(position));
					}
				});
			}

			// Attach a given OnItemSelectedListener to the ListView
			if (mOnItemSelectedListener != null) {
				listView.setOnItemSelectedListener(mOnItemSelectedListener);
			}

			if (mIsSingleChoice) {
				listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
			} else if (mIsMultiChoice) {
				listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
			}
			listView.mRecycleOnMeasure = mRecycleOnMeasure;
			dialog.mListView = listView;
		}
	}

}
