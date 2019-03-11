package com.example.hencoder.pr01;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.example.hencoder.R;
import com.example.hencoder.Utils;

public class SportsView extends View {


    private Paint mPaint;
    float radius = Utils.dp2px(150);
    Paint.FontMetrics metrics = new Paint.FontMetrics();

    public SportsView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.parseColor("#FFBDC2C2"));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(Utils.dp2px(20));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF rect = new RectF(getWidth() / 2 - radius, getHeight() / 2 - radius, getWidth() / 2 + radius, getHeight() / 2 + radius);

        //绘制圆环
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, radius, mPaint);

        //绘制进度
        mPaint.setColor(getResources().getColor(R.color.colorAccent));
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawArc(rect, 135, 90, false, mPaint);

        //绘制字体
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(150);
        mPaint.getFontMetrics(metrics);
        float offset = (metrics.ascent + metrics.descent) / 2;
        canvas.drawText("OP", getWidth() / 2, getHeight() / 2 - offset, mPaint);

        mPaint.setStrokeWidth(2);
        canvas.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2, mPaint);
        canvas.drawLine(0, getHeight() / 2 - offset, getWidth(), getHeight() / 2 - offset, mPaint);

        canvas.drawLine(0, getHeight() / 2 - offset + metrics.ascent, getWidth(), getHeight() / 2 -offset+ metrics.ascent, mPaint);
        canvas.drawLine(0, getHeight() / 2 - offset + metrics.descent, getWidth(), getHeight() / 2-offset + metrics.descent, mPaint);


    }
}
