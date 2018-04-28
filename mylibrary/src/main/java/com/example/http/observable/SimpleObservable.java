package com.example.http.observable;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2018/4/28 0028.
 */

public abstract class SimpleObservable<T> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public abstract void onNext(T value);

    @Override
    public abstract void onError(Throwable e);


    @Override
    public void onComplete() {

    }
}
