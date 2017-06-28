package com.dph.androidfire.widget;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.text.Html;
import android.widget.TextView;

import com.dph.androidfire.R;
import com.dph.androidfire.api.Api;
import com.dph.androidfire.api.HostType;
import com.dph.androidfire.app.AppApplication;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 项目名称： AndroidFireMe
 * 包名：com.jaydenxiao.androidfire.widget
 * 类描述： describe
 * 创建人： wxw https://github.com/huatianjiajia
 * 创建时间： 2017/6/24 9:00
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class URLImageGetter implements Html.ImageGetter{
    private TextView mTextView;
    private int mPicWidth;
    private String mNewsBody;
    private int mPicCount;
    private int mPicTotal;
    private static final String mFilePath= AppApplication.getAppContext().getCacheDir().getAbsolutePath();
    public Subscription mSubscription;

    public URLImageGetter(TextView mTextView, String mNewsBody, int mPicTotal) {
        this.mTextView = mTextView;
        this.mNewsBody = mNewsBody;
        this.mPicTotal = mPicTotal;
        mPicWidth=mTextView.getWidth();
    }

    @Override
    public Drawable getDrawable(final String source) {
        Drawable drawable;
        File file=new File(mFilePath,source.hashCode()+"");
        if (file.exists()){
            mPicCount++;
            drawable=getDrawableFormDisk(file);
        }else{
            drawable=getDrawableFormNet(source);
        }
        return drawable;
    }

    private Drawable getDrawableFormDisk(File file) {
        Drawable drawable=Drawable.createFromPath(file.getAbsolutePath());
        if (drawable!=null){
            int picHeight=calculatePicHeight(drawable);
            drawable.setBounds(0,0,mPicWidth,picHeight);
        }
        return drawable;
    }

    private int calculatePicHeight(Drawable drawable) {
        float imgWidth=drawable.getIntrinsicWidth();
        float imgHeight=drawable.getIntrinsicHeight();
        float rate=imgHeight/imgWidth;
        return (int)(mPicWidth*rate);
    }

    private Drawable getDrawableFormNet(final String source) {
        mSubscription= Api.getDefault(HostType.NEWS_DETAIL_HTML_PHOTO)
                .getNewsBodyHtmlPhoto(Api.getCacheControl(),source)
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<ResponseBody, Boolean>() {
                    @Override
                    public Boolean call(ResponseBody response) {
                        return WritePicToDisk(response,source);
                    }
                }).subscribe(new Subscriber<Boolean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Boolean isLoadSuccess) {
                        mPicCount++;
                        if (isLoadSuccess&&(mPicCount==mPicTotal-1)){
                            mTextView.setText(Html.fromHtml(mNewsBody,URLImageGetter.this,null));
                        }
                    }
                });

        return createPicPlaceholder();
    }


    private Boolean WritePicToDisk(ResponseBody response, String source) {
        File file=new File(mFilePath,source.hashCode()+"");
        InputStream in=null;
        FileOutputStream out=null;
        try {
            in=response.byteStream();
            out=new FileOutputStream(file);
            byte[] buffer=new byte[1024];
            int len;
            while ((len=in.read(buffer))!=-1){
                out.write(buffer,0,len);
            }
            return true;
        } catch (Exception e) {
            return false;
        }finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
            }
        }
    }

    @SuppressWarnings("deprecation")
    @NonNull
    private Drawable createPicPlaceholder() {
       Drawable drawable;
        int color= R.color.white;
        drawable=new ColorDrawable(AppApplication.getAppContext().getResources().getColor(color));
        drawable.setBounds(0,0,mPicWidth,mPicWidth/3);
        return drawable;
    }

}
