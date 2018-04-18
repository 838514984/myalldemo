package com.example.administrator.mytestallhere.dagger.module;

import com.example.administrator.mytestallhere.dagger.bean.GoodFriend;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2018/4/17 0017.
 */
@Module
public class GoodFriendModule {
    public String name;
    public int age;
    //public Person person;

    public GoodFriendModule(String name, int age) {
        this.age = age;
        this.name = name;
        //this.person = new Person(name, age);
    }

    @Provides
    public String provideName() {
        return this.name;
    }

    @Provides
    public int provideAge() {
        return this.age;
    }



    @Provides
    public GoodFriend provideGoodfriend(String name,int age) {
        return new GoodFriend(name,age);
    }


}
