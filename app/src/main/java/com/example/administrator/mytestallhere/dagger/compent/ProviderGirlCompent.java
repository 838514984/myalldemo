package com.example.administrator.mytestallhere.dagger.compent;

import com.example.administrator.mytestallhere.dagger.bean.GirlFriend;
import com.example.administrator.mytestallhere.dagger.module.GirlFriendModule;

import dagger.Component;

/**
 * Created by Administrator on 2018/4/18 0018.
 */
@Component(modules = GirlFriendModule.class)
public interface ProviderGirlCompent {
    GirlFriend provideGirlFriendFromCompent();
}
