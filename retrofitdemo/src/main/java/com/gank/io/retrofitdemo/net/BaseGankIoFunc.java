package com.gank.io.retrofitdemo.net;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * ${DESC}
 *
 * @author 91660
 * @date 2018/9/3
 */
public class BaseGankIoFunc<T> implements Function<BaseGankIoResp<T>, Observable<T>> {

    @Override
    public Observable<T> apply(BaseGankIoResp<T> tBaseGankIoResp) throws Exception {
        return Observable.just(tBaseGankIoResp.getResults());
    }
}
