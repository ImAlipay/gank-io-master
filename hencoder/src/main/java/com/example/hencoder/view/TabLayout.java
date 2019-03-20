package com.example.hencoder.view;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * ${DESC}
 *
 * @author 91660
 * @date 2019/3/18
 */
public class TabLayout extends ViewGroup {

    private static final String TAG = OneMeasureView.class.getSimpleName();

    List<Rect> childrenBounds = new ArrayList<>();

    public TabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthUsed = 0;
        int heightUsed = 0;
        int lineWidthUesd = 0;
        int lineMaxHeight = 0;
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);

        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, heightUsed);
            if (widthSpecMode != MeasureSpec.UNSPECIFIED && lineWidthUesd + child.getMeasuredWidth() > widthSpecSize) {
                lineWidthUesd = 0;
                heightUsed += lineMaxHeight;
                lineMaxHeight = 0;
                measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, heightUsed);
            }
            Rect childrenBound;
            if (childrenBounds.size() <= i) {
                childrenBound = new Rect();
                childrenBounds.add(childrenBound);
            } else {
                childrenBound = childrenBounds.get(i);
            }
            childrenBound.set(lineWidthUesd, heightUsed, lineWidthUesd + child.getMeasuredWidth(), heightUsed + child.getMeasuredHeight());
            lineWidthUesd += child.getMeasuredWidth();
            widthUsed = Math.max(widthUsed, lineWidthUesd);
            lineMaxHeight = Math.max(lineMaxHeight, child.getMeasuredHeight());
        }

        int width = widthUsed;
        int height = heightUsed + lineMaxHeight;
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            Rect rect = childrenBounds.get(i);
            child.layout(rect.left, rect.top, rect.right, rect.bottom);
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet p) {
        return new MarginLayoutParams(getContext(), p);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, "--------TabLayout+onTouchEvent----------");
        return super.onTouchEvent(event);
    }

    @Override
    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        super.requestDisallowInterceptTouchEvent(disallowIntercept);
    }
}