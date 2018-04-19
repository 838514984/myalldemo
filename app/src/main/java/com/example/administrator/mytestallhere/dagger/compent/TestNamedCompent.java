package com.example.administrator.mytestallhere.dagger.compent;

import com.example.administrator.mytestallhere.dagger.module.StudentModule;
import com.example.administrator.mytestallhere.dagger.testdagger.TestNamed;

import dagger.Component;

/**
 * Created by Administrator on 2018/4/19 0019.
 */
@Component(modules = StudentModule.class)
public interface TestNamedCompent {
    void inject(TestNamed testNamed);
}
