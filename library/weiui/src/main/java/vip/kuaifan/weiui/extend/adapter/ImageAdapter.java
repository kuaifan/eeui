package vip.kuaifan.weiui.extend.adapter;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import vip.kuaifan.weiui.activity.PageActivity;
import vip.kuaifan.weiui.extend.bean.PageBean;
import vip.kuaifan.weiui.extend.integration.glide.Glide;
import vip.kuaifan.weiui.extend.integration.glide.load.DataSource;
import vip.kuaifan.weiui.extend.integration.glide.load.engine.DiskCacheStrategy;
import vip.kuaifan.weiui.extend.integration.glide.load.engine.GlideException;
import vip.kuaifan.weiui.extend.integration.glide.request.RequestListener;
import vip.kuaifan.weiui.extend.integration.glide.request.RequestOptions;
import vip.kuaifan.weiui.extend.integration.glide.request.target.Target;
import vip.kuaifan.weiui.extend.module.weiuiHtml;

import com.taobao.weex.WXSDKManager;
import com.taobao.weex.adapter.IWXImgLoaderAdapter;
import com.taobao.weex.common.WXImageStrategy;
import com.taobao.weex.dom.WXImageQuality;

/**
 * Created by WDM on 2018/2/28.
 */

public class ImageAdapter implements IWXImgLoaderAdapter {

    private static final String TAG = "ImageAdapter";

    private Handler mHandler = new Handler();

    public ImageAdapter() {
    }

    @Override
    public void setImage(String url, ImageView view, WXImageQuality quality, WXImageStrategy strategy) {
        Runnable runnable = () -> {
            if (view == null || view.getLayoutParams() == null) {
                return;
            }
            if (TextUtils.isEmpty(url)) {
                view.setImageBitmap(null);
                return;
            }
            loadImage(0, url, view, strategy);
        };
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            runnable.run();
        } else {
            WXSDKManager.getInstance().postOnUiThread(runnable, 0);
        }
    }

    /**
     * 加载图片程序
     *
     * @param loadNum
     * @param url
     * @param view
     * @param strategy
     */
    private void loadImage(int loadNum, String url, ImageView view, WXImageStrategy strategy) {
        if (view.getLayoutParams().width <= 0 || view.getLayoutParams().height <= 0) {
            if (loadNum < 5) {
                mHandler.postDelayed(() -> view.post(() -> loadImage(loadNum + 1, url, view, strategy)), 200);
            }
            return;
        }
        //
        String tempUrl = url;
        if (tempUrl.startsWith("//")) {
            tempUrl = "http:" + url;
        } else if (!tempUrl.startsWith("http") && !tempUrl.startsWith("ftp:") && !tempUrl.startsWith("file:") && !tempUrl.startsWith("data:")) {
            if (view.getContext() instanceof PageActivity) {
                PageBean mPageBean = ((PageActivity) view.getContext()).getPageInfo();
                if (mPageBean != null) {
                    tempUrl = weiuiHtml.repairUrl(tempUrl, mPageBean.getUrl());
                }
            }
        }
        //
        if (view.getContext() == null) {
            return;
        }
        //
        RequestOptions myOptions = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(view.getContext()).load(tempUrl).apply(myOptions).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                if (strategy.getImageListener() != null) {
                    strategy.getImageListener().onImageFinish(url, view, false, null);
                }
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                if (strategy.getImageListener() != null) {
                    strategy.getImageListener().onImageFinish(url, view, true, null);
                }
                return false;
            }
        }).into(view);
    }
}