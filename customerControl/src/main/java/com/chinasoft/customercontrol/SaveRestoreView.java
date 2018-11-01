package com.chinasoft.customercontrol;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author 91660
 * @date 2018/10/31
 */
public class SaveRestoreView extends View {

    private static final int CLIP_HEIGHT = 30;
    private int clipWidth = 0;
    private Paint mPaint;
    private Bitmap mBitmap;
    private int mWidth;
    private int mHeight;
    private Path mPath;
    private Region mRegion;

    public SaveRestoreView(Context context) {
        super(context);
        init();
    }

    public SaveRestoreView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SaveRestoreView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.scenery);
        mWidth = mBitmap.getWidth();
        mHeight = mBitmap.getHeight();
        mRegion = new Region();

//        mPath = new Path();
//        mPath.addCircle(mWidth / 2, mHeight / 2, mWidth / 2, Path.Direction.CCW);
//
//        mPaint = new Paint();
//        mPaint.setColor(Color.GREEN);
//        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mRegion.setEmpty();
        int i = 0;
        while (i * CLIP_HEIGHT <= mHeight) {
            if (i % 2 == 0) {
                mRegion.union(new Rect(0, i * CLIP_HEIGHT, clipWidth, (i + 1) * CLIP_HEIGHT));
            } else {
                mRegion.union(new Rect(mWidth - clipWidth, i * CLIP_HEIGHT, mWidth, (i + 1) * CLIP_HEIGHT));
            }
            i++;
        }
//        canvas.clipRegion(mRegion);
        canvas.drawBitmap(mBitmap, 0, 0, new Paint());
        if (clipWidth > mWidth) {
            return;
        }
        clipWidth += 5;
        invalidate();

//        canvas.clipPath(mPath);
//        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
//
//        canvas.drawColor(Color.RED);
//        canvas.save();
//
//        canvas.clipRect(new Rect(100, 100, 800, 800));
//        canvas.drawColor(Color.GRAY);
//        canvas.drawText("hello", 200, 200, paint);
//        canvas.restore();

//        Region region = new Region();
//        Path path = new Path();
//        path.addCircle(100, 100, 500, Path.Direction.CW);
//        region.setPath(path, new Region(100, 100, 500, 500));
////        region.union(new Rect(100, 100, 500, 500));
//        drawRegion(canvas, region);
    }

    private void drawRegion(Canvas canvas, Region region) {
        RegionIterator iterator = new RegionIterator(region);
        Rect rect = new Rect();
        if (iterator.next(rect)) {
            canvas.drawRect(rect, mPaint);
        }
    }
}