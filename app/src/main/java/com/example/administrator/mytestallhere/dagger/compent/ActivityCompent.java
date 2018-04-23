package com.example.administrator.mytestallhere.dagger.compent;

import android.app.Activity;
import android.content.Context;

import com.example.administrator.mytestallhere.dagger.module.ActivityModule;
import com.example.administrator.mytestallhere.dagger.qualifier.ActivityContext;
import com.example.administrator.mytestallhere.dagger.qualifier.Peractivity;

import dagger.Component;

/**
 * Created by Administrator on 2018/4/20 0020.
 */
@Peractivity
@Component(dependencies = ApplicationCompent.class ,modules = ActivityModule.class)
public interface ActivityCompent {
    void inject(Activity activity);

    @ActivityContext
    Context provideContext();
}
