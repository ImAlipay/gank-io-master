package com.gank.io.retrofitdemo.component;

import android.location.LocationManager;

import com.gank.io.retrofitdemo.MainActivity;
import com.gank.io.retrofitdemo.base.DemoApplication;
import com.gank.io.retrofitdemo.module.AndroidMoudle;

import javax.inject.Singleton;

import dagger.Component;

/**
 * $ClassName$:
 *
 * @author zpl
 * @date $date$
 */
@Singleton
@Component(modules = AndroidMoudle.class)
public interface ApplicationComponent {

    void inject(DemoApplication application);

//    void inject(MainActivity mainActivity);
    LocationManager getLocationManager();
}