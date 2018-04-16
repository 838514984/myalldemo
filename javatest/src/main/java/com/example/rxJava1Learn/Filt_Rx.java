package com.example.rxJava1Learn;

import com.example.log.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import sun.rmi.runtime.Log;

/**
 * Created by Administrator on 2018/4/13 0013.
 */

public class Filt_Rx {
    public static void main(String[] string) {
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
                        Logger.ERROR("onComplete");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.ERROR("onError msg: " + e.getMessage());
                    }

                    @Override
                    public void onNext(String s) {
                        Logger.ERROR("onNext , reveive msg: " + s);
                    }
                });
    }
}
