package com.example.administrator.mytestallhere.daggerLearning.compent;

import com.example.administrator.mytestallhere.daggerLearning.testdagger.TestDependce;

import dagger.Component;

/**
 * Created by Administrator on 2018/4/18 0018.
 */
@Component(dependencies = ProviderGirlCompent.class)
public interface TestDependenceCompent {
    void inject(TestDependce testDependce);
}
