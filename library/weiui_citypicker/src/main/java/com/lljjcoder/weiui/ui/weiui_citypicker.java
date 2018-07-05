package com.lljjcoder.weiui.ui;

import com.lljjcoder.weiui.ui.module.weiuiCitypickerModule;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.common.WXException;

public class weiui_citypicker {

    public static void init() {
        try {
            WXSDKEngine.registerModule("weiui_citypicker", weiuiCitypickerModule.class);
        } catch (WXException e) {
            e.printStackTrace();
        }
    }

}
