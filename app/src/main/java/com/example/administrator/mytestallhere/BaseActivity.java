package com.example.administrator.mytestallhere;

import android.Manifest;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import com.example.administrator.mytestallhere.statusutil.StatusBarUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;

/**
 * Created by Administrator on 2017/8/24 0024.
 */

public abstract class BaseActivity<T> extends AppCompatActivity {
    T mPresent;
    View rootView;
    @Nullable
    @BindView(R.id.immersiveView)
    View immersiveView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.ACTIVITYS.add(this);
        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
        StatusBarUtil.immersive(this);
        setContentView(getLayoutId());
        initPresent();
        ButterKnife.bind(this);
        if (immersiveView != null)
            StatusBarUtil.setPaddingSmart(this, immersiveView);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        //rootView=((ViewGroup)getWindow().getDecorView().findViewById(android.R.id.content)).getChildAt(0);
        //rootView.setFitsSystemWindows(true);

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    protected  abstract int getLayoutId();

    protected  T initPresent(){
        return null;
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApplication.ACTIVITYS.remove(this);
    }
}
