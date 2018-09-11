package com.gank.io.retrofitdemo.view;

import android.support.annotation.NonNull;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Adapter包装类
 *
 * @author 91660
 * @date 2018/9/7
 */
public class HeaderAndFooterWrapper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int BASE_ITEM_TYPE_HEADER = 10000;
    private static final int BASE_ITEM_TYPE_FOOTER = 20000;

    private SparseArrayCompat<View> mHeaderView = new SparseArrayCompat<>();
    private SparseArrayCompat<View> mFooterView = new SparseArrayCompat<>();

    private RecyclerView.Adapter mInnerAdapter;

    public HeaderAndFooterWrapper(RecyclerView.Adapter adapter) {
        this.mInnerAdapter = adapter;
    }

    private boolean isHeaderViewPos(int position) {
        return position < mHeaderView.size();
    }

    private boolean isFooterViewPos(int position) {
        return position >= getHeadersCount() + getRealItemCount();
    }

    /**
     * 添加头部方法
     *
     * @param view
     */
    public void addHeaderView(View view) {
        mHeaderView.put(mHeaderView.size() + BASE_ITEM_TYPE_HEADER, view);
    }

    /**
     * 添加尾部方法
     *
     * @param view
     */
    public void addFootView(View view) {
        mFooterView.put(mFooterView.size() + BASE_ITEM_TYPE_FOOTER, view);
    }

    /**
     * 获取头部集合的大小
     *
     * @return
     */
    public int getHeadersCount() {
        return mHeaderView.size();
    }

    /**
     * 获取尾部集合的大小
     *
     * @return
     */
    public int getFootersCount() {
        return mFooterView.size();
    }

    /**
     * 获取adapter的大小
     *
     * @return
     */
    private int getRealItemCount() {
        return mInnerAdapter.getItemCount();

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mHeaderView.get(viewType) != null) {
            return new HeaderViewHolder(mHeaderView.get(viewType));
        } else if (mFooterView.get(viewType) != null) {
            return new FooterViewHolder(mFooterView.get(viewType));
        }
        return mInnerAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeaderViewPos(position)) {
            return mHeaderView.keyAt(position);
        } else if (isFooterViewPos(position)) {
            return mFooterView.keyAt(position - getHeadersCount() - getRealItemCount());
        }
        return mInnerAdapter.getItemViewType(position - getHeadersCount());
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (isHeaderViewPos(position)) {
            return;
        }
        if (isFooterViewPos(position)) {
            return;
        }
        mInnerAdapter.onBindViewHolder(holder, position - getHeadersCount());
    }

    @Override
    public int getItemCount() {
        return getHeadersCount() + getFootersCount() + getRealItemCount();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        mInnerAdapter.onAttachedToRecyclerView(recyclerView);

        /**
         * todo:
         * 解决网格布局问题
         */
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int itemViewType = getItemViewType(position);
                    if (mHeaderView.get(itemViewType) != null) {
                        return gridLayoutManager.getSpanCount();
                    } else if (mFooterView.get(itemViewType) != null) {
                        return gridLayoutManager.getSpanCount();
                    }
                    return 1;
                }
            });
        }
    }

    private class HeaderViewHolder extends RecyclerView.ViewHolder {

        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }

    private class FooterViewHolder extends RecyclerView.ViewHolder {

        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }
}
