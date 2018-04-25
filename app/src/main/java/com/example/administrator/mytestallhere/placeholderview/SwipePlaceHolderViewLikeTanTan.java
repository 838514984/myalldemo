package com.example.administrator.mytestallhere.placeholderview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.administrator.mytestallhere.MyApplication;
import com.example.administrator.mytestallhere.R;
import com.example.administrator.mytestallhere.bean.placeholderview.Child;
import com.example.administrator.mytestallhere.dataProvider.ExpendPlaceViewHolderDataProvider;
import com.example.administrator.mytestallhere.placeholderview.swipe.SwipeItem;
import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipeDirectionalView;
import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.mindorks.placeholderview.listeners.ItemRemovedListener;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SwipePlaceHolderViewLikeTanTan extends AppCompatActivity {
    @BindView(R.id.swipe_holder_view)
    SwipeDirectionalView swipePlaceHolderView;
    @BindView(R.id.btn_accept)
    Button mBtnAccept;
    @BindView(R.id.btn_reject)
    Button mBtnReject;
    @BindView(R.id.btn_undo)
    Button mBtnUndo;

    @Inject
    ExpendPlaceViewHolderDataProvider provider;

    ArrayList<Child> mChildren;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_place_holder_view_like_tan_tan);
        ButterKnife.bind(this);
        swipePlaceHolderView.addItemRemoveListener(new ItemRemovedListener() {
            @Override
            public void onItemRemoved(int count) {
                //Log.e("2332", "---------onItemRemoved count: " + count + " ----------");
                if (count == 0) {
                    addChild();
                }
            }
        });
        swipePlaceHolderView.getBuilder()
                .setSwipeVerticalThreshold(100)
                .setSwipeHorizontalThreshold(100)
                .setDisplayViewCount(3)
                .setSwipeDecor(new SwipeDecor()
                        .setPaddingTop(20)
                        .setRelativeScale(0.01f)/*这个挺重要*/
                        .setSwipeInMsgLayoutId(R.layout.swipe_in_msg)
                        .setSwipeOutMsgLayoutId(R.layout.swipe_out_msg))
                .setIsUndoEnabled(true)
        ;
        //swipePlaceHolderView.unlockViews();
        //swipePlaceHolderView.lockViews();
        provider = MyApplication.INSTANCE.getApplicationCompent().provideExpendPlaceViewHolderDataProvider();

        addChild();

    }

    private void addChild() {
        for (Child child : getChildren()) {
            swipePlaceHolderView.addView(new SwipeItem(child.Image, child.title, child.msg));
        }
    }

    private ArrayList<Child> getChildren() {
        mChildren = provider.getChild();
        return mChildren;
    }


    @OnClick(R.id.btn_accept)
    public void onAcceptClick() {
        swipePlaceHolderView.doSwipe(true);
    }


    @OnClick(R.id.btn_reject)
    public void onRejuctClick() {
        swipePlaceHolderView.doSwipe(false);
    }

    @OnClick(R.id.btn_undo)
    public void onUnDoClick(){
        swipePlaceHolderView.undoLastSwipe();
    }
}
