package com.gank.io.retrofitdemo.module;

import android.location.LocationManager;

import com.gank.io.retrofitdemo.PerActivity;
import com.gank.io.retrofitdemo.Test3;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * $ClassName$:
 *
 * @author zpl
 * @date $date$
 */
@Module
public class CMoudle {

    @Named("Test3")
    @PerActivity
    @Provides
    Test3 provideBody(LocationManager locationManager) {
        return new Test3(locationManager);
    }

    @Named("Test4")
    @PerActivity
    @Provides
    Test3 provideTest(LocationManager locationManager) {
        return new Test3(locationManager);
    }

}
