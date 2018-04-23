package com.example.administrator.mytestallhere.daggerLearning.testdagger;

import com.example.administrator.mytestallhere.daggerLearning.bean.Student;
import com.example.administrator.mytestallhere.daggerLearning.compent.DaggerTestNamedCompent;
import com.example.administrator.mytestallhere.daggerLearning.module.StudentModule;
import com.example.administrator.mytestallhere.daggerLearning.qualifier.StudentA;
import com.example.administrator.mytestallhere.daggerLearning.qualifier.StudentB;
import com.example.administrator.mytestallhere.daggerLearning.qualifier.StudentC;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Administrator on 2018/4/19 0019.
 */

public class TestNamed {
    @Inject
    @StudentA
    Student a;
    @Inject
    @StudentB
    Student b;
    @Inject
    @StudentC
    Student c;

    public static void main(String[] a) {
        TestNamed testNamed = new TestNamed();
        DaggerTestNamedCompent
                .builder().studentModule(new StudentModule("student c", 1))
                .build().inject(testNamed);

        System.out.println(testNamed.a.name);
        System.out.println(testNamed.b.name);
        System.out.println(testNamed.c.name);
    }
}
