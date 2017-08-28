package com.example.administrator.mytestallhere.startActivityFromBrowseTest;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.administrator.mytestallhere.BaseActivity;
import com.example.administrator.mytestallhere.R;
import com.example.administrator.mytestallhere.util.GetAssetsFiles;
import com.example.administrator.mytestallhere.util.Logger;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class StartActivityFromBrowse extends BaseActivity {
    @BindView(R.id.tv)
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();
        String scheme=intent.getScheme();
        Uri uri=intent.getData();
        GetAssetsFiles.putFileToSdCard(StartActivityFromBrowse.this,"startactivityfrombrows.html");

        if (uri!=null){
            String host=uri.getHost();
            String encodePath=uri.getEncodedPath();
            String getQuery=uri.getQuery();
            String getQueryParameter=uri.getQueryParameter("belove");
            String getPath=uri.getPath();
            String s="host: "+host+"\nencodePath: "+encodePath+"\ngetquery: "+getQuery+"\ngetQueryParameter: "+getQueryParameter+
            "\ngetPath: "+getPath;
            tv.setText(s);
        }
    }
@OnClick(R.id.btn)
//public void onClick(){
//    Intent intent=new Intent();
//    intent.setAction(Intent.ACTION_VIEW);
//    Uri uri= FileProvider.getUriForFile(this,getPackageName()+".fileprovider", new File(Environment.getExternalStorageDirectory().toString()+"/myHtml/startactivityfrombrows.html"));
//    intent.setData(uri);
//    intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION|Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
//    startActivity(intent);
//}
public void onClick(){
    WebView webView=new WebView(this);
    webView.loadUrl(FileProvider.getUriForFile(this,getPackageName()+".fileprovider", new File(Environment.getExternalStorageDirectory().toString()+"/myHtml/startactivityfrombrows.html")).toString());
    addContentView(webView,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
}

    @Override
    protected int getLayoutId() {
        return R.layout.activity_start_from_browse;
    }
}
