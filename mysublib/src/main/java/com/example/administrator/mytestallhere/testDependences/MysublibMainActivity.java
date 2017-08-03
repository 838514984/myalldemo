package com.example.administrator.mytestallhere.testDependences;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mysublib.R;
import com.example.mythirdlib.ThirdLibMainActivity;

public class MysublibMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mysublib_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                    startActivity(new Intent(MysublibMainActivity.this,ThirdLibMainActivity.class));
            }
        },2000);
    }
}
