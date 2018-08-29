package com.gank.io.retrofitdemo.module;

import android.content.Context;
import android.location.LocationManager;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;

/**
 * $ClassName$:
 *
 * @author zpl
 * @date $date$
 */
@Module
public class ModuleA {

    @Provides
    @IntoSet
    static String provideOneString(LocationManager locationManager, Context context) {
        return "ABC";
    }




}
