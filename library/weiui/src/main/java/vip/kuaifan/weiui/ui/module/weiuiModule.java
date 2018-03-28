package vip.kuaifan.weiui.ui.module;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;

import com.alibaba.fastjson.JSONObject;

import com.alibaba.weex.plugin.annotation.WeexModule;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;

import vip.kuaifan.weiui.PageActivity;
import vip.kuaifan.weiui.extend.bean.PageBean;
import vip.kuaifan.weiui.extend.module.rxtools.rxtoolsModule;
import vip.kuaifan.weiui.extend.module.utilcode.utilcodeModule;
import vip.kuaifan.weiui.extend.module.weiuiAdDialog;
import vip.kuaifan.weiui.extend.module.weiuiIhttp;
import vip.kuaifan.weiui.extend.module.weiuiJson;
import vip.kuaifan.weiui.extend.module.weiuiPage;
import vip.kuaifan.weiui.extend.module.weiuiScreenUtils;
import vip.kuaifan.weiui.extend.module.weiuiCommon;
import vip.kuaifan.weiui.extend.view.loading.LoadingDialog;


@WeexModule(name = "weiui")
public class weiuiModule extends WXModule {

    private static final String TAG = "weiuiModule";

    private Handler mHandler = new Handler();

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
        //是否显示等待（默认：true）
        if (json.getBoolean("loading") != null) {
            mBean.setLoading(json.getBoolean("loading"));
        }
        //是否支持滑动返回（默认：true）
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
        JSONObject json = weiuiJson.parseObject(object);
        if (json.size() == 0) {
            json.put("pageName", object);
        }
        String pageName = json.getString("pageName");
        if (pageName == null || pageName.isEmpty()) {
            if (mWXSDKInstance.getContext() instanceof PageActivity) {
                return ((PageActivity) mWXSDKInstance.getContext()).getPageInfo().toMap();
            }
            return null;
        }
        return weiuiPage.getWinInfo(pageName).toMap();
    }

    /**
     * 重新加载页面（刷新）
     * @param object
     */
    @JSMethod
    public void reloadPage(String object) {
        JSONObject json = weiuiJson.parseObject(object);
        if (json.size() == 0) {
            json.put("pageName", object);
        }
        String pageName = json.getString("pageName");
        if (pageName == null || pageName.isEmpty()) {
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
        JSONObject json = weiuiJson.parseObject(object);
        if (json.size() == 0) {
            json.put("pageName", object);
        }
        String pageName = json.getString("pageName");
        if (pageName == null || pageName.isEmpty()) {
            ((Activity) mWXSDKInstance.getContext()).finish();
            return;
        }
        weiuiPage.closeWin(pageName);
    }

    /**
     * 拦截返回按键事件
     * @param object
     * @param callback  为null时取消拦截
     */
    @JSMethod
    public void setPageBackPressed(String object, JSCallback callback) {
        JSONObject json = weiuiJson.parseObject(object);
        if (json.size() == 0) {
            json.put("pageName", object);
        }
        String pageName = json.getString("pageName");
        if (pageName == null || pageName.isEmpty()) {
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
     * 清除缓存页面
     */
    @JSMethod
    public void clearCachePage() {
        weiuiIhttp.clearCache("page");
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
     * 获取状态栏高度（屏幕像素）
     */
    @JSMethod(uiThread = false)
    public int getStatusBarHeight() {
        return weiuiCommon.getStatusBarHeight(mWXSDKInstance.getContext());
    }

    /**
     * 获取状态栏高度（weexPX单位）
     */
    @JSMethod(uiThread = false)
    public int getStatusBarHeightPx() {
        return weiuiScreenUtils.weexDp2px(mWXSDKInstance, weiuiCommon.getStatusBarHeight(mWXSDKInstance.getContext()));
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
     * 获取本地软件版本号
     */
    @JSMethod(uiThread = false)
    public int getLocalVersion() {
        return weiuiCommon.getLocalVersion(mWXSDKInstance.getContext());
    }

    /**
     * 获取本地软件版本号名称
     */
    @JSMethod(uiThread = false)
    public String getLocalVersionName() {
        return weiuiCommon.getLocalVersionName(mWXSDKInstance.getContext());
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
        return weiuiCommon.getImei(mWXSDKInstance.getContext());
    }

    /**
     * 保存缓存信息
     * @param key
     * @param value
     * @param expired
     */
    @JSMethod
    public void setCachesString(String key, String value, long expired) {
        if (key == null || value == null) {
            return;
        }
        weiuiCommon.setCachesString(mWXSDKInstance.getContext(), "weiuiCaches", key, value, expired);
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
    @JSMethod
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
    @JSMethod
    public void loadingClose(String var) {
        LoadingDialog.close(var);
    }

    /**
     * 打开滑动验证码
     * @param imgUrl
     * @param callback
     */
    @JSMethod
    public void swipeCaptcha(String imgUrl, JSCallback callback) {
        PageActivity.startSwipeCaptcha(mWXSDKInstance.getContext(), imgUrl, callback);
    }

    /**
     * 打开二维码扫描
     * @param object
     * @param callback
     */
    @JSMethod
    public void openScaner(String object, JSCallback callback) {
        PageActivity.startScanerCode(mWXSDKInstance.getContext(), object, callback);
    }

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
        weiuiCommon.ajax(mWXSDKInstance.getContext(), json, callback);
    }

    /**
     * 清除跨域异步请求缓存
     */
    @JSMethod
    public void ajaxClearCache() {
        weiuiIhttp.clearCache("ajax");
    }

    /**
     * 取消跨域异步请求
     * @param name
     */
    @JSMethod
    public void ajaxCancel(String name) {
        weiuiCommon.ajaxCancel(name);
    }

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
