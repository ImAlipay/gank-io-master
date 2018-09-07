package com.gank.io.retrofitdemo.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.gank.io.retrofitdemo.R;
import com.gank.io.retrofitdemo.bean.GankFLEntities;
import com.gank.io.retrofitdemo.glide.GlideApp;
import com.gank.io.retrofitdemo.utils.Constant;

import java.util.List;

/**
 * ${DESC}
 *
 * @author 91660
 * @date 2018/9/7
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    private List<GankFLEntities> mList;
    private Context mContext;

    public MainAdapter(List<GankFLEntities> list, Context context) {
        mList = list;
        mContext = context;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recy, parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        String url = mList.get(position).getUrl();
        Log.e(Constant.TAG, url);
        GlideApp.with(mContext).load(url).into(holder.mIv);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {

        private ImageView mIv;

        public MainViewHolder(View itemView) {
            super(itemView);
            mIv = itemView.findViewById(R.id.iv);
        }
    }

}