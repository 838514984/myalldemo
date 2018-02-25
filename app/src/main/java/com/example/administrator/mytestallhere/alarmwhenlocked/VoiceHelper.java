package com.example.administrator.mytestallhere.alarmwhenlocked;

import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;

import com.example.administrator.mytestallhere.MyApplication;

import java.io.IOException;

/**
 * Created by Administrator on 2017/11/20 0020.
 */

public class VoiceHelper {
    MediaPlayer mMediaPlayer;
    public void startAlarm() {
        mMediaPlayer = MediaPlayer.create(MyApplication.INSTANCE, getSystemDefultRingtoneUri());
//        mMediaPlayer.setLooping(true);
//        try {
//            mMediaPlayer.prepare();
//        } catch (IllegalStateException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        mMediaPlayer.start();
        try {
            mMediaPlayer = MediaPlayer.create(MyApplication.INSTANCE, RingtoneManager.getActualDefaultRingtoneUri(MyApplication.INSTANCE, RingtoneManager.TYPE_RINGTONE));
            if(mMediaPlayer != null){
                mMediaPlayer.stop();
            }
        } catch (Exception e){

        }

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

    public void stopAlarm(){
        mMediaPlayer.stop();
    }


    private Uri getSystemDefultRingtoneUri() {
        return RingtoneManager.getActualDefaultRingtoneUri(MyApplication.INSTANCE,
                RingtoneManager.TYPE_RINGTONE);
    }
}
