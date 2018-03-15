package com.example.administrator.mytestallhere.http.retrofit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2017/7/10 0010.
 */

public class RetrofitExample {
    static  String url = "http://192.168.2.42:8080/MyHttp/servlet/";
    public static void main(String ars[]){
        People people = new People();
        people.name = "rain";
        people.age = 23;
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(people);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        //查看Log
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //添加阻拦器，用来查看发送请求的log
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit retrofit = new Retrofit.
                Builder()//使用biulder创建
                .baseUrl(url)//主的链接地址
                .client(client)//设置client，不设拦截器的话可以不用
                .addConverterFactory(JacksonConverterFactory.create())//转换工厂
                .build();//创建
        Test test = retrofit.create(Test.class);//不过这样子好像没用
        Call<ResponseBody> call = test.getCall(json);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

        /*Retrofit retrofit = new Retrofit.Builder().baseUrl(url).client(client).addConverterFactory(JacksonConverterFactory.create()).build();
        BaiduServer server = retrofit.create(BaiduServer.class);

        Call<People> call1 = server.getCall(people);

        call1.enqueue(new Callback<People>() {
            @Override
            public void onResponse(Call<People> call, Response<People> response) {

                    Log.e("xxxx", "response: " + response.body().name);

            }

            @Override
            public void onFailure(Call<People> call, Throwable t) {

            }
        });*/
    }


    public interface BaiduServer {
        @POST("httptest")
        Call<People> getCall(@Body People people);
    }


    public interface Test {
        @Headers({"Accept: application/vnd.yourapi.v1.full+json",
                "User-Agent: Your-App-Name","lalala:hahaha"})
        @FormUrlEncoded
        @POST("httptest")
        Call<ResponseBody> getCall(@Field("name") String people);
    }


    public static class People {
        public String name;
        public int age;

    }

}