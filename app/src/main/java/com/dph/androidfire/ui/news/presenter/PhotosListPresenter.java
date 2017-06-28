package com.dph.androidfire.ui.news.presenter;

import com.dph.androidfire.bean.PhotoGirl;
import com.dph.androidfire.ui.news.contract.PhotoListContract;
import com.dph.common.baserx.RxSubscriber;

import java.util.List;

/**
 * 项目名称： AndroidFireMe
 * 包名：com.jaydenxiao.androidfire.ui.news.presenter
 * 类描述： describe
 * 创建人： wxw https://github.com/huatianjiajia
 * 创建时间： 2017/6/27 10:30
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class PhotosListPresenter extends PhotoListContract.Presenter {
    @Override
    public void getPhotosListDataRequest(int size, int page) {
        mRxManage.add(mModel.getPhotosListData(size,page).subscribe(new RxSubscriber<List<PhotoGirl>>(mContext,false) {
            @Override
            public void onStart() {
                super.onStart();
                mView.showLoading("请稍后...");
            }

            @Override
            protected void _onNext(List<PhotoGirl> photoGirls) {
                mView.returnPhotosListData(photoGirls);
                mView.stopLoading();
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
    }
}
