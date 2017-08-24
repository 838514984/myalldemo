package com.example.administrator.mytestallhere;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.example.administrator.mytestallhere.statusutil.StatusBarUtil;

/**
 * Created by Administrator on 2017/8/24 0024.
 */

public class BaseActivity extends FragmentActivity {
    View rootView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
        StatusBarUtil.immersive(this);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        rootView=((ViewGroup)getWindow().getDecorView().findViewById(android.R.id.content)).getChildAt(0);
        rootView.setFitsSystemWindows(true);
    }
}
