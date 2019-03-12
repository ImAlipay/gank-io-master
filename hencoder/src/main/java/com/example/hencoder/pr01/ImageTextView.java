package com.example.hencoder.pr01;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
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
public class ImageTextView extends View {

    private static final float WIDTH = Utils.dp2px(200);
    private static final float HEIGHTOFFSET = Utils.dp2px(100);

    String text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean justo sem, sollicitudin in maximus a, vulputate id magna. Nulla non quam a massa sollicitudin commodo fermentum et est. Suspendisse potenti. Praesent dolor dui, dignissim quis tellus tincidunt, porttitor vulputate nisl. Aenean tempus lobortis finibus. Quisque nec nisl laoreet, placerat metus sit amet, consectetur est. Donec nec quam tortor. Aenean aliquet dui in enim venenatis, sed luctus ipsum maximus. Nam feugiat nisi rhoncus lacus facilisis pellentesque nec vitae lorem. Donec et risus eu ligula dapibus lobortis vel vulputate turpis. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; In porttitor, risus aliquam rutrum finibus, ex mi ultricies arcu, quis ornare lectus tortor nec metus. Donec ultricies metus at magna cursus congue. Nam eu sem eget enim pretium venenatis. Duis nibh ligula, lacinia ac nisi vestibulum, vulputate lacinia tortor.";
    private Paint mPaint;
    private Paint.FontMetrics mMetrics;
    private Bitmap mBitmap;
    /**
     * 绘制文字起始位置
     */
    private int start;
    float mMeasure[] = new float[1];

    public ImageTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextSize(Utils.dp2px(14));
        mPaint.setColor(Color.BLACK);
        mMetrics = new Paint.FontMetrics();
        mPaint.getFontMetrics(mMetrics);
        mBitmap = getAvatar((int) WIDTH);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBitmap, getWidth() - WIDTH, HEIGHTOFFSET, mPaint);
        //绘制文字
        int length = text.length();
        //绘制时y轴起点
        float verticaloffset = -mMetrics.top;
        for (start = 0; start < length; ) {
            int maxWidth;
            float textTop = verticaloffset + mMetrics.top;
            float textBottom = verticaloffset + mMetrics.bottom;
            if (textTop > HEIGHTOFFSET && textTop < HEIGHTOFFSET + WIDTH
                    || textBottom > HEIGHTOFFSET && textBottom < HEIGHTOFFSET + WIDTH) {
                //和图片在同一行
                maxWidth = (int) (getWidth() - WIDTH);
            } else {
                maxWidth = getWidth();
            }
            int breakText = mPaint.breakText(text, start, length, true, maxWidth, mMeasure);
            canvas.drawText(text, start, start + breakText, 0, verticaloffset, mPaint);
            start += breakText;
            verticaloffset += mPaint.getFontSpacing();
        }
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
