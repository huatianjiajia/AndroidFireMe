package com.jaydenxiao.androidfire.ui.news.model;

import com.jaydenxiao.androidfire.api.Api;
import com.jaydenxiao.androidfire.api.HostType;
import com.jaydenxiao.androidfire.bean.GirlData;
import com.jaydenxiao.androidfire.bean.PhotoGirl;
import com.jaydenxiao.androidfire.ui.news.contract.PhotoListContract;
import com.jaydenxiao.common.baserx.RxSchedulers;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * 项目名称： AndroidFireMe
 * 包名：com.jaydenxiao.androidfire.ui.news.model
 * 类描述： describe
 * 创建人： wxw https://github.com/huatianjiajia
 * 创建时间： 2017/6/27 10:27
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class PhotosListModel implements PhotoListContract.Model{
    @Override
    public Observable<List<PhotoGirl>> getPhotosListData(int size, int page) {
        return Api.getDefault(HostType.GANK_GIRL_PHOTO)
                .getPhotoList(Api.getCacheControl(),size,page)
                .map(new Func1<GirlData, List<PhotoGirl>>() {
                    @Override
                    public List<PhotoGirl> call(GirlData girlData) {
                        return girlData.getResults();
                    }
                })
                .compose(RxSchedulers.<List<PhotoGirl>>io_main());
    }
}
