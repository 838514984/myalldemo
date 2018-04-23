package com.example.administrator.mytestallhere.daggerLearning.compent;

import com.example.administrator.mytestallhere.daggerLearning.bean.GirlFriend;
import com.example.administrator.mytestallhere.daggerLearning.bean.GoodFriend;
import com.example.administrator.mytestallhere.daggerLearning.module.GirlFriendModule;
import com.example.administrator.mytestallhere.daggerLearning.module.GoodFriendModule;

import dagger.Component;

/**
 * Created by Administrator on 2018/4/18 0018.
 */
@Component(modules = {GirlFriendModule.class, GoodFriendModule.class})
public interface ProviderGirlCompent {
    GirlFriend provideGirlFriendFromCompent();
    GoodFriend goodFriend();
}
