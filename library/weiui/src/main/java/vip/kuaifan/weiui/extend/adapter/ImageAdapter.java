package vip.kuaifan.weiui.extend.adapter;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import vip.kuaifan.weiui.PageActivity;
import vip.kuaifan.weiui.R;
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

    public ImageAdapter() {
    }

    @Override
    public void setImage(String url, ImageView view, WXImageQuality quality, WXImageStrategy strategy) {
        WXSDKManager.getInstance().postOnUiThread(() -> {
            if (view == null || view.getLayoutParams() == null) {
                return;
            }
            if (TextUtils.isEmpty(url)) {
                view.setImageBitmap(null);
                return;
            }
            if (view.getLayoutParams().width <= 0 || view.getLayoutParams().height <= 0) {
                return;
            }
            //
            String newUrl = url;
            if (newUrl.startsWith("//")) {
                newUrl = "http:" + url;
            }else if (!newUrl.startsWith("http") && !newUrl.startsWith("ftp:") && !newUrl.startsWith("file:") && !newUrl.startsWith("data:")) {
                if (view.getContext() instanceof PageActivity) {
                    PageBean mPageBean = ((PageActivity) view.getContext()).getPageInfo();
                    if (mPageBean != null) {
                        newUrl = weiuiHtml.repairUrl(newUrl, mPageBean.getUrl());
                    }
                }
            }
            //
            try {
                Glide.with(view.getContext())
                        .load(newUrl)
                        .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                        .listener(new RequestListener<Drawable>() {
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
            } catch (Exception ignored) {
                view.setImageResource(R.drawable.load_before);
            }
        }, 0);
    }
}