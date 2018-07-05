package com.luck.picture.lib.weiui.ui;

import com.luck.picture.lib.weiui.ui.module.weiuiPictureModule;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.common.WXException;

public class weiui_picture {

    public static void init() {
        try {
            WXSDKEngine.registerModule("weiui_picture", weiuiPictureModule.class);
        } catch (WXException e) {
            e.printStackTrace();
        }
    }
}
