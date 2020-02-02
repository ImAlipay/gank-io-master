package com.example.hencoder.redpoint;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.PointF;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.WindowManager;
import android.view.animation.OvershootInterpolator;
import android.widget.TextView;

import com.example.hencoder.Utils;

public class RedPointViewControl implements View.OnTouchListener, RedPointView.DragViewStatusListener {

    private View showView;
    private int draViewId;

    private WindowManager.LayoutParams params;
    private Context context;
    private int statusBarHeight;
    private View dragView;
    private WindowManager windowManager;
    private RedPointView redPointView;

    public RedPointViewControl(Context context, View showView,
                               int dragViewId, DragStatusListener dragStatusListener) {
        this.context = context;
        this.showView = showView;
        this.draViewId = dragViewId;
        this.dragStatusListener = dragStatusListener;

        //设置监听 执行自己的触摸事件
        showView.setOnTouchListener(this);
        params = new WindowManager.LayoutParams();
        params.format = PixelFormat.TRANSLUCENT;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (MotionEvent.ACTION_DOWN == event.getActionMasked()) {
            ViewParent parent = v.getParent();
            if (parent == null) {
                return false;
            }

            parent.requestDisallowInterceptTouchEvent(true);
            statusBarHeight = Utils.getStatusBarHeight(showView);
            showView.setVisibility(View.INVISIBLE);
            dragView = LayoutInflater.from(context).inflate(draViewId, null, false);
            // 获取文本内容
            getText();
            windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            // 每当触摸的时候就创建拖拽的小圆
            redPointView = new RedPointView(context, dragView, windowManager);
            init();
            // 设置监听回调
            redPointView.setDragViewwStatusListener(this);
            // 添加到窗体
            windowManager.addView(redPointView, params);
            windowManager.addView(dragView, params);
        }
        redPointView.onTouchEvent(event);
        return true;
    }

    private void init() {
        // 计算小圆在屏幕中的坐标
        int[] points = new int[2];
        showView.getLocationInWindow(points);
        int x = points[0] + showView.getWidth() / 2;
        int y = points[1] + showView.getHeight() / 2;
        redPointView.setStatusBarHeight(statusBarHeight);
        redPointView.setCenterDraPoint(x, y);
    }

    private void getText() {
        if (showView instanceof TextView && dragView instanceof TextView) {
            ((TextView) dragView).setText(((TextView) showView).getText().toString());
        }
    }

    @Override
    public void outDragView(PointF dragPoint) {

    }

    @Override
    public void outDragMoveUP(PointF dragPoint) {
        removeView();
        showExplodeImage(dragPoint);
        dragStatusListener.outScope();
    }


    @Override
    public void inDragUp(PointF dragPoint) {
        removeView();
        dragStatusListener.inScope();
    }

    @Override
    public void recoverCenterPoint(PointF centerPoint) {
        removeView();
        dragStatusListener.inScope();
    }

    private void showExplodeImage(PointF dragPoint) {
//        ValueAnimator animator = ValueAnimator.ofInt(0, explodeImages.length - 1);
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                explodeImage.setBackgroundResource(explodeImages[(int) animation.getAnimatedValue()]);
//            }
//        });
//        animator.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationCancel(Animator animation) {
//                super.onAnimationCancel(animation);
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                super.onAnimationEnd(animation);
//                explodeImage.setVisibility(GONE);
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//                super.onAnimationRepeat(animation);
//            }
//
//            @Override
//            public void onAnimationStart(Animator animation) {
//                super.onAnimationStart(animation);
//                explodeImage.setVisibility(VISIBLE);
//            }
//
//            @Override
//            public void onAnimationPause(Animator animation) {
//                super.onAnimationPause(animation);
//            }
//
//            @Override
//            public void onAnimationResume(Animator animation) {
//                super.onAnimationResume(animation);
//            }
//        });
//        animator.setDuration(600);
//        animator.setRepeatCount(ValueAnimator.RESTART);
//        animator.setInterpolator(new OvershootInterpolator());
//        animator.start();
    }

    private void removeView() {
        if (windowManager != null && redPointView.getParent() != null && dragView.getParent() != null) {
            windowManager.removeView(redPointView);
            windowManager.removeView(dragView);
        }
    }

    //在拖拽范围内还是拖拽范围外的监听
    public interface DragStatusListener {
        void inScope();

        void outScope();
    }


    private DragStatusListener dragStatusListener;
}
