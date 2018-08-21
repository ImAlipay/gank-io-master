package com.gank.io.retrofitdemo.dragger2;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * $ClassName$:
 *
 * @author zpl
 * @date $date$
 */
@Module
public class HttpModule {

    @Provides
    public OkHttpClient getClient() {
        return new OkHttpClient().newBuilder().build();
    }

}