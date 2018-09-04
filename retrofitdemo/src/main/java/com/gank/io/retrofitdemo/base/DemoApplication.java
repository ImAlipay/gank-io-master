package com.gank.io.retrofitdemo.base;

import android.app.Application;

import com.gank.io.retrofitdemo.component.ApplicationComponent;
import com.gank.io.retrofitdemo.component.DaggerApplicationComponent;
import com.gank.io.retrofitdemo.module.AndroidMoudle;
import com.squareup.leakcanary.LeakCanary;

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
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
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