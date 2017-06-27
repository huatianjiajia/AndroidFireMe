package com.jaydenxiao.androidfire.ui.news.model;

import com.jaydenxiao.androidfire.api.Api;
import com.jaydenxiao.androidfire.api.HostType;
import com.jaydenxiao.androidfire.bean.VideoData;
import com.jaydenxiao.androidfire.ui.news.contract.VideosListContract;
import com.jaydenxiao.common.baserx.RxSchedulers;
import com.jaydenxiao.common.commonutils.TimeUtil;

import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * 项目名称： AndroidFireMe
 * 包名：com.jaydenxiao.androidfire.ui.news.model
 * 类描述： describe
 * 创建人： wxw https://github.com/huatianjiajia
 * 创建时间： 2017/6/27 15:58
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class VideosListModel implements VideosListContract.Model{
    @Override
    public Observable<List<VideoData>> getVideosListData(final String type, int startPage) {
        return Api.getDefault(HostType.NETEASE_NEWS_VIDEO).getVideoList(Api.getCacheControl(),type,startPage)
                .flatMap(new Func1<Map<String, List<VideoData>>, Observable<VideoData>>() {
                    @Override
                    public Observable<VideoData> call(Map<String, List<VideoData>> stringListMap) {
                        return Observable.from(stringListMap.get(type));
                    }
                })
                //转化时间
                .map(new Func1<VideoData, VideoData>() {
                    @Override
                    public VideoData call(VideoData videoData) {
                        String ptime = TimeUtil.formatDate(videoData.getPtime());
                        videoData.setPtime(ptime);
                        return videoData;
                    }
                })
                .distinct()
                .toSortedList(new Func2<VideoData, VideoData, Integer>() {
                    @Override
                    public Integer call(VideoData videoData, VideoData videoData2) {
                        return videoData2.getPtime().compareTo(videoData.getPtime());
                    }
                })
                .compose(RxSchedulers.<List<VideoData>>io_main());
    }
}
