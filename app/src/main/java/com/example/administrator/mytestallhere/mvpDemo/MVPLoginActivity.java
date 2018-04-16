package com.example.administrator.mytestallhere.mvpDemo;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.mytestallhere.BaseActivity;
import com.example.administrator.mytestallhere.R;
import com.example.administrator.mytestallhere.bean.User;
import com.example.administrator.mytestallhere.mvpDemo.view.LoginView;
import com.example.administrator.mytestallhere.mvpDemo.present.LoginPresent;

public class MVPLoginActivity extends BaseActivity<LoginPresent> implements LoginView, View.OnClickListener {
    ProgressDialog mProgressDialog;
    EditText mEdAccount;
    EditText mEdPwd;
    Button mLoginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLoginBtn = (Button) findViewById(R.id.btn_login);
        mEdPwd = (EditText) findViewById(R.id.et_pwd);
        mEdAccount = (EditText) findViewById(R.id.et_account);
        mLoginBtn.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mvplogin;
    }

    @Override
    protected LoginPresent initPresent() {
        return new LoginPresent(this,this);
    }

    @Override
    public String getAcctount() {
        return mEdAccount.getText().toString();
    }

    @Override
    public String getPwd() {
        return mEdPwd.getText().toString();
    }


    @Override
    public void loginSuccess(User user) {
        Toast.makeText(this, "login success!!" + "\nname: "+user.name+"\nage: "+user.age,0).show();
    }

    @Override
    public void loginFail() {
        Toast.makeText(this, "login fail!!",0 ).show();
    }

    @Override
    public void showLoding() {
//        mProgressDialog = new ProgressDialog(this);
//        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        mProgressDialog.setMessage("login...");
//        mProgressDialog.show();
       mProgressDialog = ProgressDialog.show(this,"login","logining");
    }

    @Override
    public void hideLogind() {
        mProgressDialog.hide();
    }

    @Override
    public void onClick(View v) {
        mPresent.login();
    }
}
