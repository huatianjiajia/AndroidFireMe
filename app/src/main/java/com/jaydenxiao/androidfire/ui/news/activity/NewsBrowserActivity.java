package com.jaydenxiao.androidfire.ui.news.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.jaydenxiao.androidfire.R;
import com.jaydenxiao.androidfire.app.AppConstant;
import com.jaydenxiao.common.base.BaseActivity;

import butterknife.Bind;

/**
 * 项目名称： AndroidFireMe
 * 包名：com.jaydenxiao.androidfire.ui.news.activity
 * 类描述： describe
 * 创建人： wxw https://github.com/huatianjiajia
 * 创建时间： 2017/6/24 11:17
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class NewsBrowserActivity extends BaseActivity {


    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.progress_bar)
    ProgressBar progressBar;
    @Bind(R.id.web_view)
    WebView webView;

    public static void startAction(Context context, String link, String title) {
        Intent intent = new Intent(context, NewsBrowserActivity.class);
        intent.putExtra(AppConstant.NEWS_LINK, link);
        intent.putExtra(AppConstant.NEWS_TITLE, title);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.act_news_browser;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        setWebViewSettings();
        setWebView();
    }


    private void setWebViewSettings() {
        WebSettings webSetting=webView.getSettings();
        //打开页面时，自适应屏幕
        webSetting.setUseWideViewPort(true);//将图片调整到适合webview的大小
        webSetting.setLoadWithOverviewMode(true);//缩放至屏幕的大小
        //使页面支持缩放
        webSetting.setJavaScriptEnabled(true);//支持js
        webSetting.setSupportZoom(true);//支持缩放
        //        webSettings.setBuiltInZoomControls(true); // 放大和缩小的按钮，容易引发异常 http://blog.csdn.net/dreamer0924/article/details/34082687

        webSetting.setAppCacheEnabled(true);
        webSetting.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
    }

    private void setWebView() {
        webView.loadUrl(getIntent().getStringExtra(AppConstant.NEWS_LINK));
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url!=null) view.loadUrl(url);
                return true;
            }
        });
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress==100){
                    progressBar.setVisibility(View.GONE);
                }else{
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.setProgress(newProgress);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        if (webView!=null){
            webView.removeAllViews();
            webView.destroy();
        }
        super.onDestroy();
    }
}
