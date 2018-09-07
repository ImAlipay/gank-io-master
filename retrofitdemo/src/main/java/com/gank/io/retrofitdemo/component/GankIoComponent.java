package com.gank.io.retrofitdemo.component;

import com.gank.io.retrofitdemo.anno.PerActivity;
import com.gank.io.retrofitdemo.module.ActivityModule;
import com.gank.io.retrofitdemo.module.GankIoModule;
import com.gank.io.retrofitdemo.module.TestModule;
import com.gank.io.retrofitdemo.ui.LaunchActivity;
import com.gank.io.retrofitdemo.ui.MainActivity;

import dagger.Component;

/**
 * @author 91660
 * @desc:
 * @date ${date}
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, GankIoModule.class, TestModule.class})
public interface GankIoComponent {
    void inject(LaunchActivity activity);

    void inject(MainActivity activity);
}
