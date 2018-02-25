package com.example.administrator.mytestallhere.alarmwhenlocked;

import android.app.KeyguardManager;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.IntDef;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public class LockService extends Service {
    MediaPlayer mMediaPlayer;

    public LockService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        final Intent intent1 = new Intent(getBaseContext(), LockActivity.class);
        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Observable.interval(5, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                KeyguardManager manager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);
                if (manager.inKeyguardRestrictedInputMode()) {
                    //处于锁定界面,界面则通过KeyguardLock类方法来解锁
                    KeyguardManager.KeyguardLock keyguard = manager.newKeyguardLock(LockService.class.getSimpleName());
                    keyguard.disableKeyguard();
                }
                startAlarm();
                startActivity(intent1);
            }
        });
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void startAlarm() {
        mMediaPlayer = MediaPlayer.create(this, getSystemDefultRingtoneUri());
        mMediaPlayer.setLooping(true);
        try {
            mMediaPlayer.prepare();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mMediaPlayer.start();
    }

    private Uri getSystemDefultRingtoneUri() {
        return RingtoneManager.getActualDefaultRingtoneUri(this,
                RingtoneManager.TYPE_RINGTONE);
    }
}
