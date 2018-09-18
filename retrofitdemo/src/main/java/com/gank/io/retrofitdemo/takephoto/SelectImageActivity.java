package com.gank.io.retrofitdemo.takephoto;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;


import com.gank.io.retrofitdemo.R;
import com.gank.io.retrofitdemo.base.BaseAvtivity;

import java.util.List;


/**
 * Created by huanghaibin_dev
 * on 2016/7/13.
 * <p>
 * Changed by qiujuer
 * on 2016/09/01
 */
@SuppressWarnings("All")
public class SelectImageActivity extends BaseAvtivity implements SelectImageContract.Operator {

    private static final int RC_CAMERA_PERM = 0x03;
    private static final int RC_EXTERNAL_STORAGE = 0x04;
    public static final String KEY_CONFIG = "config";

    private static SelectOptions mOption;
    private SelectImageContract.View mView;

    public static void show(Context context, SelectOptions options) {
        mOption = options;
        context.startActivity(new Intent(context, SelectImageActivity.class));
    }

    @Override
    public void onBack() {
        //后退键
        onSupportNavigateUp();
    }

    @Override
    public void setDataView(SelectImageContract.View view) {
        mView = view;
    }

    @Override
    protected void onDestroy() {
        mOption = null;
        super.onDestroy();
    }


    @Override
    public boolean shouldShowRequestPermissionRationale(String permission) {
        return false;
    }


    private void removeView() {
        SelectImageContract.View view = mView;
        if (view == null) {
            return;
        }
        try {
            getSupportFragmentManager()
                    .beginTransaction()
                    .remove((Fragment) view)
                    .commitNowAllowingStateLoss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleView() {
        try {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_content, SelectFragment.newInstance(mOption))
                    .commitNowAllowingStateLoss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        handleView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_select_image;
    }
}
