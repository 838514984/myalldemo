package com.example.administrator.mytestallhere.aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.administrator.mytestallhere.R;
import com.example.administrator.mytestallhere.bean.User;
import com.example.administrator.mytestallhere.util.Logger;

import java.security.MessageDigestSpi;
import java.util.List;

public class MessengeActivity extends AppCompatActivity implements View.OnClickListener {
    Messenger serviceMessenge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);
        Intent intent=new Intent(this,MessengeService.class);
        bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE);
        findViewById(R.id.btn_query).setOnClickListener(this);
        findViewById(R.id.btn_add).setOnClickListener(this);
    }

    ServiceConnection serviceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            serviceMessenge=new Messenger(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add:
                Message message=Message.obtain();
                message.what=MessengeService.ADDUSER;
                message.obj=new User("wqh",23);
                try {
                    serviceMessenge.send(message);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_query:
                Message message1=Message.obtain();
                message1.what= MessengeService.QUERYUSERS;
                message1.replyTo=new Messenger(new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        switch (msg.what){
                            case MessengeService.REPLYRESULT:
                                Logger.error("all user: "/*msg.obj.toString()*/);
                                break;
                        }
                    }
                });
                try {
                    serviceMessenge.send(message1);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
