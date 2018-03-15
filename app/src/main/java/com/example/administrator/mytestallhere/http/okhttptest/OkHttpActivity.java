package com.example.administrator.mytestallhere.http.okhttptest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.mytestallhere.R;

import java.io.IOException;
import java.net.HttpURLConnection;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);
        try {
            okHttp();
        } catch (Exception e) {
            e.printStackTrace();
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
        //表单提交
        RequestBody formBody = new FormBody.Builder()
                .add("jsonData", "{\"data\":\"121\",\"data1\":\"2232\"}")
                .add("username", "Arison+中文").add("password", "1111111")
                .build();
        //单独json体好像
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), "{\"json key\":\"json value\"}");

        Request request = new Request.Builder()
                .addHeader("content-type", "application/json;charset:utf-8")
                .addHeader("leslielee", "leslie lee visisted")
                //请求数据只能设置一个
                //单独json提交，ngari里用的是这种
                .post(requestBody)
                //表单提交
                //.post(formBody)
                //.put(requestBody)
                .url("http://www.baidu.com")
                .build();
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
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url("http://www.baidu.com").build();
        Response response = null;
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
