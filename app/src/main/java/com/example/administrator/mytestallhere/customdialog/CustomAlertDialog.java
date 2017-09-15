package com.example.administrator.mytestallhere.customdialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v7.app.AlertDialog;

import com.example.administrator.mytestallhere.R;

/**
 * Created by Administrator on 2017/9/15 0015.
 */

public class CustomAlertDialog extends AlertDialog {
    protected CustomAlertDialog(@NonNull Context context) {
        super(context, R.style.Herily_Theme_Dialog_Alert);
    }

    protected CustomAlertDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    protected CustomAlertDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }
}
