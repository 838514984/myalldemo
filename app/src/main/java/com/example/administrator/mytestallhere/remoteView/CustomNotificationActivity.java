package com.example.administrator.mytestallhere.remoteView;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RemoteViews;

import com.example.administrator.mytestallhere.BaseActivity;
import com.example.administrator.mytestallhere.R;

public class CustomNotificationActivity extends BaseActivity implements View.OnClickListener {
    int mSystem;
    int mCustom = 10000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findViewById(R.id.btn_system_notify).setOnClickListener(this);
        findViewById(R.id.btn_custom_notify).setOnClickListener(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_custom_notification;
    }

    private void createSystemNotification() {
        Notification notification;
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentText("this is contentText");
        builder.setAutoCancel(true);
        builder.setContentTitle("this is content title");
        builder.setSubText("this is sub text");
        builder.setTicker("this is ticker");
        builder.setWhen(System.currentTimeMillis());
        builder.setSmallIcon(R.drawable.ic_launcher);
        builder.setContentIntent(PendingIntent.getActivity(this, 0, new Intent(this, CustomNotificationActivity.class), PendingIntent.FLAG_UPDATE_CURRENT));
        notification = builder.build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(mSystem++, notification);

    }


    private void createCustomNotification() {
        Notification notification;
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentText("this is contentText");
        builder.setAutoCancel(true);
        builder.setContentTitle("this is content title");
        builder.setSubText("this is sub text");
        builder.setTicker("this is ticker");
        builder.setWhen(System.currentTimeMillis());
        builder.setSmallIcon(R.drawable.ic_launcher);
        builder.setContentIntent(PendingIntent.getActivity(this, 0, new Intent(this, CustomNotificationActivity.class), PendingIntent.FLAG_UPDATE_CURRENT));
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.layout_notify_custom);
        remoteViews.setTextViewText(R.id.tv_title, "这是title");
        remoteViews.setTextViewText(R.id.tv_sub_title, "这是sub title");
        remoteViews.setImageViewResource(R.id.iv_icon, R.drawable.ic_launcher);
        builder.setContent(remoteViews);
        //builder.setCustomBigContentView(remoteViews);
        notification = builder.build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(mCustom++, notification);
        remoteViews.apply(this, (ViewGroup)((ViewGroup)(getWindow().getDecorView().findViewById(android.R.id.content))).getChildAt(0));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_system_notify:
                createSystemNotification();
                break;
            case R.id.btn_custom_notify:
                createCustomNotification();
                break;
        }
    }
}
