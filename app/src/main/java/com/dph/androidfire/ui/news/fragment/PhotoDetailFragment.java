package com.dph.androidfire.ui.news.fragment;

import android.view.View;
import android.widget.ProgressBar;

import com.dph.androidfire.R;
import com.dph.androidfire.app.AppConstant;
import com.dph.common.base.BaseFragment;
import com.dph.common.baserx.RxSchedulers;
import com.dph.common.commonutils.ImageLoaderUtils;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import rx.Observable;
import rx.Subscriber;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * 项目名称： AndroidFireMe
 * 包名：com.jaydenxiao.androidfire.ui.news.fragment
 * 类描述： describe
 * 创建人： wxw https://github.com/huatianjiajia
 * 创建时间： 2017/6/24 17:25
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class PhotoDetailFragment extends BaseFragment {

    @Bind(R.id.photo_view)
    PhotoView photoView;
    @Bind(R.id.progress_bar)
    ProgressBar progressBar;
    private String mImgSrc;

    @Override
    protected int getLayoutResource() {
        return R.layout.fra_news_photo_detail;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        if (getArguments()!=null){
            mImgSrc=getArguments().getString(AppConstant.PHOTO_DETAIL_IMGSRC);
        }
        initPhotoView();
        setPhotoViewClickEvent();
    }

    private void initPhotoView() {
        mRxManager.add(Observable.timer(100, TimeUnit.MILLISECONDS)//直接使用glide加载的话，activity切换动画时背景短暂为默认背景色
                .compose(RxSchedulers.<Long>io_main())
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onCompleted() {
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onNext(Long aLong) {
                        ImageLoaderUtils.displayBigPhoto(getContext(),photoView,mImgSrc);
                    }
                })
        );
    }

    private void setPhotoViewClickEvent() {
        photoView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float x, float y) {
                mRxManager.post(AppConstant.PHOTO_TAB_CLICK,"");
            }
        });
    }
}
