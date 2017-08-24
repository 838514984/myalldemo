package com.example.administrator.mytestallhere.testRelativeoutPaddingValueable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.administrator.mytestallhere.BaseActivity;
import com.example.administrator.mytestallhere.R;

public class RelativeLayoutpadding30dp extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relative_layoutpadding30dp);
        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RelativeLayoutpadding30dp.this,FrameLayoutContainerpadding30dp.class));
            }
        });
    }
}
