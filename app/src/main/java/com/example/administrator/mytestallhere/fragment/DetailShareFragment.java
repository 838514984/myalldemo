package com.example.administrator.mytestallhere.fragment;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.transition.Fade;
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
    String title;
    String content;
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
        title = argument.getString("title");
        content = argument.getString("content");
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
        mTvContent.setText(content);
        mTvTitle.setText(title);
        setTransitionName();
        return mContentView;
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
