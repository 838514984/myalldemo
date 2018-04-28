package com.example.administrator.mytestallhere.aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.administrator.mytestallhere.R;
import com.example.administrator.mytestallhere.UserManager;
import com.example.administrator.mytestallhere.bean.User;
import com.example.util.Logger;

import java.util.List;

public class AIDLActivity extends AppCompatActivity implements View.OnClickListener {

    List<User> users;
    UserManager userManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);
        Intent intent=new Intent(this,AidlService.class);
        bindService(intent,connection, Context.BIND_AUTO_CREATE);
        findViewById(R.id.btn_add).setOnClickListener(this);
        findViewById(R.id.btn_query).setOnClickListener(this);
    }

    ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            userManager= (UserManager) UserManager.Stub.asInterface(service);
            Logger.error("connected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Logger.error("disconnected");
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add:
                try {
                    userManager.addUser(new User("2332",24));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_query:
                try {
                    users= userManager.getUsers();
                    Logger.error("client: all user: "+users.toString());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
