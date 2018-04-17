package com.example.administrator.mytestallhere.dagger.bean;

import com.example.administrator.mytestallhere.bean.Person;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/4/17 0017.
 */

public class GoodFriend  {
    public String name;
    public int age;

    //@Inject
    public GoodFriend(){
        this.age = 15;
        this.name = "goog";
    }

    public GoodFriend(String name, int age) {
        this.age = age;
        this.name = name;
    }
}
