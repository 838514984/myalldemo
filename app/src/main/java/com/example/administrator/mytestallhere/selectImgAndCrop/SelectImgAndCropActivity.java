package com.example.administrator.mytestallhere.selectImgAndCrop;

import android.Manifest;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.administrator.mytestallhere.R;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class SelectImgAndCropActivity extends AppCompatActivity {
    @BindView(R.id.btn)
    Button button;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.ed)
    EditText editText;
    String outputsize;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_img_and_crop);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        RxTextView.textChanges(editText).subscribe(new Consumer<CharSequence>() {
            @Override
            public void accept(CharSequence charSequence) throws Exception {
                outputsize=charSequence.toString();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventCallBack(PhotoEvent photoEvent) {
        String path = photoEvent.filePath;
        Glide.with(this).load(path).into(img);
    }


    @OnClick(R.id.btn)
    public void start(Button btn) {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            new AlertDialog.Builder(SelectImgAndCropActivity.this).setItems(new String[]{"拍照", "从相册取"}, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            switch (which) {
                                                case 0:
                                                    PhotoActivity.startActivity(SelectImgAndCropActivity.this, PhotoActivity.PHOTO_REQUEST_TAKEPHOTO, SelectImgAndCropActivity.class.getName(),getoutputsize(outputsize));
                                                    break;
                                                case 1:
                                                    PhotoActivity.startActivity(SelectImgAndCropActivity.this, PhotoActivity.PHOTO_REQUEST_GALLERY, SelectImgAndCropActivity.class.getName(),getoutputsize(outputsize));

                                            }
                                        }
                                    }
                            ).setTitle("获取图片").create().show();
                        }
                    }
                });

    }


    private int getoutputsize(String s){
        return (s==null||s.equals(""))?0:Integer.parseInt(s);
    }

}
