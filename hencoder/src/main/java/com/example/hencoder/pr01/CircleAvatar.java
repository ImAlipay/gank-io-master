package com.example.hencoder.pr01;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
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
public class CircleAvatar extends View {

    private Bitmap mBitmap;
    private Paint mPaint;
    private PorterDuffXfermode mXfermode;

    public CircleAvatar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setColor(Color.GREEN);
        mPaint.setStrokeWidth(Utils.dp2px(5));
        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mBitmap = getAvatar((int) Utils.dp2px(200));
//        BitmapShader shader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
//        mPaint.setShader(shader);
//        canvas.drawCircle(Utils.dp2px(100),Utils.dp2px(100),Utils.dp2px(50), mPaint);
        int layer = canvas.saveLayer(0, 0, Utils.dp2px(200), Utils.dp2px(200), mPaint);
        canvas.drawCircle(Utils.dp2px(100), Utils.dp2px(100), Utils.dp2px(50), mPaint);
        mPaint.setXfermode(mXfermode);
        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
        mPaint.setXfermode(null);
        canvas.restoreToCount(layer);

    }

    Bitmap getAvatar(int width) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher, options);
        options.inJustDecodeBounds = false;
        options.inDensity = options.outWidth;
        options.inTargetDensity = width;
        return BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher, options);
    }
}
