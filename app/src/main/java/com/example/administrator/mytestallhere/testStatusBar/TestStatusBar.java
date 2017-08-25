package com.example.administrator.mytestallhere.testStatusBar;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.example.administrator.mytestallhere.R;

public class TestStatusBar extends AppCompatActivity {
    FrameLayout rl;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_status_bar);
        int systemUiVisiable=getWindow().getDecorView().getSystemUiVisibility();
        systemUiVisiable|= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
        systemUiVisiable|=View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        getWindow().getDecorView().setSystemUiVisibility(systemUiVisiable);
        getWindow().setStatusBarColor(0);
        rl= (FrameLayout) findViewById(R.id.immersiveView);
        //设置Margin
        ViewGroup.LayoutParams layoutParams=rl.getLayoutParams();
        layoutParams.height+=getStatusBarHeight(this);

        rl.setPadding(0,getStatusBarHeight(this),0,0);


    }
    /** 获取状态栏高度 */
    public static int getStatusBarHeight(Context context) {
        int result = 24;
        int resId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resId > 0) {
            result = context.getResources().getDimensionPixelSize(resId);
        } else {
            result = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    result, Resources.getSystem().getDisplayMetrics());
        }
        return result;
    }
}
