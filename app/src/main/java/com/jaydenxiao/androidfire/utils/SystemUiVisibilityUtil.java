package com.jaydenxiao.androidfire.utils;

import android.app.Activity;
import android.os.Build;
import android.view.View;

/**
 * 项目名称： AndroidFireMe
 * 包名：com.jaydenxiao.androidfire.utils
 * 类描述： describe
 * 创建人： wxw https://github.com/huatianjiajia
 * 创建时间： 2017/6/27 14:56
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SystemUiVisibilityUtil {
    private static final int FLAG_IMMERSIVE= View.SYSTEM_UI_FLAG_IMMERSIVE//与SYSTEM_UI_FLAG_HIDE_NAVIGATION组合使用,没有设置的话在隐藏导航栏后将没有交互
            |View.SYSTEM_UI_FLAG_HIDE_NAVIGATION//隐藏虚拟按键（导航栏）
            |View.SYSTEM_UI_FLAG_FULLSCREEN;//activity全屏显示，且状态栏被隐藏覆盖掉
    public static void exit(Activity activity) {
        if (Build.VERSION.SDK_INT >= 19) {
            addFlags(activity.getWindow().getDecorView(), FLAG_IMMERSIVE);
        }
    }

    public static void addFlags(View decorView, int flags) {
        decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | flags);
    }

    public static void enter(Activity activity) {
        if (Build.VERSION.SDK_INT >= 19) {
            clearFlags(activity.getWindow().getDecorView(), FLAG_IMMERSIVE);
        }
    }

    public static void clearFlags(View view, int flags) {
        view.setSystemUiVisibility(view.getSystemUiVisibility() & ~flags);
        // & ~flags 清除view.getSystemUiVisibility()中的flags
    }
}
