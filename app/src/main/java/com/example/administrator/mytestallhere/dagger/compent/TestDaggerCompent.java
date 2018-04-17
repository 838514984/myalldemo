package com.example.administrator.mytestallhere.dagger.compent;

import com.example.administrator.mytestallhere.dagger.bean.TestDagger;
import com.example.administrator.mytestallhere.dagger.module.GirlFriendModule;
import com.example.administrator.mytestallhere.dagger.module.GoodFriendModule;

import dagger.Component;

/**
 * Created by Administrator on 2018/4/17 0017.
 */
@Component(modules = {GirlFriendModule.class, GoodFriendModule.class})
public interface TestDaggerCompent {
    void inject(TestDagger testDagger);
}
