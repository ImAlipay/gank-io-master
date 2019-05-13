package com.gank.io.retrofitdemo.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.gank.io.retrofitdemo.R;
import com.gank.io.retrofitdemo.base.BaseMVPActivity;
import com.gank.io.retrofitdemo.bean.GankFLEntities;
import com.gank.io.retrofitdemo.component.DaggerGankIoComponent;
import com.gank.io.retrofitdemo.mvp.presenter.MianPresenter;
import com.gank.io.retrofitdemo.mvp.view.MainView;
import com.gank.io.retrofitdemo.ui.adapter.MainAdapter;
import com.gank.io.retrofitdemo.utils.Constant;

import java.util.List;

import butterknife.BindView;

public class GankRecycleviewActivity extends BaseMVPActivity<MianPresenter> implements MainView {



    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    private int rows = 10;
    private int pageNum = 1;



    @Override
    protected void initContentView(Bundle savedInstanceState) {
        mPresenter.getList(rows, pageNum, this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_gank_recycleview;
    }

    @Override
    protected void initComponent() {
        DaggerGankIoComponent.builder()
                .applicationComponent(getApplicationComponent())
                .build().inject(this);
        mPresenter.setView(this);
    }

    @Override
    public void getList(List<GankFLEntities> list) {
        Log.e(Constant.TAG, list.toString());
        //刷新recyclerview
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(new MainAdapter(list, this));
    }

}
