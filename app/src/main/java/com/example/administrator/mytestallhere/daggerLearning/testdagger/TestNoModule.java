package com.example.administrator.mytestallhere.daggerLearning.testdagger;

import com.example.administrator.mytestallhere.daggerLearning.bean.Facotry;
import com.example.administrator.mytestallhere.daggerLearning.compent.DaggerTestNoModuleCompent;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/4/18 0018.
 */

public class TestNoModule {
    @Inject
    Facotry facotry;

    public static void main(String[] a) {
        TestNoModule testNoModule = new TestNoModule();
        DaggerTestNoModuleCompent.builder().build().inject(testNoModule);
        System.out.println(testNoModule.facotry.product.person.name);
    }
}
