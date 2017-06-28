package com.dph.androidfire.ui.news.contract;

import com.dph.androidfire.bean.PhotoGirl;
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
 * 创建时间： 2017/6/27 10:23
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public interface PhotoListContract {
    interface Model extends BaseModel{
        //请求获取图片
        Observable<List<PhotoGirl>> getPhotosListData(int size,int page);
    }
    interface View extends BaseView{
        //返回获取的图片
        void returnPhotosListData(List<PhotoGirl> photoGirls);
    }
    abstract static class Presenter extends BasePresenter<View,Model>{
        //发起获取图片请求
        public abstract void getPhotosListDataRequest(int size,int page);
    }
}
