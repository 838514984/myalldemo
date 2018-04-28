package com.example.http;


import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by Administrator on 2018/4/28 0028.
 */

public class RetrofitClient {
    private static RetrofitClient mRetrofitClient;
    private static Retrofit  retrofit;

    public static Retrofit getDefault() {
        synchronized ("") {
            if (mRetrofitClient == null) {
                mRetrofitClient = new RetrofitClient();
            }
        }
        return retrofit;
    }

    private RetrofitClient() {
         retrofit = new Retrofit.Builder()
                 .baseUrl(WebSite.baseUrl)
                 .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                 .addConverterFactory(JacksonConverterFactory.create())
                 .build();
    }
}
