package com.example.administrator.mytestallhere.daggerLearning.testdagger;



import com.example.administrator.mytestallhere.bean.Person;
import com.example.administrator.mytestallhere.daggerLearning.bean.GirlFriend;
import com.example.administrator.mytestallhere.daggerLearning.bean.GoodFriend;
import com.example.administrator.mytestallhere.daggerLearning.compent.DaggerTestDaggerCompent;
import com.example.administrator.mytestallhere.daggerLearning.module.GirlFriendModule;
import com.example.administrator.mytestallhere.daggerLearning.module.GoodFriendModule;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/4/17 0017.
 */

public class TestDagger {
    @Inject
    public GirlFriend girlFriend;

    @Inject
    public GoodFriend goodFriendA;

    public TestDagger(){
        //DaggerGirlFrientCompent.builder().girlFriendModule(new GirlFriendModule(new Person("ln",25))).build().inject(this);
        //DaggerGirlFrientCompent.create().inject(this);
        //只能inject一个！！！
        DaggerTestDaggerCompent.builder()
                .girlFriendModule(new GirlFriendModule(new Person("2332",25)))
                .goodFriendModule(new GoodFriendModule("caiwei",23))
                .build()
                .inject(this);
    }

    public static void main(String[] s){
        TestDagger testDagger = new TestDagger();
        System.out.println(testDagger.girlFriend.whios.name);
        System.out.println(testDagger.goodFriendA.name);
    }
}
