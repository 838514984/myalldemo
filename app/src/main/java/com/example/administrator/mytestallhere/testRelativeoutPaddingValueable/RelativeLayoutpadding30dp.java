package com.example.administrator.mytestallhere.testRelativeoutPaddingValueable;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.administrator.mytestallhere.BaseActivity;
import com.example.administrator.mytestallhere.R;
import com.example.util.Logger;

public class RelativeLayoutpadding30dp extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.error("RelativeLayoutpadding30dp onCreate");
        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RelativeLayoutpadding30dp.this,FrameLayoutContainerpadding30dp.class));
            }
        });

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Logger.error("RelativeLayoutpadding30dp onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Logger.error("RelativeLayoutpadding30dp onResume");
    }

    @Override
    protected int getLayoutId() {
        return  R.layout.activity_relative_layoutpadding30dp;
    }
}
