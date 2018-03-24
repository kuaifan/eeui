package vip.kuaifan.weiui.extend.module;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import vip.kuaifan.weiui.extend.integration.xutils.common.Callback.Cancelable;
import vip.kuaifan.weiui.extend.integration.xutils.common.Callback.CacheCallback;
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
                }else if (value != null){
                    if (weiuiCommon.leftExists(key.toLowerCase(), "setting:")) {
                        key = key.substring(8).trim();
                        switch (key) {
                            case "timeout":
                                int timeout = weiuiParse.parseInt(value, 0);
                                if (timeout > 0) {
                                    params.setConnectTimeout(timeout);
                                    params.setReadTimeout(timeout);
                                }
                                break;
                            case "cache":
                                long cache = weiuiParse.parseLong(value, 0);
                                if (cache > 0) {
                                    params.setCacheMaxAge(cache);
                                }
                                break;
                        }
                    }else if (weiuiCommon.leftExists(key.toLowerCase(), "header:")) {
                        key = key.substring(7).trim();
                        params.addHeader(key, String.valueOf(value));
                    }else if (weiuiCommon.leftExists(key.toLowerCase(), "file:")) {
                        key = key.substring(5).trim();
                        File tempFile = new File(String.valueOf(value));
                        if (!tempFile.exists()) {
                            isMultipart = true;
                            params.addBodyParameter(key, tempFile);
                        }
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
        RequestParams params = requestParams(url, data);
        if (params.getCacheMaxAge() > 0) {
            //缓存方案
            switch (type) {
                case "get":
                    requestList.put(key, x.http().get(params, cacheCallback(key, url, callBack)));
                    break;

                case "post":
                    requestList.put(key, x.http().post(params, cacheCallback(key, url, callBack)));
                    break;
            }
        }else{
            //正常方案
            switch (type) {
                case "get":
                    requestList.put(key, x.http().get(params, progressCallback(key, url, callBack)));
                    break;

                case "post":
                    requestList.put(key, x.http().post(params, progressCallback(key, url, callBack)));
                    break;
            }
        }

    }

    /**
     * 请求相应（缓存）
     * @param key
     * @param url
     * @param callBack
     * @return
     */
    private static CacheCallback<String> cacheCallback(String key, String url, resultCall callBack) {
        final boolean[] isCache = {false};
        return new CacheCallback<String>() {

            @Override
            public boolean onCache(String result) {
                isCache[0] = true;
                if (callBack != null) {
                    Log.d(TAG, "onCache: " + url);
                    callBack.success(result);
                }
                return true;
            }

            @Override
            public void onSuccess(String result) {
                if (!isCache[0]) {
                    if (callBack != null) {
                        Log.d(TAG, "onSuccess C: " + url);
                        callBack.success(result);
                    }
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                if (callBack != null) {
                    callBack.error(ex.toString());
                }
            }

            @Override
            public void onFinished() {
                if (callBack != null) {
                    callBack.complete();
                }
                requestList.remove(key);
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.d(TAG, "onCancelled C: " + url);
            }
        };
    }

    /**
     * 请求相应（正常）
     * @param key
     * @param url
     * @param callBack
     * @return
     */
    private static ProgressCallback<String> progressCallback(String key, String url, resultCall callBack) {
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
            public void onSuccess(String result) {
                if (callBack != null) {
                    Log.d(TAG, "onSuccess P: " + url);
                    callBack.success(result);
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                if (callBack != null) {
                    callBack.error(ex.toString());
                }
            }

            @Override
            public void onFinished() {
                if (callBack != null) {
                    callBack.complete();
                }
                requestList.remove(key);
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.d(TAG, "onCancelled P: " + url);
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
