package com.gank.io.retrofitdemo.dragger2;

import com.gank.io.retrofitdemo.MainActivity;

import dagger.Component;
import dagger.Module;

/**
 * $ClassName$:
 *
 * @author zpl
 * @date $date$
 */
@Component(modules = UserModule.class)
public interface UseCompoent {

    void inject(MainActivity mainActivity);

}
