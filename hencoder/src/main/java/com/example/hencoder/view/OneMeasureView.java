package com.example.hencoder.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.hencoder.Utils;

import java.util.Random;

/**
 * ${DESC}
 *
 * @author 91660
 * @date 2019/3/18
 */
public class OneMeasureView extends AppCompatTextView {

    private static final String TAG = OneMeasureView.class.getSimpleName();
    private static final int[] COLORS = {
            Color.parseColor("#E91E63"),
            Color.parseColor("#673AB7"),
            Color.parseColor("#3F51B5"),
            Color.parseColor("#2196F3"),
            Color.parseColor("#009688"),
            Color.parseColor("#FF9800"),
            Color.parseColor("#FF5722"),
            Color.parseColor("#795548")
    };
    private static final int[] TEXT_SIZES = {
            16, 22, 28
    };
    private static final Random random = new Random();
    private static final int CORNER_RADIUS = (int) Utils.dp2px(4);
    private static final int X_PADDING = (int) Utils.dp2px(16);
    private static final int Y_PADDING = (int) Utils.dp2px(8);
    private Paint mPaint;

    {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        setTextColor(Color.WHITE);
        mPaint.setColor(COLORS[random.nextInt(3)]);
        setTextSize(TEXT_SIZES[random.nextInt(3)]);
        setPadding(X_PADDING, Y_PADDING, X_PADDING, Y_PADDING);
    }

    public OneMeasureView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Log.e(TAG, "----------构造方法-----------");

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "----------点击事件-----------");
            }
        });
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.e(TAG, "----------OnTouchListener-----------");
                return false;
            }
        });
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.e(TAG, "----------onSizeChanged-----------");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e(TAG, "----------onMeasure-----------");
    }

    @Override
    public void layout(int l, int t, int r, int b) {
        super.layout(l, t, r, b);
        Log.e(TAG, "----------layout-----------");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.e(TAG, "----------onLayout-----------");
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRoundRect(0, 0, getWidth(), getHeight(), CORNER_RADIUS, CORNER_RADIUS, mPaint);
        super.onDraw(canvas);
        Log.e(TAG, "----------onDraw-----------");
        Log.e(TAG, "after measure:" + getMeasuredWidth());
        Log.e(TAG, "after layout:" + getWidth());
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.e(TAG, "----------dispatchTouchEvent-----------");
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, "----------onTouchEvent-----------");
        return super.onTouchEvent(event);
    }


}