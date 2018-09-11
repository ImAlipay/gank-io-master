package com.gank.io.retrofitdemo.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gank.io.retrofitdemo.R;

/**
 * ${DESC}
 *
 * @author 91660
 * @date 2018/9/11
 */
public class PagerFramgent extends Fragment {

    private int a;

    /**
     * 创建一个静态实例化Fragment的方法
     *
     * @param a 外界传入的值
     * @return
     */
    public static PagerFramgent newInstance(int a) {
        PagerFramgent pf = new PagerFramgent();
        Bundle bundle = new Bundle();
        bundle.putInt("a", a);
        //向fragment中传值，使用此方法，不用使用有参构造方法
        pf.setArguments(bundle);
        return pf;
    }

    String tag = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //取出保存的值
        this.a = getArguments().getInt("a");
        //获取类名
        tag = this.getClass().getSimpleName();
        //log生命周期
        Log.e(tag, "onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //加载一个布局，布局中只有一个textview
        View view = inflater.inflate(R.layout.fragment, container, false);
        TextView textView = (TextView) view.findViewById(R.id.textView);
        //设置文本，将传进来的值设置进去
        textView.setText(a + "");
        Log.e(tag, "onCreateView");
        return view;
    }

}