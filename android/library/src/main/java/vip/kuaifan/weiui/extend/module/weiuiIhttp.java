package vip.kuaifan.weiui.extend.module;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import vip.kuaifan.weiui.extend.integration.xutils.common.Callback.Cancelable;
import vip.kuaifan.weiui.extend.integration.xutils.common.Callback.ProgressCallback;
import vip.kuaifan.weiui.extend.integration.xutils.http.RequestParams;
import vip.kuaifan.weiui.extend.integration.xutils.x;

import java.io.File;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Map;

@SuppressLint("StaticFieldLeak")
public class weiuiIhttp {

    private static final String TAG = "Ihttp";

    private static Context mContext;

    //系统IMEI
    private static String platformImei = "";

    //系统版本
    private static String platformRelease = "";

    //请求组
    private static Map<String, Cancelable> requestList = new HashMap<>();

    /**
     * 初始化模块
     * @param context
     */
    public static void init(Context context) {
        x.Ext.init((Application) context);
        x.Ext.setDebug(false);
        //
        mContext = context;
        platformRelease = weiuiCommon.getLocalVersionName(context);
        platformImei = weiuiCommon.getImei(context);
        //
        Log.d(TAG, "platformRelease: " + platformRelease);
        Log.d(TAG, "platformImei: " + platformImei);
    }

    /**
     * 格式化参数
     * @param url
     * @param data
     * @return
     */
    private static RequestParams requestParams(String url, Map<String, Object> data) {
        RequestParams params = new RequestParams(url + "");
        params.setConnectTimeout(8 * 1000);
        params.setReadTimeout(8 * 1000);
        params.addHeader("platform", "Android");
        params.addHeader("platform-imei", platformImei);
        params.addHeader("platform-release", platformRelease);
        //
        if (data != null) {
            boolean isMultipart = false;
            for (String key : data.keySet()) {
                Object value = data.get(key);
                if (value instanceof File) {
                    isMultipart = true;
                    params.addBodyParameter(key, (File) value);
                }else{
                    if (weiuiCommon.leftExists(key.toLowerCase(), "header:")) {
                        key = key.substring(7).trim();
                        params.addHeader(key, String.valueOf(value));
                    }else{
                        params.addQueryStringParameter(key, String.valueOf(value));
                    }
                }
            }
            params.setMultipart(isMultipart);
        }
        return params;
    }

    /**
     * 添加请求
     * @param type
     * @param key
     * @param url
     * @param data
     * @param callBack
     */
    private static void put(String type, final String key, final String url, final Map<String, Object> data, final resultCall callBack) {
        Log.d(TAG, "put: " + url);
        //开始读取
        switch (type) {
            case "get":
                requestList.put(key, x.http().get(requestParams(url, data), progressCallback(key, callBack)));
                break;

            case "post":
                requestList.put(key, x.http().post(requestParams(url, data), progressCallback(key, callBack)));
                break;
        }
    }

    /**
     * 请求相应
     * @param key
     * @param callBack
     * @return
     */
    private static ProgressCallback<String> progressCallback(final String key, final resultCall callBack) {
        return new ProgressCallback<String>() {
            @Override
            public void onWaiting() {
            }

            @Override
            public void onStarted() {
            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onSuccess(String result) {
                if (callBack == null) {
                    return;
                }
                callBack.success(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                if (callBack == null) {
                    return;
                }
                callBack.error(ex.toString());
            }

            @Override
            public void onFinished() {
                callBack.complete();
                requestList.remove(key);
            }
        };
    }

    /**
     * 返回
     */
    public interface resultCall {
        void success(String data);

        void error(String error);

        void complete();
    }

    /**
     * GET
     * @param pageID
     * @param url
     * @param data
     * @param callBack
     */
    public static void get(String pageID, final String url, final Map<String, Object> data, final resultCall callBack) {
        put("get", pageID + "@@" + weiuiCommon.randomString(16), url, data, callBack);
    }

    /**
     * POST
     * @param pageID
     * @param url
     * @param data
     * @param callBack
     */
    public static void post(String pageID, final String url, final Map<String, Object> data, final resultCall callBack) {
        put("post", pageID + "@@" + weiuiCommon.randomString(16), url, data, callBack);
    }

    /**
     * 取消当前所有请求
     */
    public static void cancel() {
        if (requestList.size() > 0) {
            try {
                for (Map.Entry<String, Cancelable> item : requestList.entrySet()) {
                    if (item.getValue() != null) {
                        item.getValue().cancel();
                    }
                }
                requestList.clear();
            }catch (ConcurrentModificationException ignored) { }
        }
    }

    /**
     * 取消当前页面请求
     * @param pageID
     */
    public static void cancel(String pageID) {
        if (requestList.size() > 0) {
            try {
                for (Map.Entry<String, Cancelable> item : requestList.entrySet()) {
                    if (weiuiCommon.leftExists(item.getKey(), pageID + "@@")) {
                        if (item.getValue() != null) {
                            item.getValue().cancel();
                        }
                        requestList.remove(item.getKey());
                    }
                }
            }catch (ConcurrentModificationException ignored) { }
        }
    }
}
