package com.example.administrator.mytestallhere.customdialog;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.administrator.mytestallhere.BaseActivity;
import com.example.administrator.mytestallhere.R;

import butterknife.BindView;
import butterknife.OnClick;

public class CustomDialogActivity extends BaseActivity {
    @BindView(R.id.btn)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_custom_dialog;
    }

    @OnClick(R.id.btn)
    public void onclick(){
       new SysAlertDialog.Builder(this).setMessage("message").setPositiveButton("positive", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {
               dialog.dismiss();
           }
       }).setNegativeButton("negative", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {
               dialog.dismiss();
           }
       }).create().show();
    }
}
