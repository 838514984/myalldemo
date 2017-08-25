package com.example.administrator.mytestallhere.startActivityFromBrowseTest;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.mytestallhere.BaseActivity;
import com.example.administrator.mytestallhere.R;
import com.example.administrator.mytestallhere.util.Logger;

public class StartActivityFromBrowse extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();
        String scheme=intent.getScheme();
        Uri uri=intent.getData();
        if (uri!=null){
            String host=uri.getHost();
            String encodePath=uri.getEncodedPath();
            String getQuery=uri.getQuery();
            String getQueryParameter=uri.getQueryParameter("belove");
            String getPath=uri.getPath();
            Logger.Erroe("host: "+host+"\nencodePath: "+encodePath+"\ngetquery: "+getQuery+"\ngetQueryParameter: "+getQueryParameter+
            "\ngetPath: "+getPath);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_start_from_browse;
    }
}
