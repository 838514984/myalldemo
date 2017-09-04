package com.example.administrator.mytestallhere.myJecenterDependence;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.mytestallhere.BaseActivity;
import com.example.administrator.mytestallhere.R;
import com.example.secretbaselibrary.JecenterMainActivityMainActivity;

import butterknife.OnClick;

public class MyJecenterDependence extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_jecenter_dependence;
    }

    @OnClick(R.id.btn)
    public void startActivity(){
        startActivity(new Intent(this, JecenterMainActivityMainActivity.class));
    }
}
