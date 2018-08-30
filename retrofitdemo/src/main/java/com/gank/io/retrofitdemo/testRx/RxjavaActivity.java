package com.gank.io.retrofitdemo.testRx;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.gank.io.retrofitdemo.R;

import java.util.ArrayList;
import java.util.Observable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * RxjavaActivity:
 *
 * @author zpl
 * @date 2018/8/30
 */
public class RxjavaActivity extends AppCompatActivity {

    private static final String TAG = "RxJava";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void create(View view) {
        io.reactivex.Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onNext(4);
            }
        }).subscribe(new Observer<Integer>() {

            Disposable mDisposable;

            @Override
            public void onSubscribe(Disposable d) {
                mDisposable = d;
            }

            @Override
            public void onNext(Integer integer) {
                if (integer == 3) {
                    // 在RxJava 2.x 中，新增的Disposable可以做到切断的操作，让Observer观察者不再接收上游事件
                    mDisposable.dispose();
                    Log.e(TAG, "mDisposable: " + integer);
                } else {
                    Log.e(TAG, "onNext: " + integer);
                }
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(RxjavaActivity.this, "wrong happend", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete() {
                Toast.makeText(RxjavaActivity.this, "onComplete", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private io.reactivex.Observable getIntger() {
        return io.reactivex.Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                if (!emitter.isDisposed()) {
                    emitter.onNext(1);
                    emitter.onNext(2);
                    emitter.onNext(3);
                    emitter.onNext(4);
                }
            }
        });
    }

    private io.reactivex.Observable getStringObservable() {
        return io.reactivex.Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                if (!emitter.isDisposed()) {
                    emitter.onNext("1");
                    emitter.onNext("2");
                    emitter.onNext("3");
                }
            }
        });
    }

    /**
     * zip
     * <p>
     * 合并事件专用
     * <p>
     * 分别从两个上游事件中各取出一个组合
     * 一个事件只能被使用一次，顺序严格按照事件发送的顺序
     * 最终下游事件收到的是和上游事件最少的数目相同（必须两两配对，多余的舍弃)
     * <p>
     */
    public void rxzip(View view) {
        io.reactivex.Observable.zip(getStringObservable(), getIntger(), new BiFunction<String, Integer, String>() {
            @Override
            public String apply(String s, Integer i) throws Exception {
                return s + i;
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, "onNext: " + s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    /**
     * map
     * <p>
     * 基本是RxJava 最简单的操作符了
     * 作用是对上游发送的每一个事件应用一个函数，使得每一个事件都按照指定的函数去变化
     * <p>
     */
    public void rxmap(View view) {
        io.reactivex.Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onNext(4);
            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return integer + "";
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.e(TAG, "accept: " + s);
            }
        });
    }

    public void rxflatmap(View view) {
        io.reactivex.Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
            }
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                ArrayList list = new ArrayList();
                for (int i = 0; i < 3; i++) {
                    list.add(integer + "");
                }
                return io.reactivex.Observable.fromIterable(list);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.e(TAG, s);
            }
        });
    }

    /**
     * doOnNext
     * <p>
     * 让订阅者在接收到数据前干点事情的操作符
     */
    public void rxdoonnext(View view) {
        io.reactivex.Observable.just(1, 2, 3, 4).doOnNext(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.e(TAG, "onnext:" + (integer - 1));
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.e(TAG, "subscribe:" + integer);
            }
        });
    }

    /**
     * filter
     * <p>
     * 过滤操作符，取正确的值
     * <p>
     */
    public void rxfilter(View view) {
        io.reactivex.Observable.just(1, 10, -1, 34, 25)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        if (integer >= 10) {
                            return true;
                        }
                        return false;
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.e(TAG, integer + "");
            }
        });
    }

    /**
     * take
     * <p>
     * 用于指定订阅者最多收到多少数据
     * <p>
     */
    public void rxtake(View view) {
        Flowable.fromArray(1, 2, 3, 4, 5).take(2)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e(TAG, "take:" + integer);
                    }
                });
    }

    /**
     * timer
     * <p>
     * 在Rxjava中timer 操作符既可以延迟执行一段逻辑，也可以间隔执行一段逻辑
     * 【注意】但在RxJava 2.x已经过时了，现在用interval操作符来间隔执行，详见RxIntervalActivity
     * timer和interval都默认执行在一个新线程上。
     * <p>
     */
    public void rxtimer(View view) {
        io.reactivex.Observable.timer(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.e(TAG, "timer:" + aLong);
                    }
                });
    }

    /**
     * interval
     * <p>
     * 间隔执行操作，默认在新线程
     * <p>
     */
    public void rxinterval(View view) {
        io.reactivex.Observable.interval(3, 2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.e(TAG, "interval:" + aLong);
                    }
                });
    }

}