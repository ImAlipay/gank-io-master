package com.gank.io.retrofitdemo.dragger2.ano;

import dagger.Module;
import dagger.Provides;

/**
 * $ClassName$:
 *
 * @author zpl
 * @date $date$
 */
@Module
public class ShangjiaAModule {

    @Provides
    public Baozi providerBaozi() {
        return new Baozi("小笼包");
    }

    @Provides
    public Noddle providerNoddle() {
        return new Noddle();
    }

    @Provides
    public int provideCode() {
        return 123455265;
    }

    @Provides
    public String providerString() {
        return "haha";
    }
}