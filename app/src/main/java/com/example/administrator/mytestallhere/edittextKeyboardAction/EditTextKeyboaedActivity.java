package com.example.administrator.mytestallhere.edittextKeyboardAction;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.mytestallhere.BaseActivity;
import com.example.administrator.mytestallhere.R;

import butterknife.BindView;

public class EditTextKeyboaedActivity extends BaseActivity {
    @BindView(R.id.ed)
    EditText mEditText;
    @BindView(R.id.tv_left)
    TextView tvLeft;
    @BindView(R.id.tv_center)
    TextView tvCenter;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_left_1)
    TextView tvLeft1;
    @BindView(R.id.tv_center_1)
    TextView tvCenter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mEditText.setImeOptions(EditorInfo.IME_ACTION_GO);

        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s != null && !s.equals("")) {
                    //mEditText.setImeOptions(EditorInfo.IME_ACTION_GO);
                } else {
                   // mEditText.setImeOptions(EditorInfo.IME_ACTION_UNSPECIFIED);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        int windowsWidth=getWindowManager().getDefaultDisplay().getWidth();
        int leftLength=tvLeft.getMeasuredWidth();
        int centerLength=tvCenter.getMeasuredWidth();
        int rightLength=tvRight.getMeasuredWidth();

        int left1Length=tvLeft1.getMeasuredWidth();
        int center1Length=tvCenter1.getMeasuredWidth();
        Log.e("xxx","windowsWidth: "+windowsWidth);
        Log.e("xxx","left1 Length: "+left1Length);
        Log.e("xxx","center1 Length: "+center1Length);


        Log.e("xxx","leftLength: "+leftLength);
        Log.e("xxx","centerLength: "+centerLength);
        Log.e("xxx","rightLength: "+rightLength);


        if (left1Length+center1Length+rightLength>windowsWidth){
            tvLeft.setWidth(windowsWidth-rightLength-center1Length);
        }


    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_text_keyboaed;
    }
}
