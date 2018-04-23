package com.example.administrator.mytestallhere.daggerLearning.module;

import com.example.administrator.mytestallhere.daggerLearning.bean.Student;
import com.example.administrator.mytestallhere.daggerLearning.qualifier.StudentA;
import com.example.administrator.mytestallhere.daggerLearning.qualifier.StudentB;
import com.example.administrator.mytestallhere.daggerLearning.qualifier.StudentC;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2018/4/19 0019.
 */
@Module
public class StudentModule {
    String name;
    int age;

    public StudentModule(String name, int age) {
        this.age = age + 100000;
        this.name = name + " xixixixixi";
    }


    @StudentA
    @Provides
    Student studentA() {
        return new Student("student A", 25);
    }

    @StudentB
    @Provides
    Student studentB() {
        return new Student("student B", 30);
    }

    @Provides
    @StudentC
    Student studentC(@Named("real")String name, int age) {
        return new Student(name, age);
    }

    @Named("real")
    @Provides
    String providerName() {
        return this.name;
    }

    @Provides
    int providerAge() {
        return this.age;
    }

    @Named("not_real")
    @Provides
    String providerName2() {
        return "yoyoyoyoyooyoyoyoyoyoyoyo";
    }
}
