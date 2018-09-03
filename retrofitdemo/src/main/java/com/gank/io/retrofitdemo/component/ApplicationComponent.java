package com.gank.io.retrofitdemo.component;

import android.content.Context;

import com.gank.io.retrofitdemo.base.BaseAvtivity;
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

    void inject(BaseAvtivity application);

    Context context();
}