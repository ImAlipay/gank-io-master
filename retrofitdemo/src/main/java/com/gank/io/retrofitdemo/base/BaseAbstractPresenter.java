package com.gank.io.retrofitdemo.base;

import android.content.Context;

import javax.inject.Inject;

/**
 * $ClassName$:
 *
 * @author zpl
 * @date $date$
 */
public class BaseAbstractPresenter<T extends BaseView> implements BasePresenter<T> {

    @Inject
    Context mContext;

    protected T mView;


    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void setView(T view) {
        this.mView = view;
    }
}
