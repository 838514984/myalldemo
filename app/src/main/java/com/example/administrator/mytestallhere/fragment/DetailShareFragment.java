package com.example.administrator.mytestallhere.fragment;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.graphics.Palette;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.administrator.mytestallhere.R;
import com.example.administrator.mytestallhere.glide.transform.BlurTransformation;
import com.example.util.Logger;

/**
 * Created by Administrator on 2018/4/2 0002.
 */

public class DetailShareFragment extends Fragment {
    View mContentView;
    ImageView mIv;
    TextView mTvTitle;
    TextView mTvContent;
    String mTitle;
    String mContent;
    ImageView mPalette;
    int src;
    Bitmap mBitmap;
    ValueAnimator mBulrAnimator;
    ValueAnimator mPaletteAnimator;
    ColorDrawable mPaletteColorDrawable;
    Drawable mBlurDrawable;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle argument = getArguments();
        src = argument.getInt("src");
        mTitle = argument.getString("title");
        mContent = argument.getString("content");
        setSharedElementEnterTransition(TransitionInflater.from(getContext()).inflateTransition(android.R.transition.move));
        setSharedElementReturnTransition(TransitionInflater.from(getContext()).inflateTransition(android.R.transition.move));
        //setEnterTransition(new Fade());
        //setExitTransition(new Fade());

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //if (mContentView == null) {
        mContentView = inflater.inflate(R.layout.fragment_detail, null);
        mIv = (ImageView) mContentView.findViewById(R.id.iv);
        mTvTitle = (TextView) mContentView.findViewById(R.id.tv_title);
        mTvContent = (TextView) mContentView.findViewById(R.id.tv_content);
        mIv.setImageResource(src);
        mTvContent.setText(mContent);
        mTvTitle.setText(mTitle);
        mPalette = (ImageView) mContentView.findViewById(R.id.rl_paltte);
        setPaletteColor();
        //setBlurDrawable();
        setTransitionName();
        return mContentView;
    }

    private void setBlurDrawable(){
        Glide.with(getActivity()).load(src).apply(RequestOptions.bitmapTransform(new BlurTransformation(15))).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {

                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                Logger.error("onresourceReady preload ");
                mBlurDrawable = resource;
                setBulrImageAlpha();
                return false;
            }
        }).preload();
    }
    private void setPaletteColor(){
        Glide.with(getActivity()).load(src).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                if (resource instanceof BitmapDrawable) {
                    Logger.error("is bitmap drawable");
                    mBitmap = ((BitmapDrawable) resource).getBitmap();
                } else {
                    Logger.error("is not a bitmap drawable");
                    mBitmap = Bitmap.createBitmap(resource.getIntrinsicWidth(), resource.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
                    Canvas canvas = new Canvas(mBitmap);
                    resource.setBounds(0, 0, resource.getIntrinsicWidth(), resource.getIntrinsicHeight());
                    resource.draw(canvas);
                }
                setPalette();
                setBlurDrawable();
                return false;
            }
        }).into(mIv);
    }
    private void setPalette() {
        Palette.Builder builder = Palette.from(mBitmap);
        builder.generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch swatch = palette.getVibrantSwatch();
                //mPalette.setBackgroundColor(swatch.getRgb());
                if (swatch != null){
                    mPaletteColorDrawable = new ColorDrawable(swatch.getRgb());
                    mPaletteColorDrawable.setAlpha(0);
                    setPaletteAlpha();
                }
            }
        });
    }

    public void setBulrImageAlpha() {
        if (mBulrAnimator != null && mBulrAnimator.isRunning()){
            mBulrAnimator.cancel();
        }
        if (mPaletteAnimator != null && mPaletteAnimator.isRunning()){
            Logger.error("palette animator is running...");
            mPaletteAnimator.cancel();
        }
        mPalette.setImageDrawable(mBlurDrawable);
        mBulrAnimator = ValueAnimator.ofInt(0, 255);
        mBulrAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int current = (int) animation.getAnimatedValue();
                mPalette.setImageAlpha(current);
            }
        });
        mBulrAnimator.setDuration(2500);
        mBulrAnimator.start();
    }


    private void setPaletteAlpha(){
        if (mPaletteAnimator != null && mPaletteAnimator.isRunning()){
            mPaletteAnimator.cancel();
        }
        if (mBulrAnimator != null && mBulrAnimator.isRunning()){
            Logger.error("bulr animator is running , return ...");
            return;
        }
        mPalette.setImageDrawable(mPaletteColorDrawable);
        mPaletteAnimator = ValueAnimator.ofInt(0,255);
        mPaletteAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mPaletteColorDrawable.setAlpha((Integer) animation.getAnimatedValue());
            }
        });
        mPaletteAnimator.setDuration(2500).start();
    }

    private void setTransitionName() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mTvContent.setTransitionName(getResources().getString(R.string.share_content));
            mTvTitle.setTransitionName(getResources().getString(R.string.share_title));
            mIv.setTransitionName(getResources().getString(R.string.share_img));
            mContentView.setTransitionName(getResources().getString(R.string.share_rootview));
        }

    }
}
