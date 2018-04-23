package com.example.administrator.mytestallhere.dagger.module;

import android.app.Application;
import android.content.Context;

import com.example.administrator.mytestallhere.dagger.qualifier.ApplicationContext;
import com.example.administrator.mytestallhere.dataProvider.ExpendPlaceViewHolderDataProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2018/4/20 0020.
 */
@Module
public class ApplicationModule {
    Application application;


    public ApplicationModule(Application application){
        this.application = application;
    }

//    @Provides
//    ExpendPlaceViewHolderDataProvider providerExpendPlaceViewHolderDataProvider() {
//        return expendPlaceViewHolderDataProvider;
//    }

    @ApplicationContext
    @Provides
    Context provideApplicationContext(){
        return this.application;
    }

}
