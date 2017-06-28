package com.dph.androidfire.ui.main.contract;

import com.dph.androidfire.bean.NewsChannelTable;
import com.dph.common.base.BaseModel;
import com.dph.common.base.BasePresenter;
import com.dph.common.base.BaseView;

import java.util.List;

import rx.Observable;

/**
 * 项目名称： AndroidFireMe
 * 包名：com.jaydenxiao.androidfire.ui.main.contract
 * 类描述： describe
 * 创建人： wxw https://github.com/huatianjiajia
 * 创建时间： 2017/6/20 16:35
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public interface NewsMainContract {
    interface Model extends BaseModel{
        Observable<List<NewsChannelTable>> lodeMineNewsChannels();
    }

    interface View extends BaseView{
        void returnMineNewsChannels(List<NewsChannelTable> newsChannelTables);
    }
    abstract  static class Presenter extends BasePresenter<View,Model>{
        public abstract void lodeMineChannelsRequest();
    }
}
