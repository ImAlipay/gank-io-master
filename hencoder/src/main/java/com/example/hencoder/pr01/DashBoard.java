package com.example.hencoder.pr01;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.hencoder.R;
import com.example.hencoder.Utils;

/**
 * ${DESC}
 *
 * @author 91660
 * @date 2019/3/12
 */
public class DashBoard extends View {

    private Paint mPaint;
    private Path mPath;
    private static final float RADIUS = Utils.dp2px(100);
    private static final float LENGTH = Utils.dp2px(50);
    private static final float ANGLE = 120;
    private RectF mRectF;
    private Path mDash;
    private PathDashPathEffect mEffect;
    private PathMeasure mPathMeasure;

    public DashBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(Utils.dp2px(2));
        mPath = new Path();
        mDash = new Path();
        mDash.addRect(0, 0, Utils.dp2px(2), Utils.dp2px(10), Path.Direction.CW);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //画外侧弧
        mRectF = new RectF(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS);
        mPath.addArc(mRectF, 90 + ANGLE / 2, 360 - ANGLE);
        canvas.drawArc(mRectF, 90 + ANGLE / 2, 360 - ANGLE, false, mPaint);
        mPathMeasure = new PathMeasure(mPath, false);
        mEffect = new PathDashPathEffect(mDash, (mPathMeasure.getLength() - Utils.dp2px(2)) / 20, 0, PathDashPathEffect.Style.ROTATE);

        //画分割线
        mPaint.setPathEffect(mEffect);
        canvas.drawArc(mRectF, 90 + ANGLE / 2, 360 - ANGLE, false, mPaint);
        mPaint.setPathEffect(null);

        //绘制指针
        canvas.drawLine(getWidth() / 2, getHeight() / 2, (float) (getWidth() / 2 + LENGTH * Math.cos(Math.toRadians(getAngle(5))))
                , (float) (getHeight() / 2 + LENGTH * Math.sin(Math.toRadians(getAngle(5)))), mPaint);

    }

    private double getAngle(int i) {
        return 90 + ANGLE / 2 + (360 - ANGLE) / 20 * 5;
    }


}
