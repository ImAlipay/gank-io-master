package com.chinasoft.customercontrol;

import android.animation.TimeInterpolator;

/**
 * ${DESC}
 *
 * @author 91660
 * @date 2018/11/5
 */
public class MyInterpolator implements TimeInterpolator {
    @Override
    public float getInterpolation(float input) {
        return 1 - input;
    }
}
