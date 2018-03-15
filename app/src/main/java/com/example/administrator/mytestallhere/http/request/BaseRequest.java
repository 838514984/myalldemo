package com.example.administrator.mytestallhere.http.request;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.lang.reflect.Type;

/**
 * Created by Administrator on 2017/7/10 0010.
 */

public interface BaseRequest<T> {
    @JsonIgnore
    String getUrl();
    @JsonIgnore
    Type getClassTYpe();//其实还是下面的好用
    @JsonIgnore
    Class<T> getclass();
}