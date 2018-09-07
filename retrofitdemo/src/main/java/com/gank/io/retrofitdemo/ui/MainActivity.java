package com.gank.io.retrofitdemo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.gank.io.retrofitdemo.R;
import com.gank.io.retrofitdemo.base.BaseMVPActivity;
import com.gank.io.retrofitdemo.base.BaseView;
import com.gank.io.retrofitdemo.bean.GankFLEntities;
import com.gank.io.retrofitdemo.component.DaggerGankIoComponent;
import com.gank.io.retrofitdemo.mvp.presenter.MianPresenter;
import com.gank.io.retrofitdemo.mvp.view.MainView;

import java.util.List;

import butterknife.BindView;

/**
 * MainActivity:
 *
 * @author zpl
 * @date 2018/7/24
 */
public class MainActivity extends BaseMVPActivity<MianPresenter> implements MainView {

    public static final String TAG = "MainActivity";
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    @Override
    protected void initContentView(Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initComponent() {
        DaggerGankIoComponent.builder().build().inject(this);
        mPresenter.setView(this);
    }

    @Override
    public void getList(List<GankFLEntities> list) {

    }
}