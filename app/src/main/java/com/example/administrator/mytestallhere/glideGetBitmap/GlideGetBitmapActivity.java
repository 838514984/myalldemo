package com.example.administrator.mytestallhere.glideGetBitmap;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.administrator.mytestallhere.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class GlideGetBitmapActivity extends AppCompatActivity {
    @BindView(R.id.iv)
    ImageView iv;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_get_bitmap);
        ButterKnife.bind(this);
        Glide.with(this).load(R.drawable.ic_launcher).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                bitmap = Bitmap.createBitmap(resource.getIntrinsicWidth(), resource.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);
                resource.setBounds(0, 0, resource.getIntrinsicWidth(), resource.getIntrinsicHeight());
                resource.draw(canvas);
                iv.setImageBitmap(bitmap);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
