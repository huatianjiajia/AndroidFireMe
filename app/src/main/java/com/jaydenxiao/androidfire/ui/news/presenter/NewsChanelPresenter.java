package com.jaydenxiao.androidfire.ui.news.presenter;

import com.jaydenxiao.androidfire.app.AppConstant;
import com.jaydenxiao.androidfire.bean.NewsChannelTable;
import com.jaydenxiao.androidfire.ui.news.contract.NewsChannelContract;
import com.jaydenxiao.common.baserx.RxSubscriber;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称： AndroidFireMe
 * 包名：com.jaydenxiao.androidfire.ui.news.presenter
 * 类描述： describe
 * 创建人： wxw https://github.com/huatianjiajia
 * 创建时间： 2017/6/26 16:01
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class NewsChanelPresenter extends NewsChannelContract.Presenter{
    @Override
    public void lodeChannelsRequest() {
        mRxManage.add(mModel.lodeMineNewsChannels().subscribe(new RxSubscriber<List<NewsChannelTable>>(mContext,false) {
            @Override
            protected void _onNext(List<NewsChannelTable> newsChannelTables) {
                mView.returnMineNewsChannels(newsChannelTables);
            }

            @Override
            protected void _onError(String message) {

            }
        }));
        mRxManage.add(mModel.lodeMoreNewsChannels().subscribe(new RxSubscriber<List<NewsChannelTable>>(mContext,false) {
            @Override
            protected void _onNext(List<NewsChannelTable> newsChannelTables) {
                mView.returnMoreNewsChannels(newsChannelTables);
            }

            @Override
            protected void _onError(String message) {

            }
        }));
    }

    @Override
    public void onItemSwap(final ArrayList<NewsChannelTable> newsChannelTableList, int fromPosition, int toPosition) {
        mRxManage.add(mModel.swapDb(newsChannelTableList,fromPosition,toPosition).subscribe(new RxSubscriber<String>(mContext,false) {
            @Override
            protected void _onNext(String s) {
                mRxManage.post(AppConstant.NEWS_CHANEL_CHANGED,newsChannelTableList);
            }

            @Override
            protected void _onError(String message) {

            }
        }));
    }

    @Override
    public void onItemAddOrRemove(final ArrayList<NewsChannelTable> mineChannelTableList, ArrayList<NewsChannelTable> moreChannelTableList) {
        mRxManage.add(mModel.updateDb(mineChannelTableList,moreChannelTableList).subscribe(new RxSubscriber<String>(mContext,false) {
            @Override
            protected void _onNext(String s) {
                mRxManage.post(AppConstant.NEWS_CHANEL_CHANGED,mineChannelTableList);
            }

            @Override
            protected void _onError(String message) {

            }
        }));
    }
}
