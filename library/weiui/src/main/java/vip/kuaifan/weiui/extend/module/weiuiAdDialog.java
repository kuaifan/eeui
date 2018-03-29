package vip.kuaifan.weiui.extend.module;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.bridge.JSCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vip.kuaifan.weiui.PageActivity;
import vip.kuaifan.weiui.R;
import vip.kuaifan.weiui.extend.bean.PageBean;
import vip.kuaifan.weiui.extend.integration.glide.Glide;
import vip.kuaifan.weiui.extend.integration.glide.load.engine.DiskCacheStrategy;
import vip.kuaifan.weiui.extend.integration.glide.request.RequestOptions;
import vip.kuaifan.weiui.extend.integration.glide.request.target.SimpleTarget;
import vip.kuaifan.weiui.extend.integration.glide.request.transition.Transition;
import vip.kuaifan.weiui.extend.module.utilcode.util.ScreenUtils;

/**
 * Created by WDM on 2018/3/26.
 */

@SuppressWarnings("unchecked")
public class weiuiAdDialog {

    private static List<DialogBean> mAdDialogList = new ArrayList<>();

    private static class DialogBean {

        private String dialogName;
        private String imgUrl;
        private String pageName;
        private Context context;
        private View view;

        public String getDialogName() {
            return dialogName;
        }

        public void setDialogName(String dialogName) {
            this.dialogName = dialogName;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getPageName() {
            return pageName;
        }

        public void setPageName(String pageName) {
            this.pageName = pageName;
        }


        public Context getContext() {
            return context;
        }

        public void setContext(Context context) {
            this.context = context;
        }

        public View getView() {
            return view;
        }

        public void setView(View view) {
            this.view = view;
        }
    }

    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/

    public static void create(WXSDKInstance mWXSDKInstance, JSONObject json, JSCallback callback) {
        if (mWXSDKInstance == null || json == null) {
            return;
        }
        String imgUrl = weiuiParse.parseStr(json.getString("imgUrl"), json.getString("img"));
        if (imgUrl == null || imgUrl.isEmpty()) {
            return;
        }
        Context mContext = mWXSDKInstance.getContext();
        if (mContext == null) {
            return;
        }
        String dialogName = weiuiParse.parseStr(json.getString("dialogName"), weiuiCommon.randomString(8));
        int dialogWidth = weiuiParse.parseInt(json.getString("width"));
        int dialogHeight = weiuiParse.parseInt(json.getString("height"));
        boolean showClose = weiuiParse.parseBool(json.getString("showClose"), true);
        boolean backClose = weiuiParse.parseBool(json.getString("backClose"), true);
        //
        if (callback != null) {
            Map<String, Object> newData = new HashMap<>();
            newData.put("status", "load");
            newData.put("dialogName", dialogName);
            newData.put("imgUrl", imgUrl);
            callback.invokeAndKeepAlive(newData);
        }
        Glide.with(mContext)
                .asBitmap()
                .load(imgUrl)
                .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, Transition<? super Bitmap> transition) {
                        PageActivity.startTransparentPage(mContext, new JSCallback() {

                            @Override
                            public void invoke(Object data) {
                                if (callback != null) {
                                    callback.invoke(data);
                                }
                            }

                            @Override
                            public void invokeAndKeepAlive(Object data) {
                                Map<String, Object> retData = weiuiMap.objectToMap(data);
                                String pageName = weiuiParse.parseStr(retData.get("pageName"));
                                String status = weiuiParse.parseStr(retData.get("status"));
                                PageBean mBean = weiuiPage.getPageBean(pageName);
                                if (mBean == null) {
                                    return;
                                }
                                if (status.equals("create")) {
                                    Activity mActivity = (Activity) mBean.getContext();
                                    Animation mAnimation = AnimationUtils.loadAnimation(mActivity, R.anim.in_to_down);
                                    //
                                    View tmpView = LayoutInflater.from(mActivity).inflate(R.layout.dialog_ad, null);
                                    ImageView tmpImage = tmpView.findViewById(R.id.v_activity_picture);
                                    ImageView tmpClose = tmpView.findViewById(R.id.v_activity_close);
                                    ViewGroup fullBox = tmpView.findViewById(R.id.float_full_box);
                                    //
                                    if (callback != null) {
                                        tmpImage.setOnClickListener(view -> {
                                            Map<String, Object> newData = (Map<String, Object>) data;
                                            newData.put("status", "click");
                                            newData.put("dialogName", dialogName);
                                            newData.put("imgUrl", imgUrl);
                                            callback.invokeAndKeepAlive(newData);
                                        });
                                    }
                                    if (showClose) {
                                        tmpClose.setVisibility(View.VISIBLE);
                                        tmpClose.setOnClickListener(view -> close(dialogName));
                                    }
                                    if (mActivity instanceof PageActivity) {
                                        ((PageActivity) mActivity).setOnBackPressed(() -> !backClose || close(dialogName));
                                    }
                                    //
                                    DialogBean tmpBean = new DialogBean();
                                    tmpBean.setDialogName(dialogName);
                                    tmpBean.setImgUrl(imgUrl);
                                    tmpBean.setPageName(mBean.getPageName());
                                    tmpBean.setContext(mBean.getContext());
                                    tmpBean.setView(tmpView);
                                    mAdDialogList.add(tmpBean);
                                    //
                                    ViewGroup.LayoutParams params = tmpImage.getLayoutParams();
                                    if (dialogWidth > 0 && dialogHeight > 0) {
                                        params.width = weiuiScreenUtils.weexPx2dp(mWXSDKInstance, dialogWidth);
                                        params.height = weiuiScreenUtils.weexPx2dp(mWXSDKInstance, dialogHeight);
                                    }else if (dialogWidth > 0) {
                                        params.width = weiuiScreenUtils.weexPx2dp(mWXSDKInstance, dialogWidth);
                                        params.height = (int) weiuiCommon.scaleHeight(params.width, resource.getWidth(), resource.getHeight());
                                    }else if (dialogHeight > 0) {
                                        params.height = weiuiScreenUtils.weexPx2dp(mWXSDKInstance, dialogHeight);
                                        params.width = (int) weiuiCommon.scaleWidth(params.height, resource.getWidth(), resource.getHeight());
                                    }else{
                                        params.width = (int) (ScreenUtils.getScreenWidth() * 0.75);
                                        params.height = (int) weiuiCommon.scaleHeight(params.width, resource.getWidth(), resource.getHeight());
                                    }
                                    tmpImage.setLayoutParams(params);
                                    tmpImage.setImageBitmap(resource);
                                    fullBox.startAnimation(mAnimation);
                                    //
                                    if (callback != null) {
                                        Map<String, Object> newData = (Map<String, Object>) data;
                                        newData.put("status", "show");
                                        newData.put("dialogName", dialogName);
                                        newData.put("imgUrl", imgUrl);
                                        callback.invokeAndKeepAlive(newData);
                                    }
                                    //
                                    ((FrameLayout) mActivity.findViewById(R.id.v_body)).addView(tmpView);
                                }
                                if (callback != null) {
                                    if (data instanceof Map) {
                                        Map<String, Object> newData = (Map<String, Object>) data;
                                        newData.put("dialogName", dialogName);
                                        newData.put("imgUrl", imgUrl);
                                        callback.invokeAndKeepAlive(newData);
                                    }else{
                                        callback.invokeAndKeepAlive(data);
                                    }
                                }
                            }
                        });
                    }
                });
    }

    public static boolean close(String dialogName) {
        DialogBean tmpBean = null;
        for (int i = 0; i < mAdDialogList.size(); i++) {
            DialogBean temp = mAdDialogList.get(i);
            if (temp != null) {
                if (dialogName == null) {
                    tmpBean = mAdDialogList.get(i);
                    mAdDialogList.remove(i);
                    break;
                }else{
                    if (mAdDialogList.get(i).getDialogName().equals(dialogName)) {
                        tmpBean = mAdDialogList.get(i);
                        mAdDialogList.remove(i);
                        break;
                    }
                }
            }
        }
        if (tmpBean == null) {
            return false;
        }
        Context mContext = tmpBean.getContext();
        ViewGroup fullBox = tmpBean.getView().findViewById(R.id.float_full_box);
        //
        Animation mAnimation = AnimationUtils.loadAnimation(mContext, R.anim.in_to_down_out);
        mAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ((Activity) mContext).finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        fullBox.startAnimation(mAnimation);
        return true;
    }
}
