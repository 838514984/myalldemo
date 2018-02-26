package com.example.administrator.mytestallhere.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.example.administrator.mytestallhere.R;

public class ViewMoveActivity extends AppCompatActivity implements View.OnClickListener {
    ViewMove viewMove;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_move);
        findViewById(R.id.btn_scroll).setOnClickListener(this);
        viewMove= (ViewMove) findViewById(R.id.v_move);
    }

    @Override
    public void onClick(View v) {
        viewMove.smothScroll();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }
}
