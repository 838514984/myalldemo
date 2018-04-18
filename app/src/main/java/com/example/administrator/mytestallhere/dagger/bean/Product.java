package com.example.administrator.mytestallhere.dagger.bean;

import com.example.administrator.mytestallhere.bean.Person;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/4/18 0018.
 */

public class Product {
    public String name;
    public Person person;
    @Inject
    public Product(Person person) {
        this.person = person;
        name = "2332";
    }
}
