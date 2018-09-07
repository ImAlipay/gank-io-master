package com.gank.io.retrofitdemo.mvp.view;

import com.gank.io.retrofitdemo.base.BaseView;
import com.gank.io.retrofitdemo.bean.GankFLEntities;

import java.util.List;

/**
 * ${DESC}
 *
 * @author 91660
 * @date 2018/9/6
 */
public interface MainView extends BaseView {

    void getList(List<GankFLEntities> list);

}