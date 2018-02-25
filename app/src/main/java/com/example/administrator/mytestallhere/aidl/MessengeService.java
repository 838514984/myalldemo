package com.example.administrator.mytestallhere.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import com.example.administrator.mytestallhere.bean.User;
import com.example.administrator.mytestallhere.util.Logger;

import java.util.ArrayList;

public class MessengeService extends Service {
    public static final int ADDUSER=1;
    public static final int QUERYUSERS=2;
    public static final int REPLYRESULT=3;
    ArrayList<User> users=new ArrayList<>();
    class ServiceHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            Logger.error("handleMessage");
            switch (msg.what){
                case ADDUSER:
                    User user= (User) msg.obj;
                    users.add(user);
                    Logger.error("service: client add a user to server");
                    break;
                case QUERYUSERS:
                    Logger.error("service: client send a query request");
                    Messenger repy=msg.replyTo;
                    //msg.obj=users;
                    msg.what=REPLYRESULT;
                    try {
                        repy.send(msg);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        User user=new User();
        user.age=24;
        user.name="2332";
        users.add(user);
        messenger=new Messenger(new ServiceHandler());
        Logger.error("service oncreate");
    }

    Messenger messenger;

    public MessengeService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Logger.error("service onBind");
       return messenger.getBinder();
    }
}
