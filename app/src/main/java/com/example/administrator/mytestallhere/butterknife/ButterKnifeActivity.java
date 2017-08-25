package com.example.administrator.mytestallhere.butterknife;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.mytestallhere.BaseActivity;
import com.example.administrator.mytestallhere.R;
import com.example.administrator.mytestallhere.rxJava.TestRxJavaActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;

public class ButterKnifeActivity extends BaseActivity {
    @BindView(R.id.tv) TextView tv;
    @BindView(R.id.ed) EditText editText;
    @BindView(R.id.btn) Button btn;
    @BindViews({R.id.tv,R.id.ed,R.id.btn})
    List<View> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tv.setText("hahahahha ");
        for (View view:list){
            TestRxJavaActivity.LogE(String.format("------------view.getClassName %s-----------------",view.getClass().getSimpleName()));
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_butter_knife;
    }

    @OnClick(R.id.tv)
    public void clickTextView(TextView view){
        view.setText("i have been clicked");
    }

    @OnClick({R.id.tv,R.id.ed,R.id.btn})
    public void clickTotalEvents(View view){
        switch (view.getId()){
            case R.id.tv:
                tv.setText("xixi");
                break;
            case R.id.ed:
                editText.setText("haha");
                break;
            case R.id.btn:
                btn.setText("lalalal");
                break;
        }
    }

}
