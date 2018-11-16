package com.chinasoft.customercontrol;

import android.animation.TypeEvaluator;

/**
 * ${DESC}
 *
 * @author 91660
 * @date 2018/11/9
 */
public class CharEvaluator implements TypeEvaluator<Character> {
    @Override
    public Character evaluate(float fraction, Character startValue, Character endValue) {
        int startInt = (int) startValue;
        int endInt = (int) endValue;
        int curInt = (int) (startInt + fraction * (endInt - startInt));
        char result = (char) curInt;
        return result;
    }
}
