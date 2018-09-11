package com.gank.io.retrofitdemo.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gank.io.retrofitdemo.R;

/**
 * @author 91660
 * @date 2018/9/11
 */
public class RecyclerViewHeader extends LinearLayout {

    public static final int STATE_NORMAL = 0;
    public static final int STATE_READY = 1;
    public static final int STATE_REFRESHING = 2;

    /**
     * 当前状态
     */
    private int mState = STATE_NORMAL;
    private LinearLayout mContainer;
    private RelativeLayout mRealityContent;
    private TextView mHintTextView;
    private TextView mTitleTextView;


    public RecyclerViewHeader(Context context) {
        this(context, null);
    }

    public RecyclerViewHeader(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RecyclerViewHeader(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        //初始情况，设置下拉刷新高度为0
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        mContainer = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.pullrefrefh_recyclerview_header, null);
        addView(mContainer,params);

        mRealityContent = (RelativeLayout) mContainer.findViewById(R.id.pullRefresh_reality_content);
//        mArrowImageView = (ImageView) mContainer.findViewById(R.id.pullRefresh_arrow);
        mHintTextView = (TextView) mContainer.findViewById(R.id.pullRefresh_text);
        mTitleTextView = (TextView) mContainer.findViewById(R.id.pullRefresh_title);

    }


}
