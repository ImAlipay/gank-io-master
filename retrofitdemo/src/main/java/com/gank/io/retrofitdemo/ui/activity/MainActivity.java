package com.gank.io.retrofitdemo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import com.gank.io.retrofitdemo.R;
import com.gank.io.retrofitdemo.base.BaseAvtivity;
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
import butterknife.OnClick;

/**
 * MainActivity:
 *
 * @author zpl
 * @date 2018/7/24
 */
public class MainActivity extends BaseAvtivity {

    @OnClick({R.id.btn_take_photo, R.id.btn_gank_beauty,R.id.btn_okhttp,R.id.btn_rxjava})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.btn_take_photo:
                startActivity(new Intent(this,TakePhotoActivity.class));
                break;
            case R.id.btn_gank_beauty:
                startActivity(new Intent(this,GankRecycleviewActivity.class));
                break;
                case R.id.btn_okhttp:
                startActivity(new Intent(this,OkhttpActivity.class));
                break;
            case R.id.btn_rxjava:
                startActivity(new Intent(this,RxJavaActivity.class));
                break;
            default:
        }
    }

    @Override
    protected void initContentView(Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
}