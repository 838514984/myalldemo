package com.example.administrator.mytestallhere.http.retrofit;

import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.mytestallhere.R;
import com.example.administrator.mytestallhere.http.intercept.LogIntercept;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

public class RetrofitRxjavaActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String BaseUrl = "http://192.168.2.42:8080/MyHttp/servlet/MyHello/";
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_rxjava);
        setOnclickItems(R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5);
        OkHttpClient.Builder clientbuild = new OkHttpClient.Builder();
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //自定义拦截器
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        LogIntercept interceptor = new LogIntercept();
        clientbuild.addInterceptor(interceptor);

        retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(clientbuild.build())
                .build();


    }

    private void postUseField() {
        MyRequestTest myRequestTest = retrofit.create(MyRequestTest.class);
        myRequestTest.getRequest("anything is not good")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<List<People>, Publisher<People>>() {
                    @Override
                    public Publisher<People> apply(@NonNull List<People> peoples) throws Exception {
                        return Flowable.fromIterable(peoples);
                    }
                }).subscribe(new Consumer<People>() {
            @Override
            public void accept(@NonNull People people) throws Exception {
                Log.e("xxx", "people 's name: " + people.name + " people's age: " + people.age);
            }
        });

    }

    private void postUseQuery() {
//        retrofit.create(MyRequestTest.class).getRequest(new String("一刀9999"))
//                .subscribeOn(Schedulers.io())
//                .flatMap(new Function<List<People>, Flowable<People>>() {
//                    @Override
//                    public Flowable<People> apply(@NonNull List<People> peoples) throws Exception {
//                        return retrofit.create(MyInnerRequest.class).getRequest("what the fuck is query");
//                    }
//                }).subscribe(new Consumer<People>() {
//            @Override
//            public void accept(@NonNull People people) throws Exception {
//                Log.e("xxx", "people's name: " + people.name + " people's age: " + people.age + " people 's sex: " + people.sex);
//            }
//        });

        retrofit.create(MyInnerRequest.class).getRequest("what the fuck is the query")
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<People>() {
                    @Override
                    public void accept(@NonNull People people) throws Exception {

                    }
                });

    }

    private void uploadUseSinglePart() {
        String path = Environment.getExternalStorageDirectory().toString() + "/123.png";
        File file = new File(path);
        RequestBody responseBody = RequestBody.create(MediaType.parse("image/*"), file);
        retrofit.create(UploadImgApi.class)
                .uploadPic(responseBody)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            Toast.makeText(RetrofitRxjavaActivity.this, "网络访问成功 ,return msg: " + responseBody.string(), 0).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    private void uploadUsePartMap() {
        String path = Environment.getExternalStorageDirectory().toString() + "/123.png";
        File file = new File(path);
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
        Map<String, RequestBody> map = new HashMap<>();
        map.put("this is the first pic", requestBody);
        map.put("this is the seconed pic", requestBody);
        map.put("this is the third pic", requestBody);

        retrofit.create(UploadImgApi.class).uploadUseMap(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(@NonNull ResponseBody responseBody) throws Exception {
                        Toast.makeText(RetrofitRxjavaActivity.this,responseBody.string(),0).show();
                    }
                });
    }

    public interface MyRequestTest {
        @FormUrlEncoded
        @POST("http://192.168.2.42:8080/MyHttp/servlet/MyHello")
        Flowable<List<People>> getRequest(@Field("param") String s);
    }

    public interface MyInnerRequest {
        @Headers({"xixixi:lololo", "hahah:lalal"})
        @POST("http://192.168.2.42:8080/MyHttp/servlet/httptest")
        Flowable<People> getRequest(@Query("haha") String s);
    }

    public interface UploadImgApi {
        @Multipart
        @POST("http://192.168.2.42:8080/MyHttp/servlet/MyHello")
        Flowable<ResponseBody> uploadPic(@Part(" the fucking part") RequestBody requestBody);

        @Multipart
        @POST("http://192.168.2.42:8080/MyHttp/servlet/MyHello")
        Flowable<ResponseBody> uploadPicTwo(@Part("the fitst pic") RequestBody requestBody, @Part("thie seconed pic") RequestBody requestbody2);

        @Multipart
        @POST("http://192.168.2.42:8080/MyHttp/servlet/MyHello")
        Flowable<ResponseBody> uploadUseMap(@PartMap Map<String, RequestBody> map);
    }


    private void setOnclickItems(int... items) {
        for (int i : items) {
            findViewById(i).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                postUseField();
                break;
            case R.id.btn2:
                postUseQuery();
                break;
            case R.id.btn3:
                uploadUseSinglePart();
                break;
            case R.id.btn4:
                break;
            case R.id.btn5:
                uploadUsePartMap();
                break;
        }


    }


    public static class People {
        public People(String name,int age){
            this.name=name;
            this.age=age;
        }

        public int age;
        public String name;
        public String sex;

    }
}