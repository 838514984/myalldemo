package com.example.administrator.mytestallhere;

import android.app.Activity;
import android.app.Application;
import android.os.Process;
import android.util.Log;

import com.example.administrator.mytestallhere.dagger.compent.ApplicationCompent;
import com.example.administrator.mytestallhere.dagger.compent.DaggerApplicationCompent;
import com.example.administrator.mytestallhere.dagger.module.ApplicationModule;
import com.example.administrator.mytestallhere.dataProvider.ExpendPlaceViewHolderDataProvider;
import com.example.util.Logger;
import com.example.swipeback.BGASwipeBackHelper;
import com.mob.MobSDK;
import com.tencent.smtt.sdk.QbSdk;


import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/8/29 0029.
 */

public class MyApplication extends Application {
    public static MyApplication INSTANCE;
    public static ArrayList<Activity> ACTIVITYS = new ArrayList<>();
    private  ApplicationCompent compent;

    @Inject
    ExpendPlaceViewHolderDataProvider expendPlaceViewHolderDataProvider;
    @Override
    public void onCreate() {
        super.onCreate();
        //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。

        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {
                // TODO Auto-generated method stub
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                Log.d("app", " onViewInitFinished is " + arg0);
            }

            @Override
            public void onCoreInitFinished() {
                // TODO Auto-generated method stub
            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(),  cb);
        INSTANCE = this;
        compent = DaggerApplicationCompent.builder().applicationModule(new ApplicationModule(this)).build();
        compent.inject(this);
        Logger.error("Thread name: " + Thread.currentThread().getName() + " ,pid: " + Process.myPid());
        BGASwipeBackHelper.init(this,null);





//        if (LeakCanary.isInAnalyzerProcess(this))
//            return;
        // LeakCanary.install(this);

//        Observable.interval(3000, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {
//            @Override
//            public void accept(Long aLong) throws Exception {
//                Toast.makeText(MyApplication.this,"activity.size(): "+ACTIVITYS.size(),0).show();
//            }
//        });
    }

    public ApplicationCompent getApplicationCompent(){
        return compent;
    }
}
