package com.example.administrator.mytestallhere.testDependences;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.mytestallhere.R;
import com.example.mylibrary.statusutil.StatusBarUtil;
import com.example.mysublib.SubLibTest;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DependencesActivity extends AppCompatActivity {
    @BindView(R.id.btn_main)
    Button mMainlibBtn;
    @BindView(R.id.btn_sub)
    Button mSublibBtn;
    @BindView(R.id.btn_third)
    Button mThirdBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dependences);
        StatusBarUtil.immersive(this);
        SubLibTest.Logger("invoke the subLibTest's class method");
        Toast.makeText(this, "invoke the subLibTest's class method  as a aar library into mylibrary success", 0).show();
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_main)
    public void jumptomainlibAct(){
        startActivity(new Intent(this,MylibraryMainActivity.class));
    }
    @OnClick(R.id.btn_sub)
    public void jumptosublibAct(){
        startActivity(new Intent(this,MysublibMainActivity.class));
    }
    @OnClick(R.id.btn_third)
    public void jumptothirdAct(){

    }
}
