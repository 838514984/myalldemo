package com.example.administrator.mytestallhere.dagger.module;

import android.app.Activity;
import android.content.Context;

import com.example.administrator.mytestallhere.dagger.qualifier.ActivityContext;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2018/4/20 0020.
 */
@Module
public class ActivityModule {
    Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @ActivityContext
    @Provides
    Context provideContext() {
        return this.activity;
    }


}
