package com.dph.androidfire.ui.news.contract;

import com.dph.androidfire.bean.NewsSummary;
import com.dph.common.base.BaseModel;
import com.dph.common.base.BasePresenter;
import com.dph.common.base.BaseView;

import java.util.List;

import rx.Observable;

/**
 * 项目名称： AndroidFireMe
 * 包名：com.jaydenxiao.androidfire.ui.news.contract
 * 类描述： describe
 * 创建人： wxw https://github.com/huatianjiajia
 * 创建时间： 2017/6/22 10:43
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public interface NewsListContract {
    interface Model extends BaseModel{
        //请求获取新闻
        Observable<List<NewsSummary>> getNewsListData(String type,final String id,int startPage);
    }
    interface View extends BaseView{
        //返回获取的新闻
        void returnNewsListData(List<NewsSummary> newsSummaries);
        //返回顶部
        void scrolltoTop();
    }
    abstract static class Presenter extends BasePresenter<View,Model>{
        //发起获取新闻请求
        public abstract void getNewsListDataRequest(String type,final String id,int startPage);
    }
}
