package com.dph.androidfire.ui.news.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.dph.androidfire.R;
import com.dph.androidfire.app.AppConstant;
import com.dph.androidfire.bean.NewsPhotoDetail;
import com.dph.androidfire.ui.news.fragment.PhotoDetailFragment;
import com.dph.common.base.BaseActivity;
import com.dph.common.base.BaseFragmentAdapter;
import com.dph.common.commonwidget.ViewPagerFixed;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 项目名称： AndroidFireMe
 * 包名：com.jaydenxiao.androidfire.ui.news.activity
 * 类描述： describe
 * 创建人： wxw https://github.com/huatianjiajia
 * 创建时间： 2017/6/23 10:24
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class NewsPhotoDetailActivity extends BaseActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.viewpager)
    ViewPagerFixed viewpager;
    @Bind(R.id.photo_detail_title_tv)
    TextView photoDetailTitleTv;

    private List<Fragment> mPhotoDetailFragmentList=new ArrayList<>();
    private NewsPhotoDetail mNewsPhotoDetail;

    /**
     * 入口
     *
     * @param context
     * @param mNewsPhotoDetail
     */
    public static void startAction(Context context, NewsPhotoDetail mNewsPhotoDetail) {
        Intent intent = new Intent(context, NewsPhotoDetailActivity.class);
        intent.putExtra(AppConstant.PHOTO_DETAIL, mNewsPhotoDetail);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.act_news_photo_detail;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        mNewsPhotoDetail=getIntent().getParcelableExtra(AppConstant.PHOTO_DETAIL);
        createFragment(mNewsPhotoDetail);
        initViewPager();
    }


    private void createFragment(NewsPhotoDetail newsPhotoDetail){
        mPhotoDetailFragmentList.clear();
        for (NewsPhotoDetail.Picture picture:newsPhotoDetail.getPictures()){
            PhotoDetailFragment fragment=new PhotoDetailFragment();
            Bundle bundle=new Bundle();
            bundle.putString(AppConstant.PHOTO_DETAIL_IMGSRC,picture.getImgSrc());
            fragment.setArguments(bundle);
            mPhotoDetailFragmentList.add(fragment);
        }
    }
    private void initViewPager() {
        BaseFragmentAdapter photoPagerAdapter=new BaseFragmentAdapter(getSupportFragmentManager(),mPhotoDetailFragmentList);
        viewpager.setAdapter(photoPagerAdapter);
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setPhotoDetailTitle(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setPhotoDetailTitle(int position) {
        String title=getTitle(position);
        photoDetailTitleTv.setText(getString(R.string.photo_detail_title,position+1,
                mPhotoDetailFragmentList.size(),title));
    }

    private String getTitle(int position){
        String title=mNewsPhotoDetail.getPictures().get(position).getTitle();
        if (title==null){
            title=mNewsPhotoDetail.getTitle();
        }
        return title;
    }
}
