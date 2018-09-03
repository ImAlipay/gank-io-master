package com.gank.io.retrofitdemo.base;

import android.app.Application;

import com.gank.io.retrofitdemo.component.ApplicationComponent;
import com.gank.io.retrofitdemo.component.DaggerApplicationComponent;
import com.gank.io.retrofitdemo.module.AndroidMoudle;

/**
 * $ClassName$:
 *
 * @author zpl
 * @date $date$
 */
public class DemoApplication extends Application {

    private ApplicationComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mComponent = DaggerApplicationComponent.builder().androidMoudle(new AndroidMoudle(this)).build();
    }

    /**
     * 提供component
     *
     * @return
     */
    public ApplicationComponent getComponent() {
        return mComponent;
    }

}