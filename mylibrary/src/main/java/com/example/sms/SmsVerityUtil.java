package com.example.sms;

import android.text.TextUtils;

import com.example.http.HttpService;
import com.example.http.RetrofitClient;
import com.example.http.observable.SimpleObservable;
import com.example.sms.callback.CallBackBean;
import com.example.util.Logger;

import cn.smssdk.EventHandler;
import cn.smssdk.OnSendMessageHandler;
import cn.smssdk.SMSSDK;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class SmsVerityUtil {
    private static SmsVerityUtil smsUtil;
    private SMSVerityListener smsVerityListener;

    public static SmsVerityUtil getInstance() {
        synchronized ("") {
            if (smsUtil == null) {
                smsUtil = new SmsVerityUtil();
            }
        }
        return smsUtil;
    }

    private SmsVerityUtil() {
        SMSSDK.registerEventHandler(eventHandlerSend);
    }

    public void setSmsVerityListener(SMSVerityListener smsVerityListener) {
        this.smsVerityListener = smsVerityListener;
    }

    EventHandler eventHandlerSend = new EventHandler() {
        @Override
        public void afterEvent(int event, int result, Object data) {
            if (result == SMSSDK.RESULT_COMPLETE) {
                //回调完成
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    if (smsVerityListener != null) {
                        smsVerityListener.onSmsVeritySusccess();
                    }
                    //提交验证码成功
                    Logger.error(data.toString());
                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    if (smsVerityListener != null) {
                        smsVerityListener.onSMSSendSuccess();
                    }
                    //获取验证码成功
                    Logger.error(data.toString());
                } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                    //返回支持发送验证码的国家列表
                    Logger.error(data.toString());
                }
            } else {
                Throwable throwable = (Throwable) data;
                if (smsVerityListener != null) {
                    smsVerityListener.onSmsActionFail("发送失败");
                }
                Logger.error(data.toString());
            }
        }
    };

    public void getVerityCode(String country, String phone) {
        SMSSDK.getVerificationCode(country, phone, null, new OnSendMessageHandler() {
            /**
             * 此方法在发送验证短信前被调用，传入参数为接收者号码
             * 返回true表示此号码无须实际接收短信
             */
            /**
             * @param s  coutry
             * @param s1 phone
             * @return
             */
            @Override
            public boolean onSendMessage(String s, String s1) {
                Logger.error("onSendMessage: s: " + s + " s1: " + s1);
                return false;
            }
        });

    }


    // 提交验证码，其中的code表示验证码，如“1357”
    public void simpleSubmitCode(String country, String phone, String code) {
        SMSSDK.submitVerificationCode(country, phone, code);
    }


    public void ServerSubmitCode(String country, String phone, String code) {
        RetrofitClient.getDefault().create(HttpService.class)
                .getSMSVerityStatus(phone, country, code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SimpleObservable<CallBackBean>() {
                    @Override
                    public void onNext(CallBackBean value) {
                        if (value == null){
                            if (smsVerityListener !=null){
                                smsVerityListener.onSmsActionFail("验证失败");
                            }
                        }else{
                            if (value.status.equals("200")){
                                if (smsVerityListener !=null){
                                    smsVerityListener.onSmsActionFail("验证失败");
                                }else{
                                    smsVerityListener.onSmsVeritySusccess();
                                }
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (smsVerityListener !=null){
                            smsVerityListener.onSmsActionFail("验证失败");
                        }
                    }
                });
    }


    public void recycle() {
        SMSSDK.unregisterAllEventHandler();
        smsVerityListener = null;
    }

    public interface SMSVerityListener {
        void onSMSSendSuccess();

        void onSmsActionFail(String msg);

        void onSmsVeritySusccess();
    }

}
