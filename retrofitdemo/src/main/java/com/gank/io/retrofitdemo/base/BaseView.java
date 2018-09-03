package com.gank.io.retrofitdemo.base;


/**
 * BaseView:
 *
 * @author zpl
 * @date 2018/8/31
 */
public interface BaseView {

    void showloading();

    void hideloading();

    void onError(String msg);

    void onSuccess(String msg);

    void onThrowable(Throwable a);

}
