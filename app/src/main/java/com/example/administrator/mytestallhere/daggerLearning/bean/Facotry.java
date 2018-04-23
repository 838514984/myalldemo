package com.example.administrator.mytestallhere.daggerLearning.bean;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/4/18 0018.
 */

public class Facotry {
    public Product product;

    @Inject //会找Product构造方法@inject注解
    public Facotry(Product product) {
        this.product = product;
    }
}
