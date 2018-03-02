package com.example.administrator.mytestallhere.window.windowManager;

import android.app.Activity;
import android.app.ActivityThread;
import android.app.Dialog;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.PixelFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewRootImpl;
import android.view.WindowManager;
import android.view.WindowManagerImpl;
import android.widget.Button;
import android.widget.Toast;

import com.android.internal.policy.PhoneWindow;
import com.example.administrator.mytestallhere.MyApplication;
import com.example.administrator.mytestallhere.R;

public class AddWindowActivity extends Activity {
    WindowManager mWindowManager;
    WindowManager.LayoutParams mLayoutParams;
    Button mButton;
    int mLastX;
    int mLastY;

    private void blank() {
        WindowManagerImpl windowManager = null;
        ViewRootImpl viewRootImpl = null;
        PhoneWindow phoneWindow = null;
        Dialog dialog = null;
        ActivityThread activityThread = null;
        activityThread.getApplication();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_window);
        PhoneWindow phoneWindow = new PhoneWindow(this);
        phoneWindow.setContentView(R.layout.activity_circle_view);
        addContentView(phoneWindow.getDecorView(), new ViewGroup.LayoutParams(200, 200));
        mButton = new Button(this);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddWindowActivity.this, "xixixi", 0).show();
            }
        });
        mButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int x = (int) event.getRawX();
                int y = (int) event.getRawY();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        mLayoutParams.x = mLayoutParams.x + (int) (event.getRawX() - mLastX);
                        mLayoutParams.y = mLayoutParams.y + (int) (event.getRawY() - mLastY);
                        mWindowManager.updateViewLayout(mButton, mLayoutParams);
                        break;
                }
                mLastX = x;
                mLastY = y;
                //if return true , onclicklistener will be invalid
                return true;
            }
        });
        mButton.setText("button");
        mWindowManager = (WindowManager) MyApplication.INSTANCE.getSystemService(Context.WINDOW_SERVICE);
        mLayoutParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT
                , WindowManager.LayoutParams.WRAP_CONTENT
                , WindowManager.LayoutParams.TYPE_SYSTEM_ERROR      //type 可以显示在系统锁屏上
                , WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL   //flag 系统会将当前Window区域意外的单机事件传递给底层的Window
                | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED  //可以让Window显示在锁屏界面上
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE     //事件会直接传递给下层具有焦点的Window
                , PixelFormat.TRANSPARENT);
        mWindowManager.addView(mButton, mLayoutParams);
    }
}
