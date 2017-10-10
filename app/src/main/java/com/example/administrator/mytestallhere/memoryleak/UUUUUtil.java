package com.example.administrator.mytestallhere.memoryleak;

import android.content.Context;

/**
 * Created by Administrator on 2017/10/10 0010.
 */

public class UUUUUtil {
    private static UUUUUtil instance;
    private Context context;
    private UUUUUtil(Context context){
        this.context=context;
    }
    public static UUUUUtil getInstance(Context context){
        if (instance==null)
            instance=new UUUUUtil(context);
        return instance;
    }
}
