package com.chinasoft.customercontrol;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * ${DESC}
 *
 * @author 91660
 * @date 2018/10/30
 */
public class SpiderView extends View {

    private Paint mRadarPaint;
    private Paint mValuePaint;
    /**
     * 最大半径
     */
    private float mRadius;
    private int num = 6;
    private float mCenterX;
    private float mCenterY;
    private float angle = (float) Math.PI * 2 / num;
    /**
     * 数据
     */
    private double datas[] = {1, 2, 3, 4, 5, 6};

    public SpiderView(Context context) {
        this(context, null, 0);
    }

    public SpiderView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SpiderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mRadius = Math.min(w, h) / 2 * 0.9f;
        mCenterX = w / 2;
        mCenterY = h / 2;
        postInvalidate();
        super.onSizeChanged(w, h, oldw, oldh);
    }

    private void init() {
        mRadarPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRadarPaint.setColor(Color.BLACK);
        mRadarPaint.setStyle(Paint.Style.STROKE);

        mValuePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mValuePaint.setColor(Color.RED);
        mValuePaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawPolygon(canvas);
        drawLines(canvas);
        drawRegion(canvas);
    }

    private void drawRegion(Canvas canvas) {
        Path path = new Path();
        mValuePaint.setAlpha(127);
        for (int i = 0; i < datas.length; i++) {
            double data = datas[i];
            float x = (float) (mCenterX + mRadius * data / num * Math.cos(i * angle));
            float y = (float) (mCenterY + mRadius * data / num * Math.sin(i * angle));
            canvas.drawCircle(x, y, 10, mValuePaint);
            if (i == 0) {
                path.moveTo(x, y);
            } else {
                path.lineTo(x, y);
            }
        }
        path.close();
        canvas.drawPath(path, mValuePaint);
    }

    /**
     * 绘制横线
     *
     * @param canvas
     */
    private void drawLines(Canvas canvas) {
        Path path = new Path();
        for (int i = 0; i < num; i++) {
            path.moveTo(mCenterX, mCenterY);
            float x = (float) (mCenterX + mRadius * Math.cos(i * angle));
            float y = (float) (mCenterY + mRadius * Math.sin(i * angle));
            path.lineTo(x, y);
            canvas.drawPath(path, mRadarPaint);
        }
    }

    /**
     * @param canvas 绘制网格图
     */
    private void drawPolygon(Canvas canvas) {
        Path path = new Path();
        for (int j = 1; j <= num; j++) {
            float curRadius = mRadius * j / num;
            path.reset();
            for (int i = 0; i < num; i++) {
                if (i == 0) {
                    path.moveTo(mCenterX + curRadius, mCenterY);
                } else {
                    float x = (float) (mCenterX + curRadius * Math.cos(i * angle));
                    float y = (float) (mCenterY + curRadius * Math.sin(i * angle));
                    Log.e("math", Math.cos(i * angle) + "");
                    path.lineTo(x, y);
                }
            }
            path.close();
            canvas.drawPath(path, mRadarPaint);
        }
    }
}