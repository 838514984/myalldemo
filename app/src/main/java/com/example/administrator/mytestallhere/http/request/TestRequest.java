package com.example.administrator.mytestallhere.http.request;


import com.example.administrator.mytestallhere.http.response.TestResponse;

import java.lang.reflect.Type;

/**
 * Created by Administrator on 2017/7/10 0010.
 */

public class TestRequest implements BaseRequest<TestResponse> {
    public String name;
    public int age;
    @Override
    public String getUrl() {
        return "http://www.baidu.com ";//如果那边设置了全部链接，则这里可以随便设置";
    }

    @Override
    public Type getClassTYpe() {
        return TestResponse.class;
    }

    @Override
    public Class<TestResponse> getclass() {
        return TestResponse.class;
    }


}