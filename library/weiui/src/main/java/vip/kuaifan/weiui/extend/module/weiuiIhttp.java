package vip.kuaifan.weiui.extend.module;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSONArray;
import com.taobao.weex.bridge.JSCallback;

import vip.kuaifan.weiui.R;
import vip.kuaifan.weiui.extend.integration.xutils.cache.LruDiskCache;
import vip.kuaifan.weiui.extend.integration.xutils.common.Callback.Cancelable;
import vip.kuaifan.weiui.extend.integration.xutils.common.Callback.CacheCallback;
import vip.kuaifan.weiui.extend.integration.xutils.common.Callback.ProgressCallback;
import vip.kuaifan.weiui.extend.integration.xutils.http.RequestParams;
import vip.kuaifan.weiui.extend.integration.xutils.x;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Map;

@SuppressLint("StaticFieldLeak")
public class weiuiIhttp {

    private static final String TAG = "Ihttp";

    //缓存文件夹名称
    private static String cacheDirName = "weiuiIhttp";

    //请求组
    private static Map<String, Cancelable> requestList = new HashMap<>();

    //获取ContentType组
    private static Map<String, String> contentTypeData = new HashMap<>();

    /**
     * 初始化模块
     * @param context
     */
    public static void init(Context context) {
        x.Ext.init((Application) context);
        x.Ext.setDebug(false);
    }

    /**
     * 格式化参数
     * @param url
     * @param data
     * @return
     */
    private static RequestParams FormatRequestParams(String url, Map<String, Object> data) {
        RequestParams params = new RequestParams(url + "");
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
                                    String cacheLabel = weiuiParse.parseStr(data.get("setting:cacheLabel"), "ajax");
                                    params.setCacheDirName(cacheDirName + "/" + cacheLabel);
                                    params.setCacheMaxAge(cache);
                                }
                                break;
                        }
                    }else if (weiuiCommon.leftExists(key.toLowerCase(), "header:")) {
                        key = key.substring(7).trim();
                        params.addHeader(key, String.valueOf(value));
                    }else if (weiuiCommon.leftExists(key.toLowerCase(), "file:")) {
                        key = key.substring(5).trim();
                        if (value instanceof JSONArray) {
                            JSONArray values = (JSONArray) value;
                            for (int i = 0; i < values.size(); i++) {
                                File tempFile = new File(String.valueOf(values.get(i)));
                                if (tempFile.exists()) {
                                    isMultipart = true;
                                    params.addBodyParameter(key + "[]", tempFile);
                                }
                            }
                        }else{
                            File tempFile = new File(String.valueOf(value));
                            if (tempFile.exists()) {
                                isMultipart = true;
                                params.addBodyParameter(key, tempFile);
                            }
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
    private static void put(String type, String key, String url, Map<String, Object> data, ResultCallback callBack) {
        RequestParams params = FormatRequestParams(url, data);
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
    private static CacheCallback<String> cacheCallback(String key, String url, ResultCallback callBack) {
        final boolean[] isCache = {false};
        return new CacheCallback<String>() {

            @Override
            public boolean onCache(String result) {
                isCache[0] = true;
                if (callBack != null) {
                    callBack.success(result, true);
                }
                return true;
            }

            @Override
            public void onSuccess(String result) {
                if (!isCache[0]) {
                    if (callBack != null) {
                        callBack.success(result, false);
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
    private static ProgressCallback<String> progressCallback(String key, String url, ResultCallback callBack) {
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
                    callBack.success(result, false);
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

    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/

    /**
     * 返回
     */
    public interface ResultCallback {
        void success(String resData, boolean isCache);

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
    public static void get(String pageID, String url, Map<String, Object> data, ResultCallback callBack) {
        put("get", pageID + "@@" + weiuiCommon.randomString(16), url, data, callBack);
    }

    /**
     * POST
     * @param pageID
     * @param url
     * @param data
     * @param callBack
     */
    public static void post(String pageID, String url, Map<String, Object> data, ResultCallback callBack) {
        put("post", pageID + "@@" + weiuiCommon.randomString(16), url, data, callBack);
    }

    /**
     * 线程获取缓存大小
     */
    public static class getCacheSize extends Thread {

        private String cacheLabel;
        private JSCallback callback;

        public getCacheSize(String cacheLabel, JSCallback callback) {
            this.cacheLabel = cacheLabel;
            this.callback = callback;
        }

        public void run(){
            if (callback != null) {
                Map<String, Object> data = new HashMap<>();
                if (cacheLabel == null || cacheLabel.isEmpty()) {
                    data.put("size", 0);
                }else{
                    LruDiskCache mLruDiskCache = LruDiskCache.getDiskCache(cacheDirName + "/" + cacheLabel);
                    if (mLruDiskCache != null) {
                        data.put("size", mLruDiskCache.getCacheSize());
                    } else {
                        data.put("size", 0);
                    }
                }
                callback.invoke(data);
            }
        }
    }

    /**
     * 清除缓存
     */
    public static class clearCache extends Thread {

        private String cacheLabel;

        public clearCache(String cacheLabel) {
            this.cacheLabel = cacheLabel;
        }

        public void run(){
            if (cacheLabel == null || cacheLabel.isEmpty()) {
                return;
            }
            LruDiskCache mLruDiskCache = LruDiskCache.getDiskCache(cacheDirName + "/" + cacheLabel);
            if (mLruDiskCache != null) {
                mLruDiskCache.clearCache();
            }
        }
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

    /**
     * 获取ContentType
     * @param url
     * @param mTypeCallback
     */
    public static void getContentType(String url, ContentTypeCallback mTypeCallback) {
        if (contentTypeData.get(url) != null) {
            if (mTypeCallback != null) {
                mTypeCallback.onResult(contentTypeData.get(url));
            }
            return;
        }
        class TempAsyncTask extends AsyncTask<String, String, String> {
            @Override
            protected String doInBackground(String... params) {
                String contentType = null;
                try {
                    URL tempUrl = new URL(url);
                    URLConnection tempConn = tempUrl.openConnection();
                    tempConn.setRequestProperty("accept", "*/*");
                    tempConn.setRequestProperty("connection", "Keep-Alive");
                    tempConn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
                    tempConn.connect();
                    contentType = tempConn.getContentType();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return contentType;
            }

            @Override
            protected void onPostExecute(String result) {
                contentTypeData.put(url, result);
                if (mTypeCallback != null) {
                    mTypeCallback.onResult(result);
                }
            }
        }
        new TempAsyncTask().execute();
    }

    public interface ContentTypeCallback {
        void onResult(String result);
    }
}
