package com.example.administrator.mytestallhere.learn.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2018/4/26 0026.
 */

public class CustomGroup extends ViewGroup {
    public CustomGroup(Context context) {
        super(context);
    }

    public CustomGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec,heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
    }
}
