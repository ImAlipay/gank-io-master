package com.example.hencoder.anim;

import android.content.Context;
import android.graphics.Point;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * ${DESC}
 *
 * @author 91660
 * @date 2019/3/14
 */
public class FallingBallImageView extends AppCompatImageView {
    public FallingBallImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setFallingPos(Point pos) {
        layout(pos.x, pos.y, pos.x + getWidth(), pos.y + getHeight());
    }
}
