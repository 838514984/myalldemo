package com.example.administrator.mytestallhere.butterknife;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.mytestallhere.R;
import com.example.administrator.mytestallhere.rxJava.TestRxJavaActivity;
import com.example.administrator.mytestallhere.statusutil.StatusBarUtil;

import javax.crypto.spec.RC2ParameterSpec;

import butterknife.*;

/**
 * Created by Administrator on 2017/7/31 0031.
 */

public class TestFragment extends Fragment {
   @BindView(R.id.iv_back) ImageView ivBack;
   @BindView(R.id.tv_title) TextView tvTitle;
   @BindView(R.id.tv_right) TextView tvRight;
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view==null){
            view=inflater.inflate(R.layout.layout_my_topbar,container,false);
            butterknife.ButterKnife.bind(this,view);
            StatusBarUtil.setPaddingSmart(getContext(),view);
        }
        return view;
    }
    @OnClick(R.id.iv_back)
    public void onBackImgEvent(ImageView imageView){
        TestRxJavaActivity.LogE("---------back img has been clicked-----------");
    }
    @OnClick(R.id.tv_title)
    public void titleClick(TextView textView){
        textView.setText("title has been clicked");
    }

}
