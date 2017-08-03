package com.example.administrator.mytestallhere.testDependences;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.administrator.mytestallhere.R;
import com.example.mylibrary.statusutil.StatusBarUtil;
import com.example.mysublib.SubLibTest;

public class DependencesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dependences);
        StatusBarUtil.immersive(this);
        SubLibTest.Logger("invoke the subLibTest's class method");
        Toast.makeText(this,"invoke the subLibTest's class method  as a aar library into mylibrary success",0).show();

    }
}
