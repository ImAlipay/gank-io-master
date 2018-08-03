package com.gank.io.myapplication;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;

import java.sql.Ref;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * TransactionMessageActivity:交易记录推送
 *
 * @author zpl
 * @date 2018/6/4
 */
public class TransactionMessageActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;

    RefreshLayout mRefreshLayout;
    private OperaDatabase mDatabase;
    /**
     * 数据流
     */
    private List<NotificationData> mQuery;
    private int start;
    /**
     * 每页查询的页数
     */
    private int page = 1;
    private TransactionMessageAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_message);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRefreshLayout = (RefreshLayout) findViewById(R.id.refreshLayout);

        initWidget();
    }

    protected void initWidget() {
        // TODO: 2018/6/5 查询数据库，忽略耗时
        mDatabase = new OperaDatabase(TransactionMessageActivity.this);
        mQuery = new ArrayList<>();
        initRecy();
        setRefresh();
        queryData();
    }

    private void queryData() {
        if (mQuery.size() > 0) {
            start = mQuery.size();
        }
        List<NotificationData> datas = mDatabase.queryLimit(NotificationData.TYPE_TRADE, page, start);
        mQuery.addAll(datas);
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 设置刷新控件
     */
    private void setRefresh() {
        mRefreshLayout.setEnableRefresh(false);
        //设置 Footer 为 球脉冲
        mRefreshLayout.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale));
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                mRefreshLayout.finishLoadMore();
                queryData();
            }
        });
    }

    /**
     * 初始化recyclerview
     */
    protected void initRecy() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        mAdapter = new TransactionMessageAdapter(R.layout.item_transaction_message, mQuery);
        mRecyclerView.setAdapter(mAdapter);
    }

    private class TransactionMessageAdapter extends BaseQuickAdapter<NotificationData, BaseViewHolder> {

        public TransactionMessageAdapter(@LayoutRes int layoutResId, @Nullable List<NotificationData> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, final NotificationData item) {
            helper.setText(R.id.tv_time, item.getTimestamp());
            TextView mContent = helper.getView(R.id.tv_content);
            mContent.setText(item.getContent());

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDatabase.destory();
    }
}