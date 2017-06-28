package com.dph.androidfire.ui.news.model;

import com.dph.androidfire.api.Api;
import com.dph.androidfire.api.HostType;
import com.dph.androidfire.bean.NewsDetail;
import com.dph.androidfire.ui.news.contract.NewsDetailContract;
import com.dph.common.baserx.RxSchedulers;

import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.functions.Func1;

/**
 * 项目名称： AndroidFireMe
 * 包名：com.jaydenxiao.androidfire.ui.news.model
 * 类描述： describe
 * 创建人： wxw https://github.com/huatianjiajia
 * 创建时间： 2017/6/23 16:46
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class NewsDetailModel implements NewsDetailContract.Model {
    @Override
    public Observable<NewsDetail> getOneNewsData(final String postId) {
        return Api.getDefault(HostType.NETEASE_NEWS_VIDEO).getNewDetail(Api.getCacheControl(),postId)
                .map(new Func1<Map<String, NewsDetail>, NewsDetail>() {
                    @Override
                    public NewsDetail call(Map<String, NewsDetail> map) {
                        NewsDetail newsDetail=map.get(postId);
                        changeNewsDetail(newsDetail);
                        return newsDetail;
                    }
                })
                .compose(RxSchedulers.<NewsDetail>io_main());
    }

    private void changeNewsDetail(NewsDetail newsDetail) {
        List<NewsDetail.ImgBean> imgSrcs=newsDetail.getImg();
        if (isChange(imgSrcs)){
            String newsBody=newsDetail.getBody();
            newsBody=changeNewsBody(imgSrcs,newsBody);
            newsDetail.setBody(newsBody);
        }
    }


    private boolean isChange(List<NewsDetail.ImgBean> imgSrcs) {
        return imgSrcs!=null&&imgSrcs.size()>=2;
    }
    private String changeNewsBody(List<NewsDetail.ImgBean> imgSrcs, String newsBody) {
        for (int i = 0; i < imgSrcs.size(); i++) {
            String oldChars = "<!--IMG#" + i + "-->";
            String newChars;
            if (i == 0) {
                newChars = "";
            } else {
                newChars = "<img src=\"" + imgSrcs.get(i).getSrc() + "\" />";
            }
            newsBody = newsBody.replace(oldChars, newChars);

        }
        return newsBody;
    }



}
