package com.tiantian.weiui.ui.component.tabpager.bean;


import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.taobao.weex.WXSDKInstance;

public class WXSDKBean {

    private FrameLayout container;

    private ProgressBar progress;

    private WXSDKInstance instance;

    public FrameLayout getContainer() {
        return container;
    }

    public void setContainer(FrameLayout container) {
        this.container = container;
    }

    public ProgressBar getProgress() {
        return progress;
    }

    public void setProgress(ProgressBar progress) {
        this.progress = progress;
    }

    public WXSDKInstance getInstance() {
        return instance;
    }

    public void setInstance(WXSDKInstance instance) {
        this.instance = instance;
    }
}
