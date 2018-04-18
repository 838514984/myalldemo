package com.example.administrator.mytestallhere.dagger.bean;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/4/18 0018.
 */

public class Facotry {
    public Product product;

    @Inject
    public Facotry(Product product) {
        this.product = product;
    }
}
