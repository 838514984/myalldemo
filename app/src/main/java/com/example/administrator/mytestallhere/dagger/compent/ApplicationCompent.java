package com.example.administrator.mytestallhere.dagger.compent;

import android.content.Context;

import com.example.administrator.mytestallhere.MyApplication;
import com.example.administrator.mytestallhere.dagger.module.ApplicationModule;
import com.example.administrator.mytestallhere.dagger.qualifier.ApplicationContext;
import com.example.administrator.mytestallhere.dataProvider.ExpendPlaceViewHolderDataProvider;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Administrator on 2018/4/20 0020.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationCompent {

    void inject(MyApplication application);

    @ApplicationContext
    Context providerContext();

    ExpendPlaceViewHolderDataProvider provideExpendPlaceViewHolderDataProvider();
}
