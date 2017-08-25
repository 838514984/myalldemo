package com.example.administrator.mytestallhere.TransitionsTest.activityTransitionTest;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Slide;

import com.example.administrator.mytestallhere.BaseActivity;
import com.example.administrator.mytestallhere.R;

public class ActivityBBB extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bbb;
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.enteranim,R.anim.exitanim);
    }
}
