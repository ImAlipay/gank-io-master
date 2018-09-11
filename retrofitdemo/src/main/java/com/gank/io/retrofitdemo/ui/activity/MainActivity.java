package com.gank.io.retrofitdemo.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.gank.io.retrofitdemo.R;
import com.gank.io.retrofitdemo.base.BaseMVPActivity;
import com.gank.io.retrofitdemo.base.BaseView;
import com.gank.io.retrofitdemo.bean.GankFLEntities;
import com.gank.io.retrofitdemo.component.DaggerGankIoComponent;
import com.gank.io.retrofitdemo.mvp.presenter.MianPresenter;
import com.gank.io.retrofitdemo.mvp.view.MainView;
import com.gank.io.retrofitdemo.ui.adapter.MainAdapter;
import com.gank.io.retrofitdemo.ui.adapter.MyAdapter;
import com.gank.io.retrofitdemo.ui.fragment.PagerFramgent;
import com.gank.io.retrofitdemo.utils.Constant;

import java.util.List;

import butterknife.BindView;

/**
 * MainActivity:
 *
 * @author zpl
 * @date 2018/7/24
 */
public class MainActivity extends BaseMVPActivity<MianPresenter> implements MainView {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    private int rows = 10;
    private int pageNum = 1;

    private ViewPager vp;
    private TabLayout tabLayout;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        mPresenter.getList(rows, pageNum, this);

        vp = (ViewPager) findViewById(R.id.vp);
        tabLayout= (TabLayout) findViewById(R.id.tab);
        //实例化适配器
        MyAdapter adapter=new MyAdapter(getSupportFragmentManager());
        //循环添加数据
        for (int i=0;i<4;i++){
            adapter.add(PagerFramgent.newInstance(i));
        }
        //viewpager设置适配器
        vp.setAdapter(adapter);
        //设置预加载的数量当然这个值越小越好
        vp.setOffscreenPageLimit(2);
        //给TabLayout设置ViewPager
        tabLayout.setupWithViewPager(vp);
        //设置Mode样式
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
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