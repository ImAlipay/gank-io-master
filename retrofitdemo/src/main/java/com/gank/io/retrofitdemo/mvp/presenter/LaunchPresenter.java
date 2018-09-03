package com.gank.io.retrofitdemo.mvp.presenter;

import android.util.Log;

import com.gank.io.retrofitdemo.base.BaseAbstractPresenter;
import com.gank.io.retrofitdemo.base.BaseAvtivity;
import com.gank.io.retrofitdemo.mvp.view.LaunchView;
import com.gank.io.retrofitdemo.net.GankIoService;
import com.gank.io.retrofitdemo.utils.Constant;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * $ClassName$:
 *
 * @author zpl
 * @date $date$
 */
public class LaunchPresenter extends BaseAbstractPresenter<LaunchView> {

    @Inject
    GankIoService mGankIoService;

    @Inject
    public LaunchPresenter() {}

    public void getList(int num, BaseAvtivity avtivity) {
        mGankIoService.execute(new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object o) {
                Log.e(Constant.TAG, o.toString());
                mView.getGirls(o.toString());

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        }, mGankIoService.getGrils(num), avtivity);
    }
}
