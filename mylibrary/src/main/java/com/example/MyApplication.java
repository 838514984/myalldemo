package com.example;

import android.app.Application;

import com.example.util.Logger;
import com.mob.MobSDK;

/**
 * Created by Administrator on 2018/4/28 0028.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //貌似不会调用了
        Logger.error("myLibrary Application-------------");
        MobSDK.init(this);
    }
}
