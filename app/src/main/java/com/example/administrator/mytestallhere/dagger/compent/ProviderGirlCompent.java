package com.example.administrator.mytestallhere.dagger.compent;

import com.example.administrator.mytestallhere.dagger.bean.GirlFriend;
import com.example.administrator.mytestallhere.dagger.bean.GoodFriend;
import com.example.administrator.mytestallhere.dagger.module.GirlFriendModule;
import com.example.administrator.mytestallhere.dagger.module.GoodFriendModule;
import com.example.administrator.mytestallhere.dagger.testdagger.TestDagger;
import com.example.administrator.mytestallhere.dagger.testdagger.TestNoModule;

import dagger.Component;

/**
 * Created by Administrator on 2018/4/18 0018.
 */
@Component(modules = {GirlFriendModule.class, GoodFriendModule.class})
public interface ProviderGirlCompent {
    GirlFriend provideGirlFriendFromCompent();
    GoodFriend goodFriend();
}
