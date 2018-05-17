package vip.kuaifan.weiui.extend.module;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.WXSDKInstance;

import java.util.HashMap;
import java.util.Map;

import vip.kuaifan.weiui.activity.PageActivity;
import vip.kuaifan.weiui.activity.PageActivityNoTransparent;
import vip.kuaifan.weiui.activity.PageActivityTransparent;
import vip.kuaifan.weiui.extend.bean.PageBean;
import vip.kuaifan.weiui.extend.integration.swipebacklayout.BGAKeyboardUtil;

/**
 * Created by WDM on 2018/3/25.
 */

public class weiuiPage {

    private static final String TAG = "weiuiPage";

    private static Map<String, PageBean> mPageBean = new HashMap<>();

    private static Map<String, Long> openTime = new HashMap<>();

    public static void setPageBean(String key, PageBean var) {
        mPageBean.put(key, var);
    }

    public static PageBean getPageBean(String key) {
        return mPageBean.get(key);
    }

    public static void removePageBean(String key) {
        if (key != null) {
            mPageBean.remove(key);
        }
    }

    /**
     * 打开页面
     * @param context
     * @param mBean
     */
    public static void openWin(Context context, PageBean mBean) {
        if (mBean == null) {
            return;
        }
        if (mBean.getPageName() == null) {
            mBean.setPageName("open_" + weiuiCommon.randomString(8));
        } else {
            if (System.currentTimeMillis() - weiuiParse.parseLong(openTime.get(mBean.getPageName())) < 1000) {
                return;
            }
            openTime.put(mBean.getPageName(), System.currentTimeMillis());
        }
        if (mPageBean.get(mBean.getPageName()) != null) {
            mBean.setPageName("open_" + mBean.getPageName() + "_" + weiuiCommon.randomString(6));
        }
        mPageBean.put(mBean.getPageName(), mBean);
        //
        if (mBean.getCallback() != null) {
            Map<String, Object> ret = new HashMap<>();
            ret.put("pageName", mBean.getPageName());
            ret.put("status", "ready");
            mBean.getCallback().invokeAndKeepAlive(ret);
        }
        //
        Intent intent = new Intent();
        intent.setClass(context, mBean.isTranslucent() ? PageActivityTransparent.class : PageActivityNoTransparent.class);
        intent.putExtra("name", mBean.getPageName());
        context.startActivity(intent);
    }

    /**
     * 获取页面详细参数
     * @param name
     * @return
     */
    public static PageBean getWinInfo(String name) {
        if (name == null) {
            return null;
        }
        PageBean mBean = getPageBean(name);
        if (mBean == null) {
            return null;
        }
        Activity activity = mBean.getActivity();
        if (activity == null) {
            return null;
        }
        if (activity instanceof PageActivity) {
            return ((PageActivity) activity).getPageInfo();
        }
        return null;
    }

    /**
     * 刷新页面
     * @param name
     */
    public static void reloadWin(String name) {
        if (name == null) {
            return;
        }
        PageBean mBean = getPageBean(name);
        if (mBean == null) {
            return;
        }
        Activity activity = mBean.getActivity();
        if (activity == null) {
            return;
        }
        if (activity instanceof PageActivity) {
            ((PageActivity) activity).reload();
        }
    }

    /**
     * 关闭页面
     * @param name
     */
    public static void closeWin(String name) {
        if (name == null) {
            return;
        }
        PageBean mBean = getPageBean(name);
        if (mBean == null) {
            return;
        }
        Activity activity = mBean.getActivity();
        if (activity == null) {
            return;
        }
        BGAKeyboardUtil.closeKeyboard(activity);
        activity.finish();
    }

    /**
     * 获取页面名称
     * @param object
     * @return
     */
    public static String getPageName(String object) {
        JSONObject json = weiuiJson.parseObject(object);
        if (json.size() == 0) {
            json.put("pageName", object);
        }
        String pageName = json.getString("pageName");
        if (pageName == null) {
            pageName = "";
        }
        return pageName;
    }

    /**
     * 补全地址
     * @param context
     * @param url
     * @return
     */
    public static String rewriteUrl(Context context, String url) {
        if (url == null || url.startsWith("http") || url.startsWith("ftp://")) {
            return url;
        }
        if (context instanceof PageActivity) {
            PageBean info = ((PageActivity) context).getPageInfo();
            if (info != null) {
                return weiuiHtml.repairUrl(url, info.getUrl());
            }
        }
        return url;
    }

    /**
     * 缓存页面
     * @param url       缓存地址
     * @param cache     缓存时长，单位：毫秒
     * @param params    传递参数
     * @param mOnCachePageCallback
     */
    public static void cachePage(String url, long cache, Object params, OnCachePageCallback mOnCachePageCallback) {
        if (url == null || mOnCachePageCallback == null) {
            return;
        }
        Map<String, Object> resParams = new HashMap<>();
        resParams.put(WXSDKInstance.BUNDLE_URL, url);
        resParams.put("params", params);
        //
        if (cache < 1000) {
            Log.d(TAG, "cachePage nocache: " + url);
            mOnCachePageCallback.error(resParams);
            return;
        }
        //
        Map<String, Object> data = new HashMap<>();
        data.put(WXSDKInstance.BUNDLE_URL, url);
        data.put("params", params);
        data.put("setting:cache", cache);
        data.put("setting:cacheLabel", "page");
        weiuiIhttp.get("weiuiPage", url, data, new weiuiIhttp.ResultCallback() {
            @Override
            public void success(String resData, boolean isCache) {
                Log.d(TAG, "cachePage success: " + isCache + ": " + url);
                mOnCachePageCallback.success(resParams, resData);
            }

            @Override
            public void error(String error) {
                Log.d(TAG, "cachePage error: " + url);
                mOnCachePageCallback.error(resParams);
            }

            @Override
            public void complete() {
                mOnCachePageCallback.complete(resParams);
            }
        });
    }

    /**
     * 缓存页面相应函数
     */
    public interface OnCachePageCallback {
        void success(Map<String, Object> resParams, String resData);
        void error(Map<String, Object> resParams);
        void complete(Map<String, Object> resParams);
    }
}
