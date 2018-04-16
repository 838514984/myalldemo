package com.example.administrator.mytestallhere.mvpDemo.view;

import com.example.administrator.mytestallhere.bean.User;

/**
 * Created by Administrator on 2018/4/16 0016.
 */

public interface LoginView extends IView {
    String getAcctount();
    String getPwd();
    void loginSuccess(User user);
    void loginFail();
    void showLoding();
    void hideLogind();
}
