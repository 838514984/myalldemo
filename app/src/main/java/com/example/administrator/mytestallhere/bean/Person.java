package com.example.administrator.mytestallhere.bean;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/4/17 0017.
 */

public class Person extends User {

    public Person(String name,int age){
        super(name,age);
    }
    @Inject
    public Person(){
        this.name = "xixixi";
        this.age = 0;
    }
}
