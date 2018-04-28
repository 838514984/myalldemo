package com.example.administrator.mytestallhere.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.example.administrator.mytestallhere.UserManager;
import com.example.administrator.mytestallhere.bean.User;
import com.example.util.Logger;

import java.util.ArrayList;
import java.util.List;

public class AidlService extends Service {
    ArrayList<User> users=new ArrayList<>();
    UserManager.Stub manager=new UserManager.Stub() {
        @Override
        public void addUser(User user) throws RemoteException {
            Logger.error("Service: client add user to service");
            users.add(user);
        }

        @Override
        public List<User> getUsers() throws RemoteException {
            Logger.error("Service:client query users from service");
            return users;
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        User user=new User();
        user.age=24;
        user.name="treasure";
        users.add(user);
    }

    public AidlService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return manager.asBinder();
    }
}
