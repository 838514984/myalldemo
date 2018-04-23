package com.example.administrator.mytestallhere.daggerLearning.compent;

import com.example.administrator.mytestallhere.daggerLearning.module.StudentModule;
import com.example.administrator.mytestallhere.daggerLearning.testdagger.TestNamed;

import dagger.Component;

/**
 * Created by Administrator on 2018/4/19 0019.
 */
@Component(modules = StudentModule.class)
public interface TestNamedCompent {
    void inject(TestNamed testNamed);
}
