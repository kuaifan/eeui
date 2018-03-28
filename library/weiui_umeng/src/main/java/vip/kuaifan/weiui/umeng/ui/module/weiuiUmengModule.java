package vip.kuaifan.weiui.umeng.ui.module;

import com.alibaba.weex.plugin.annotation.WeexModule;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;

import vip.kuaifan.weiui.umeng.weiui_umeng;

/**
 * Created by WDM on 2018/3/27.
 */

@WeexModule(name = "weiui_umeng")
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
