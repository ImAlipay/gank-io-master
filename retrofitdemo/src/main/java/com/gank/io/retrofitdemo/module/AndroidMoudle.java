package com.gank.io.retrofitdemo.module;

import android.content.Context;

import com.gank.io.retrofitdemo.base.DemoApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * $ClassName$:
 *
 * @author zpl
 * @date $date$
 */
@Module
public class AndroidMoudle {

    private final DemoApplication mDemoApplication;

    public AndroidMoudle(DemoApplication application) {
        mDemoApplication = application;
    }

    @Provides
    @Singleton
    Context getApplication() {
        return mDemoApplication;
    }


}