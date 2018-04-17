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

//    @Provides
//    public Person providePerson() {
//        return this.person;
//    }

    /**
     *  因为最后是要提供一个这个，所以要这样做，不然报错，这样bean里面构造参数就不需要@Inject注解
     * @return
     */
    @Provides
    public GirlFriend provideGirlFriend(){
        return new GirlFriend(this.person);
    }
}
