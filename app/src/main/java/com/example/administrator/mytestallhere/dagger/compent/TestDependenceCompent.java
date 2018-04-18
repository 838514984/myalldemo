package com.example.administrator.mytestallhere.dagger.compent;

import com.example.administrator.mytestallhere.dagger.testdagger.TestDependce;

import dagger.Component;

/**
 * Created by Administrator on 2018/4/18 0018.
 */
@Component(dependencies = ProviderGirlCompent.class)
public interface TestDependenceCompent {
    void inject(TestDependce testDependce);
}
