package com.example.administrator.mytestallhere.buildConfigTest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.administrator.mytestallhere.BaseActivity;
import com.example.administrator.mytestallhere.BuildConfig;
import com.example.administrator.mytestallhere.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class BuildConfigActivity extends BaseActivity{
    @BindView(R.id.tv_1)
    TextView tv_1;
    @BindView(R.id.tv_2)
    TextView tv_2;
    @BindView(R.id.tv_3)
    TextView tv_3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String s= BuildConfig.MyBuild;
        tv_1.setText(s);
        tv_2.setText(getResources().getString(R.string.buildTime));
        tv_3.setText(com.example.mylibrary.BuildConfig.BUILD_TYPE);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_build_config;
    }
}
