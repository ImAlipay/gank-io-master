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
 * @date 2019/4/2
 */
public class MultiTouchView02 extends View {

    private final Bitmap mAvatar;
    private float downX;
    private float downY;
    private float offsetX;
    private float offsetY;
    private float originalX;
    private float originalY;
    private final Paint mPaint;

    public MultiTouchView02(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mAvatar = Utils.getAvatar((int) Utils.dp2px(300), getResources());
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int pointerCount = event.getPointerCount();
        boolean isPointerUp = event.getActionMasked() == MotionEvent.ACTION_POINTER_UP;
        float sumX = 0;
        float sumY = 0;
        for (int i = 0; i < pointerCount; i++) {
            if (!(isPointerUp && i == event.getActionIndex())) {
                sumX += event.getX(i);
                sumY += event.getY(i);
            }
        }
        if (isPointerUp) {
            pointerCount -= 1;
        }
        float focusX = sumX / pointerCount;
        float focusY = sumY / pointerCount;
        int actionMasked = event.getActionMasked();
        switch (actionMasked) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
            case MotionEvent.ACTION_POINTER_UP:
                downX = focusX;
                downY = focusY;
                originalX = offsetX;
                originalY = offsetY;
                break;
            case MotionEvent.ACTION_MOVE:
                offsetX = originalX + focusX - downX;
                offsetY = originalY + focusY - downY;
                invalidate();
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
