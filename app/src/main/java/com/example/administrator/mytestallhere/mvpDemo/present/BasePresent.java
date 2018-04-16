package com.example.administrator.mytestallhere.mvpDemo.present;

import android.app.Activity;
import android.content.Context;

import com.example.administrator.mytestallhere.mvpDemo.view.IView;

/**
 * Created by Administrator on 2018/4/16 0016.
 */

public class BasePresent<T extends IView> {
    T mIView;
    Context mContext;
    public BasePresent(Context activity,T iView){
        this.mIView = iView;
        this.mContext = activity;
    }
}
