package com.example.administrator.mytestallhere.testDependences;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.mytestallhere.R;
import com.example.mylibrary.statusutil.StatusBarUtil;

public class DependencesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dependences);
        StatusBarUtil.immersive(this);
    }
}
