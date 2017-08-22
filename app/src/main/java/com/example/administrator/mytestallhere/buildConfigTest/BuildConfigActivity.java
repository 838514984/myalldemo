package com.example.administrator.mytestallhere.buildConfigTest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.administrator.mytestallhere.BuildConfig;
import com.example.administrator.mytestallhere.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class BuildConfigActivity extends AppCompatActivity {
    @BindView(R.id.tv_1)
    TextView tv_1;
    @BindView(R.id.tv_2)
    TextView tv_2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_config);
        ButterKnife.bind(this);
        String s= BuildConfig.MyBuild;
        tv_1.setText(s);
        tv_2.setText(getResources().getString(R.string.buildTime));
    }
}
