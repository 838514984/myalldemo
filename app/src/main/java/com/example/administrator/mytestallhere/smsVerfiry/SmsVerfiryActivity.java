package com.example.administrator.mytestallhere.smsVerfiry;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.mytestallhere.BaseActivity;
import com.example.administrator.mytestallhere.R;
import com.example.sms.SmsVerityUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SmsVerfiryActivity extends BaseActivity implements SmsVerityUtil.SMSVerityListener {

    public static void StartActivity(Context context){
        Intent intent = new Intent(context,SmsVerfiryActivity.class);
        context.startActivity(intent);
    }


    @BindView(R.id.btn_sms)
    Button mBtnGetSms;
    @BindView(R.id.btn_verity_code)
    Button mBtnVerity;
    @BindView(R.id.ed_sms_input)
    EditText mEdPhoneNumber;
    @BindView(R.id.ed_sms_verity)
    EditText mEdVerityCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        SmsVerityUtil.getInstance().setSmsVerityListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sms_verfiry;
    }

    @OnClick(R.id.btn_sms)
    public void getSms(){
        SmsVerityUtil.getInstance().getVerityCode("86",mEdPhoneNumber.getText().toString());
    }

    @OnClick(R.id.btn_verity_code)
    public void btnVerity(){
        //SmsVerityUtil.getInstance().simpleSubmitCode("86",mEdPhoneNumber.getText().toString(), mEdVerityCode.getText().toString());
        SmsVerityUtil.getInstance().ServerSubmitCode("86",mEdPhoneNumber.getText().toString(),mEdVerityCode.getText().toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SmsVerityUtil.getInstance().recycle();
    }

    @Override
    public void onSMSSendSuccess() {
        Snackbar.make(mRootView,"验证码发送成功！",Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onSmsActionFail(String msg) {
        Snackbar.make(mRootView,msg,Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onSmsVeritySusccess() {
        Snackbar.make(mRootView,"验证成功！",Snackbar.LENGTH_SHORT).show();
    }
}
