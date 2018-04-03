package com.example.administrator.mytestallhere.fragment;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
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

import com.example.administrator.mytestallhere.R;

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
    View mPalette;
    int src;

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
        mPalette = mContentView.findViewById(R.id.rl_paltte);
        setPalette();
        setTransitionName();
        return mContentView;
    }

    private void setPalette() {
        BitmapDrawable drawable = (BitmapDrawable) mIv.getDrawable();
//        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(bitmap);
//        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
//        drawable.draw(canvas);
        Palette.Builder builder=Palette.from(drawable.getBitmap());
        builder.generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch swatch = palette.getMutedSwatch();
                mPalette.setBackgroundColor(swatch.getRgb());
                backGroundFade();
            }
        });
    }

    public void backGroundFade(){
        mPalette.setAlpha(0);
        mPalette.animate().alpha(1).setDuration(3000).start();
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
