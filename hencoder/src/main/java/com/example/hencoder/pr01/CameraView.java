package com.example.hencoder.pr01;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.hencoder.Utils;

public class CameraView extends View {

    private Paint paint;
    private Bitmap bitmap;
    private Camera camera;

    public CameraView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bitmap = Utils.getAvatar(600, getResources());
        camera = new Camera();
        camera.rotateX(45);
        camera.setLocation(0,0,- 6 * Resources.getSystem().getDisplayMetrics().density);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.save();
//        camera.save();
//        camera.rotateX(30);
//        canvas.translate(Utils.dp2px(100),Utils.dp2px(100));
//        camera.applyToCanvas(canvas);
//        canvas.translate(-Utils.dp2px(100),-Utils.dp2px(100));
//        camera.restore();
//
//        canvas.drawBitmap(bitmap,0,0,paint);
//        canvas.restore();

        //绘制上班部分
//        canvas.save();
//        canvas.translate(100+600/2,100+600/2);
//        canvas.rotate(-20);
//        canvas.clipRect(-600,-600,600,0);
//        canvas.rotate(20);
//        canvas.translate(-(100+600/2),-(100+600/2));
//        canvas.drawBitmap(Utils.getAvatar(600,getResources()),100,100,paint);
//        canvas.restore();

        //绘制下半部分
        canvas.save();
        canvas.translate(100+600/2,100+600/2);
        canvas.rotate(-20);
        camera.applyToCanvas(canvas);
        canvas.clipRect(-600,0,600,600);
        canvas.rotate(20);
        canvas.translate(-(100+600/2),-(100+600/2));
        canvas.drawBitmap(Utils.getAvatar(600,getResources()),100,100,paint);
        canvas.restore();

    }
}
