package com.example.administrator.mytestallhere.multiApksBuildTest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.administrator.mytestallhere.BaseActivity;
import com.example.administrator.mytestallhere.BuildConfig;
import com.example.administrator.mytestallhere.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MuiltyApksbuildTestActivity extends BaseActivity {
    @BindView(R.id.tv)
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muilty_apksbuild_test);
        ButterKnife.bind(this);
        tv.setText(Constant.msg);
    }
}
