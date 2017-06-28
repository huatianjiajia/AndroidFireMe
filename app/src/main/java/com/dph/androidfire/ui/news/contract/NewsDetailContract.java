package com.dph.androidfire.ui.news.contract;

import com.dph.androidfire.bean.NewsDetail;
import com.dph.common.base.BaseModel;
import com.dph.common.base.BasePresenter;
import com.dph.common.base.BaseView;

import rx.Observable;

/**
 * 项目名称： AndroidFireMe
 * 包名：com.jaydenxiao.androidfire.ui.news.contract
 * 类描述： describe
 * 创建人： wxw https://github.com/huatianjiajia
 * 创建时间： 2017/6/23 16:28
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public interface NewsDetailContract {
    interface Model extends BaseModel{
        //请求获取新闻
        Observable<NewsDetail> getOneNewsData(String postId);
    }
    interface View extends BaseView{
        //返回获取的新闻
        void returnOneNewsData(NewsDetail newsDetail);
    }
    abstract static class Presenter extends BasePresenter<View,Model>{
        //发起获取单条新闻请求
        public abstract void getOneNewsDataRequest(String postId);
    }
}
