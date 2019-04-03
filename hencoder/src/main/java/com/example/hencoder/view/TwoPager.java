package com.example.hencoder.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.OverScroller;

/**
 * ${DESC}
 *
 * @author 91660
 * @date 2019/4/3
 */
public class TwoPager extends ViewGroup {

    private float mDownX;
    private float mDownY;
    private int mOffsetX;
    private float mDownScrollX;
    private final OverScroller mScroller;
    private final ViewConfiguration mConfiguration;
    private final int mMaximumFlingVelocity;
    private final int mMinimumFlingVelocity;
    private final VelocityTracker mVelocityTracker;
    private boolean mScrolling;


    public TwoPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScroller = new OverScroller(context);
        mConfiguration = ViewConfiguration.get(context);
        mMaximumFlingVelocity = mConfiguration.getScaledMaximumFlingVelocity();
        mMinimumFlingVelocity = mConfiguration.getScaledMinimumFlingVelocity();
        mVelocityTracker = VelocityTracker.obtain();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            child.layout(i * getWidth(), 0, getWidth() * (i + 1), getHeight());
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getActionMasked() == MotionEvent.ACTION_DOWN) {
            mVelocityTracker.clear();
        }
        mVelocityTracker.addMovement(ev);

        boolean result = false;
        switch (ev.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                mScrolling = false;
                mDownX = ev.getX();
                mDownY = ev.getY();
                mDownScrollX = getScrollX();
                break;
            case MotionEvent.ACTION_MOVE:
                float dx = mDownX - ev.getX();
                if (!mScrolling) {
                    if (Math.abs(dx) > mConfiguration.getScaledPagingTouchSlop()) {
                        mScrolling = true;
                        getParent().requestDisallowInterceptTouchEvent(true);
                        result = true;
                    }
                }
                break;
            default:
        }
        return result;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
            mVelocityTracker.clear();
        }
        mVelocityTracker.addMovement(event);

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = event.getX();
                mDownY = event.getY();
                mDownScrollX = getScrollX();
                break;
            case MotionEvent.ACTION_MOVE:
                float dx = mDownScrollX - event.getX() + mDownX;
                if (dx > getWidth()) {
                    dx = getWidth();
                } else if (dx < 0) {
                    dx = 0;
                }
                scrollTo((int) dx, 0);
                break;
            case MotionEvent.ACTION_UP:
                mVelocityTracker.computeCurrentVelocity(1000, mMaximumFlingVelocity);
                float xVelocity = mVelocityTracker.getXVelocity();
                int scrollX = getScrollX();
                int targetPage;
                if (Math.abs(xVelocity) < mMinimumFlingVelocity) {
                    targetPage = scrollX > getWidth() / 2 ? 1 : 0;
                } else {
                    targetPage = xVelocity < 0 ? 1 : 0;
                }
                int scrollDistance = targetPage == 1 ? (getWidth() - scrollX) : -scrollX;
                mScroller.startScroll(getScrollX(), 0, scrollDistance, 0);
                postInvalidateOnAnimation();
                break;
            default:
        }
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidateOnAnimation();
        }
    }
}
