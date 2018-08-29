package com.gank.io.retrofitdemo.component;

import com.gank.io.retrofitdemo.MainActivity;
import com.gank.io.retrofitdemo.PerActivity;
import com.gank.io.retrofitdemo.module.CMoudle;

import dagger.Component;

/**
 * $ClassName$:
 *
 * @author zpl
 * @date $date$
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = CMoudle.class)
public interface MainActivityComponent {

    void inject(MainActivity mainActivity);
}
