package com.example.hencoder.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;

import com.example.hencoder.R;
import com.example.hencoder.Utils;

/**
 * ${DESC}
 *
 * @author 91660
 * @date 2019/3/15
 */
public class MaterialEditTextView extends AppCompatEditText {

    private static final float TEXT_SIZE = Utils.dp2px(12);
    private static final float TEXT_MARGIN = Utils.dp2px(8);
    private static final int TEXT_VERTICAL_OFFSET = (int) Utils.dp2px(22);
    private static final int TEXT_HORIZONTAL_OFFSET = (int) Utils.dp2px(5);
    private static final int TEXT_ANIMATION_OFFSET = (int) Utils.dp2px(16);

    private boolean floatingLabelShown;
    private boolean mUseFloatingLabel;
    private Paint mPaint;
    private float mFraction;
    Rect mRect = new Rect();
    private ObjectAnimator mAnimator;

    public MaterialEditTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MaterialEditTextView);
        mUseFloatingLabel = typedArray.getBoolean(R.styleable.MaterialEditTextView_useFloatingLabel, true);
        typedArray.recycle();
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextSize(TEXT_SIZE);

        onUseFloatingLabelChanged();
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (mUseFloatingLabel) {
                    if (floatingLabelShown && TextUtils.isEmpty(s)) {
                        floatingLabelShown = false;
                        getAnimator().reverse();
                    } else if (!floatingLabelShown && !TextUtils.isEmpty(s)) {
                        floatingLabelShown = true;
                        getAnimator().start();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private ObjectAnimator getAnimator() {
        if (mAnimator == null) {
            mAnimator = ObjectAnimator.ofFloat(MaterialEditTextView.this, "floatingLabelFraction", 0, 1);
        }
        return mAnimator;
    }

    public void setFloatingLabelFraction(float fraction) {
        mFraction = fraction;
        invalidate();
    }

    public float getFloatingLabelFraction() {
        return mFraction;
    }

    private void onUseFloatingLabelChanged() {
        getBackground().getPadding(mRect);
        if (mUseFloatingLabel) {
            setPadding(getPaddingLeft(), (int) (mRect.top + TEXT_SIZE + TEXT_MARGIN), getPaddingRight(), getPaddingBottom());
        } else {
            setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom());
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.parseColor("#008577"));
        super.onDraw(canvas);
        mPaint.setAlpha((int) (0xff * mFraction));
        float offset = TEXT_ANIMATION_OFFSET * (1 - mFraction);
        canvas.drawText(getHint().toString(), TEXT_HORIZONTAL_OFFSET, TEXT_VERTICAL_OFFSET + offset, mPaint);
    }

}