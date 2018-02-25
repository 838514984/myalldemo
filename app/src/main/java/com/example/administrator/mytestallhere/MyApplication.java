package com.example.administrator.mytestallhere;

import android.app.Activity;
import android.app.Application;
import android.os.Process;
import android.widget.Toast;

import com.example.administrator.mytestallhere.util.Logger;
import com.squareup.leakcanary.LeakCanary;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * Created by Administrator on 2017/8/29 0029.
 */

public class MyApplication extends Application {
    public static Application INSTANCE;
    public static ArrayList<Activity> ACTIVITYS=new ArrayList<>();
    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        Logger.error("Thread name: "+Thread.currentThread().getName()+" ,pid: "+ Process.myPid());
        if (LeakCanary.isInAnalyzerProcess(this))
            return;
       // LeakCanary.install(this);

//        Observable.interval(3000, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {
//            @Override
//            public void accept(Long aLong) throws Exception {
//                Toast.makeText(MyApplication.this,"activity.size(): "+ACTIVITYS.size(),0).show();
//            }
//        });


    }
}
