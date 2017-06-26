package com.jaydenxiao.androidfire.ui.news.contract;

import com.jaydenxiao.androidfire.bean.NewsChannelTable;
import com.jaydenxiao.common.base.BaseModel;
import com.jaydenxiao.common.base.BasePresenter;
import com.jaydenxiao.common.base.BaseView;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * 项目名称： AndroidFireMe
 * 包名：com.jaydenxiao.androidfire.ui.news.contract
 * 类描述： describe
 * 创建人： wxw https://github.com/huatianjiajia
 * 创建时间： 2017/6/26 15:43
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public interface NewsChannelContract {
    interface Model extends BaseModel {
        Observable<List<NewsChannelTable>> lodeMineNewsChannels();

        Observable<List<NewsChannelTable>> lodeMoreNewsChannels();

        Observable<String> swapDb(ArrayList<NewsChannelTable> newsChannelTableList, int fromPosition, int toPosition);

        Observable<String> updateDb(ArrayList<NewsChannelTable> mineChannelTableList, ArrayList<NewsChannelTable> moreChannelTableList);
    }

    interface View extends BaseView{
        void returnMineNewsChannels(List<NewsChannelTable> newsChannelsMine);
        void returnMoreNewsChannels(List<NewsChannelTable> newsChannelsMore);
    }

    abstract static class Presenter extends BasePresenter<View,Model>{
        public abstract void lodeChannelsRequest();
        public abstract void onItemSwap(ArrayList<NewsChannelTable> newsChannelTableList,int fromPosition,final int toPosition);
        public abstract void onItemAddOrRemove(ArrayList<NewsChannelTable> mineChannelTableList,ArrayList<NewsChannelTable> moreChannelTableList);
    }
}
