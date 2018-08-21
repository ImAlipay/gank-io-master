package com.gank.io.retrofitdemo.dragger2.ano;

import android.util.Log;

import javax.inject.Inject;

/**
 * $ClassName$:
 *
 * @author zpl
 * @date $date$
 */
public class Zhainan {

    @Inject
    Baozi mBaozi;

    @Inject
    Noddle mNoddle;

    @Inject
    String meida;

    @Inject
    public Zhainan() {
    }

    public void eat() {
        Log.e("Zhainan", mBaozi.toString() + mNoddle.toString() + meida);
    }

}