package com.example.administrator.mytestallhere.http.httpUtil;

import com.example.administrator.mytestallhere.http.request.BaseRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.Serializable;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2017/7/10 0010.
 */

public class HttpUtil {

    public static void post(final BaseRequest request, final OnSuccessListener successListener, final OnFailListener failListener) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(request.getUrl())
                .build();
        BaseRequestServer server = retrofit.create(BaseRequestServer.class);
        final ObjectMapper objectMapper = new ObjectMapper();
        String json = "";
        try {
            json = objectMapper.writeValueAsString(request);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Call<ResponseBody> call = server.getCall(json);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String json = response.body().string();
                    //Serializable serializable=objectMapper.readValue(json,objectMapper.constructType(request.getClassTYpe()));
                    Serializable serializable = (Serializable) objectMapper.readValue(json, request.getclass());
                    if (successListener != null) {
                        successListener.success(serializable);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (failListener != null) {
                    failListener.fail(t);
                }
            }
        });

    }

    public interface BaseRequestServer {
        @FormUrlEncoded
        @POST("http://192.168.2.42:8080/MyHttp/servlet/httptest")
        Call<ResponseBody> getCall(@Field("name") String json);
    }

    public interface OnSuccessListener<T extends Serializable> {
        void success(T response);
    }

    public interface OnSuccessStringListener {
        void success(String string);
    }

    public interface OnFailListener {
        void fail(Throwable t);

    }
}