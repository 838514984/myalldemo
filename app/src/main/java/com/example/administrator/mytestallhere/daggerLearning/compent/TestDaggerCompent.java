package com.example.administrator.mytestallhere.daggerLearning.compent;

import com.example.administrator.mytestallhere.daggerLearning.bean.GirlFriend;
import com.example.administrator.mytestallhere.daggerLearning.bean.GoodFriend;
import com.example.administrator.mytestallhere.daggerLearning.testdagger.TestDagger;
import com.example.administrator.mytestallhere.daggerLearning.module.GirlFriendModule;
import com.example.administrator.mytestallhere.daggerLearning.module.GoodFriendModule;

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
