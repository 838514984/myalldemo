package com.example.administrator.mytestallhere.daggerLearning.qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;
import javax.inject.Scope;

/**
 * Created by Administrator on 2018/4/20 0020.
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface StudentB {
}
