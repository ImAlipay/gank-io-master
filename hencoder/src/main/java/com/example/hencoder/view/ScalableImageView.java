package com.example.hencoder.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.example.hencoder.Utils;

/**
 * ${DESC}
 *
 * @author 91660
 * @date 2019/3/28
 */
public class ScalableImageView extends View implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    private static final int IMAGE_WIDTH = (int) Utils.dp2px(300);
    private static final float OVER_SCALE_FRACTION = 1.5f;
    private final Bitmap mAvatar;
    private final Paint mPaint;
    private float originalOffsetX;
    private float originalOffsetY;
    private float mSmallScale;
    private float mBigScale;
    private float mCurrentScale;
    private final GestureDetectorCompat mGestureDetectorCompat;
    boolean big;
    private ObjectAnimator mScaleAnimator;
    private float offsetX;
    private float offsetY;

    public ScalableImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mAvatar = Utils.getAvatar(IMAGE_WIDTH, context.getResources());
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mGestureDetectorCompat = new GestureDetectorCompat(context, this);
        mGestureDetectorCompat.setOnDoubleTapListener(this);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        originalOffsetX = (getWidth() - mAvatar.getWidth()) / 2f;
        originalOffsetY = (getHeight() - mAvatar.getHeight()) / 2f;

        if ((float) getWidth() / mAvatar.getWidth() < (float) getHeight() / mAvatar.getHeight()) {
            mSmallScale = (float) getWidth() / mAvatar.getWidth();
            mBigScale = (float) getHeight() / mAvatar.getHeight() * OVER_SCALE_FRACTION;
        } else {
            mBigScale = (float) getWidth() / mAvatar.getWidth() * OVER_SCALE_FRACTION;
            mSmallScale = (float) getHeight() / mAvatar.getHeight();
        }
        mCurrentScale = mSmallScale;
    }

    private ObjectAnimator getScaleAnimator() {
        if (mScaleAnimator == null) {
            mScaleAnimator = ObjectAnimator.ofFloat(this, "currentScale", mSmallScale, mBigScale);
        }
        return mScaleAnimator;
    }

    public void setCurrentScale(float scale) {
        mCurrentScale = scale;
        invalidate();
    }

    public float getCurrentScale() {
        return mCurrentScale;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(offsetX, offsetY);
        canvas.scale(mCurrentScale, mCurrentScale, getWidth() / 2, getHeight() / 2);
        canvas.drawBitmap(mAvatar, originalOffsetX, originalOffsetY, mPaint);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mGestureDetectorCompat.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        // TODO: 2019/3/28 ？？？？？
        offsetX -= distanceX;
        offsetY -= distanceY;
        invalidate();
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        big = !big;
        if (big) {
            getScaleAnimator().start();
        } else {
            getScaleAnimator().reverse();
        }
        invalidate();
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }
}
