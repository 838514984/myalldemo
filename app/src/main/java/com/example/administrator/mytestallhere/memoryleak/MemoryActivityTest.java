package com.example.administrator.mytestallhere.memoryleak;

import android.os.Handler;
import android.os.Bundle;
import android.widget.Button;

import com.example.administrator.mytestallhere.BaseActivity;
import com.example.administrator.mytestallhere.R;
import com.example.util.Logger;

import butterknife.BindView;
import butterknife.OnClick;

public class MemoryActivityTest extends BaseActivity {
    @BindView(R.id.btn)
    Button btn;
    UUUUUtil uuuuUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uuuuUtil=UUUUUtil.getInstance(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_memory_test;
    }

    @OnClick(R.id.btn)
    public void onClick(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Logger.error("onThread doing end");
                btn.setText("hhhhhhhhhhhhhhhhhhhhhh");
                Logger.error(btn.getText().toString());
            }
        },2000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Logger.error("onDestroy");
    }
}
