package com.dph.androidfire.db;

import com.dph.androidfire.R;
import com.dph.androidfire.app.AppApplication;
import com.dph.androidfire.bean.VideoChannelTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 项目名称： AndroidFireMe
 * 包名：com.jaydenxiao.androidfire.db
 * 类描述： describe
 * 创建人： wxw https://github.com/huatianjiajia
 * 创建时间： 2017/6/27 15:18
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class VideosChannelTableManager {
    /**
     * 加载视频类型
     */
    public static List<VideoChannelTable> loadVideosChannelsMine(){
        List<String> channelName= Arrays.asList(AppApplication.getAppContext().getResources().getStringArray(R.array.video_channel_name));
        List<String> channelId = Arrays.asList(AppApplication.getAppContext().getResources().getStringArray(R.array.video_channel_id));
        ArrayList<VideoChannelTable> newsChannelTables=new ArrayList<>();
        for (int i = 0; i < channelName.size(); i++) {
            VideoChannelTable entity=new VideoChannelTable(channelId.get(i),channelName.get(i));
            newsChannelTables.add(entity);
        }
        return newsChannelTables;
    }
}
