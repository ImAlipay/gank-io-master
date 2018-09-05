package com.gank.io.retrofitdemo.module;

import com.gank.io.retrofitdemo.Test;
import com.gank.io.retrofitdemo.TestImp;
import com.gank.io.retrofitdemo.net.GankIoRepository;
import com.gank.io.retrofitdemo.net.GankIoRepositoryImp;
import com.gank.io.retrofitdemo.net.GankIoService;
import com.gank.io.retrofitdemo.net.GankIoServiceImp;

import dagger.Module;
import dagger.Provides;

/**
 * ${DESC}
 *
 * @author 91660
 * @date 2018/9/3
 */
@Module
public class GankIoModule {

    @Provides
    GankIoRepository provideGankIoRepository(GankIoRepositoryImp serviceImp) {
        return serviceImp;
    }

    @Provides
    GankIoService provideGankIoService(GankIoServiceImp repository) {
        return repository;
    }

}
