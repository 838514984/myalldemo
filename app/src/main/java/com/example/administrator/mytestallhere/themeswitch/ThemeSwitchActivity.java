package com.example.administrator.mytestallhere.themeswitch;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;

import com.example.administrator.mytestallhere.Constant;
import com.example.administrator.mytestallhere.R;

public class ThemeSwitchActivity extends AppCompatActivity implements View.OnClickListener {
    private SharedPreferences mSp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_switch);
        findViewById(R.id.btn_switch_theme).setOnClickListener(this);
        mSp = getSharedPreferences(Constant.SPNAME,0);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_switch_theme){
            boolean isNight = mSp.getBoolean(Constant.ISNIGHT,false);
            if (isNight){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
            mSp.edit().putBoolean(Constant.ISNIGHT,!isNight).commit();
            recreate();
        }
    }
}
