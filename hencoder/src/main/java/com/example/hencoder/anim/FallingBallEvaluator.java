package com.example.hencoder.anim;

import android.animation.TypeEvaluator;
import android.graphics.Point;

/**
 * ${DESC}
 *
 * @author 91660
 * @date 2019/3/14
 */
public class FallingBallEvaluator implements TypeEvaluator<Point> {

    private Point mPoint = new Point();

    @Override
    public Point evaluate(float fraction, Point startValue, Point endValue) {

        mPoint.x = (int) (startValue.x + fraction * (endValue.x - startValue.x));
        if (fraction * 2 < 1) {
            mPoint.y = (int) (startValue.y + fraction * 2 * (endValue.y - startValue.y));
        } else {
            mPoint.y = endValue.y;
        }
        return mPoint;
    }
}
