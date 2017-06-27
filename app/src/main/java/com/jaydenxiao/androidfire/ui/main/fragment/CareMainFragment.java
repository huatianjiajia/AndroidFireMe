package com.jaydenxiao.androidfire.ui.main.fragment;

import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jaydenxiao.androidfire.R;
import com.jaydenxiao.androidfire.ui.zone.activity.CircleZoneActivity;
import com.jaydenxiao.common.base.BaseFragment;
import com.jaydenxiao.common.commonutils.ImageLoaderUtils;
import com.jaydenxiao.common.commonwidget.WaveView;
import com.jaydenxiao.common.daynightmodeutils.ChangeModeController;

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
public class CareMainFragment extends BaseFragment {
    @Bind(R.id.wave_view)
    WaveView waveView;
    @Bind(R.id.img_logo)
    ImageView imgLogo;
    @Bind(R.id.ll_friend_zone)
    LinearLayout llFriendZone;

    @Override
    protected int getLayoutResource() {
        return R.layout.fra_care_main;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        //设置头像跟着波浪背景浮动
        ImageLoaderUtils.displayRound(getContext(),imgLogo,R.drawable.bgkobe);
        final FrameLayout.LayoutParams lp=new FrameLayout.LayoutParams(-2,-2);
        lp.gravity= Gravity.CENTER;
        waveView.setOnWaveAnimationListener(new WaveView.OnWaveAnimationListener() {
            @Override
            public void OnWaveAnimation(float y) {
                lp.setMargins(0,0,0,(int)y+2);
                imgLogo.setLayoutParams(lp);
            }
        });
    }
    @OnClick(R.id.ll_friend_zone)
    public void friendZone(){
        CircleZoneActivity.startAction(getContext());
    }
    @OnClick(R.id.ll_daynight_toggle)
    public void dayNightToggle(){
        ChangeModeController.toggleThemeSetting(getActivity());
    }
    @OnClick(R.id.ll_daynight_about)
    public void about(){
//        AboutActivity.startAction(getContext());
    }
}
