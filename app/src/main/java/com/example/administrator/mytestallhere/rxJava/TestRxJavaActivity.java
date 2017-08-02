package com.example.administrator.mytestallhere.rxJava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.administrator.mytestallhere.R;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class TestRxJavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_rx_java);
        //计时器
        //testTimer();
        //period count
        //testInterval();

        //testContact();
        //testMerge();
        testFlowable();
    }


    private void testFlowable() {
        Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> e) throws Exception {
                e.onNext("1");
                e.onNext("2");
                e.onComplete();
            }
        }, BackpressureStrategy.BUFFER).subscribe(new Subscriber<String>() {
            //如果是用Subcriber，那么这里必须设置，否则发不出消息
            @Override
            public void onSubscribe(Subscription s) {
                s.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(String s) {
                LogE(" on next: "+s);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void testTimer() {
        Observable.timer(2, TimeUnit.SECONDS).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                //Log.e("xxx","aLong: "+aLong+"");

            }
        });
    }

    private void testInterval() {
        Observable.interval(1, TimeUnit.SECONDS)
                .take(10)
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        LogE("this is the doFinally");
                    }
                })
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(Long aLong) throws Exception {
                        return 30 - aLong;
                    }
                })
                .doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        LogE("this is doOnNext--------------------------------------");
                    }
                })
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.e("xxx", "aLong : " + aLong);
                    }
                });
    }

    private void testContact() {
        //联接两个Observeable发出的信息
        //这里complete不能忘记！！！
        Observable O1 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("the first");
                e.onComplete();
            }
        });
        Observable O2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("the seconed");
                e.onComplete();
            }
        });

        Observable.concat(O1, O2).subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                LogE("concat: " + o.toString());
            }
        });

        Observable.concat(O1, O2).first("no data").subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                LogE("concat: " + o.toString());
            }
        });

    }

    private void testMerge() {
        //主要就是将两个Observeable合并到一个Observeable，@Link RxBinDingActivity;
    }

    public static void LogE(String msg) {
        Log.e("xxx", msg);
    }
}
