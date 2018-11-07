package com.chinasoft.customercontrol;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTv1;
    private TextView mTv2;
    private ValueAnimator mValueAnimator;
    private boolean click;
    private Button mItem1;
    private Button mItem2;
    private Button mItem3;
    private Button mItem4;
    private Button mItem5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTv1 = findViewById(R.id.tv1);
        mTv2 = findViewById(R.id.tv2);
        Button menu = findViewById(R.id.menu);
        mItem1 = findViewById(R.id.item1);
        mItem2 = findViewById(R.id.item2);
        mItem3 = findViewById(R.id.item3);
        mItem4 = findViewById(R.id.item4);
        mItem5 = findViewById(R.id.item5);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (click) {
                    click = false;
                    closedAnima(mItem1, 0, 5, 800);
                    closedAnima(mItem2, 1, 5, 800);
                    closedAnima(mItem3, 2, 5, 800);
                    closedAnima(mItem4, 3, 5, 800);
                    closedAnima(mItem5, 4, 5, 800);
                } else {
                    click = true;
                    openAnima(mItem1, 0, 5, 800);
                    openAnima(mItem2, 1, 5, 800);
                    openAnima(mItem3, 2, 5, 800);
                    openAnima(mItem4, 3, 5, 800);
                    openAnima(mItem5, 4, 5, 800);
                }
            }
        });
        //Animation animation = AnimationUtils.loadAnimation(this, R.anim.scale);
        //view.startAnimation(animation);

        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mValueAnimator = doAnima();
//                startObject().start();
                playTogether();
            }
        });

        findViewById(R.id.btn_stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mValueAnimator.cancel();
            }
        });
    }

    /**
     * 打开
     */
    private void openAnima(View view, int index, int total, int radius) {
        if (view.getVisibility() != View.VISIBLE) {
            view.setVisibility(View.VISIBLE);
        }
        double degree = Math.toRadians(90) / (total - 1) * index;
        int translationX = -(int) (radius * Math.sin(degree));
        int translationY = -(int) (radius * Math.cos(degree));
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(view, "translationY", 0, translationY),
                ObjectAnimator.ofFloat(view, "translationX", 0, translationX),
                ObjectAnimator.ofFloat(view, "scaleX", 0f, 1f),
                ObjectAnimator.ofFloat(view, "scaleY", 0f, 1f),
                ObjectAnimator.ofFloat(view, "alpha", 0f, 1)
        );
        set.setDuration(500).start();
    }

    /**
     * 关闭
     */
    private void closedAnima(View view, int index, int total, int radius) {
        if (view.getVisibility() != View.VISIBLE) {
            view.setVisibility(View.VISIBLE);
        }
        double degree = Math.PI * index / ((total - 1) * 2);
        int translationX = -(int) (radius * Math.sin(degree));
        int translationY = -(int) (radius * Math.cos(degree));
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(view, "translationX", translationX, 0),
                ObjectAnimator.ofFloat(view, "translationY", translationY, 0),
                ObjectAnimator.ofFloat(view, "scaleX", 1f, 0f),
                ObjectAnimator.ofFloat(view, "scaleY", 1f, 0f),
                ObjectAnimator.ofFloat(view, "alpha", 1f, 0f)
        );
        set.setDuration(500).start();
    }

    private void playTogether() {
        ObjectAnimator tv1BgaAnimator = ObjectAnimator.ofInt(mTv1, "BackgroundColor", 0xffff00ff, 0xffffff00, 0xffff00ff);
        tv1BgaAnimator.setStartDelay(2000);
        ObjectAnimator tv1TransAnimator = ObjectAnimator.ofFloat(mTv1, "translationY", 0, 400, 0);
        tv1TransAnimator.setRepeatCount(ValueAnimator.INFINITE);

        ObjectAnimator tv2TranslateY = ObjectAnimator.ofFloat(mTv2, "translationY", 0, 400, 0);
//        tv2TranslateY.setStartDelay(2000);

        AnimatorSet animatorSet = new AnimatorSet();
//        animatorSet.playSequentially(tv1BgaAnimator, tv1TransAnimator, tv2TranslateY);
        animatorSet.play(tv1BgaAnimator).with(tv1TransAnimator).after(tv2TranslateY);
        animatorSet.setDuration(2000);
        animatorSet.start();

    }

    private ValueAnimator doAnima() {
        ValueAnimator animator = ValueAnimator.ofObject(new FallingBallEvaluator(), new Point(0, 0), new Point(500, 500));
        animator.setDuration(3000);
//        animator.setRepeatCount(ValueAnimator.INFINITE);
//        animator.setRepeatMode(ValueAnimator.REVERSE);
//        animator.setInterpolator(new MyInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Point mCurrentPoint = (Point) animation.getAnimatedValue();
//                mTv1.setBackgroundColor(value);
                mTv1.layout(mCurrentPoint.x, mCurrentPoint.y, mCurrentPoint.x + mTv1.getWidth(), mCurrentPoint.y + mTv1.getHeight());

            }
        });
        animator.start();
        return animator;
    }

    private ObjectAnimator startObject() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mTv1, "alpha", 1, 0, 1);
        objectAnimator.setDuration(2000);
        return objectAnimator;
    }
}