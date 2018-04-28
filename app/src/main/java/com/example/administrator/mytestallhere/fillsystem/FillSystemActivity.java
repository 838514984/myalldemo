package com.example.administrator.mytestallhere.fillsystem;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.mytestallhere.BaseActivity;
import com.example.administrator.mytestallhere.R;
import com.example.statusbar.StatusBarUtil;

public class FillSystemActivity extends BaseActivity {

    public static void startActivity(Context context){
        Intent intent = new Intent(context,FillSystemActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_system);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fill_system;
    }

    @Override
    protected void setStatusBarAction() {
        StatusBarUtil.setTranslucentForImageView(this,null);
    }

    @Override
    protected boolean needUseSwipeBackLayout() {
        return false;
    }
}
