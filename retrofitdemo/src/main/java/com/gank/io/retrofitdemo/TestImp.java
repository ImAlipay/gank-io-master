package com.gank.io.retrofitdemo;

import android.util.Log;

import com.gank.io.retrofitdemo.utils.Constant;

import javax.inject.Inject;

/**
 * ${DESC}
 *
 * @author 91660
 * @date 2018/9/4
 */
public class TestImp extends Test {

    @Inject
    public TestImp() {
    }

    @Override
    public void show() {
        Log.e(Constant.TAG, "我是来测试的");
    }
}
