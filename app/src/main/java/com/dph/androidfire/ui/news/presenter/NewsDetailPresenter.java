package com.dph.androidfire.ui.news.presenter;

import com.dph.androidfire.R;
import com.dph.androidfire.bean.NewsDetail;
import com.dph.androidfire.ui.news.contract.NewsDetailContract;
import com.dph.common.baserx.RxSubscriber;
import com.dph.common.commonutils.ToastUitl;

/**
 * 项目名称： AndroidFireMe
 * 包名：com.jaydenxiao.androidfire.ui.news.presenter
 * 类描述： describe
 * 创建人： wxw https://github.com/huatianjiajia
 * 创建时间： 2017/6/24 8:53
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class NewsDetailPresenter extends NewsDetailContract.Presenter{
    @Override
    public void getOneNewsDataRequest(String postId) {
        mRxManage.add(mModel.getOneNewsData(postId).subscribe(new RxSubscriber<NewsDetail>(mContext) {
            @Override
            protected void _onNext(NewsDetail newsDetail) {
                mView.returnOneNewsData(newsDetail);
            }

            @Override
            protected void _onError(String message) {
                ToastUitl.showToastWithImg(message, R.drawable.ic_wrong);
            }
        }));
    }
}
