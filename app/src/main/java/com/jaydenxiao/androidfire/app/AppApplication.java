package com.jaydenxiao.androidfire.app;

import com.jaydenxiao.common.BuildConfig;
import com.jaydenxiao.common.baseapp.BaseApplication;
import com.jaydenxiao.common.commonutils.LogUtils;

/**
 * 项目名称： AndroidFireWxw
 * 包名：com.jaydenxiao.androidfire.app
 * 类描述： describe
 * 创建人： wxw https://github.com/huatianjiajia
 * 创建时间： 2017/6/13 11:26
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class AppApplication extends BaseApplication{
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化logger
        LogUtils.logInit(BuildConfig.LOG_DEBUG);
    }
}
