package com.example.administrator.mytestallhere.placeholderview.swipe;

import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.mytestallhere.R;
import com.mindorks.placeholderview.SwipeDirection;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.mindorks.placeholderview.annotations.swipe.SwipeCancelState;
import com.mindorks.placeholderview.annotations.swipe.SwipeInDirectional;
import com.mindorks.placeholderview.annotations.swipe.SwipeOutDirectional;
import com.mindorks.placeholderview.annotations.swipe.SwipeTouch;
import com.mindorks.placeholderview.annotations.swipe.SwipingDirection;

/**
 * Created by Administrator on 2018/4/23 0023.
 */
@Layout(R.layout.swipe_item)
public class SwipeItem {
    private static int count;
    @View(R.id.iv)
    ImageView imageView;
    @View(R.id.tv_title)
    TextView mTvTitle;
    @View(R.id.tv_msg)
    TextView mTvMsg;

    int drawable;
    String title;
    String msg;

    public SwipeItem(int drawable, String title, String msg) {
        this.drawable = drawable;
        this.title = title;
        this.msg = msg;
    }

    @Resolve
    public void onResolve() {
        mTvTitle.setText(title + " " + count++);
        imageView.setImageResource(drawable);
        mTvMsg.setText(msg);
    }

    @SwipeOutDirectional
    public void onSwipeOutDirectional(SwipeDirection direction) {
        Log.e("2332", "SwipeOutDirectional " + direction.name());
    }

    @SwipeCancelState
    public void onSwipeCancelState() {
        Log.e("2332", "onSwipeCancelState");
    }

    @SwipeInDirectional
    public void onSwipeInDirectional(SwipeDirection direction) {
        Log.e("2332", "SwipeInDirectional " + direction.name());
    }

    @SwipingDirection
    public void onSwipingDirection(SwipeDirection direction) {
        Log.e("2332", "SwipingDirection " + direction.name());
    }

    @SwipeTouch
    public void onSwipeTouch(float xStart, float yStart, float xCurrent, float yCurrent) {
        Log.e("2332", "onSwipeTouch "
                + " xStart : " + xStart
                + " yStart : " + yStart
                + " xCurrent : " + xCurrent
                + " yCurrent : " + yCurrent
                + " distance : "
                + Math.sqrt(Math.pow(xCurrent - xStart, 2) + (Math.pow(yCurrent - yStart, 2)))
        );
    }

}
