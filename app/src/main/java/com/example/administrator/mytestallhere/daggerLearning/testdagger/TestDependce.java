package com.example.administrator.mytestallhere.daggerLearning.testdagger;

import com.example.administrator.mytestallhere.bean.Person;
import com.example.administrator.mytestallhere.daggerLearning.bean.GirlFriend;
import com.example.administrator.mytestallhere.daggerLearning.bean.GoodFriend;
import com.example.administrator.mytestallhere.daggerLearning.compent.DaggerProviderGirlCompent;
import com.example.administrator.mytestallhere.daggerLearning.compent.DaggerTestDependenceCompent;
import com.example.administrator.mytestallhere.daggerLearning.compent.ProviderGirlCompent;
import com.example.administrator.mytestallhere.daggerLearning.module.GirlFriendModule;
import com.example.administrator.mytestallhere.daggerLearning.module.GoodFriendModule;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/4/18 0018.
 */

public class TestDependce {
    @Inject
    GirlFriend girlFriend;

    @Inject
    GoodFriend goodFriend;

    @Inject
    GoodFriend goodFriend2;

    public static void main(String[] args) {
        TestDependce testDependceCompent = new TestDependce();
        ProviderGirlCompent providerGirlCompent = getProviderGirlCompent();
        DaggerTestDependenceCompent.builder().providerGirlCompent(providerGirlCompent)
                .build()
                .inject(testDependceCompent);
        System.out.println(testDependceCompent.girlFriend.whios.name);
        System.out.println(testDependceCompent.goodFriend.name);
        System.out.println(testDependceCompent.goodFriend2.name);
    }

    public static ProviderGirlCompent getProviderGirlCompent() {
        return DaggerProviderGirlCompent
                .builder()
                .girlFriendModule(new GirlFriendModule(new Person("2332", 25)))
                .goodFriendModule(new GoodFriendModule("ln", 25))
                .build();
    }
}
