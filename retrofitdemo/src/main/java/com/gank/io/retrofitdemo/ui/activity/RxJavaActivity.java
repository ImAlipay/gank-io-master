package com.gank.io.retrofitdemo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.gank.io.retrofitdemo.R;
import com.gank.io.retrofitdemo.base.BaseAvtivity;

import java.util.Observable;

import butterknife.OnClick;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RxJavaActivity extends BaseAvtivity {
    private String TAG = RxJavaActivity.class.getSimpleName();

    @Override
    protected void initContentView(Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_rxjava_ex;
    }

    @OnClick({R.id.btn})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.btn:
                io.reactivex.Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                        emitter.onNext(1);
                        emitter.onNext(2);
                        emitter.onNext(3);
                        emitter.onNext(4);
                        emitter.onComplete();
                    }
                }).subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "接收到的信息：" + integer);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
                break;
            default:
        }
    }
}
