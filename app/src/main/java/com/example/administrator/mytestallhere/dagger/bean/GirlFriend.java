package com.example.administrator.mytestallhere.dagger.bean;

import com.example.administrator.mytestallhere.bean.Person;
import com.example.administrator.mytestallhere.bean.User;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/4/17 0017.
 */

public class GirlFriend {
    public Person whios;

    //@Inject
    public GirlFriend() {
        whios = new Person("ln", 25);
    }

    //@Inject
    public GirlFriend(Person person) {
        this.whios = person;
    }
}
