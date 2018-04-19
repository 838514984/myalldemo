package com.example.administrator.mytestallhere.dagger.testdagger;

import com.example.administrator.mytestallhere.dagger.bean.Student;
import com.example.administrator.mytestallhere.dagger.compent.DaggerTestNamedCompent;
import com.example.administrator.mytestallhere.dagger.module.StudentModule;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Administrator on 2018/4/19 0019.
 */

public class TestNamed {
    @Inject
    @Named("A")
    Student a;
    @Inject
    @Named("B")
    Student b;
    @Inject
    @Named("C")
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
