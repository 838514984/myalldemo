package com.example;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MyClass {
    int vaule = 20;

    public static void main(String[] args) {
        MyClass myClass = new MyClass();
        myClass.hashSet();
        try {
            myClass.okHttp();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void hashSet(){
        HashSet<String> hashSet = new HashSet<>();
        hashSet.add("hahaha");
        hashSet.add("dfgdfsgs");
        Iterator<String> iterator = hashSet.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next());
        }
    }

    private void okHttp() throws Exception {
        //get
        httpGet();
        //post
        httpPost();
    }

    private static void httpPost() throws Exception {
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),"{\"json key\",\"json value\"}");
        Request request=new Request.Builder().put(requestBody).url("http://www.baidu.com").build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                    System.out.print(response.body().string());
            }
        });
    }



    private static void httpGet() throws Exception {
        OkHttpClient okHttpClient=new OkHttpClient();
        Request request=new Request.Builder().url("http://www.baidu.com").build();
        Response response=null;
        okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    System.out.print(response.body().string());
                }
            });
        System.out.print("sleep");
        Thread.sleep(2000);
        System.out.print("wake");
    }
}
