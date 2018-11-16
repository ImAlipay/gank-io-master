package com.chinasoft.customercontrol;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * ${DESC}
 *
 * @author 91660
 * @date 2018/11/13
 */
    public class DrawTextView extends View {

    private Paint mPaint;
    private int mBaseLineX = 0;
    private int mBaseLineY = 100;

    public DrawTextView(Context context) {
        super(context);
        init();
    }

    public DrawTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DrawTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(5);
        mPaint.setTextSize(60);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawText("Hello World!", mBaseLineX, mBaseLineY, mPaint);
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        canvas.drawLine(mBaseLineX, mBaseLineY, 3000, mBaseLineY, paint);
        Paint.FontMetrics metrics = mPaint.getFontMetrics();
        int ascent = (int) (mBaseLineY + metrics.ascent);
        int bottom = (int) (mBaseLineY + metrics.bottom);
        int descent = (int) (mBaseLineY + metrics.descent);
        int top = (int) (mBaseLineY + metrics.top);
        canvas.drawLine(mBaseLineX, ascent, 3000, ascent, paint);
        canvas.drawLine(mBaseLineX, bottom, 3000, bottom, paint);
        canvas.drawLine(mBaseLineX, descent, 3000, descent, paint);
        canvas.drawLine(mBaseLineX, top, 3000, top, paint);
    }
}
