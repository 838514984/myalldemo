package com.example.administrator.mytestallhere.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.transition.Fade;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.mytestallhere.R;

/**
 * Created by Administrator on 2018/4/2 0002.
 */

public class SharedFragment extends Fragment {

    public SharedFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        setEnterTransition(new Fade());
        setReturnTransition(new Fade());
        setSharedElementEnterTransition(TransitionInflater.from(getContext()).inflateTransition(android.R.transition.move));
        setSharedElementReturnTransition(TransitionInflater.from(getContext()).inflateTransition(android.R.transition.move));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_shared_element, null);
        return contentView;
    }
}
