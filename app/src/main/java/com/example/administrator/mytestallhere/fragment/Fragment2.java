package com.example.administrator.mytestallhere.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.mytestallhere.R;

/**
 * Created by Administrator on 2018/3/30 0030.
 */

public class Fragment2 extends Fragment {
    View contentView;
    TextView tvIndex;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (contentView == null){
            contentView =inflater.inflate(R.layout.fragment_index,null);
            tvIndex = (TextView) contentView.findViewById(R.id.tv_index);
            tvIndex.setText("2");
        }
        return contentView;
    }

    public View getSharedView(){
        return tvIndex;
    }
}
