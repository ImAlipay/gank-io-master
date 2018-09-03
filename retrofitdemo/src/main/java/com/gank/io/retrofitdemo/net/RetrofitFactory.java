package com.gank.io.retrofitdemo.net;


import com.gank.io.retrofitdemo.utils.Constant;

import java.util.concurrent.TimeUnit;

import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.OkHttpClient;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Retrofit工厂类
 */
public class RetrofitFactory {

    public static String BASE_URL = Constant.SERVER_ADDRESS;

    private static volatile RetrofitFactory sInstance;

    private Retrofit retrofit;

    private RetrofitFactory() {
        retrofit = new Retrofit.Builder()
                //访问主机地址
                .baseUrl(BASE_URL)
                //解析方式
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(genericClient())
                .build();
    }


    public static RetrofitFactory getInstance() {
        if (null == sInstance) {
            synchronized (RetrofitFactory.class) {
                if (null == sInstance) {
                    sInstance = new RetrofitFactory();
                }
            }
        }
        return sInstance;
    }

    public <T> T create(final Class<T> service) {
        return retrofit.create(service);
    }

    private static HttpLoggingInterceptor initLoggingInterceptor() {
        HttpLoggingInterceptor
                httpLoggingInterceptor = new HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT);
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

    private static OkHttpClient genericClient() {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(initLoggingInterceptor())
                .addNetworkInterceptor(new HttpLoggingInterceptor(new HttpInterceptor()))
//                .addInterceptor(new TimeoutIntercepter())
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();
        return httpClient;
    }

}