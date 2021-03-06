package com.dph.androidfire.ui.news.model;

import com.dph.androidfire.api.Api;
import com.dph.androidfire.api.ApiConstants;
import com.dph.androidfire.api.HostType;
import com.dph.androidfire.bean.NewsSummary;
import com.dph.androidfire.ui.news.contract.NewsListContract;
import com.dph.common.baserx.RxSchedulers;
import com.dph.common.commonutils.TimeUtil;

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
 * 创建时间： 2017/6/22 11:07
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class NewsListModel implements NewsListContract.Model{
    /**
     * 获取新闻列表
     * @param type
     * @param id
     * @param startPage
     * @return
     */
    @Override
    public Observable<List<NewsSummary>> getNewsListData(final String type, final String id, final int startPage) {
        return Api.getDefault(HostType.NETEASE_NEWS_VIDEO).getNewsList(Api.getCacheControl(),type,id,startPage)
                .flatMap(new Func1<Map<String, List<NewsSummary>>, Observable<NewsSummary>>() {
                    @Override
                    public Observable<NewsSummary> call(Map<String, List<NewsSummary>> map) {
                        if(id.endsWith(ApiConstants.HOUSE_ID)){
                            //房产实际上针对地区的它的id与返回key不同
                            return Observable.from(map.get("北京"));
                        }
                        return Observable.from(map.get(id));
                    }
                })
                //转化时间
                .map(new Func1<NewsSummary, NewsSummary>() {
                    @Override
                    public NewsSummary call(NewsSummary newsSummary) {
                        String ptime= TimeUtil.formatDate(newsSummary.getPtime());
                        newsSummary.setPtime(ptime);
                        return newsSummary;
                    }
                })
                .distinct()//去重
                .toSortedList(new Func2<NewsSummary, NewsSummary, Integer>() {
                    @Override
                    public Integer call(NewsSummary newsSummary, NewsSummary newsSummary2) {
                        return newsSummary2.getPtime().compareTo(newsSummary.getPtime());
                    }
                })
                //声明线程调度
                .compose(RxSchedulers.<List<NewsSummary>>io_main());
    }
}
