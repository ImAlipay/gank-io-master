package com.gank.io.retrofitdemo.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gank.io.retrofitdemo.R;
import com.gank.io.retrofitdemo.base.BaseAvtivity;
import com.gank.io.retrofitdemo.ui.adapter.MyAdapter;
import com.gank.io.retrofitdemo.ui.fragment.PagerFramgent;

public class TakePhotoActivity extends BaseAvtivity {

    private ViewPager vp;
    private TabLayout tabLayout;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        vp = (ViewPager) findViewById(R.id.vp);
        tabLayout = (TabLayout) findViewById(R.id.tab);
        //实例化适配器
        MyAdapter adapter = new MyAdapter(getSupportFragmentManager());
        //循环添加数据
        for (int i = 0; i < 4; i++) {
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
        return R.layout.activity_take_photo;
    }
}
