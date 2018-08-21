package com.gank.io.retrofitdemo.dragger2.ano;

import javax.inject.Inject;

/**
 * $ClassName$:
 *
 * @author zpl
 * @date $date$
 */
public class Baozi {

    private String baozi;

    @Inject
    public Baozi() {
        baozi = "豆沙包";
    }


    public Baozi(String baozi) {
        this.baozi = baozi;
    }

    @Override
    public String toString() {
        return baozi;
    }
}
