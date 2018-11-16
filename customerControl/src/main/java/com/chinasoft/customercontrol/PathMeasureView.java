package com.chinasoft.customercontrol;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * ${DESC}
 *
 * @author 91660
 * @date 2018/11/12
 */
public class PathMeasureView extends View {

    private Paint mPaint;
    private Path mPath;
    private Path mDst;
    private Path mDstPath;
    private Path mCirclePath;
    private PathMeasure mPathMeasure;
    private Float mValue;
    private float mCentX = 100;
    private float mCentY = 100;
    private float mRadius = 50;

    public PathMeasureView(Context context) {
        super(context);
        init();
    }

    public PathMeasureView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PathMeasureView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(8);

//        mPath = new Path();
////        mPath.moveTo(0, 0);
////        mPath.lineTo(0, 100);
////        mPath.lineTo(100, 100);
////        mPath.lineTo(100, 0);
//        mPath.addRect(-50, -50, 50, 50, Path.Direction.CW);
//        mDst = new Path();
//        mDst.lineTo(10, 100);
        mDstPath = new Path();
        mCirclePath = new Path();
//        mCirclePath.addCircle(100, 100, 50, Path.Direction.CW);

        mCirclePath.addCircle(mCentX, mCentY, mRadius, Path.Direction.CW);
        mCirclePath.moveTo(mCentX - mRadius / 2, mCentY);
        mCirclePath.lineTo(mCentX, mCentY + mRadius / 2);
        mCirclePath.lineTo(mCentX + mRadius / 2, mCentY - mRadius / 3);

        mPathMeasure = new PathMeasure(mCirclePath, false);

        ValueAnimator animator = ValueAnimator.ofFloat(0, 2);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mValue = (Float) animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.setDuration(4000);
        animator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.save();
//        canvas.translate(100, 100);
////        canvas.drawPath(mPath, mPaint);
//        PathMeasure measure = new PathMeasure(mPath, false);
//        measure.getSegment(0, 150, mDst, false);
//        canvas.drawPath(mDst, mPaint);
//        canvas.restore();
        canvas.drawColor(Color.WHITE);
//        float length = mPathMeasure.getLength();
//        float stop = length * mValue;
//        float start = (float) (stop - (0.5 - Math.abs(mValue - 0.5)) * length);
//        mDstPath.reset();
//        mPathMeasure.getSegment(start, stop, mDstPath, true);
//        canvas.drawPath(mDstPath, mPaint);

        if (mValue < 1) {
            float stop = mPathMeasure.getLength() * mValue;
            mPathMeasure.getSegment(0, stop, mDstPath, true);
        } else if (mValue == 1) {
            mPathMeasure.getSegment(0, mPathMeasure.getLength() * mValue, mDstPath, true);
            mPathMeasure.nextContour();
        } else {
            float stop = mPathMeasure.getLength() * (mValue - 1);
            mPathMeasure.getSegment(0, stop, mDstPath, true);
        }
        canvas.drawPath(mDstPath, mPaint);
    }
}