package com.example.administrator.mytestallhere.daggerLearning.compent;

import com.example.administrator.mytestallhere.daggerLearning.testdagger.TestNoModule;

import dagger.Component;

/**
 * Created by Administrator on 2018/4/18 0018.
 */
@Component
public interface TestNoModuleCompent {
    void inject(TestNoModule testNoModule);
}
