package com.gank.io.retrofitdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.gank.io.retrofitdemo.dragger2.ApiService;
import com.gank.io.retrofitdemo.dragger2.ano.DaggerZhinanCompent;
import com.gank.io.retrofitdemo.dragger2.ano.ZhinanCompent;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import javax.inject.Inject;

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
    @Inject
    ApiService mApiService;
    @Inject
    int code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testRxJava();

//        DaggerUseCompoent.create().inject(this);
//        mApiService.register();

        ZhinanCompent zhinanCompent = DaggerZhinanCompent.builder().build();
        zhinanCompent.waimai().eat();
//        zhinanCompent.inject(this);
//        Log.e("........", code + "");
    }

    public void get(View view) {
        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                // 设置 网络请求 Url
                .baseUrl("http://fy.iciba.com/")
                //设置使用Gson解析(记得加入依赖)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // 步骤5:创建 网络请求接口 的实例
        GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);

        //对 发送请求 进行封装
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

    private void testRxJava() {
//        io.reactivex.Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
//                Log.e(TAG, Thread.currentThread().getName());
//                e.onNext(1);
//                e.onNext(2);
//                e.onNext(3);
//                e.onComplete();
//            }
//        }).map(new Function<Integer, String>() {
//            @Override
//            public String apply(Integer integer) throws Exception {
//                return "This is result" + integer;
//            }
//        }).subscribe(new Consumer<String>() {
//            @Override
//            public void accept(String integer) throws Exception {
//                Log.e(TAG, integer + "");
//            }
//        });
//
//        io.reactivex.Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
//                e.onNext(1);
//                e.onNext(2);
//                e.onNext(3);
//                e.onComplete();
//            }
//        }).concatMap(new Function<Integer, ObservableSource<String>>() {
//            @Override
//            public ObservableSource<String> apply(Integer integer) throws Exception {
//                ArrayList<String> list = new ArrayList<>();
//                for (int i = 0; i < 3; i++) {
//                    list.add("hello" + integer);
//                }
//                return io.reactivex.Observable.fromIterable(list).delay(10, TimeUnit.MILLISECONDS);
//            }
//        }).subscribe(new Consumer<String>() {
//            @Override
//            public void accept(String s) throws Exception {
//                Log.e(TAG, s);
//            }
//        });

//        io.reactivex.Observable<Integer> observable1 = io.reactivex.Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
//                e.onNext(1);
//                Log.e(TAG, "发射：" + 1);
//                e.onNext(2);
//                Log.e(TAG, "发射：" + 2);
//                e.onNext(3);
//                Log.e(TAG, "发射：" + 3);
//                e.onNext(4);
//                Log.e(TAG, "发射：" + 4);
//                e.onComplete();
//                Log.e(TAG, "发射：onComplete");
//            }
//        });
//
//        io.reactivex.Observable<String> observable2 = io.reactivex.Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(ObservableEmitter<String> e) throws Exception {
//                e.onNext("A");
//                Log.e(TAG, "发射：A");
//                e.onNext("B");
//                Log.e(TAG, "发射：B");
//                e.onNext("C");
//                Log.e(TAG, "发射：C");
//                e.onComplete();
//                Log.e(TAG, "发射String：onComplete");
//            }
//        });
//
//        io.reactivex.Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {
//            @Override
//            public String apply(Integer integer, String s) throws Exception {
//                return integer + s;
//            }
//        }).subscribe(new Observer<String>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                Log.e(TAG, "onSubscribe");
//            }
//
//            @Override
//            public void onNext(String value) {
//                Log.e(TAG, "onNext:" + value);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.e(TAG,"onError");
//            }
//
//            @Override
//            public void onComplete() {
//                Log.e(TAG,"onComplete");
//            }
//        });

//        io.reactivex.Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
//                for (int i = 0; ; i++) {
//                    e.onNext(i);
//                }
//            }
//        }).subscribeOn(Schedulers.io())
//                .filter(new Predicate<Integer>() {
//                    @Override
//                    public boolean test(Integer integer) throws Exception {
//                        return integer % 10 == 0;
//                    }
//                }).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer integer) throws Exception {
//                        Log.e(TAG, "" + integer);
//                    }
//                });

        Flowable<Integer> upstream = Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> e) throws Exception {
                Log.e(TAG, "emit 1");
                e.onNext(1);
                Log.e(TAG, "emit 2");
                e.onNext(2);
                Log.e(TAG, "emit 3");
                e.onNext(3);
                Log.e(TAG, "emit complete");
                e.onComplete();
            }
        }, BackpressureStrategy.ERROR);

        Subscriber<Integer> downstream = new Subscriber<Integer>() {

            @Override
            public void onSubscribe(Subscription s) {
                Log.e(TAG, "onSubscribe");
                s.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(Integer integer) {
                Log.e(TAG, "onNext:" + integer);
            }

            @Override
            public void onError(Throwable t) {
                Log.e(TAG, "onError");
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete");
            }
        };

        upstream.subscribe(downstream);

    }

    public void flexbox(View view) {
        Intent intent = new Intent(this, FlexBoxActivity.class);
        startActivity(intent);
    }

}