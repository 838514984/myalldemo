package com.example.administrator.mytestallhere.placeholderview.expend;

import android.widget.TextView;

import com.example.administrator.mytestallhere.R;
import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.mindorks.placeholderview.annotations.expand.Collapse;
import com.mindorks.placeholderview.annotations.expand.Expand;
import com.mindorks.placeholderview.annotations.expand.Parent;
import com.mindorks.placeholderview.annotations.expand.ParentPosition;

/**
 * Created by Administrator on 2018/4/20 0020.
 */
@Parent
@Layout(R.layout.placeholdview_expend_parent)
public class ParentView {
    @View(R.id.title)
    TextView textView;
    @ParentPosition
    int mParentPosition;

    String mTitle;
    public ParentView(String title){
        this.mTitle = title;
    }

    @Resolve
    public void resolve(){
        textView.setText(mTitle + "off");
    }

    @Expand
    public void onExpand() {
        textView.setText(mTitle + "on");
        click();
    }

    @Collapse
    public void onCollapse() {
        textView.setText(mTitle + "off");
        //click();
    }


    public void click(){
        if (onItemClick != null){
            onItemClick.click(mParentPosition);
        }
    }

    OnItemClick onItemClick;

    public void setOnItemClick(OnItemClick onItemClick){
        this.onItemClick = onItemClick;
    }

    public interface OnItemClick{
        void click(int position);
    }
}
