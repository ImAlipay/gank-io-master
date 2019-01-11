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
 * @date 2018/11/16
 */
public class ShadowLayerView extends View {

    private Paint mPaint;

    public ShadowLayerView(Context context) {
        super(context);
    }

    public ShadowLayerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ShadowLayerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(25);
//        mPaint.setShadowLayer(1, 10, 10, Color.GRAY);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        canvas.drawText("Hello World!", 100, 100, mPaint);
        canvas.drawCircle(300, 100, 50, mPaint);
    }

}