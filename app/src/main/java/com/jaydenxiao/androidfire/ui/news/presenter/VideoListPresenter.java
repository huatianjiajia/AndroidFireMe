package com.jaydenxiao.androidfire.ui.news.presenter;

import com.jaydenxiao.androidfire.R;
import com.jaydenxiao.androidfire.app.AppConstant;
import com.jaydenxiao.androidfire.bean.VideoData;
import com.jaydenxiao.androidfire.ui.news.contract.VideosListContract;
import com.jaydenxiao.common.baserx.RxSubscriber;

import java.util.List;

import rx.functions.Action1;

/**
 * 项目名称： AndroidFireMe
 * 包名：com.jaydenxiao.androidfire.ui.news.presenter
 * 类描述： describe
 * 创建人： wxw https://github.com/huatianjiajia
 * 创建时间： 2017/6/27 16:07
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class VideoListPresenter extends VideosListContract.Presenter {
    @Override
    public void onStart() {
        super.onStart();
        //监听返回顶部动作
        mRxManage.on(AppConstant.NEWS_LIST_TO_TOP, new Action1<Object>() {
            @Override
            public void call(Object o) {
                mView.scrolltoTop();
            }
        });
    }

    /**
     * 获取视频列表请求
     * @param type
     * @param startPage
     */
    @Override
    public void getVideosListDataRequest(String type, int startPage) {
       mRxManage.add(mModel.getVideosListData(type,startPage).subscribe(new RxSubscriber<List<VideoData>>(mContext,false) {
           @Override
           public void onStart() {
               super.onStart();
               mView.showLoading(mContext.getString(R.string.loading));
           }

           @Override
           protected void _onNext(List<VideoData> videoDatas) {
               mView.returnVideosListData(videoDatas);
               mView.stopLoading();
           }

           @Override
           protected void _onError(String message) {
                mView.showErrorTip(message);
           }
       }));
    }
}
