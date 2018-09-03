package com.gank.io.retrofitdemo.module;

import android.app.Activity;

import com.gank.io.retrofitdemo.anno.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * @author 91660
 * @desc:
 * @date ${date}
 */
@Module
public class ActivityModule {

    private final Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    @PerActivity
    Activity activity() {
        return mActivity;
    }

}