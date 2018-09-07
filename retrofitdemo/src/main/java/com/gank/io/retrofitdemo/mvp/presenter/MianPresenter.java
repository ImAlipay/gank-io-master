package com.gank.io.retrofitdemo.mvp.presenter;

import android.util.Log;

import com.gank.io.retrofitdemo.base.BaseAbstractPresenter;
import com.gank.io.retrofitdemo.base.BaseAvtivity;
import com.gank.io.retrofitdemo.base.BaseView;
import com.gank.io.retrofitdemo.bean.GankFLEntities;
import com.gank.io.retrofitdemo.mvp.view.MainView;
import com.gank.io.retrofitdemo.net.GankIoService;
import com.gank.io.retrofitdemo.utils.Constant;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static android.support.constraint.Constraints.TAG;

/**
 * ${DESC}
 *
 * @author 91660
 * @date 2018/9/6
 */
public class MianPresenter extends BaseAbstractPresenter<MainView> {

    @Inject
    GankIoService mGankIoService;

    @Inject
    public MianPresenter() {
    }

    public void getList(int rows, int page, BaseAvtivity bac) {
        mGankIoService.execute(new Observer<List<GankFLEntities>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<GankFLEntities> o) {
                Log.e(Constant.TAG, o.toString());
                mView.getList(o);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        }, mGankIoService.getList(rows, page), bac);
    }

}
