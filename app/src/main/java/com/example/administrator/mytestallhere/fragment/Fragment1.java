package com.example.administrator.mytestallhere.fragment;

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

public class Fragment1 extends Fragment {
    View content;
    TextView tvIndex;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        content =inflater.inflate(R.layout.fragment_index,null);
        tvIndex = (TextView) content.findViewById(R.id.tv_index);
        tvIndex.setText("1");
        return content;
    }

    public View getSharedView(){
        return tvIndex;
    }
}
