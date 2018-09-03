package com.gank.io.retrofitdemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import javax.inject.Inject;

/**
 * BaseMVPActivity:
 *
 * @author zpl
 * @date 2018/9/3
 */
public abstract class BaseMVPActivity<T extends BasePresenter> extends BaseAvtivity implements BaseView {

    @Inject
    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        initComponent();
        super.onCreate(savedInstanceState);
    }

    protected abstract void initComponent();

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.pause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.destroy();
    }

    @Override
    public void onSuccess(String msg) {

    }

    @Override
    public void onError(String msg) {

    }

    @Override
    public void showloading() {

    }

    @Override
    public void hideloading() {

    }

    @Override
    public void onThrowable(Throwable a) {

    }
}
