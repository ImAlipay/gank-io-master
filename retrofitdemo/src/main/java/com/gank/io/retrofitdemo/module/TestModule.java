package com.gank.io.retrofitdemo.module;

import com.gank.io.retrofitdemo.Test;
import com.gank.io.retrofitdemo.TestImp;

import dagger.Module;
import dagger.Provides;

/**
 * ${DESC}
 *
 * @author 91660
 * @date 2018/9/5
 */
@Module
public class TestModule {

    @Provides
    Test provideTest(TestImp testImp) {
        return testImp;
    }

}
