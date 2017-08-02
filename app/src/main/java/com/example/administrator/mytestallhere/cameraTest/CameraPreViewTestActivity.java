package com.example.administrator.mytestallhere.cameraTest;

import android.Manifest;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.hardware.Camera;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.administrator.mytestallhere.R;
import com.example.administrator.mytestallhere.rxJava.TestRxJavaActivity;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class CameraPreViewTestActivity extends AppCompatActivity {
    Camera camera;
    SurfaceHolder surfaceHolder;
    @BindView(R.id.surfaceview)
    SurfaceView surfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_pre_view_test);
        ButterKnife.bind(this);

    }


    @Override
    protected void onResume() {
        super.onResume();
        Observable.timer(1, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                RxPermissions rxPermissions = new RxPermissions(CameraPreViewTestActivity.this);
                rxPermissions.request(Manifest.permission.CAMERA).subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Boolean value) {
                        TestRxJavaActivity.LogE("-----------------------rxPermission: " + value);
                        surfaceHolder = surfaceView.getHolder();
                        surfaceHolder.addCallback(new MySuffaceViewCallBack());
                        camera = Camera.open();
                        camera.setDisplayOrientation(90);
                        camera.setPreviewCallback(new MyPreViewCallBack());
                        try {
                            camera.setPreviewDisplay(surfaceHolder);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        camera.startPreview();

                    }


                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        camera.setPreviewCallback(null);
        try {
            camera.setPreviewDisplay(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //camera.unlock();
        camera.release();
        camera=null;

    }
}




class MyPreViewCallBack implements Camera.PreviewCallback {
    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {
        StringBuffer sb = new StringBuffer();
        for (byte b : data) {
            sb.append(b);
        }
        TestRxJavaActivity.LogE(sb.toString());
    }
}

class MySuffaceViewCallBack implements SurfaceHolder.Callback {
    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}


