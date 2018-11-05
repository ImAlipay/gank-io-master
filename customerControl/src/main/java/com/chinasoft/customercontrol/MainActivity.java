package com.chinasoft.customercontrol;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTv;
    private ValueAnimator mValueAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTv = findViewById(R.id.tv);
//        Animation animation = AnimationUtils.loadAnimation(this, R.anim.scale);
//        view.startAnimation(animation);


        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mValueAnimator = doAnima();
                startObject().start();
            }
        });

        findViewById(R.id.btn_stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mValueAnimator.cancel();
            }
        });
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
//                mTv.setBackgroundColor(value);
                mTv.layout(mCurrentPoint.x, mCurrentPoint.y, mCurrentPoint.x + mTv.getWidth(), mCurrentPoint.y + mTv.getHeight());

            }
        });
        animator.start();
        return animator;
    }

    private ObjectAnimator startObject() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mTv, "alpha", 1, 0, 1);
        objectAnimator.setDuration(2000);
        return objectAnimator;
    }
}