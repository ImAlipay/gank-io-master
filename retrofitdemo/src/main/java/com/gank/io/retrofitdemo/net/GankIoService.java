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
public abstract class GankIoService extends BaseService {

    public abstract Observable<List<GankFLEntities>> getList(int rows, int pageNum);

    public abstract Observable<String> getGrils(int num);
}
