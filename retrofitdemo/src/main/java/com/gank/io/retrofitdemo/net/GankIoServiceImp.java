package com.gank.io.retrofitdemo.net;

import com.gank.io.retrofitdemo.bean.GankFLEntities;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * ${DESC}
 *
 * @author 91660
 * @date 2018/9/3
 */
public class GankIoServiceImp extends GankIoService {

    @Inject
    GankIoRepository mGankIoRepository;

    @Inject
    public GankIoServiceImp() {
    }

    @Override
    public Observable<List<GankFLEntities>> getList(int rows, int pageNum) {
        return mGankIoRepository.getFlist(rows, pageNum).flatMap(new BaseGankIoFunc());
    }

    @Override
    public Observable<String> getGrils(int num) {
        return mGankIoRepository.getFlGrils(num).flatMap(new Function<BaseGankIoResp<List<GankFLEntities>>, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(BaseGankIoResp<List<GankFLEntities>> listBaseGankIoResp) throws Exception {
                return Observable.just(listBaseGankIoResp.getResults()).flatMap(new Function<List<GankFLEntities>, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(List<GankFLEntities> gankFLEntities) throws Exception {
                        return Observable.just(gankFLEntities.get(0).getUrl());
                    }
                });
            }
        });
    }


}