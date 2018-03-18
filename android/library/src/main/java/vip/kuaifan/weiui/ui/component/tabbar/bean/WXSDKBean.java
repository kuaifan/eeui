package vip.kuaifan.weiui.ui.component.tabbar.bean;


import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.taobao.weex.WXSDKInstance;

public class WXSDKBean {

    private SwipeRefreshLayout swipeRefresh;

    private boolean swipeRefreshEnable;

    private FrameLayout container;

    private ProgressBar progress;

    private WXSDKInstance instance;

    private String type = "";

    private Object view;

    public SwipeRefreshLayout getSwipeRefresh() {
        return swipeRefresh;
    }

    public void setSwipeRefresh(SwipeRefreshLayout swipeRefresh) {
        this.swipeRefresh = swipeRefresh;
    }

    public boolean getSwipeRefreshEnable() {
        return swipeRefreshEnable;
    }

    public void setSwipeRefreshEnable(boolean swipeRefreshEnable) {
        this.swipeRefreshEnable = swipeRefreshEnable;
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getView() {
        return view;
    }

    public void setView(Object view) {
        this.view = view;
    }
}
