package com.tiantian.weiui.ui.module;

import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.common.WXModule;
import com.taobao.weex.ui.component.WXComponent;
import com.tiantian.weiui.extend.module.Json;
import com.tiantian.weiui.ui.component.tabpager.TabPager;

public class weiuiEvent extends WXModule {

    private static final String TAG = "weiuiEvent";

    public static class Action {

        public enum TabPager {
            showmsg,
            showdot,
            hidemsg,
            setcurrentitem,
            removepageall,
            removepageat,
            load,
            reload,
        }

    }

    @JSMethod(uiThread = true)
    public void action(String ref, JSONObject params) {
        String method = Json.getString(params, "method").toLowerCase();
        String action = Json.getString(params, "action").toLowerCase();
        if (method.isEmpty() || ref.isEmpty()) {
            return;
        }
        //
        WXComponent myComponent = WXSDKManager.getInstance().getWXRenderManager().getWXComponent(mWXSDKInstance.getInstanceId(), ref);
        if (myComponent == null) {
            return;
        }
        //
        switch (method) {
            case "tabpager":
                if(myComponent instanceof TabPager) {
                    ((TabPager) myComponent).setAction(action, params);
                }
                break;
        }
    }
}
