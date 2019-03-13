package com.example.hencoder;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private View mView;
    private ImageView mImageView;
    private View mView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mView = findViewById(R.id.view);
        mImageView = findViewById(R.id.imageview);
        mView2 = findViewById(R.id.view2);

//        animation();
//        propertyAnimation();
    }

    /**
     * 视图动画（View Animation）,包括补间动画（Tween Animation）和逐帧动画（Frame Animation）
     */
    private void animation() {
        //xml形式
//        Animation animation = AnimationUtils.loadAnimation(this, R.anim.view_anim_set);
//        mView.startAnimation(animation);

        //代码形式
        ScaleAnimation scaleAnimation = new ScaleAnimation(0f, 1.4f, 0f, 1.4f);
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 1.0f);
        TranslateAnimation translateAnimation = new TranslateAnimation(0, 100, 0, 100);
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setDuration(5000);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(rotateAnimation);
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(translateAnimation);
        //插值器 控制动画变化速率
        animationSet.setInterpolator(new LinearInterpolator());
        mView.startAnimation(animationSet);

        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        //逐帧动画
        AnimationDrawable anim = (AnimationDrawable) mImageView.getDrawable();
        anim.start();
    }

    /**
     * 属性动画（Property Animation）包括ValueAnimator和ObjectAnimator
     */
    private void propertyAnimation() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 400);
        valueAnimator.setDuration(5000);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                mView2.layout(value, value, value + mView2.getWidth(), value + mView2.getHeight());
            }
        });
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        valueAnimator.start();

    }

}