package vip.kuaifan.weiui.ui.module;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.alibaba.fastjson.JSONObject;

import com.alibaba.weex.plugin.annotation.WeexModule;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import vip.kuaifan.weiui.BuildConfig;
import vip.kuaifan.weiui.activity.PageActivity;
import vip.kuaifan.weiui.extend.bean.PageBean;
import vip.kuaifan.weiui.extend.integration.swipebacklayout.BGAKeyboardUtil;
import vip.kuaifan.weiui.extend.module.rxtools.rxtoolsModule;
import vip.kuaifan.weiui.extend.module.utilcode.util.DeviceUtils;
import vip.kuaifan.weiui.extend.module.utilcode.util.FileUtils;
import vip.kuaifan.weiui.extend.module.utilcode.utilcodeModule;
import vip.kuaifan.weiui.extend.module.weiuiAdDialog;
import vip.kuaifan.weiui.extend.module.weiuiAjax;
import vip.kuaifan.weiui.extend.module.weiuiAlertDialog;
import vip.kuaifan.weiui.extend.module.weiuiIhttp;
import vip.kuaifan.weiui.extend.module.weiuiJson;
import vip.kuaifan.weiui.extend.module.weiuiOpenApp;
import vip.kuaifan.weiui.extend.module.weiuiPage;
import vip.kuaifan.weiui.extend.module.weiuiParse;
import vip.kuaifan.weiui.extend.module.weiuiScreenUtils;
import vip.kuaifan.weiui.extend.module.weiuiCommon;
import vip.kuaifan.weiui.extend.module.weiuiShareUtils;
import vip.kuaifan.weiui.extend.view.loading.LoadingDialog;


@WeexModule(name = "weiui")
public class weiuiModule extends WXModule {

    private static final String TAG = "weiuiModule";

    private Object[] objectGroup(Object... var) {
        return var;
    }

    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/

    /**
     * 打开页面 或 打开网页（内置浏览器）
     * @param object
     * @param callback
     */
    @JSMethod
    public void openPage(String object, JSCallback callback) {
        JSONObject json = weiuiJson.parseObject(object);
        if (json.size() == 0) {
            json.put("url", object);
        }
        if (json.getString("url") == null || json.getString("url").isEmpty()) {
            return;
        }
        PageBean mBean = new PageBean();

        //网址
        mBean.setUrl(weiuiPage.rewriteUrl(mWXSDKInstance.getContext(), json.getString("url")));
        //名称（默认：随机生成）
        if (json.getString("pageName") != null) {
            mBean.setPageName(json.getString("pageName"));
        }
        //类型（默认：weex）
        if (json.getString("pageType") != null) {
            mBean.setPageType(json.getString("pageType"));
        }
        //缓存（默认：0）
        if (json.getString("cache") != null) {
            mBean.setCache(json.getIntValue("cache"));
        }
        //转递数据（默认：无）
        if (json.get("params") != null) {
            mBean.setParams(json.get("params"));
        }
        //是否显示等待（默认：true）
        if (json.getBoolean("loading") != null) {
            mBean.setLoading(json.getBoolean("loading"));
        }
        //是否支持滑动返回（默认：false）
        if (json.getBoolean("swipeBack") != null) {
            mBean.setSwipeBack(json.getBoolean("swipeBack"));
        }
        //状态栏样式（可选，等于fullscreen|immersion时statusBarType、statusBarAlpha无效）
        if (json.getString("statusBarType") != null) {
            mBean.setStatusBarType(json.getString("statusBarType"));
        }
        //状态栏颜色值（默认：#3EB4FF）
        if (json.getString("statusBarColor") != null) {
            mBean.setStatusBarColor(json.getString("statusBarColor"));
        }
        //状态栏透明度（默认：0）
        if (json.getInteger("statusBarAlpha") != null) {
            mBean.setStatusBarAlpha(json.getInteger("statusBarAlpha"));
        }
        //透明底色窗口（默认：false）
        if (json.getBoolean("translucent") != null) {
            mBean.setTranslucent(json.getBoolean("translucent"));
        }
        //页面背景颜色（默认：#f4f8f9）
        if (json.getString("backgroundColor") != null) {
            mBean.setBackgroundColor(json.getString("backgroundColor"));
        }
        //返回键关闭（默认：true）
        if (json.getBoolean("backPressedClose") != null) {
            mBean.setBackPressedClose(json.getBoolean("backPressedClose"));
        }

        //JS回调事件
        if (callback != null) {
            mBean.setCallback(callback);
        }

        weiuiPage.openWin(mWXSDKInstance.getContext(), mBean);
    }

    /**
     * 获取页面信息
     * @param object
     * @return
     */
    @JSMethod(uiThread = false)
    public Object getPageInfo(String object) {
        String pageName = weiuiPage.getPageName(object);
        if (pageName.isEmpty()) {
            if (mWXSDKInstance.getContext() instanceof PageActivity) {
                return ((PageActivity) mWXSDKInstance.getContext()).getPageInfo().toMap();
            }
            return null;
        }
        return weiuiPage.getWinInfo(pageName).toMap();
    }

    /**
     * 获取页面传递的参数
     * @param object
     * @return
     */
    @JSMethod(uiThread = false)
    public Object getPageParams(String object) {
        String pageName = weiuiPage.getPageName(object);
        if (pageName.isEmpty()) {
            if (mWXSDKInstance.getContext() instanceof PageActivity) {
                return ((PageActivity) mWXSDKInstance.getContext()).getPageInfo().getParams();
            }
            return null;
        }
        return weiuiPage.getWinInfo(pageName).getParams();
    }

    /**
     * 重新加载页面（刷新）
     * @param object
     */
    @JSMethod
    public void reloadPage(String object) {
        String pageName = weiuiPage.getPageName(object);
        if (pageName.isEmpty()) {
            if (mWXSDKInstance.getContext() instanceof PageActivity) {
                ((PageActivity) mWXSDKInstance.getContext()).reload();
            }
            return;
        }
        weiuiPage.reloadWin(pageName);
    }

    /**
     * 关闭页面 或 关闭网页（内置浏览器）
     * @param object
     */
    @JSMethod
    public void closePage(String object) {
        String pageName = weiuiPage.getPageName(object);
        if (pageName.isEmpty()) {
            BGAKeyboardUtil.closeKeyboard((Activity) mWXSDKInstance.getContext());
            ((Activity) mWXSDKInstance.getContext()).finish();
            return;
        }
        weiuiPage.closeWin(pageName);
    }

    /**
     * 设置键盘弹出方式
     * @param object
     * @param mode
     */
    @JSMethod
    public void setSoftInputMode(String object, String mode) {
        String pageName = weiuiPage.getPageName(object);
        if (pageName.isEmpty()) {
            if (mWXSDKInstance.getContext() instanceof PageActivity) {
                pageName = ((PageActivity) mWXSDKInstance.getContext()).getPageInfo().getPageName();
            }
        }
        PageBean mPageBean = weiuiPage.getWinInfo(pageName);
        if (mPageBean == null) {
            return;
        }
        PageActivity mPageActivity = ((PageActivity) mPageBean.getContext());
        mPageActivity.setSoftInputMode(mode);
    }

    /**
     * 拦截返回按键事件
     * @param object
     * @param callback  为null时取消拦截
     */
    @JSMethod
    public void setPageBackPressed(String object, JSCallback callback) {
        String pageName = weiuiPage.getPageName(object);
        if (pageName.isEmpty()) {
            if (mWXSDKInstance.getContext() instanceof PageActivity) {
                pageName = ((PageActivity) mWXSDKInstance.getContext()).getPageInfo().getPageName();
            }
        }
        PageBean mPageBean = weiuiPage.getWinInfo(pageName);
        if (mPageBean == null) {
            return;
        }
        PageActivity mPageActivity = ((PageActivity) mPageBean.getContext());
        if (callback == null) {
            mPageActivity.setOnBackPressed(null);
        }else{
            mPageActivity.setOnBackPressed(() -> {
                callback.invokeAndKeepAlive(null);
                return true;
            });
        }
    }

    /**
     * 监听下拉刷新事件
     * @param object
     * @param callback  为null时取消监听
     */
    @JSMethod
    public void setOnRefreshListener(String object, JSCallback callback) {
        String pageName = weiuiPage.getPageName(object);
        if (pageName.isEmpty()) {
            if (mWXSDKInstance.getContext() instanceof PageActivity) {
                pageName = ((PageActivity) mWXSDKInstance.getContext()).getPageInfo().getPageName();
            }
        }
        PageBean mPageBean = weiuiPage.getWinInfo(pageName);
        if (mPageBean == null) {
            return;
        }
        PageActivity mPageActivity = ((PageActivity) mPageBean.getContext());
        if (callback == null) {
            mPageActivity.setOnRefreshListener(null);
        }else{
            mPageActivity.setOnRefreshListener(callback::invokeAndKeepAlive);
        }
    }

    /**
     * 设置下拉刷新状态
     * @param object
     * @param refreshing
     */
    @JSMethod
    public void setRefreshing(String object, boolean refreshing) {
        String pageName = weiuiPage.getPageName(object);
        if (pageName.isEmpty()) {
            if (mWXSDKInstance.getContext() instanceof PageActivity) {
                pageName = ((PageActivity) mWXSDKInstance.getContext()).getPageInfo().getPageName();
            }
        }
        PageBean mPageBean = weiuiPage.getWinInfo(pageName);
        if (mPageBean == null) {
            return;
        }
        PageActivity mPageActivity = ((PageActivity) mPageBean.getContext());
        mPageActivity.setRefreshing(refreshing);
    }

    /**
     * 监听页面状态变化
     * @param object
     * @param callback
     */
    @JSMethod
    public void setPageStatusListener(String object, JSCallback callback) {
        if (object == null) {
            return;
        }
        JSONObject json = weiuiJson.parseObject(object);
        String listenerName = weiuiJson.getString(json, "listenerName", object);
        if (listenerName.isEmpty()) {
            return;
        }
        String pageName = weiuiJson.getString(json, "pageName");
        if (pageName.isEmpty()) {
            if (mWXSDKInstance.getContext() instanceof PageActivity) {
                pageName = ((PageActivity) mWXSDKInstance.getContext()).getPageInfo().getPageName();
            }
        }
        PageBean mPageBean = weiuiPage.getWinInfo(pageName);
        if (mPageBean == null) {
            return;
        }
        PageActivity mPageActivity = ((PageActivity) mPageBean.getContext());
        mPageActivity.setPageStatusListener(listenerName, callback);
    }

    /**
     * 取消监听页面状态变化
     * @param object
     */
    @JSMethod
    public void clearPageStatusListener(String object) {
        if (object == null) {
            return;
        }
        JSONObject json = weiuiJson.parseObject(object);
        String listenerName = weiuiJson.getString(json, "listenerName", object);
        if (listenerName.isEmpty()) {
            return;
        }
        String pageName = weiuiJson.getString(json, "pageName");
        if (pageName.isEmpty()) {
            if (mWXSDKInstance.getContext() instanceof PageActivity) {
                pageName = ((PageActivity) mWXSDKInstance.getContext()).getPageInfo().getPageName();
            }
        }
        PageBean mPageBean = weiuiPage.getWinInfo(pageName);
        if (mPageBean == null) {
            return;
        }
        PageActivity mPageActivity = ((PageActivity) mPageBean.getContext());
        mPageActivity.clearPageStatusListener(listenerName);
    }

    /**
     * 手动执行(触发)页面状态
     * @param object
     * @param status
     */
    @JSMethod
    public void onPageStatusListener(String object, String status) {
        if (status == null) {
            status = object;
            object = null;
        }
        if (status == null) {
            return;
        }
        JSONObject json = weiuiJson.parseObject(object);
        String pageName = weiuiJson.getString(json, "pageName");
        if (pageName.isEmpty()) {
            if (mWXSDKInstance.getContext() instanceof PageActivity) {
                pageName = ((PageActivity) mWXSDKInstance.getContext()).getPageInfo().getPageName();
            }
        }
        PageBean mPageBean = weiuiPage.getWinInfo(pageName);
        if (mPageBean == null) {
            return;
        }
        PageActivity mPageActivity = ((PageActivity) mPageBean.getContext());
        mPageActivity.onPageStatusListener(weiuiJson.getString(json, "listenerName", object), status, weiuiJson.getString(json, "extra"));
    }

    /**
     * 获取页面缓存大小
     */
    @JSMethod
    public void getCacheSizePage(JSCallback callback) {
        new weiuiIhttp.getCacheSize("page", callback).start();
    }

    /**
     * 清除缓存页面
     */
    @JSMethod
    public void clearCachePage() {
        new weiuiIhttp.clearCache("page").start();
    }

    /**
     * 打开网页（系统浏览器）
     * @param url
     */
    @JSMethod
    public void openWeb(String url) {
        if (url == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        mWXSDKInstance.getContext().startActivity(intent);
    }

    /**
     * 返回桌面
     */
    @JSMethod
    public void goDesktop() {
        Intent home = new Intent(Intent.ACTION_MAIN);
        home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        home.addCategory(Intent.CATEGORY_HOME);
        mWXSDKInstance.getContext().startActivity(home);
    }

    /****************************************************************************************/
    /****************************************************************************************/

    /**
     * 获取状态栏高度（屏幕像素）
     */
    @JSMethod(uiThread = false)
    public int getStatusBarHeight() {
        Object var = weiuiCommon.getVariate("__weiuiModule::getStatusBarHeight");
        if (var == null) {
            var = weiuiCommon.getStatusBarHeight(mWXSDKInstance.getContext());
            weiuiCommon.setVariate("__weiuiModule::getStatusBarHeight", var);
        }
        return weiuiParse.parseInt(var);
    }

    /**
     * 获取状态栏高度（weexPX单位）
     */
    @JSMethod(uiThread = false)
    public int getStatusBarHeightPx() {
        Object var = weiuiCommon.getVariate("__weiuiModule::getStatusBarHeightPx");
        if (var == null) {
            var = weiuiScreenUtils.weexDp2px(mWXSDKInstance, weiuiCommon.getStatusBarHeight(mWXSDKInstance.getContext()));
            weiuiCommon.setVariate("__weiuiModule::getStatusBarHeightPx", var);
        }
        return weiuiParse.parseInt(var);
    }

    /**
     * 获取虚拟键盘高度（屏幕像素）
     */
    @JSMethod(uiThread = false)
    public int getNavigationBarHeight() {
        return weiuiCommon.getNavigationBarHeight(mWXSDKInstance.getContext());
    }

    /**
     * 获取虚拟键盘高度（weexPX单位）
     */
    @JSMethod(uiThread = false)
    public int getNavigationBarHeightPx() {
        return weiuiScreenUtils.weexDp2px(mWXSDKInstance, weiuiCommon.getNavigationBarHeight(mWXSDKInstance.getContext()));
    }

    /**
     * 获取weiui版本号
     */
    @JSMethod(uiThread = false)
    public int getVersion() {
        Object var = weiuiCommon.getVariate("__weiuiModule::getVersion");
        if (var == null) {
            var = BuildConfig.VERSION_CODE;
            weiuiCommon.setVariate("__weiuiModule::getVersion", var);
        }
        return weiuiParse.parseInt(var);
    }

    /**
     * 获取weiui版本号名称
     */
    @JSMethod(uiThread = false)
    public String getVersionName() {
        Object var = weiuiCommon.getVariate("__weiuiModule::getVersionName");
        if (var == null) {
            var = BuildConfig.VERSION_NAME;
            weiuiCommon.setVariate("__weiuiModule::getVersionName", var);
        }
        return weiuiParse.parseStr(var);
    }

    /**
     * 获取本地软件版本号
     */
    @JSMethod(uiThread = false)
    public int getLocalVersion() {
        Object var = weiuiCommon.getVariate("__weiuiModule::getLocalVersion");
        if (var == null) {
            var = weiuiCommon.getLocalVersion(mWXSDKInstance.getContext());
            weiuiCommon.setVariate("__weiuiModule::getLocalVersion", var);
        }
        return weiuiParse.parseInt(var);
    }

    /**
     * 获取本地软件版本号名称
     */
    @JSMethod(uiThread = false)
    public String getLocalVersionName() {
        Object var = weiuiCommon.getVariate("__weiuiModule::getLocalVersionName");
        if (var == null) {
            var = weiuiCommon.getLocalVersionName(mWXSDKInstance.getContext());
            weiuiCommon.setVariate("__weiuiModule::getLocalVersionName", var);
        }
        return weiuiParse.parseStr(var);
    }

    /**
     * 比较版本号的大小,前者大则返回一个正数,后者大返回一个负数,相等则返回0
     * @param version1
     * @param version2
     * @return
     */
    @JSMethod(uiThread = false)
    public int compareVersion(String version1, String version2) {
        try {
            return weiuiCommon.compareVersion(version1, version2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取手机的IMEI
     */
    @JSMethod(uiThread = false)
    public String getImei() {
        Object var = weiuiCommon.getVariate("__weiuiModule::getImei");
        if (var == null) {
            var = weiuiCommon.getImei(mWXSDKInstance.getContext());
            weiuiCommon.setVariate("__weiuiModule::getImei", var);
        }
        return weiuiParse.parseStr(var);
    }

    /**
     * 获取设备系统版本号
     */
    @JSMethod(uiThread = false)
    public int getSDKVersionCode() {
        Object var = weiuiCommon.getVariate("__weiuiModule::getSDKVersionCode");
        if (var == null) {
            var = DeviceUtils.getSDKVersionCode();
            weiuiCommon.setVariate("__weiuiModule::getSDKVersionCode", var);
        }
        return weiuiParse.parseInt(var);
    }

    /**
     * 获取设备系统版本名称
     */
    @JSMethod(uiThread = false)
    public String getSDKVersionName() {
        Object var = weiuiCommon.getVariate("__weiuiModule::getSDKVersionName");
        if (var == null) {
            var = DeviceUtils.getSDKVersionName();
            weiuiCommon.setVariate("__weiuiModule::getSDKVersionName", var);
        }
        return weiuiParse.parseStr(var);
    }

    /****************************************************************************************/
    /****************************************************************************************/

    /**
     * 保存缓存信息
     * @param key
     * @param value
     * @param expired
     */
    @JSMethod(uiThread = false)
    public void setCachesString(String key, String value, Long expired) {
        if (key == null || value == null) {
            return;
        }
        weiuiCommon.setCachesString(mWXSDKInstance.getContext(), "weiuiCaches", key, value, weiuiParse.parseLong(expired));
    }

    /**
     * 获取缓存信息
     * @param key
     * @param defaultVal
     */
    @JSMethod(uiThread = false)
    public String getCachesString(String key, String defaultVal) {
        if (key == null) {
            return defaultVal;
        }
        return weiuiCommon.getCachesString(mWXSDKInstance.getContext(), "weiuiCaches", key, defaultVal);
    }

    /**
     * 设置全局变量
     * @param key
     * @param value
     */
    @JSMethod(uiThread = false)
    public void setVariate(String key, String value) {
        if (key == null || value == null) {
            return;
        }
        weiuiCommon.setVariate(key, value);
    }

    /**
     * 获取全局变量
     * @param key
     * @param defaultVal
     */
    @JSMethod(uiThread = false)
    public String getVariate(String key, String defaultVal) {
        if (key == null) {
            return defaultVal;
        }
        return weiuiCommon.getVariateStr(key, defaultVal);
    }

    /****************************************************************************************/
    /****************************************************************************************/

    /**
     * 获取内部缓存目录大小
     * @param callback
     */
    @JSMethod
    public void getCacheSizeDir(JSCallback callback) {
        if (callback != null) {
            new Thread(() -> {
                Map<String, Object> data = new HashMap<>();
                data.put("size", FileUtils.getDirLength(mWXSDKInstance.getContext().getCacheDir()));
                callback.invoke(data);
            }).start();
        }
    }

    /**
     * 清空内部缓存目录
     */
    @JSMethod
    public void clearCacheDir(JSCallback callback) {
        new Thread(() -> {
            JSONObject json = weiuiCommon.deleteAllInDir(mWXSDKInstance.getContext().getCacheDir());
            if (callback != null) {
                Map<String, Object> data = new HashMap<>();
                data.put("success", json.getIntValue("success"));
                data.put("error", json.getIntValue("error"));
                callback.invoke(data);
            }
        }).start();
    }

    /**
     * 获取内部文件目录大小
     * @param callback
     */
    @JSMethod
    public void getCacheSizeFiles(JSCallback callback) {
        if (callback != null) {
            new Thread(() -> {
                Map<String, Object> data = new HashMap<>();
                data.put("size", FileUtils.getDirLength(mWXSDKInstance.getContext().getFilesDir()));
                callback.invoke(data);
            }).start();
        }
    }

    /**
     * 清空内部文件目录
     */
    @JSMethod
    public void clearCacheFiles(JSCallback callback) {
        new Thread(() -> {
            JSONObject json = weiuiCommon.deleteAllInDir(mWXSDKInstance.getContext().getFilesDir());
            if (callback != null) {
                Map<String, Object> data = new HashMap<>();
                data.put("success", json.getIntValue("success"));
                data.put("error", json.getIntValue("error"));
                callback.invoke(data);
            }
        }).start();
    }

    /**
     * 获取内部数据库目录大小
     * @param callback
     */
    @JSMethod
    public void getCacheSizeDbs(JSCallback callback) {
        if (callback != null) {
            new Thread(() -> {
                Map<String, Object> data = new HashMap<>();
                data.put("size", FileUtils.getDirLength(new File(mWXSDKInstance.getContext().getFilesDir().getParent(), "databases")));
                callback.invoke(data);
            }).start();
        }
    }

    /**
     * 清空内部数据库目录
     */
    @JSMethod
    public void clearCacheDbs(JSCallback callback) {
        new Thread(() -> {
            JSONObject json = weiuiCommon.deleteAllInDir(new File(mWXSDKInstance.getContext().getFilesDir().getParent(), "databases"));
            if (callback != null) {
                Map<String, Object> data = new HashMap<>();
                data.put("success", json.getIntValue("success"));
                data.put("error", json.getIntValue("error"));
                callback.invoke(data);
            }
        }).start();
    }

    /****************************************************************************************/
    /****************************************************************************************/

    /**
     * weex px转dp
     * @param var
     */
    @JSMethod(uiThread = false)
    public int weexPx2dp(String var) {
        return weiuiScreenUtils.weexPx2dp(mWXSDKInstance, var);
    }

    /**
     * weex dp转px
     * @param var
     */
    @JSMethod(uiThread = false)
    public int weexDp2px(String var) {
        return weiuiScreenUtils.weexDp2px(mWXSDKInstance, var);
    }

    /****************************************************************************************/
    /****************************************************************************************/

    /**
     * alert 警告框
     */
    @JSMethod
    public void alert(String object, JSCallback callback) {
        weiuiAlertDialog.alert(mWXSDKInstance.getContext(), object, callback);
    }

    /**
     * confirm 确认对话框
     */
    @JSMethod
    public void confirm(String object, JSCallback callback) {
        weiuiAlertDialog.confirm(mWXSDKInstance.getContext(), object, callback);
    }

    /**
     * input 输入对话框
     */
    @JSMethod
    public void input(String object, JSCallback callback) {
        weiuiAlertDialog.input(mWXSDKInstance.getContext(), object, callback);
    }

    /****************************************************************************************/
    /****************************************************************************************/

    /**
     * 显示等待图标
     * @param object        参数
     * @param callback      返回键或点击空白处取消回调事件
     * @return
     */
    @JSMethod(uiThread = false)
    public String loading(String object, JSCallback callback) {
        return LoadingDialog.init(mWXSDKInstance.getContext(), object, callback);
    }

    /**
     * 关闭等待图标
     */
    @JSMethod(uiThread = false)
    public void loadingClose(String var) {
        LoadingDialog.close(var);
    }

    /****************************************************************************************/
    /****************************************************************************************/

    /**
     * 打开滑动验证码
     * @param imgUrl
     * @param callback
     */
    @JSMethod
    public void swipeCaptcha(String imgUrl, JSCallback callback) {
        PageActivity.startSwipeCaptcha(mWXSDKInstance.getContext(), imgUrl, callback);
    }

    /****************************************************************************************/
    /****************************************************************************************/

    /**
     * 打开二维码扫描
     * @param object
     * @param callback
     */
    @JSMethod
    public void openScaner(String object, JSCallback callback) {
        PageActivity.startScanerCode(mWXSDKInstance.getContext(), object, callback);
    }

    /****************************************************************************************/
    /****************************************************************************************/

    /**
     * 跨域异步请求
     * @param object
     * @param callback
     */
    @JSMethod
    public void ajax(String object, JSCallback callback) {
        JSONObject json = weiuiJson.parseObject(object);
        if (json.size() == 0) {
            json.put("url", object);
        }
        weiuiAjax.ajax(mWXSDKInstance.getContext(), json, callback);
    }

    /**
     * 取消跨域异步请求
     * @param name
     */
    @JSMethod
    public void ajaxCancel(String name) {
        weiuiAjax.ajaxCancel(name);
    }

    /**
     * 获取异步请求缓存大小
     */
    @JSMethod
    public void getCacheSizeAjax(JSCallback callback) {
        new weiuiIhttp.getCacheSize("ajax", callback).start();
    }

    /**
     * 清除异步请求缓存
     */
    @JSMethod
    public void clearCacheAjax() {
        new weiuiIhttp.clearCache("ajax").start();
    }

    /****************************************************************************************/
    /****************************************************************************************/

    /**
     * 复制文本到剪贴板
     * @param var
     */
    @JSMethod
    public void copyText(String var) {
        ClipboardManager clipboard = (ClipboardManager) mWXSDKInstance.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
        if (clipboard != null) {
            clipboard.setPrimaryClip(ClipData.newPlainText("text", var));
        }
    }

    /**
     * 获取剪贴板的文本
     * @return
     */
    @JSMethod(uiThread = false)
    public CharSequence pasteText() {
        ClipboardManager clipboard = (ClipboardManager) mWXSDKInstance.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
        if (clipboard != null) {
            ClipData clip = clipboard.getPrimaryClip();
            if (clip != null && clip.getItemCount() > 0) {
                return clip.getItemAt(0).coerceToText(mWXSDKInstance.getContext());
            }
        }
        return null;
    }

    /****************************************************************************************/
    /****************************************************************************************/

    /**
     * 吐司(Toast)显示
     * @param object
     */
    @JSMethod
    public void toast(String object) {
        utilcodeModule.Toast(mWXSDKInstance, object);
    }

    /**
     * 吐司(Toast)隐藏
     */
    @JSMethod
    public void toastClose() {
        utilcodeModule.ToastClose();
    }

    /****************************************************************************************/
    /****************************************************************************************/

    /**
     * 图片广告弹窗
     * @param object
     * @param callback
     */
    @JSMethod
    public void adDialog(String object, JSCallback callback) {
        JSONObject json = weiuiJson.parseObject(object);
        if (json.size() == 0) {
            json.put("imgUrl", object);
        }
        weiuiAdDialog.create(mWXSDKInstance, json, callback);
    }

    /**
     * 手动关闭图片广告弹窗
     * @param dialogName
     */
    @JSMethod
    public void adDialogClose(String dialogName) {
        weiuiAdDialog.close(dialogName);
    }

    /****************************************************************************************/
    /****************************************************************************************/

    /**
     * 保存图片到本地
     * @param url
     */
    @JSMethod
    public void saveImage(String url, JSCallback callback) {
        weiuiCommon.saveImage(mWXSDKInstance.getContext(), url, callback);
    }

    /****************************************************************************************/
    /****************************************************************************************/

    /**
     * 打开其他APP
     * @param type
     */
    @JSMethod
    public void openOtherApp(String type) {
        if (type == null) {
            return;
        }
        switch (type.toLowerCase()) {
            case "wx":
                weiuiOpenApp.openWeChat(mWXSDKInstance.getContext());
                break;

            case "qq":
                weiuiOpenApp.openQQ(mWXSDKInstance.getContext());
                break;

            case "alipay":
                weiuiOpenApp.openAlipay(mWXSDKInstance.getContext());
                break;

            case "jd":
                weiuiOpenApp.openJd(mWXSDKInstance.getContext());
                break;
        }
    }

    /****************************************************************************************/
    /****************************************************************************************/

    /**
     * 分享文字
     * @param text
     */
    @JSMethod
    public void shareText(String text) {
        weiuiShareUtils.shareText(mWXSDKInstance.getContext(), text);
    }

    /**
     * 分享图片
     * @param imgUrl
     */
    @JSMethod
    public void shareImage(String imgUrl) {
        weiuiShareUtils.shareImage(mWXSDKInstance.getContext(), imgUrl);
    }

    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/

    /**
     * App 相关
     * @param method
     * @return
     */
    @JSMethod(uiThread = false)
    public Object appUtils(String method, Object var0, Object var1) {
        return utilcodeModule.AppUtils(method, objectGroup(var0, var1));
    }

    /**
     * 设备相关
     * @param method
     * @return
     */
    @JSMethod(uiThread = false)
    public Object deviceUtils(String method) {
        return utilcodeModule.DeviceUtils(method);
    }

    /**
     * 键盘相关
     * @param method
     * @return
     */
    @JSMethod(uiThread = false)
    public Object keyboardUtils(String method) {
        return utilcodeModule.KeyboardUtils((Activity) mWXSDKInstance.getContext(), method);
    }

    /**
     * 网络相关
     * @param method
     * @return
     */
    @JSMethod(uiThread = false)
    public Object networkUtils(String method, Object var0, Object var1) {
        return utilcodeModule.NetworkUtils(method, objectGroup(var0, var1));
    }

    /**
     * 权限相关
     * @param method
     * @return
     */
    @JSMethod(uiThread = false)
    public Object permissionUtils(String method, Object var0, Object var1) {
        return utilcodeModule.PermissionUtils(method, objectGroup(var0, var1));
    }

    /**
     * 手机相关
     * @param method
     * @return
     */
    @JSMethod(uiThread = false)
    public Object phoneUtils(String method, Object var0, Object var1, Object var2) {
        return utilcodeModule.PhoneUtils(method, objectGroup(var0, var1, var2));
    }

    /**
     * 进程相关
     * @param method
     * @return
     */
    @JSMethod(uiThread = false)
    public Object processUtils(String method, Object var0, Object var1) {
        return utilcodeModule.ProcessUtils(method, objectGroup(var0, var1));
    }

    /**
     * 屏幕相关
     * @param method
     * @return
     */
    @JSMethod(uiThread = false)
    public Object screenUtils(String method, Object var0, Object var1) {
        return utilcodeModule.ScreenUtils((Activity) mWXSDKInstance.getContext(), method, objectGroup(var0, var1));
    }

    /**
     * 时间相关
     * @param method
     * @return
     */
    @JSMethod(uiThread = false)
    public Object timeUtils(String method, Object var0, Object var1, Object var2) {
        return utilcodeModule.TimeUtils(method, objectGroup(var0, var1, var2));
    }

    /**
     * 摄像机相关
     * @param method
     */
    @JSMethod
    public void cameraTool(String method) {
        rxtoolsModule.RxCameraTool(method);
    }

    /**
     * 定位相关
     * @param method
     * @return
     */
    @JSMethod(uiThread = false)
    public Object locationTool(String method, Object var0, Object var1, Object var2) {
        return rxtoolsModule.RxLocationTool(mWXSDKInstance.getContext(), method, objectGroup(var0, var1, var2));
    }

    /**
     * 震动相关
     * @param method
     */
    @JSMethod
    public void vibrateTool(String method, Object var0, Object var1) {
        rxtoolsModule.RxVibrateTool(mWXSDKInstance.getContext(), method, objectGroup(var0, var1));
    }
}
