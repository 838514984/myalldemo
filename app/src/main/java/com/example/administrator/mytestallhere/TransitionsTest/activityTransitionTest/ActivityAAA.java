package com.example.administrator.mytestallhere.TransitionsTest.activityTransitionTest;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v17.leanback.transition.FadeAndShortSlide;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.administrator.mytestallhere.BaseActivity;
import com.example.administrator.mytestallhere.R;

import butterknife.BindView;
import butterknife.OnClick;

public class ActivityAAA extends BaseActivity {
    @BindView(R.id.img)
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aa);
    }
    @OnClick(R.id.img)
    public void click(){
        startActivity(new Intent(this,ActivityBBB.class));
        overridePendingTransition(R.anim.enteranim,R.anim.exitanim);
    }
}
