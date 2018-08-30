package com.gank.io.retrofitdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.gank.io.retrofitdemo.base.DemoApplication;
import com.gank.io.retrofitdemo.component.ApplicationComponent;
import com.gank.io.retrofitdemo.component.DaggerApplicationComponent;
import com.gank.io.retrofitdemo.component.DaggerMainActivityComponent;
import com.gank.io.retrofitdemo.module.AndroidMoudle;
import com.gank.io.retrofitdemo.module.CMoudle;
import com.gank.io.retrofitdemo.testRx.RxjavaActivity;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * MainActivity:
 *
 * @author zpl
 * @date 2018/7/24
 */
public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    @Named("Test3")
    @Inject
    Test3 mTest3;
    @Named("Test4")
    @Inject
    Test3 mTest4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerMainActivityComponent
                .builder()
                .applicationComponent(((DemoApplication) getApplication())
                        .getComponent())
                .cMoudle(new CMoudle()).build()
                .inject(this);
        Log.e(TAG, mTest3.toString());
    }

    public void get(View view) {
        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                // 设置 网络请求 Url
                .baseUrl("http://fy.iciba.com/")
                //设置使用Gson解析(记得加入依赖)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // 步骤5:创建 网络请求接口的实例
        GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);

        //对 发送请求进行封装
        Call<Translation> call = request.getCall();

        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<Translation>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<Translation> call, Response<Translation> response) {
                // 步骤7：处理返回的数据结果
                response.body().show();
            }

            //请求失败时回调
            @Override
            public void onFailure(Call<Translation> call, Throwable throwable) {
                System.out.println("连接失败");
            }
        });
    }

    public void post(View view) {
        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                // 设置 网络请求 Url
                .baseUrl("http://fanyi.youdao.com/")
                //设置使用Gson解析(记得加入依赖)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // 步骤5:创建 网络请求接口 的实例
        PostRequest_Interface request = retrofit.create(PostRequest_Interface.class);

        //对 发送请求 进行封装(设置需要翻译的内容)
        Call<String> call = request.getCall("I love you");

        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<String>() {

            //请求成功时回调
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                // 步骤7：处理返回的数据结果：输出翻译的内容
                System.out.println(response.body());
            }

            //请求失败时回调
            @Override
            public void onFailure(Call<String> call, Throwable throwable) {
                System.out.println("请求失败");
                System.out.println(throwable.getMessage());
            }
        });
    }

    public void flexbox(View view) {
        Intent intent = new Intent(this, FlexBoxActivity.class);
        startActivity(intent);
    }

    public void rxjava(View view) {
        Intent intent = new Intent(this, RxjavaActivity.class);
        startActivity(intent);
    }

}