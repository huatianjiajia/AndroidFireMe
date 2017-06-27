package com.jaydenxiao.androidfire.ui.news.contract;

import com.jaydenxiao.androidfire.bean.VideoData;
import com.jaydenxiao.common.base.BaseModel;
import com.jaydenxiao.common.base.BasePresenter;
import com.jaydenxiao.common.base.BaseView;

import java.util.List;

import rx.Observable;

/**
 * 项目名称： AndroidFireMe
 * 包名：com.jaydenxiao.androidfire.ui.news.contract
 * 类描述： describe
 * 创建人： wxw https://github.com/huatianjiajia
 * 创建时间： 2017/6/27 15:25
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public interface VideosListContract {
    interface Model extends BaseModel{
        //请求获取视频
        Observable<List<VideoData>> getVideosListData(String type,int startPage);
    }
    interface View extends BaseView{
        //返回获取的视频
        void returnVideosListData(List<VideoData> newsSummaries);
        //返回顶部
        void scrolltoTop();
    }
    abstract static class Presenter extends BasePresenter<View,Model>{
        //发起获取视频请求
        public abstract void getVideosListDataRequest(String type,int startPage);
    }
}
