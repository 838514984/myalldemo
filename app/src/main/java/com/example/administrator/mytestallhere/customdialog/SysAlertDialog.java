package com.example.administrator.mytestallhere.customdialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.administrator.mytestallhere.R;


/**
 * 自定义alertdialog
 * 
 * @author Administrator
 *
 */
public class SysAlertDialog extends AlertDialog implements DialogInterface {
	private SysAlertController mAlert;

	protected SysAlertDialog(Context context) {
		this(context, R.style.Herily_Theme_Dialog_Alert);
	}

	protected SysAlertDialog(Context context, int theme) {
		super(context, theme);
		mAlert = new SysAlertController(context, this, getWindow());
	}

	protected SysAlertDialog(Context context, boolean cancelable,
                             OnCancelListener cancelListener) {
		super(context, R.style.Herily_Theme_Dialog_Alert);
		setCancelable(cancelable);
		setOnCancelListener(cancelListener);
		mAlert = new SysAlertController(context, this, getWindow());
	}

	public Button getButton(int whichButton) {
		return mAlert.getButton(whichButton);
	}

	/**
	 * Gets the list view used in the dialog.
	 * 
	 * @return The {@link ListView} from the dialog.
	 */
	public ListView getListView() {
		return mAlert.getListView();
	}

	@Override
	public void setTitle(CharSequence title) {
		super.setTitle(title);
		mAlert.setTitle(title);
	}

	/**
	 * @see Builder#setCustomTitle(View)
	 */
	public void setCustomTitle(View customTitleView) {
		mAlert.setCustomTitle(customTitleView);
	}

	public void setMessage(CharSequence message) {
		mAlert.setMessage(message);
	}

	/**
	 * Set the view to display in that dialog.
	 */
	public void setView(View view) {
		mAlert.setView(view);
	}

	/**
	 * Set the view to display in that dialog, specifying the spacing to appear
	 * around that view.
	 * 
	 * @param view
	 *            The view to show in the content area of the dialog
	 * @param viewSpacingLeft
	 *            Extra space to appear to the left of {@code view}
	 * @param viewSpacingTop
	 *            Extra space to appear above {@code view}
	 * @param viewSpacingRight
	 *            Extra space to appear to the right of {@code view}
	 * @param viewSpacingBottom
	 *            Extra space to appear below {@code view}
	 */
	public void setView(View view, int viewSpacingLeft, int viewSpacingTop,
			int viewSpacingRight, int viewSpacingBottom) {
		mAlert.setView(view, viewSpacingLeft, viewSpacingTop, viewSpacingRight,
				viewSpacingBottom);
	}

	/**
	 * Set a message to be sent when a button is pressed.
	 * 
	 * @param whichButton
	 *            Which button to set the message for, can be one of
	 *            {@link DialogInterface#BUTTON_POSITIVE},
	 *            {@link DialogInterface#BUTTON_NEGATIVE}, or
	 *            {@link DialogInterface#BUTTON_NEUTRAL}
	 * @param text
	 *            The text to display in positive button.
	 * @param msg
	 *            The {@link Message} to be sent when clicked.
	 */
	public void setButton(int whichButton, CharSequence text, Message msg) {
		mAlert.setButton(whichButton, text, null, msg);
	}

	/**
	 * Set a listener to be invoked when the positive button of the dialog is
	 * pressed.
	 * 
	 * @param whichButton
	 *            Which button to set the listener on, can be one of
	 *            {@link DialogInterface#BUTTON_POSITIVE},
	 *            {@link DialogInterface#BUTTON_NEGATIVE}, or
	 *            {@link DialogInterface#BUTTON_NEUTRAL}
	 * @param text
	 *            The text to display in positive button.
	 * @param listener
	 *            The {@link OnClickListener} to use.
	 */
	public void setButton(int whichButton, CharSequence text,
			OnClickListener listener) {
		mAlert.setButton(whichButton, text, listener, null);
	}

	/**
	 * @deprecated Use {@link #setButton(int, CharSequence, Message)} with
	 *             {@link DialogInterface#BUTTON_POSITIVE}.
	 */
	@Deprecated
	public void setButton(CharSequence text, Message msg) {
		setButton(BUTTON_POSITIVE, text, msg);
	}

	/**
	 * @deprecated Use {@link #setButton(int, CharSequence, Message)} with
	 *             {@link DialogInterface#BUTTON_NEGATIVE}.
	 */
	@Deprecated
	public void setButton2(CharSequence text, Message msg) {
		setButton(BUTTON_NEGATIVE, text, msg);
	}

	/**
	 * @deprecated Use {@link #setButton(int, CharSequence, Message)} with
	 *             {@link DialogInterface#BUTTON_NEUTRAL}.
	 */
	@Deprecated
	public void setButton3(CharSequence text, Message msg) {
		setButton(BUTTON_NEUTRAL, text, msg);
	}

	/**
	 * Set a listener to be invoked when button 1 of the dialog is pressed.
	 *
	 * @param text
	 *            The text to display in button 1.
	 * @param listener
	 *            The {@link OnClickListener} to use.
	 * @deprecated Use
	 *             {@link #setButton(int, CharSequence, OnClickListener)}
	 *             with {@link DialogInterface#BUTTON_POSITIVE}
	 */
	@Deprecated
	public void setButton(CharSequence text, final OnClickListener listener) {
		setButton(BUTTON_POSITIVE, text, listener);
	}

	/**
	 * Set a listener to be invoked when button 2 of the dialog is pressed.
	 *
	 * @param text
	 *            The text to display in button 2.
	 * @param listener
	 *            The {@link OnClickListener} to use.
	 * @deprecated Use
	 *             {@link #setButton(int, CharSequence, OnClickListener)}
	 *             with {@link DialogInterface#BUTTON_NEGATIVE}
	 */
	@Deprecated
	public void setButton2(CharSequence text, final OnClickListener listener) {
		setButton(BUTTON_NEGATIVE, text, listener);
	}

	/**
	 * Set a listener to be invoked when button 3 of the dialog is pressed.
	 *
	 * @param text
	 *            The text to display in button 3.
	 * @param listener
	 *            The {@link OnClickListener} to use.
	 * @deprecated Use
	 *             {@link #setButton(int, CharSequence, OnClickListener)}
	 *             with {@link DialogInterface#BUTTON_POSITIVE}
	 */
	@Deprecated
	public void setButton3(CharSequence text, final OnClickListener listener) {
		setButton(BUTTON_NEUTRAL, text, listener);
	}

	/**
	 * Set resId to 0 if you don't want an icon.
	 *
	 * @param resId
	 *            the resourceId of the drawable to use as the icon or 0 if you
	 *            don't want an icon.
	 */
	public void setIcon(int resId) {
		mAlert.setIcon(resId);
	}

	public void setIcon(Drawable icon) {
		mAlert.setIcon(icon);
	}

	public void setInverseBackgroundForced(boolean forceInverseBackground) {
		mAlert.setInverseBackgroundForced(forceInverseBackground);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		mAlert.installContent();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (mAlert.onKeyDown(keyCode, event))
			return true;
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		if (mAlert.onKeyUp(keyCode, event))
			return true;
		return super.onKeyUp(keyCode, event);
	}

	public static class Builder extends AlertDialog.Builder {
		private final SysAlertController.AlertParams P;

		public Builder(Context context) {
			super(context);
			P = new SysAlertController.AlertParams(context);
		}
		public Builder(Context context, int themeResId) {
			super(context,themeResId);
			P = new SysAlertController.AlertParams(context);
		}

		public Builder setTitle(int titleId) {
			P.mTitle = P.mContext.getText(titleId);
			return this;
		}

		public Builder setTitle(CharSequence title) {
			P.mTitle = title;
			return this;
		}

		public Builder setCustomTitle(View customTitleView) {// 自定义title
																// 优先级高于默认title
			P.mCustomTitleView = customTitleView;
			return this;
		}

		public Builder setMessage(int messageId) {
			P.mMessage = P.mContext.getText(messageId);
			return this;
		}

		public Builder setMessage(CharSequence message) {
			P.mMessage = message;
			return this;
		}

		public Builder setIcon(int iconId) {
			P.mIconId = iconId;
			return this;
		}

		public Builder setIcon(Drawable icon) {
			P.mIcon = icon;
			return this;
		}

		public Builder setPositiveButton(int textId,
				final OnClickListener listener) {
			P.mPositiveButtonText = P.mContext.getText(textId);
			P.mPositiveButtonListener = listener;
			return this;
		}

		public Builder setPositiveButton(CharSequence text,
				final OnClickListener listener) {
			P.mPositiveButtonText = text;
			P.mPositiveButtonListener = listener;
			return this;
		}

		public Builder setNegativeButton(int textId,
				final OnClickListener listener) {
			P.mNegativeButtonText = P.mContext.getText(textId);
			P.mNegativeButtonListener = listener;
			return this;
		}

		public Builder setNegativeButton(CharSequence text,
				final OnClickListener listener) {
			P.mNegativeButtonText = text;
			P.mNegativeButtonListener = listener;
			return this;
		}

		public Builder setNeutralButton(int textId,
				final OnClickListener listener) {
			P.mNeutralButtonText = P.mContext.getText(textId);
			P.mNeutralButtonListener = listener;
			return this;
		}

		public Builder setNeutralButton(CharSequence text,
				final OnClickListener listener) {
			P.mNeutralButtonText = text;
			P.mNeutralButtonListener = listener;
			return this;
		}

		public Builder setCancelable(boolean cancelable) {
			P.mCancelable = cancelable;
			return this;
		}

		public Builder setOnCancelListener(OnCancelListener onCancelListener) {
			P.mOnCancelListener = onCancelListener;
			return this;
		}

		public Builder setOnKeyListener(OnKeyListener onKeyListener) {
			P.mOnKeyListener = onKeyListener;
			return this;
		}

		public Builder setItems(int itemsId, final OnClickListener listener) {
			P.mItems = P.mContext.getResources().getTextArray(itemsId);
			P.mOnClickListener = listener;
			return this;
		}

		public Builder setItems(CharSequence[] items,
				final OnClickListener listener) {
			P.mItems = items;
			P.mOnClickListener = listener;
			return this;
		}

		public Builder setAdapter(final ListAdapter adapter,
				final OnClickListener listener) {
			P.mAdapter = adapter;
			P.mOnClickListener = listener;
			return this;
		}

		public Builder setCursor(final Cursor cursor,
				final OnClickListener listener, String labelColumn) {
			P.mCursor = cursor;
			P.mLabelColumn = labelColumn;
			P.mOnClickListener = listener;
			return this;
		}

		public Builder setMultiChoiceItems(int itemsId, boolean[] checkedItems,
				final OnMultiChoiceClickListener listener) {
			P.mItems = P.mContext.getResources().getTextArray(itemsId);
			P.mOnCheckboxClickListener = listener;
			P.mCheckedItems = checkedItems;
			P.mIsMultiChoice = true;
			return this;
		}

		public Builder setMultiChoiceItems(CharSequence[] items,
				boolean[] checkedItems,
				final OnMultiChoiceClickListener listener) {
			P.mItems = items;
			P.mOnCheckboxClickListener = listener;
			P.mCheckedItems = checkedItems;
			P.mIsMultiChoice = true;
			return this;
		}

		public Builder setMultiChoiceItems(Cursor cursor,
				String isCheckedColumn, String labelColumn,
				final OnMultiChoiceClickListener listener) {
			P.mCursor = cursor;
			P.mOnCheckboxClickListener = listener;
			P.mIsCheckedColumn = isCheckedColumn;
			P.mLabelColumn = labelColumn;
			P.mIsMultiChoice = true;
			return this;
		}

		public Builder setSingleChoiceItems(int itemsId, int checkedItem,
				final OnClickListener listener) {
			P.mItems = P.mContext.getResources().getTextArray(itemsId);
			P.mOnClickListener = listener;
			P.mCheckedItem = checkedItem;
			P.mIsSingleChoice = true;
			return this;
		}

		public Builder setSingleChoiceItems(Cursor cursor, int checkedItem,
				String labelColumn, final OnClickListener listener) {
			P.mCursor = cursor;
			P.mOnClickListener = listener;
			P.mCheckedItem = checkedItem;
			P.mLabelColumn = labelColumn;
			P.mIsSingleChoice = true;
			return this;
		}

		public Builder setSingleChoiceItems(CharSequence[] items,
				int checkedItem, final OnClickListener listener) {
			P.mItems = items;
			P.mOnClickListener = listener;
			P.mCheckedItem = checkedItem;
			P.mIsSingleChoice = true;
			return this;
		}

		public Builder setSingleChoiceItems(ListAdapter adapter,
				int checkedItem, final OnClickListener listener) {
			P.mAdapter = adapter;
			P.mOnClickListener = listener;
			P.mCheckedItem = checkedItem;
			P.mIsSingleChoice = true;
			return this;
		}

		public Builder setOnItemSelectedListener(
				final AdapterView.OnItemSelectedListener listener) {
			P.mOnItemSelectedListener = listener;
			return this;
		}

		public Builder setView(View view) {
			P.mView = view;
			P.mViewSpacingSpecified = false;
			return this;
		}

		public Builder setView(View view, int viewSpacingLeft,
				int viewSpacingTop, int viewSpacingRight, int viewSpacingBottom) {
			P.mView = view;
			P.mViewSpacingSpecified = true;
			P.mViewSpacingLeft = viewSpacingLeft;
			P.mViewSpacingTop = viewSpacingTop;
			P.mViewSpacingRight = viewSpacingRight;
			P.mViewSpacingBottom = viewSpacingBottom;
			return this;
		}

		public Builder setInverseBackgroundForced(boolean useInverseBackground) {
			P.mForceInverseBackground = useInverseBackground;
			return this;
		}

		public Builder setRecycleOnMeasureEnabled(boolean enabled) {
			P.mRecycleOnMeasure = enabled;
			return this;
		}

		public SysAlertDialog create() {
			final SysAlertDialog dialog = new SysAlertDialog(P.mContext);
			// final GoldtelAlertDialog dialog = new
			// GoldtelAlertDialog(P.mContext, P.mCancelable,
			// P.mOnCancelListener);
			P.apply(dialog.mAlert);
			// dialog.setCanceledOnTouchOutside(P.mCancelable);
			dialog.setCancelable(P.mCancelable);
			dialog.setOnCancelListener(P.mOnCancelListener);
			if (P.mOnKeyListener != null) {
				dialog.setOnKeyListener(P.mOnKeyListener);
			}
			return dialog;
		}

		public AlertDialog show() {// 次方法可以直接使用父类的
			/*
			 * AlertDialog创建过程：
			 * 1、先创建Builder实例b、设置各种资源到参数HuzAlertController.AlertParams(ap)中；
			 * 2、b调用show()方法,其中会new一个AlertDialog实例ad,并调用ap的apply()
			 * 方法将ad的AlertController参数ac传递到ap中 3、在ap的apply()方法中将各种资源再设置到ac中，
			 * 4、调用ad的show()方法【onCreate】来调用ac的installContent()方法来完成具体的对话框工作
			 */
			AlertDialog dialog = create();
			dialog.show();// 此时才会调用onCreate()方法执行AlertController.installContent()
			return dialog;
		}
	}

}
