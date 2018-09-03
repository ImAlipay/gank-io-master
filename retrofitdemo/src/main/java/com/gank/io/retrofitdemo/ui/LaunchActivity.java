package com.gank.io.retrofitdemo.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import com.gank.io.retrofitdemo.R;
import com.gank.io.retrofitdemo.base.BaseMVPActivity;
import com.gank.io.retrofitdemo.component.DaggerGankIoComponent;
import com.gank.io.retrofitdemo.module.ActivityModule;
import com.gank.io.retrofitdemo.module.GankIoModule;
import com.gank.io.retrofitdemo.mvp.presenter.LaunchPresenter;
import com.gank.io.retrofitdemo.mvp.view.LaunchView;
import com.gank.io.retrofitdemo.utils.Constant;

public class LaunchActivity extends BaseMVPActivity<LaunchPresenter> implements LaunchView {

    @Override
    protected void initComponent() {
        DaggerGankIoComponent.builder().applicationComponent(getApplicationComponent())
                .gankIoModule(new GankIoModule())
                .activityModule(new ActivityModule(this))
                .build().inject(this);
    }

    @SuppressLint("CheckResult")
    @Override
    protected void initContentView(Bundle savedInstanceState) {
        mPresenter.getList(1, this);
        delay();
    }

    private void delay() {
//        io.reactivex.Observable.timer(3, TimeUnit.MINUTES).subscribe(new Consumer<Long>() {
//            @Override
//            public void accept(Long aLong) throws Exception {
//                Log.e(Constant.TAG, aLong + "");
//                Intent intent = new Intent(LaunchActivity.this, MainActivity.class);
//                startActivity(intent);
//            }
//        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_launch;
    }

    @Override
    public void getGirls(String url) {
        Log.e(Constant.TAG, url);
    }

}