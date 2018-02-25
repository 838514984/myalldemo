package com.example.administrator.mytestallhere.alarmwhenlocked;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.administrator.mytestallhere.BaseActivity;
import com.example.administrator.mytestallhere.MyApplication;
import com.example.administrator.mytestallhere.R;

import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public class PrepareActivity extends BaseActivity {
    WindowManager mWindowManager;
    int mScreenWidth;
    int mScreenHeight;
    VoiceHelper voiceHelper=new VoiceHelper();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //startService(new Intent(this, LockService.class));

        Observable.timer(3000,TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                //init();
                wakeUpAndUnlock();
                //voiceHelper.startAlarm();
            }
        });

    }


    WindowManager.LayoutParams mWmParams;

    private void init() {
//        TextView textView = new TextView(this);
//        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//        textView.setBackgroundColor(Color.WHITE);
//        textView.setText("HELLO WORDS   ");
//        textView.setTextColor(Color.RED);
        final View view=getLayoutInflater().inflate(R.layout.ngr_video_activity_incoming_call,null);
        view.findViewById(R.id.acceptcall).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //PrepareActivity.this.startActivity(new Intent(PrepareActivity.this,LockActivity.class));
                mWindowManager.removeView(view);
                voiceHelper.stopAlarm();
            }
        });
        mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        // 更新浮动窗口位置参数 靠边
        DisplayMetrics dm = new DisplayMetrics();
        // 获取屏幕信息
        mWindowManager.getDefaultDisplay().getMetrics(dm);
        mScreenWidth = dm.widthPixels;
        mScreenHeight = dm.heightPixels;
        this.mWmParams = new WindowManager.LayoutParams();
        // 设置window type
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mWmParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
        } else {
            mWmParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
        }
        // 设置图片格式，效果为背景透明
        mWmParams.format = PixelFormat.RGBA_8888;
        // 设置浮动窗口不可聚焦（实现操作除浮动窗口外的其他可见窗口的操作）
        //mWmParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        // 调整悬浮窗显示的停靠位置为左侧置�?
        mWmParams.gravity = Gravity.NO_GRAVITY;

        mScreenHeight = mWindowManager.getDefaultDisplay().getHeight();

        // 以屏幕左上角为原点，设置x、y初始值，相对于gravity
        mWmParams.x = 0;
        //mWmParams.y = mScreenHeight / 2;
        mWmParams.y =0;
        // 设置悬浮窗口长宽数据
        mWmParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        mWmParams.height = WindowManager.LayoutParams.MATCH_PARENT;

        mWindowManager.addView(view, mWmParams);

    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_prepare;
    }


    public  void wakeUpAndUnlock() {
        // 获取电源管理器对象
        PowerManager pm = (PowerManager)getSystemService(Context.POWER_SERVICE);
        boolean screenOn = pm.isScreenOn();
        if (!screenOn) {
            // 获取PowerManager.WakeLock对象,后面的参数|表示同时传入两个值,最后的是LogCat里用的Tag
            PowerManager.WakeLock wl = pm.newWakeLock(
                    PowerManager.ACQUIRE_CAUSES_WAKEUP |
                            PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "bright");
            wl.acquire(10000); // 点亮屏幕
            wl.release(); // 释放

            init();
            voiceHelper.startAlarm();

        }
//        // 屏幕解锁
//        KeyguardManager keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);
//        KeyguardManager.KeyguardLock keyguardLock = keyguardManager.newKeyguardLock("unLock");
//        // 屏幕锁定
//        keyguardLock.reenableKeyguard();
//        keyguardLock.disableKeyguard(); // 解锁
    }


}
