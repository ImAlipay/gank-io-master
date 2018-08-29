package com.gank.io.retrofitdemo;

import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * $ClassName$:
 *
 * @author zpl
 * @date $date$
 */
@Singleton
public class Test {

    private Context mContext;

    @Inject
    public Test(Context context) {
        mContext = context;
    }

}
