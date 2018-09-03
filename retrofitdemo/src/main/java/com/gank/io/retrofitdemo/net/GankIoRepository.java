package com.gank.io.retrofitdemo.net;

import com.gank.io.retrofitdemo.bean.GankFLEntities;

import java.util.List;

import io.reactivex.Observable;

/**
 * ${DESC}
 *
 * @author 91660
 * @date 2018/9/3
 */
public interface GankIoRepository {
    Observable<BaseGankIoResp<List<GankFLEntities>>> getFlist(int rows, int pageNum);

    Observable<BaseGankIoResp<List<GankFLEntities>>> getFlGrils(int num);
}
