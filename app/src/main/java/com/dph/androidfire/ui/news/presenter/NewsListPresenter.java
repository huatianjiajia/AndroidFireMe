package com.dph.androidfire.ui.news.presenter;

import com.dph.androidfire.app.AppConstant;
import com.dph.androidfire.bean.NewsSummary;
import com.dph.androidfire.ui.news.contract.NewsListContract;
import com.dph.common.baserx.RxSubscriber;

import java.util.List;

import rx.functions.Action1;

/**
 * 项目名称： AndroidFireMe
 * 包名：com.jaydenxiao.androidfire.ui.news.presenter
 * 类描述： describe
 * 创建人： wxw https://github.com/huatianjiajia
 * 创建时间： 2017/6/23 9:29
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class NewsListPresenter extends NewsListContract.Presenter{
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
     * 请求获取数据列表
     * @param type
     * @param id
     * @param startPage
     */
    @Override
    public void getNewsListDataRequest(String type, String id, int startPage) {
        mRxManage.add(mModel.getNewsListData(type,id,startPage).subscribe(new RxSubscriber<List<NewsSummary>>(mContext,false) {
            @Override
            public void onStart() {
                super.onStart();
                mView.showLoading("请稍后...");
            }

            @Override
            protected void _onNext(List<NewsSummary> newsSummaries) {
                mView.returnNewsListData(newsSummaries);
                mView.stopLoading();
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
    }
}
