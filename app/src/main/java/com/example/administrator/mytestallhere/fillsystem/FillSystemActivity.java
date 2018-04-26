package com.example.administrator.mytestallhere.fillsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.mytestallhere.BaseActivity;
import com.example.administrator.mytestallhere.R;

public class FillSystemActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_system);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fill_system;
    }
}
