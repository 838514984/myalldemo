package com.example.administrator.mytestallhere.fragment;

import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionInflater;
import android.view.View;

import com.example.administrator.mytestallhere.R;
import com.example.util.Logger;

public class FragmentJumpActivity extends AppCompatActivity implements View.OnClickListener, ListFragment.OnItemClickListener {
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    Fragment1 fragment1 = new Fragment1();
    Fragment2 fragment2 = new Fragment2();
    SharedFragment sharedFragment = new SharedFragment();
    ListFragment listFragment = new ListFragment();
    DetailShareFragment detailShareFragment = new DetailShareFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_jump);
        findViewById(R.id.btn_jump).setOnClickListener(this);
        findViewById(R.id.btn_jump_share).setOnClickListener(this);
        findViewById(R.id.btn_jump_list).setOnClickListener(this);
        listFragment.setOnItemClickListener(this);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container, listFragment)
                .addToBackStack(null)
                .commitAllowingStateLoss();

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_jump) {
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction
                    .replace(R.id.container, fragment2)
                    .addToBackStack(null)
                    .commitAllowingStateLoss();
        } else if (v.getId() == R.id.btn_jump_share) {
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, sharedFragment)
                    .addToBackStack(null)
                    .addSharedElement(fragment2.getSharedView(), "share")
                    .commitAllowingStateLoss();
        } else if (v.getId() == R.id.btn_jump_list) {
            fragmentManager.beginTransaction()
                    .replace(R.id.container, listFragment)
                    .addToBackStack(null)
                    .commitAllowingStateLoss();
        }
    }



    @Override
    public void onItemClickListener(View view, int position, ListFragment.Holder holder) {
        detailShareFragment.setSharedElementEnterTransition(TransitionInflater.from(this).inflateTransition(android.R.transition.move));
        detailShareFragment.setSharedElementReturnTransition(TransitionInflater.from(this).inflateTransition(android.R.transition.move));
        Bundle bundle = new Bundle();
        bundle.putInt("src", R.mipmap.two);
        bundle.putString("title", "this is title");
        bundle.putString("content", "this is the " + (position + 1) + " item");
        detailShareFragment.setArguments(bundle);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            fragmentManager.beginTransaction()
                    .addSharedElement(view.findViewById(R.id.img), getResources().getString(R.string.share_img))
                    .addSharedElement(view.findViewById(R.id.tv_title), getResources().getString(R.string.share_title))
                    .addSharedElement(view.findViewById(R.id.tv_content), getResources().getString(R.string.share_content))
                    .addSharedElement(view,getResources().getString(R.string.share_rootview))
                    .addToBackStack(null)
                    .replace(R.id.container, detailShareFragment)
                    .commitAllowingStateLoss();
            Logger.error("view.findViewById(R.id.img).getTransitionName(): " + view.findViewById(R.id.img).getTransitionName());
            Logger.error("view.findViewById(R.id.tv_title).getTransitionName(): " + view.findViewById(R.id.tv_title).getTransitionName());
            Logger.error("view.findViewById(R.id.tv_content).getTransitionName(): " + view.findViewById(R.id.tv_content).getTransitionName());
        }
    }
}
