package com.dph.androidfire.ui.news.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.dph.androidfire.R;
import com.dph.androidfire.app.AppConstant;
import com.dph.androidfire.bean.NewsChannelTable;
import com.dph.androidfire.ui.news.adapter.ChannelAdapter;
import com.dph.androidfire.ui.news.contract.NewsChannelContract;
import com.dph.androidfire.ui.news.event.ChannelItemMoveEvent;
import com.dph.androidfire.ui.news.model.NewsChannelModel;
import com.dph.androidfire.ui.news.presenter.NewsChanelPresenter;
import com.dph.androidfire.widget.ItemDragHelperCallback;
import com.dph.common.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import rx.functions.Action1;

/**
 * 项目名称： AndroidFireMe
 * 包名：com.jaydenxiao.androidfire.ui.news.activity
 * 类描述： describe
 * 创建人： wxw https://github.com/huatianjiajia
 * 创建时间： 2017/6/26 15:42
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class NewsChannelActivity extends BaseActivity<NewsChanelPresenter, NewsChannelModel> implements NewsChannelContract.View {


    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.news_channel_mine_rv)
    RecyclerView newsChannelMineRv;
    @Bind(R.id.news_channel_more_rv)
    RecyclerView newsChannelMoreRv;

    private ChannelAdapter channelAdapterMine;
    private ChannelAdapter channelAdapterMore;

    @Override
    public int getLayoutId() {
        return R.layout.act_news_channel;
    }

    /**
     * 入口
     *
     * @param context
     */
    public static void startAction(Context context) {
        Intent intent = new Intent(context, NewsChannelActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        mRxManager.on(AppConstant.CHANNEL_SWAP, new Action1<ChannelItemMoveEvent>() {
            @Override
            public void call(ChannelItemMoveEvent channelItemMoveEvent) {
                if (channelItemMoveEvent!=null){
                    mPresenter.onItemSwap((ArrayList<NewsChannelTable>) channelAdapterMine.getAll(),channelItemMoveEvent.getFromPosition(),channelItemMoveEvent.getToPosition());
                }
            }
        });
    }

    @Override
    public void showLoading(String title) {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showErrorTip(String msg) {

    }

    @Override
    public void returnMineNewsChannels(List<NewsChannelTable> newsChannelsMine) {
        channelAdapterMine = new ChannelAdapter(mContext,R.layout.item_news_channel);
        newsChannelMineRv.setLayoutManager(new GridLayoutManager(this, 4, LinearLayoutManager.VERTICAL, false));
        newsChannelMineRv.setItemAnimator(new DefaultItemAnimator());
        newsChannelMineRv.setAdapter(channelAdapterMine);
        channelAdapterMine.replaceAll(newsChannelsMine);
        channelAdapterMine.setOnItemClickListener(new ChannelAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                NewsChannelTable newsChannel = channelAdapterMine.get(position);
                channelAdapterMore.add(newsChannel);
                channelAdapterMine.removeAt(position);
                mPresenter.onItemAddOrRemove((ArrayList<NewsChannelTable>) channelAdapterMine.getAll(), (ArrayList<NewsChannelTable>)channelAdapterMore.getAll());

            }
        });


        ItemDragHelperCallback itemDragHelperCallback = new ItemDragHelperCallback(channelAdapterMine);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemDragHelperCallback);
        itemTouchHelper.attachToRecyclerView(newsChannelMineRv);
        channelAdapterMine.setItemDragHelperCallback(itemDragHelperCallback);
    }

    @Override
    public void returnMoreNewsChannels(List<NewsChannelTable> newsChannelsMore) {
        channelAdapterMore = new ChannelAdapter(mContext,R.layout.item_news_channel);
        newsChannelMoreRv.setLayoutManager(new GridLayoutManager(this, 4, LinearLayoutManager.VERTICAL, false));
        newsChannelMoreRv.setItemAnimator(new DefaultItemAnimator());
        newsChannelMoreRv.setAdapter(channelAdapterMore);
        channelAdapterMore.replaceAll(newsChannelsMore);
        channelAdapterMore.setOnItemClickListener(new ChannelAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                NewsChannelTable newsChannel = channelAdapterMore.get(position);
                channelAdapterMine.add(newsChannel);
                channelAdapterMore.removeAt(position);
                mPresenter.onItemAddOrRemove((ArrayList<NewsChannelTable>) channelAdapterMine.getAll(), (ArrayList<NewsChannelTable>)channelAdapterMore.getAll());
            }
        });
    }


    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    finishAfterTransition();
                } else {
                    finish();
                }
            }
        });
        mPresenter.lodeChannelsRequest();
    }

}
