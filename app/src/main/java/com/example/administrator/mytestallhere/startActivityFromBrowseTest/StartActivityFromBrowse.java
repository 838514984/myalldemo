package com.example.administrator.mytestallhere.startActivityFromBrowseTest;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.administrator.mytestallhere.BaseActivity;
import com.example.administrator.mytestallhere.R;
import com.example.administrator.mytestallhere.util.GetAssetsFiles;
import com.example.administrator.mytestallhere.util.Logger;

import butterknife.BindView;
import butterknife.OnClick;

public class StartActivityFromBrowse extends BaseActivity {
    @BindView(R.id.tv)
    TextView tv;

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
            String s="host: "+host+"\nencodePath: "+encodePath+"\ngetquery: "+getQuery+"\ngetQueryParameter: "+getQueryParameter+
            "\ngetPath: "+getPath;
            tv.setText(s);
        }
    }
@OnClick(R.id.btn)
public void onClick(){
    Intent intent=new Intent();
    intent.setAction(Intent.ACTION_VIEW);
    Uri uri= FileProvider.getUriForFile(this,"com.secretbase.treasure", GetAssetsFiles.getFileFromAssetsFile(this,"startactivityfrombrows.html"));
    intent.setData(uri);
    intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
    intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
    startActivity(intent);
}
    @Override
    protected int getLayoutId() {
        return R.layout.activity_start_from_browse;
    }
}
