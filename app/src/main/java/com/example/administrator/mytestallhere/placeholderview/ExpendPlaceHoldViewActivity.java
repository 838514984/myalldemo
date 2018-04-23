package com.example.administrator.mytestallhere.placeholderview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.mytestallhere.MyApplication;
import com.example.administrator.mytestallhere.R;
import com.example.administrator.mytestallhere.bean.placeholderview.Child;
import com.example.administrator.mytestallhere.bean.placeholderview.Parent;
import com.example.administrator.mytestallhere.dagger.compent.DaggerActivityCompent;
import com.example.administrator.mytestallhere.dagger.module.ActivityModule;
import com.example.administrator.mytestallhere.dataProvider.ExpendPlaceViewHolderDataProvider;
import com.example.administrator.mytestallhere.placeholderview.expend.ChildView;
import com.example.administrator.mytestallhere.placeholderview.expend.ParentView;
import com.mindorks.placeholderview.ExpandablePlaceHolderView;
//import com.mindorks.placeholderview.ExpandablePlaceHolderView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExpendPlaceHoldViewActivity extends AppCompatActivity implements ParentView.OnItemClick {
    @BindView(R.id.expend_placeholder)
    ExpandablePlaceHolderView expandablePlaceHolderView;
    @Inject
    ExpendPlaceViewHolderDataProvider mProvider;
    ArrayList<Parent> mParent;
    ArrayList<Child> mChildren;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expend_place_hold_view);
        ButterKnife.bind(this);
        DaggerActivityCompent.builder().activityModule(new ActivityModule(this))
                .applicationCompent(MyApplication.INSTANCE.getApplicationCompent())
                .build()
                .inject(this);

        mProvider = MyApplication.INSTANCE.getApplicationCompent().provideExpendPlaceViewHolderDataProvider();
        mParent = mProvider.getExpendPlaceParent();


        for (Parent parent : mParent) {
            ParentView parentView = new ParentView(parent.name);
            parentView.setOnItemClick(this);
            expandablePlaceHolderView.addView(parentView);


        }


    }


    @Override
    public void click(int position) {
        mChildren = mProvider.getChild();
        for (Child child : mChildren) {
            expandablePlaceHolderView.addChildView(position
                    , new ChildView(expandablePlaceHolderView, child.Image, child.msg, child.title));
        }
    }
}
