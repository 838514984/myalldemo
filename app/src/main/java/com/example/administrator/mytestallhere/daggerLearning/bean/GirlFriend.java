package com.example.administrator.mytestallhere.daggerLearning.bean;

import com.example.administrator.mytestallhere.bean.Person;

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
