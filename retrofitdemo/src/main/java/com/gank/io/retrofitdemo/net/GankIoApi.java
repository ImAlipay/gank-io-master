package com.gank.io.retrofitdemo.net;

import com.gank.io.retrofitdemo.bean.GankFLEntities;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * ${DESC}
 *
 * @author 91660
 * @date 2018/9/3
 */
public interface GankIoApi {

    /**
     * 分类数据: http://gank.io/api/data/数据类型/请求个数/第几页
     * 数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
     * 请求个数： 数字，大于0
     * 第几页：数字，大于0
     * @param rows
     * @param pageNum
     * @return
     */
    @GET("福利/{rows}/{pageNum}")
    Observable<BaseGankIoResp<List<GankFLEntities>>> getFlList(@Path("rows") int rows, @Path("pageNum") int pageNum);

    @GET("http://gank.io/api/random/data/福利/{num}")
    Observable<BaseGankIoResp<List<GankFLEntities>>> getGirls(@Path("num") int num);
}
