package com.gank.io.retrofitdemo.base;

/**
 * presenter基类
 *
 * @author zpl
 * @date $date$
 */
public interface BasePresenter<T extends BaseView> {

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onResume() method.
     */
    void resume();

    void pause();

    void stop();

    void destroy();

    void setView(T view);

}
