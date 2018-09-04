package com.gank.io.retrofitdemo.net;

import com.gank.io.retrofitdemo.base.BaseAvtivity;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.ActivityEvent;

import org.reactivestreams.Subscription;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author 91660
 * @desc:
 * @date ${date}
 */
public abstract class BaseService {

    public void execute(Observer schedulers, Observable observable, BaseAvtivity act) {
         observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycle.bindUntilEvent(act.lifecycle(), ActivityEvent.DESTROY))
                .subscribe(schedulers);
    }


}