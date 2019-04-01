package com.example.hencoder.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.hencoder.Utils;

/**
 * ${DESC}
 *
 * @author 91660
 * @date 2019/4/1
 */
public class MultiTouchView01 extends View {

    private final Paint mPaint;
    private final Bitmap mAvatar;
    private float offsetX;
    private float offsetY;
    private int mTrackingPointerId;

    public MultiTouchView01(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mAvatar = Utils.getAvatar((int) Utils.dp2px(300), getResources());
    }

    float originalX, originalY;
    float lastX, lastY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int actionMasked = event.getActionMasked();
        switch (actionMasked) {
            case MotionEvent.ACTION_DOWN:
                mTrackingPointerId = event.getPointerId(0);
                originalX = event.getX();
                originalY = event.getY();
                lastX = offsetX;
                lastY = offsetY;
                break;
            case MotionEvent.ACTION_MOVE:
                int pointerIndex = event.findPointerIndex(mTrackingPointerId);
                offsetX = lastX + event.getX(pointerIndex) - originalX;
                offsetY = lastY + event.getY(pointerIndex) - originalY;
                invalidate();
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                int actionIndex = event.getActionIndex();
                mTrackingPointerId = event.getPointerId(actionIndex);
                originalX = event.getX(actionIndex);
                originalY = event.getY(actionIndex);
                lastX = offsetX;
                lastY = offsetY;
                break;
            case MotionEvent.ACTION_POINTER_UP:
                actionIndex = event.getActionIndex();
                int pointerId = event.getPointerId(actionIndex);
                if (pointerId == mTrackingPointerId) {
                    int newIndex;
                    if (actionIndex == event.getPointerCount() - 1) {
                        newIndex = event.getPointerCount() - 2;
                    } else {
                        newIndex = event.getPointerCount() - 1;
                    }
                    mTrackingPointerId = event.getPointerId(newIndex);
                    lastX = offsetX;
                    lastY = offsetY;
                    originalX = event.getX(actionIndex);
                    originalY = event.getY(actionIndex);
                }
                break;
            default:
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(mAvatar, offsetX, offsetY, mPaint);
    }
}
