package com.jaydenxiao.androidfire.ui.zone.model;

import com.jaydenxiao.androidfire.bean.Result;
import com.jaydenxiao.androidfire.ui.zone.DatasUtil;
import com.jaydenxiao.androidfire.ui.zone.bean.CommentItem;
import com.jaydenxiao.androidfire.ui.zone.contract.CircleZoneContract;
import com.jaydenxiao.common.baseapp.AppCache;
import com.jaydenxiao.common.baserx.RxSchedulers;
import com.jaydenxiao.common.commonutils.LogUtils;

import rx.Observable;
import rx.Subscriber;

/**
 * 项目名称： AndroidFireMe
 * 包名：com.jaydenxiao.androidfire.ui.zone.model
 * 类描述： describe
 * 创建人： wxw https://github.com/huatianjiajia
 * 创建时间： 2017/6/27 17:28
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ZoneModel implements CircleZoneContract.Model {
    /**
     * 获取未读条数
     * @return
     */
    @Override
    public Observable<String> getZoneNotReadNews() {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext(AppCache.getInstance().getIcon());
                subscriber.onCompleted();
                LogUtils.logd(AppCache.getInstance().getIcon());
            }
        }).compose(RxSchedulers.<String>io_main());
    }

    @Override
    public Observable<Result> getListDatas(String type, String userId, int page, int rows) {
        return Observable.create(new Observable.OnSubscribe<Result>() {
            @Override
            public void call(Subscriber<? super Result> subscriber) {
                Result result = DatasUtil.getZoneListDatas();
                subscriber.onNext(result);
                subscriber.onCompleted();
                LogUtils.logd("result"+result.toString());
            }
        }).compose(RxSchedulers.<Result>io_main());
    }

    @Override
    public Observable<Result> deleteCircle(String circleId, int position) {
        return Observable.create(new Observable.OnSubscribe<Result>() {
            @Override
            public void call(Subscriber<? super Result> subscriber) {
                Result result = new Result();
                subscriber.onNext(result);
                subscriber.onCompleted();
                LogUtils.logd(result.toString());
            }
        }).compose(RxSchedulers.<Result>io_main());
    }
    /**
     * 增加点赞
     * @param publishId
     * @param publishUserId
     * @return
     */
    @Override
    public Observable<Result> addFavort(String publishId, String publishUserId) {
        return Observable.create(new Observable.OnSubscribe<Result>() {
            @Override
            public void call(Subscriber<? super Result> subscriber) {
                Result result = new Result();
                subscriber.onNext(result);
                subscriber.onCompleted();
                LogUtils.logd(result.toString());
            }
        }).compose(RxSchedulers.<Result>io_main());
    }

    /**
     * 取消点赞
     * @param publishId
     * @param publishUserId
     * @return
     */
    @Override
    public Observable<Result> deleteFavort(String publishId, String publishUserId) {
        return Observable.create(new Observable.OnSubscribe<Result>() {
            @Override
            public void call(Subscriber<? super Result> subscriber) {
                Result result = new Result();
                subscriber.onNext(result);
                subscriber.onCompleted();
                LogUtils.logd(result.toString());
            }
        }).compose(RxSchedulers.<Result>io_main());
    }

    /**
     * 增加评论
     * @param publishUserId
     * @param circleItem
     * @return
     */
    @Override
    public Observable<Result> addComment(String publishUserId, CommentItem circleItem) {
        return Observable.create(new Observable.OnSubscribe<Result>() {
            @Override
            public void call(Subscriber<? super Result> subscriber) {
                Result result = new Result();
                subscriber.onNext(result);
                subscriber.onCompleted();
                LogUtils.logd(result.toString());
            }
        }).compose(RxSchedulers.<Result>io_main());
    }

    /**
     * 删除评论
     * @param commentId
     * @return
     */
    @Override
    public Observable<Result> deleteComment(String commentId) {
        return Observable.create(new Observable.OnSubscribe<Result>() {
            @Override
            public void call(Subscriber<? super Result> subscriber) {
                Result result = new Result();
                subscriber.onNext(result);
                subscriber.onCompleted();
                LogUtils.logd(result.toString());
            }
        }).compose(RxSchedulers.<Result>io_main());
    }

}
