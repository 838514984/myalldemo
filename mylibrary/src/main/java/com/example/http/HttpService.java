package com.example.http;

import com.example.sms.callback.CallBackBean;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2018/4/28 0028.
 */

public interface HttpService {
    @FormUrlEncoded
    @POST("sms.php")
    Observable<CallBackBean> getSMSVerityStatus(@Field("phone") String phone,
                                                @Field("zone") String zone,
                                                @Field("code") String code);
}
