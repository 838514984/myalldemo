package com.example.administrator.mytestallhere.dagger.module;

import com.example.administrator.mytestallhere.bean.Person;
import com.example.administrator.mytestallhere.dagger.bean.GirlFriend;


import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2018/4/17 0017.
 */
@Module
public class GirlFriendModule {
    public Person person;


    public GirlFriendModule(Person person) {
        this.person = person;
    }

    @Provides
    public Person providePerson() {
        return this.person;
    }

    /**
     *  如果上面没有提供对象，那么会到Person的构造方法看是否有@Inject注解，有就实例化，灭有就报错
     * @return
     */
    @Provides
    public GirlFriend provideGirlFriend(Person person){
        return new GirlFriend(person);
    }
}
