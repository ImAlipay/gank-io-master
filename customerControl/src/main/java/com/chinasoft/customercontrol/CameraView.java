package com.chinasoft.customercontrol;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * ${DESC}
 *
 * @author 91660
 * @date 2019/2/15
 */
public class CameraView extends View {


    private Paint mPaint;

    public CameraView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.avator);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Camera camera = new Camera();
        canvas.save();
//        canvas.translate(-width / 2, -height / 2);
        camera.rotateX(30);
        camera.applyToCanvas(canvas);
//        canvas.translate(width / 2, height / 2);
        canvas.drawBitmap(bitmap, 100, 100, mPaint);
        canvas.restore();
    }


}
