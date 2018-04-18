package com.example.administrator.mytestallhere.dagger.compent;

import com.example.administrator.mytestallhere.bean.Person;
import com.example.administrator.mytestallhere.dagger.bean.GirlFriend;
import com.example.administrator.mytestallhere.dagger.bean.GoodFriend;
import com.example.administrator.mytestallhere.dagger.testdagger.TestDagger;
import com.example.administrator.mytestallhere.dagger.module.GirlFriendModule;
import com.example.administrator.mytestallhere.dagger.module.GoodFriendModule;

import dagger.Component;

/**
 * Created by Administrator on 2018/4/17 0017.
 */
@Component(modules = {GirlFriendModule.class, GoodFriendModule.class})
public interface TestDaggerCompent {
    void inject(TestDagger testDagger);

    GoodFriend provideGoodFriend();

    GirlFriend provideGirlFriend();
}
