package com.gank.io.retrofitdemo.net;

import android.util.Log;

import com.gank.io.retrofitdemo.utils.Constant;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * ${DESC}
 *
 * @author 91660
 * @date 2018/9/3
 */
class HttpInterceptor implements HttpLoggingInterceptor.Logger {

    @Override
    public void log(String message) {
        Log.e(Constant.TAG, message);
    }
}
