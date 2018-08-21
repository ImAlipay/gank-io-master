package com.gank.io.retrofitdemo.dragger2;

import android.util.Log;

import com.gank.io.retrofitdemo.MainActivity;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * $ClassName$:
 *
 * @author zpl
 * @date $date$
 */
@Module(includes = HttpModule.class)
public class UserModule {

    @Provides
    public ApiService provideApiService(OkHttpClient client) {
        Log.e(MainActivity.TAG, client.toString());
        return new ApiService(client);
    }

}