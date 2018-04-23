package com.example.administrator.mytestallhere.placeholderview.expend;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.mytestallhere.R;
import com.mindorks.placeholderview.ExpandablePlaceHolderView;
import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.mindorks.placeholderview.annotations.expand.Collapse;
import com.mindorks.placeholderview.annotations.expand.Expand;
import com.mindorks.placeholderview.annotations.expand.ParentPosition;

/**
 * Created by Administrator on 2018/4/20 0020.
 */
@NonReusable
@Layout(R.layout.placeholdview_expend_child)
public class ChildView {
    ExpandablePlaceHolderView expandablePlaceHolderView;
    @View(R.id.title)
    TextView title;
    @View(R.id.iv)
    ImageView icon;
    @View(R.id.msg)
    TextView mMsg;
    @ParentPosition
    int mParentPosition;

    String mTitle;
    int drawable;
    String msg;
    public ChildView(ExpandablePlaceHolderView expandablePlaceHolderView,int drawable ,String msg,String title){
        this.mTitle = title;
        this.drawable = drawable;
        this.msg = msg;
        this.expandablePlaceHolderView = expandablePlaceHolderView;
    }

    @Resolve
    public void resolve(){
        title.setText(mTitle );
        icon.setImageResource(drawable);
        mMsg.setText(msg);
    }

   @Click(R.id.main)
    public void onClick(){
       expandablePlaceHolderView.removeView(this);
   }
}
