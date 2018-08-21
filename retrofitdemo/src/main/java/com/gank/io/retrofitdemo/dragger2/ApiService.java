package com.gank.io.retrofitdemo.dragger2;

import android.content.Context;
import android.util.Log;

import javax.inject.Inject;

import okhttp3.OkHttpClient;

public class ApiService {

    OkHttpClient mOkHttpClient;

    public static final String TAG = "Dragger";

    @Inject
    public ApiService(OkHttpClient client) {
        mOkHttpClient = client;
    }

    public void register() {
        Log.e(TAG, "register............");
    }

}