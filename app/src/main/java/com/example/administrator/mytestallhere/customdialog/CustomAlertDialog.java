package com.example.administrator.mytestallhere.customdialog;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;

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

    public static class Builder extends AlertDialog.Builder {
        Context context;
        CustomAlertDialog customAlertDialog;
        public Builder(Context context) {
            super(context);
            this.context=context;
            customAlertDialog=new CustomAlertDialog(context);
        }
        public CustomAlertDialog createDialog() {
            return customAlertDialog;
        }
        public Builder setTitle(String s){
            customAlertDialog.setTitle(s);
            return this;
        }
    }
}
