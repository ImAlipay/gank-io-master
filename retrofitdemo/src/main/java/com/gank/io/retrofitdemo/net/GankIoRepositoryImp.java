package com.gank.io.retrofitdemo.net;

import com.gank.io.retrofitdemo.bean.GankFLEntities;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * ${DESC}
 *
 * @author 91660
 * @date 2018/9/3
 */
public class GankIoRepositoryImp implements GankIoRepository {

    @Inject
    public GankIoRepositoryImp() {
    }

    @Override
    public Observable<BaseGankIoResp<List<GankFLEntities>>> getFlist(int rows, int pageNum) {
        return RetrofitFactory.getInstance().create(GankIoApi.class).getFlList(rows, pageNum);
    }

    @Override
    public Observable<BaseGankIoResp<List<GankFLEntities>>> getFlGrils(int num) {
        return RetrofitFactory.getInstance().create(GankIoApi.class).getGirls(num);
    }
}
