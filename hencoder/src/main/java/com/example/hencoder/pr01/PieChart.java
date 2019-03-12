package com.example.hencoder.pr01;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.example.hencoder.Utils;

public class PieChart extends View {


    private Paint mPaint;
    private static final float RADIUS = Utils.dp2px(100);
    private static final float OFFSET = Utils.dp2px(5);
    int[] mColors = {Color.parseColor("#FF850B00")
            , Color.parseColor("#FF574600")
            , Color.parseColor("#FF525800")
            , Color.parseColor("#FF6F0085")};

    public PieChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(Utils.dp2px(1));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF rect = new RectF(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS);
        float startAngle = 0;
        for (int i = 0; i < 4; i++) {
            mPaint.setColor(mColors[i]);
            if (i == 2) {
                canvas.save();
                float angle = startAngle + 30;
                float dx = (float) (OFFSET * Math.cos(Math.toRadians(angle)));
                float dy = (float) (OFFSET * Math.sin(Math.toRadians(angle)));
                canvas.translate(dx, dy);
                canvas.drawArc(rect, startAngle, 60, true, mPaint);
                canvas.restore();
            } else {
                canvas.drawArc(rect, startAngle, 60, true, mPaint);
            }
            startAngle += 60;
        }
    }
}
