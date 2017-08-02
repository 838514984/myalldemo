package com.example.administrator.mytestallhere.rxbinding;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.mytestallhere.R;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxAdapter;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jakewharton.rxbinding2.widget.TextViewEditorActionEvent;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class RxBinDingActivity extends AppCompatActivity {

    Button btnClickMe;
    EditText editText1;
    EditText editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_bin_ding);
        btnClickMe = (Button) findViewById(R.id.btn);
        editText1 = (EditText) findViewById(R.id.edit_1);
        editText2 = (EditText) findViewById(R.id.edit_2);
        final Long count = Long.valueOf(5);
        RxView.clicks(btnClickMe).throttleFirst(5, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                Log.e("xxx", "*************be clicked*************");
                Observable.interval(0, 1, TimeUnit.SECONDS).take(count + 1).map(new Function<Long, Long>() {
                    @Override
                    public Long apply(Long aLong) throws Exception {
                        return count - aLong;
                    }
                }).doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        //初始化操作
                        RxView.enabled(btnClickMe).accept(false);
                        btnClickMe.setTextColor(0xff000000);
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Long>() {
                            @Override
                            public void accept(Long aLong) throws Exception {
                                btnClickMe.setText(aLong + "");
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                            }
                        }, new Action() {
                            @Override
                            public void run() throws Exception {
                                RxView.enabled(btnClickMe).accept(true);
                                btnClickMe.setText("complete");
                                btnClickMe.setTextColor(0xff0000ff);
                            }
                        });
            }
        });

        Observable<CharSequence> o1 = RxTextView.textChanges(editText1);
        Observable<CharSequence> o2 = RxTextView.textChanges(editText2);

        Observable.combineLatest(o1, o2, new BiFunction<CharSequence,CharSequence,Boolean>() {

            @Override
            public Boolean apply(CharSequence s, CharSequence s2) throws Exception {
                return verfName(s.toString())&&verfPsd(s2.toString());
            }
        }).debounce(2,TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean o) throws Exception {
                Log.e("xxx","Boolean: "+o);
                if (o){
                    Toast.makeText(RxBinDingActivity.this,"verf success",0).show();
                }
            }
        });




        RxTextView.editorActionEvents(editText1).subscribe(new Consumer<TextViewEditorActionEvent>() {
            @Override
            public void accept(TextViewEditorActionEvent textViewEditorActionEvent) throws Exception {
                Log.e("xxx","editorActionEvents: ");
            }
        });


        Observable.concat(o2,o1).subscribe(new Consumer<CharSequence>() {
            @Override
            public void accept(CharSequence charSequence) throws Exception {
                Log.e("xxx","concat: "+charSequence.toString());
            }
        });


        Observable.merge(o1,o2).subscribe(new Consumer<CharSequence>() {
            @Override
            public void accept(CharSequence charSequence) throws Exception {
                Log.e("xxx","merge: "+charSequence.toString());
            }
        });


    }

    private boolean verfName(String s){
        if (s.length()<=3){
            Toast.makeText(this,"the name's length must bigger than three ,you fool",0).show();
            return false;
        }
        return true;
    }

    private boolean verfPsd(String s){
        if (s.length()<=3){
            Toast.makeText(this,"the psd's length must bigger than three ,you fool",0).show();
            return false;
        }
        return true;
    }
}
