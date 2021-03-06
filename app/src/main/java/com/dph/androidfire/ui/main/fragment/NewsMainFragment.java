package com.dph.androidfire.ui.main.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.dph.androidfire.R;
import com.dph.androidfire.app.AppConstant;
import com.dph.androidfire.bean.NewsChannelTable;
import com.dph.androidfire.ui.main.contract.NewsMainContract;
import com.dph.androidfire.ui.main.model.NewsMainModel;
import com.dph.androidfire.ui.main.presenter.NewsMainPresenter;
import com.dph.androidfire.ui.news.activity.NewsChannelActivity;
import com.dph.androidfire.ui.news.fragment.NewsFragment;
import com.dph.androidfire.utils.MyUtils;
import com.dph.common.base.BaseFragment;
import com.dph.common.base.BaseFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 项目名称： AndroidFireMe
 * 包名：com.jaydenxiao.androidfire.ui.main.fragment
 * 类描述： describe
 * 创建人： wxw https://github.com/huatianjiajia
 * 创建时间： 2017/6/17 11:32
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class NewsMainFragment extends BaseFragment<NewsMainPresenter, NewsMainModel> implements NewsMainContract.View {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tabs)
    TabLayout tabs;
    @Bind(R.id.add_channel_iv)
    ImageView addChannelIv;
    @Bind(R.id.view_pager)
    ViewPager viewPager;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    private BaseFragmentAdapter fragmentAdapter;

    @Override
    protected int getLayoutResource() {
        return R.layout.app_bar_news;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this,mModel);
    }


    @Override
    protected void initView() {
        mPresenter.lodeMineChannelsRequest();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRxManager.post(AppConstant.NEWS_LIST_TO_TOP,"");
            }
        });
    }
    @OnClick(R.id.add_channel_iv)
    public void clickAdd(){
        NewsChannelActivity.startAction(getContext());
    }

    @Override
    public void returnMineNewsChannels(List<NewsChannelTable> newsChannelsMine) {
        if (newsChannelsMine!=null){
            List<String> channelNames=new ArrayList<>();
            List<Fragment> mNewsFragmentList=new ArrayList<>();
            for (int i = 0; i < newsChannelsMine.size(); i++) {
                channelNames.add(newsChannelsMine.get(i).getNewsChannelName());
                mNewsFragmentList.add(createListFragments(newsChannelsMine.get(i)));
            }
            if (fragmentAdapter==null){
                fragmentAdapter=new BaseFragmentAdapter(getChildFragmentManager(),mNewsFragmentList,channelNames);
            }else{
                //刷新fragment
                fragmentAdapter.setFragments(getChildFragmentManager(),mNewsFragmentList,channelNames);
            }
            viewPager.setAdapter(fragmentAdapter);
            tabs.setupWithViewPager(viewPager);
            MyUtils.dynamicSetTabLayoutMode(tabs);
            setPageChangeListener();
        }
    }

    private void setPageChangeListener(){
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    private NewsFragment createListFragments(NewsChannelTable newsChannel){
        NewsFragment fragment=new NewsFragment();
        Bundle bundle=new Bundle();
        bundle.putString(AppConstant.NEWS_ID,newsChannel.getNewsChannelId());
        bundle.putString(AppConstant.NEWS_TYPE,newsChannel.getNewsChannelType());
        bundle.putInt(AppConstant.CHANEL_POSITION,newsChannel.getNewsChannelIndex());
        fragment.setArguments(bundle);
        return fragment;
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

}
