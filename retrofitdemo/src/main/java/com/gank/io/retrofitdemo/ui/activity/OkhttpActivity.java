package com.gank.io.retrofitdemo.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.gank.io.retrofitdemo.R;
import com.gank.io.retrofitdemo.base.BaseAvtivity;

import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkhttpActivity extends BaseAvtivity {

    @BindView(R.id.btn_get)
    Button mGet;
    private final OkHttpClient client = new OkHttpClient();
    private String TAG = OkhttpActivity.class.getName();

    @Override
    protected void initContentView(Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_okhttp;
    }

    @OnClick({R.id.btn_get, R.id.btn_post})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.btn_get:
                get();
                break;
            case R.id.btn_post:

                break;

            default:
        }
    }


    private void get() {
        Request request = new Request.Builder()
                .get()
                .url("https://publicobject.com/helloworld.txt")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "Failure " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, Thread.currentThread().getName()+"     "+response.body().string());
                mGet.setText("Succeful");
            }
        });
    }
}
