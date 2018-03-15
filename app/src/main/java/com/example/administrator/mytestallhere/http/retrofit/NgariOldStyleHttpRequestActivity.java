package com.example.administrator.mytestallhere.http.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.mytestallhere.R;
import com.example.administrator.mytestallhere.http.httpUtil.HttpUtil;
import com.example.administrator.mytestallhere.http.request.TestRequest;

import java.io.Serializable;
import java.util.HashSet;

public class NgariOldStyleHttpRequestActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv= (TextView) findViewById(R.id.tv);
        tv.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        TestRequest request=new TestRequest();
        HashSet hashSet=new HashSet();
        hashSet.add(new String("hahaha"));

        request.name="fuck off";
        request.age=999;
        HttpUtil.post(request, new HttpUtil.OnSuccessListener() {
            @Override
            public void success(Serializable response) {
                RetrofitExample.People people= (RetrofitExample.People) response;
                Log.e("Xxxx","people's name: "+people.name+" people's age: "+people.age);
            }
        }, new HttpUtil.OnFailListener() {
            @Override
            public void fail(Throwable t) {

            }
        });
    }
}
