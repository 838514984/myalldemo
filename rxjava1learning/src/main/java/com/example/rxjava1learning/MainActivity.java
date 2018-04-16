package com.example.rxjava1learning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.rxjava1learning.log.Logger;

import java.util.Arrays;
import java.util.List;

import rx.Emitter;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.observables.SyncOnSubscribe;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_filter).setOnClickListener(this);
        findViewById(R.id.btn_create).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_filter){
            filter_Rx();
        } else if(v.getId() == R.id.btn_create){
            create_Rx();
        }
    }

    private void filter_Rx(){
        List<Integer> ints = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Observable.from(ints)
                .filter(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        boolean b = false;
                        if (integer > 5) {
                            b = true;
                        }
                        return b;
                    }
                })
                .map(new Func1<Integer, String>() {
                    @Override
                    public String call(Integer integer) {
                        return "this is " + integer;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        Logger.error("onComplete");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.error("onError msg: " + e.getMessage());
                    }

                    @Override
                    public void onNext(String s) {
                        Logger.error("onNext , reveive msg: " + s);
                    }
                });
    }

    private void create_Rx(){
        Observable ob = Observable.create(new SyncOnSubscribe() {
            @Override
            protected Object generateState() {
                return null;
            }

            @Override
            protected Object next(Object state, Observer observer) {
                return null;
            }

            @Override
            public void call(Object o) {

            }
        });
    }
}
