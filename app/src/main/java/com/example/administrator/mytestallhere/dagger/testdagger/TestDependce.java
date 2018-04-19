package com.example.administrator.mytestallhere.dagger.testdagger;

import com.example.administrator.mytestallhere.bean.Person;
import com.example.administrator.mytestallhere.dagger.bean.GirlFriend;
import com.example.administrator.mytestallhere.dagger.bean.GoodFriend;
import com.example.administrator.mytestallhere.dagger.compent.DaggerProviderGirlCompent;
import com.example.administrator.mytestallhere.dagger.compent.DaggerTestDaggerCompent;
import com.example.administrator.mytestallhere.dagger.compent.DaggerTestDependenceCompent;
import com.example.administrator.mytestallhere.dagger.compent.ProviderGirlCompent;
import com.example.administrator.mytestallhere.dagger.compent.TestDaggerCompent;
import com.example.administrator.mytestallhere.dagger.module.GirlFriendModule;
import com.example.administrator.mytestallhere.dagger.module.GoodFriendModule;

import javax.inject.Inject;
import javax.inject.Named;

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
