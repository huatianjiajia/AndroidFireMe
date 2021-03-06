package com.dph.androidfire.ui.main.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dph.androidfire.R;
import com.dph.androidfire.app.AppConstant;
import com.dph.androidfire.bean.VideoChannelTable;
import com.dph.androidfire.db.VideosChannelTableManager;
import com.dph.androidfire.ui.news.fragment.VideosFragment;
import com.dph.androidfire.utils.MyUtils;
import com.dph.common.base.BaseFragment;
import com.dph.common.base.BaseFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

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
public class VideoMainFragment extends BaseFragment {
    @Bind(R.id.tabs)
    TabLayout tabs;
    @Bind(R.id.view_pager)
    ViewPager viewPager;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    private BaseFragmentAdapter fragmentAdapter;

    @Override
    protected int getLayoutResource() {
        return R.layout.app_bar_video;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        List<String> channelNames=new ArrayList<>();
        List<VideoChannelTable> videoChannelTableList= VideosChannelTableManager.loadVideosChannelsMine();
        List<Fragment> mNewsFragmentList=new ArrayList<>();
        for (int i = 0; i < videoChannelTableList.size(); i++) {
            channelNames.add(videoChannelTableList.get(i).getChannelName());
            mNewsFragmentList.add(createListFragments(videoChannelTableList.get(i)));
        }

        fragmentAdapter=new BaseFragmentAdapter(getChildFragmentManager(),mNewsFragmentList,channelNames);
        viewPager.setAdapter(fragmentAdapter);
        tabs.setupWithViewPager(viewPager);
        MyUtils.dynamicSetTabLayoutMode(tabs);
        setPageChangeListener();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRxManager.post(AppConstant.NEWS_LIST_TO_TOP,"");
            }
        });
    }



    private void setPageChangeListener() {
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
    private VideosFragment createListFragments(VideoChannelTable videoChannelTable) {
        VideosFragment fragment=new VideosFragment();
        Bundle bundle=new Bundle();
        bundle.putString(AppConstant.VIDEO_TYPE,videoChannelTable.getChannelId());
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
