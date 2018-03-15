package com.example.administrator.mytestallhere.annotationTest.Annotation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.administrator.mytestallhere.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
@MyBindLayout(layoutId=R.layout.activity_custom_annotation)
public class AnnotationActivity extends AppCompatActivity {
    @MyBindView(R.id.tv)
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_custom_annotation);
        MyAnnotationParser.bindContentView(this);
        MyAnnotationParser.bindView(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Observable.timer(2, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                textView.setText("HELLO WORLD...");
            }
        });
    }
}
