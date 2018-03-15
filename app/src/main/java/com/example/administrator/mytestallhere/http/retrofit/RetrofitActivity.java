package com.example.administrator.mytestallhere.http.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.mytestallhere.R;

import retrofit2.Retrofit;

public class RetrofitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        Retrofit retrofit=new Retrofit.Builder().build();
    }
}
