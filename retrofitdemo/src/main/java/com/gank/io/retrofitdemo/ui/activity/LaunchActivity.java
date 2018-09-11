package com.gank.io.retrofitdemo.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.gank.io.retrofitdemo.R;
import com.gank.io.retrofitdemo.base.BaseMVPActivity;
import com.gank.io.retrofitdemo.component.DaggerGankIoComponent;
import com.gank.io.retrofitdemo.glide.GlideApp;
import com.gank.io.retrofitdemo.module.ActivityModule;
import com.gank.io.retrofitdemo.module.GankIoModule;
import com.gank.io.retrofitdemo.mvp.presenter.LaunchPresenter;
import com.gank.io.retrofitdemo.mvp.view.LaunchView;
import com.gank.io.retrofitdemo.utils.Constant;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

/**
 * LaunchActivity:
 *
 * @author zpl
 * @date 2018/9/4
 */
public class LaunchActivity extends BaseMVPActivity<LaunchPresenter> implements LaunchView {

    @BindView(R.id.iv)
    ImageView mImageView;

    @Override
    protected void initComponent() {
        DaggerGankIoComponent.builder().applicationComponent(getApplicationComponent())
                .gankIoModule(new GankIoModule())
                .activityModule(new ActivityModule(this))
                .build().inject(this);
        mPresenter.setView(this);
    }

    @SuppressLint("CheckResult")
    @Override
    protected void initContentView(Bundle savedInstanceState) {
        mPresenter.getList(1, this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_launch;
    }

    @Override
    public void getGirls(String url) {
        Log.e(Constant.TAG, url);
        GlideApp.with(this).load(url).into(mImageView);
        delay();
    }

    private void delay() {
        io.reactivex.Observable.timer(3, TimeUnit.SECONDS).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                Log.e(Constant.TAG, aLong + "");
                Intent intent = new Intent(LaunchActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}