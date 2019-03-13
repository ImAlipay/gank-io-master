package com.example.hencoder.anim;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.example.hencoder.R;

/**
 * ${DESC}
 *
 * @author 91660
 * @date 2019/3/13
 */
public class LoadingImageView extends AppCompatImageView {

    private int mCurImgIndex = 0;
    private static int mImgCount = 3;
    private int mTop;

    public LoadingImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 100, 0);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setDuration(2000);
        valueAnimator.setStartDelay(2000);
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int dx = (int) animation.getAnimatedValue();
                setTop(mTop - dx);
            }
        });

        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                setImageDrawable(getResources().getDrawable(R.mipmap.pic_1));
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                mCurImgIndex++;
                switch (mCurImgIndex % mImgCount) {
                    case 0:
                        setImageDrawable(getResources().getDrawable(R.mipmap.pic_1));
                        break;
                    case 1:
                        setImageDrawable(getResources().getDrawable(R.mipmap.pic_2));
                        break;
                    case 2:
                        setImageDrawable(getResources().getDrawable(R.mipmap.pic_3));
                        break;
                    default:
                        break;
                }
            }
        });
        valueAnimator.start();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mTop = top;
    }
}
