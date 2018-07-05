package vip.kuaifan.weiui.umeng.ui.module;

import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;

import vip.kuaifan.weiui.umeng.ui.weiui_umeng;

/**
 * Created by WDM on 2018/3/27.
 */

public class weiuiUmengModule extends WXModule {

    private static final String TAG = "weiuiUmengModule";

    /**
     * 获取deviceToken
     */
    @JSMethod(uiThread = false)
    public Object getToken() {
        return weiui_umeng.getToken();
    }

    /**
     * 设置点击通知事件
     * @param callback
     */
    @JSMethod
    public void setNotificationClickHandler(JSCallback callback) {
        if (callback == null) {
            return;
        }
        weiui_umeng.addNotificationClickHandler(mWXSDKInstance.getContext(), callback);
    }
}
