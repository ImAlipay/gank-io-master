package com.example.hencoder.redpoint;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.hencoder.R;
import com.example.hencoder.Utils;

public class RedPointView extends View {

    // 画笔
    private Paint rPaint, tPaint;

    // 半径
    private int rRadius;

    // 固定圆的圆心
    PointF tCenterPointF = new PointF(300, 400);

    // 拖拽圆的圆心
    PointF tDragPointF = new PointF(200, 500);

    // 固定圆的半径
    private float tCenterRadius = 30;

    // 拖拽圆的半径
    private float tDragRadius = 30;

    // 线条
    private Path rPath;

    // 中心圆的最小半径
    private float minRadius = 8;
    // 默认拖拽的最大距离
    private float maxDistance = 160;

    // 标识 拖拽距离是否大于规定的拖拽范围
    private boolean isOut;

    // 标识 超出拖拽范围
    private boolean isOverStep;

    //初始化爆炸图片
    private int[] explodeImages = new int[]{
            R.mipmap.explode_1,
            R.mipmap.explode_1,
            R.mipmap.explode_1,
            R.mipmap.explode_4,
            R.mipmap.explode_5
    };

    // 爆炸图片
    private ImageView explodeImage;
    private boolean isOverAndUp;

    // WindowManager对象
    private WindowManager windowManager;
    // 拖拽view的宽高
    private int dragViewWidth;
    // 拖拽view的高
    private int dragViewHeight;
    // WindowManager 布局参数
    private WindowManager.LayoutParams params;
    // 状态监听
    private DragViewStatusListener dragViewwStatusListener;
    private Context context;
    private View dragView;
    private int statusBarHeight;

    public RedPointView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RedPointView(Context context, View dragView, WindowManager windowManager) {
        super(context);
        this.context = context;
        this.dragView = dragView;
        this.windowManager = windowManager;
        init();
    }

    private void init() {

        dragView.measure(MeasureSpec.EXACTLY, MeasureSpec.EXACTLY);
        dragViewWidth = dragView.getMeasuredWidth() / 2;
        dragViewHeight = dragView.getMeasuredHeight() / 2;

        tDragRadius = dragViewWidth;
        tCenterRadius = Utils.dp2px(8);
        maxDistance = Utils.dp2px(80);
        minRadius = Utils.dp2px(3);

        rPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        rPaint.setColor(Color.RED);
        rPaint.setAntiAlias(true);
        rPaint.setStyle(Paint.Style.FILL);

        tPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        tPaint.setColor(Color.RED);
        rRadius = 25;

        // 添加爆炸图像
        explodeImage = new ImageView(getContext());
//        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        explodeImage.setLayoutParams(lp);
//        explodeImage.setImageResource(R.mipmap.explode_1);
//        // 一开始不显示
//        explodeImage.setVisibility(INVISIBLE);
//        addView(explodeImage);

        params = new WindowManager.LayoutParams();
        // 透明背景
        params.format = PixelFormat.TRANSLUCENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        // 以坐上角为基准
        params.gravity = Gravity.TOP | Gravity.LEFT;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                float originalDragX = event.getRawX();
                float originalDragY = event.getRawY();
                updateDragPoint(originalDragX, originalDragY);
                break;
            case MotionEvent.ACTION_MOVE:
                float overDragX = event.getRawX();
                float overDragY = event.getRawY();
                updateDragPoint(overDragX, overDragY);
                float distanceTwo = getDistanceTwo(tCenterPointF, tDragPointF);
                // 如果拖拽距离大于给定距离时
                if (distanceTwo > maxDistance) {
                    isOut = true;
                } else {
                    // tips: 不能赋值isOut为false,因为一旦超出给定的拖拽距离就没办法恢复
                    isOverStep = false;
                }
                break;
            case MotionEvent.ACTION_UP:
                if (!isOut) {
                    // 没有超出
                    kickBack();
                }
                if (isOut) {
                    // 抬起标识
                    isOverAndUp = true;
                    // 让爆炸图片在原点中央
//                    explodeImage.setX(event.getX() - tDragRadius);
//                    explodeImage.setY(event.getY() - tDragRadius);
                    // 如果中心圆和拖拽圆大于拖拽距离，就播放爆炸
                    if (getDistanceTwo(tCenterPointF, tDragPointF) > maxDistance) {
//                        showExplodeImage();
                        if (dragViewwStatusListener != null) {
                            dragViewwStatusListener.outDragMoveUP(tDragPointF);
                        }
                    }
                    // 如果拖拽圆和中心圆距离已经超出拖拽距离，然后又把拖拽圆移动与中心圆大于30 还是会爆炸
                    if (getDistanceTwo(tCenterPointF, tDragPointF) >= 30) {
//                        showExplodeImage();
                        if (dragViewwStatusListener != null) {
                            dragViewwStatusListener.outDragMoveUP(tDragPointF);
                        }
                    }
                }
                postInvalidate();
                break;
        }
        return true;
    }

    /**
     * 拖拽回弹的动画
     */
    private void kickBack() {
        final PointF initPoint = new PointF(tDragPointF.x, tDragPointF.y);
        final PointF finsihPoint = new PointF(tCenterPointF.x, tCenterPointF.y);

        ValueAnimator animator = ValueAnimator.ofFloat(0.0f, 1.0f);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();
                // 更新拖拽圆的圆心
                PointF point = getPoint(initPoint, finsihPoint, fraction);
                updateDragPoint(point.x, point.y);
            }
        });
        animator.setInterpolator(new OvershootInterpolator(3.0f));
        animator.setDuration(500);
        animator.start();
    }

    private PointF getPoint(PointF initPoint, PointF finsihPoint, float fraction) {
        return new PointF(getVaule(initPoint.x, finsihPoint.x, fraction),
                getVaule(initPoint.y, finsihPoint.y, fraction));
    }

    private float getVaule(Number start, float finish, float fraction) {
        return start.floatValue() + fraction * (finish - start.floatValue());
    }

    private float getDistanceTwo(PointF tCenterPointF, PointF tDragPointF) {
        return (float) Math.sqrt(Math.pow(tCenterPointF.x - tDragPointF.x, 2) + Math.pow(tCenterPointF.y - tDragPointF.y, 2));
    }

    private void updateDragPoint(float x, float y) {
        tDragPointF.set(x, y);
        changeManagerView(x, y);
        postInvalidate();
    }

    private void changeManagerView(float x, float y) {
        params.x = (int) (x - dragViewWidth);
        params.y = (int) (y - dragViewHeight - statusBarHeight);
        windowManager.updateViewLayout(dragView, params);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        statusBarHeight = Utils.getStatusBarHeight(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();

        // 需要去除状态栏高度偏差
        canvas.translate(0, -statusBarHeight);
        if (!isOut) {
            tCenterRadius = changeCenterRadius();
            // 绘制固定圆
            canvas.drawCircle(tCenterPointF.x, tCenterPointF.y, tCenterRadius, rPaint);
            // 绘制固定圆
            canvas.drawCircle(tDragPointF.x, tDragPointF.y, tDragRadius, rPaint);

            float x = tCenterPointF.x - tDragPointF.x;
            float y = tDragPointF.y - tCenterPointF.y;
            // 求a的角度
            double a = Math.atan(y / x);

            // 中心圆的p1 x左边偏移
            float offsetX1 = (float) (tCenterRadius * Math.sin(a));
            float offsetY1 = (float) (tCenterRadius * Math.cos(a));

            // 拖拽圆的p2 x坐标偏移
            float offsetX2 = (float) (tDragRadius * Math.sin(a));
            float offsetY2 = (float) (tDragRadius * Math.cos(a));

            // p1的左边
            float p1_x = tCenterPointF.x - offsetX1;
            float p1_y = tCenterPointF.y - offsetY1;

            // p2的左边
            float p2_x = tCenterPointF.x + offsetX1;
            float p2_y = tCenterPointF.y + offsetY1;

            // p3的坐标
            float p3_x = tDragPointF.x - offsetX2;
            float p3_y = tDragPointF.y - offsetY2;

            // p4的坐标
            float p4_x = tDragPointF.x + offsetX2;
            float p4_y = tDragPointF.y + offsetY2;

            // 控制点M的坐标
            float controll_x = (tCenterPointF.x + tDragPointF.x) / 2;
            float controll_y = (tDragPointF.y + tCenterPointF.y) / 2;

            rPath = new Path();
            // 绘制路径方向 P1
            rPath.reset();
            rPath.moveTo(p1_x, p1_y);
            rPath.quadTo(controll_x, controll_y, p3_x, p3_y);
            rPath.lineTo(p4_x, p4_y);
            rPath.quadTo(controll_x, controll_y, p2_x, p2_y);
            rPath.lineTo(p1_x, p1_y);
            rPath.close();
            canvas.drawPath(rPath, tPaint);
        }
        if (isOut) {
            //如果一开始超出拖拽范围，后面又移动拖拽圆与中心圆的距离少于30，就恢复中心圆位置
            if (getDistanceTwo(tCenterPointF, tDragPointF) < 30 && isOverAndUp) {
                canvas.drawCircle(tCenterPointF.x, tCenterPointF.y, tCenterRadius, rPaint);
                isOut = false;
                isOverAndUp = false;
            }
        }
        // 一旦超出给定的拖拽距离，就绘制拖拽圆
        if (!isOverStep) {
            // 如果超出并且抬起
            if (!isOverAndUp && isOut) {
                canvas.drawCircle(tDragPointF.x, tDragPointF.y, tCenterRadius, rPaint);
            }
        }
    }

    // 计算拖动过程中心圆的半径
    private float changeCenterRadius() {
        float mDistance_x = tDragPointF.x - tCenterPointF.x;
        float mDistance_y = tDragPointF.y - tCenterPointF.y;
        // 两个圆之间的距离
        float mDistance = (float) Math.sqrt(Math.pow(mDistance_x, 2) + Math.pow(mDistance_y, 2));
        // 计算中心圆的半径
        float r = tDragRadius - minRadius * (mDistance / maxDistance);
        if (r < minRadius) {
            r = minRadius;
        }
        return r;
    }


    public void setDragViewwStatusListener(DragViewStatusListener listener) {
        this.dragViewwStatusListener = listener;
    }

    public void setStatusBarHeight(int statusBarHeight) {
        this.statusBarHeight = statusBarHeight;
    }

    /**
     * 设置中心圆和拖拽圆的圆心
     *
     * @param x
     * @param y
     */
    public void setCenterDraPoint(int x, int y) {
        tCenterPointF.set(x, y);
        tDragPointF.set(x, y);
        invalidate();
    }

    public interface DragViewStatusListener {
        /**
         * 在拖拽范围外移动
         *
         * @param dragPoint
         */
        void outDragView(PointF dragPoint);

        /**
         * 在拖拽范围外移动，产生爆炸效果
         *
         * @param dragPoint
         */
        void outDragMoveUP(PointF dragPoint);

        /**
         * 在拖拽范围内移动
         *
         * @param dragPoint
         */
        void inDragUp(PointF dragPoint);

        /**
         * 当移出拖拽范围后拖拽到范围内，恢复中心圆
         *
         * @param centerPoint
         */
        void recoverCenterPoint(PointF centerPoint);
    }
}
