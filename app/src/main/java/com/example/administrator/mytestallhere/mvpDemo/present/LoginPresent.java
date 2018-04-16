package com.example.administrator.mytestallhere.mvpDemo.present;

import android.content.Context;

import com.example.administrator.mytestallhere.bean.User;
import com.example.administrator.mytestallhere.mvpDemo.view.IView;
import com.example.administrator.mytestallhere.mvpDemo.view.LoginView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/4/16 0016.
 */

public class LoginPresent extends BasePresent<LoginView> {

    public LoginPresent(Context activity, LoginView iView) {
        super(activity, iView);
    }

    public void login() {
        final String account = mIView.getAcctount();
        final String pwd = mIView.getPwd();
        mIView.showLoding();
        Observable.timer(2000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        if (account.equals("ln") && pwd.equals("2332")) {
                            mIView.loginSuccess(new User("ln", 25));
                        } else {
                            mIView.loginFail();
                        }
                        mIView.hideLogind();
                    }
                });

    }

}
