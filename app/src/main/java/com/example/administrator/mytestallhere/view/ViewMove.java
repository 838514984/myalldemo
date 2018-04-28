package com.example.administrator.mytestallhere.view;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

import com.example.util.Logger;

/**
 * Created by Administrator on 2018/2/25 0025.
 */

public class ViewMove extends View {
    int mX;
    int mY;
    Scroller mScroller;

    public ViewMove(Context context) {
        super(context);
        init();
    }

    public ViewMove(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ViewMove(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mScroller = new Scroller(getContext());
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Logger.error("onTouch");

                return false;
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Logger.error("onlayout changed: " + changed + " left:" + left + " top:" + top + " right:" + right + " bottom:" + bottom);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Logger.error("onTouchEvent");
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_MOVE:
                int dx = (int) (x - mX);
                int dy = (int) (y - mY);
                int transX = (int) getTranslationX();
                int transY = (int) getTranslationY();
                Logger.error("dx: " + dx + " dy: " + dy + " transX: " + transX + " transY: " + transY);
                //this.setTranslationX(transX + dx);
                //this.setTranslationY(transY + dy);
                ((View)(getParent())).scrollBy(-dx,-dy);
                //((View) (getParent())).scrollTo(getScrollX() - x, getScrollY() - y);//bug
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        mX = x;
        mY = y;
        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }

    public void smothScroll() {
        mScroller.startScroll(0, 0, -200, -600, 3000);
        invalidate();
    }

    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            ((View) getParent()).scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
