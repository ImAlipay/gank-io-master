package com.gank.io.retrofitdemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.gank.io.retrofitdemo.component.ApplicationComponent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;

/**
 * $ClassName$:
 *
 * @author zpl
 * @date $date$
 */
public abstract class BaseAvtivity extends RxAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((DemoApplication) getApplication()).getComponent().inject(this);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initContentView(savedInstanceState);
    }

    public ApplicationComponent getApplicationComponent() {
        return ((DemoApplication) getApplication()).getComponent();
    }

    protected abstract void initContentView(Bundle savedInstanceState);

    protected abstract int getLayoutId();
}
