package com.jaydenxiao.androidfire.db;

import com.jaydenxiao.androidfire.R;
import com.jaydenxiao.androidfire.api.ApiConstants;
import com.jaydenxiao.androidfire.app.AppApplication;
import com.jaydenxiao.androidfire.bean.NewsChannelTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 项目名称： AndroidFireMe
 * 包名：com.jaydenxiao.androidfire.db
 * 类描述： describe
 * 创建人： wxw https://github.com/huatianjiajia
 * 创建时间： 2017/6/20 16:53
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class NewsChannelTableManager {
    /**
     * 加载新闻类型
     */
    public static List<NewsChannelTable> loadNewsChannelsMine(){
        List<String> channelName= Arrays.asList(AppApplication.getAppContext().getResources().getStringArray(R.array.news_channel_name));
        List<String> channelId=Arrays.asList(AppApplication.getAppContext().getResources().getStringArray(R.array.news_channel_id));
        ArrayList<NewsChannelTable> newsChannelTables=new ArrayList<>();
        for (int i = 0; i < channelName.size(); i++) {
            NewsChannelTable entity=new NewsChannelTable(channelName.get(i),channelId.get(i)
            , ApiConstants.getType(channelId.get(i)),i<=5,i,false);
            newsChannelTables.add(entity);
        }
        return newsChannelTables;
    }

    /**
     * 加载固定的新闻类型
     */
    public static List<NewsChannelTable> loadNewsChannelsStatic(){
        List<String> channelName = Arrays.asList(AppApplication.getAppContext().getResources().getStringArray(R.array.news_channel_name_static));
        List<String> channelId = Arrays.asList(AppApplication.getAppContext().getResources().getStringArray(R.array.news_channel_id_static));
        ArrayList<NewsChannelTable>newsChannelTables=new ArrayList<>();
        for (int i = 0; i < channelName.size(); i++) {
            NewsChannelTable entity = new NewsChannelTable(channelName.get(i), channelId.get(i)
                    , ApiConstants.getType(channelId.get(i)), i <= 5, i, true);
            newsChannelTables.add(entity);
        }
        return newsChannelTables;
    }
}
