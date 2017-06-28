package com.dph.androidfire.ui.main.model;

import com.dph.androidfire.app.AppApplication;
import com.dph.androidfire.app.AppConstant;
import com.dph.androidfire.bean.NewsChannelTable;
import com.dph.androidfire.db.NewsChannelTableManager;
import com.dph.androidfire.ui.main.contract.NewsMainContract;
import com.dph.common.baserx.RxSchedulers;
import com.dph.common.commonutils.ACache;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * 项目名称： AndroidFireMe
 * 包名：com.jaydenxiao.androidfire.ui.main.model
 * 类描述： describe
 * 创建人： wxw https://github.com/huatianjiajia
 * 创建时间： 2017/6/20 16:46
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class NewsMainModel implements NewsMainContract.Model{
    @Override
    public Observable<List<NewsChannelTable>> lodeMineNewsChannels() {
        return Observable.create(new Observable.OnSubscribe<List<NewsChannelTable>>() {
            @Override
            public void call(Subscriber<? super List<NewsChannelTable>> subscriber) {
                ArrayList<NewsChannelTable> newsChannelTableList= (ArrayList<NewsChannelTable>) ACache.get(AppApplication.getAppContext()).getAsObject(AppConstant.CHANEL_MINE);
                if (newsChannelTableList==null){
                    newsChannelTableList= (ArrayList<NewsChannelTable>) NewsChannelTableManager.loadNewsChannelsStatic();
                    ACache.get(AppApplication.getAppContext()).put(AppConstant.CHANEL_MINE,newsChannelTableList);
                }
                subscriber.onNext(newsChannelTableList);
                subscriber.onCompleted();
            }
        }).compose(RxSchedulers.<List<NewsChannelTable>>io_main());
    }
}
