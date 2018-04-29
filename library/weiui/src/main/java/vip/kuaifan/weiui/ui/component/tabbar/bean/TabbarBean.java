package vip.kuaifan.weiui.ui.component.tabbar.bean;

import vip.kuaifan.weiui.extend.module.weiuiCommon;

/**
 * Created by WDM on 2018/3/10.
 */

public class TabbarBean {

    private String tabName = weiuiCommon.randomString(8);
    private String title = "New Page";
    private String url = "";
    private String selectedIcon = "";
    private String unSelectedIcon = "";
    private Object params;
    private long cache = 0;
    private int message = 0;
    private boolean dot = false;
    private String statusBarColor = "";
    private Object view;

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSelectedIcon() {
        if (selectedIcon.isEmpty()) {
            return unSelectedIcon.isEmpty() ? "home" : unSelectedIcon;
        }
        return selectedIcon;
    }

    public void setSelectedIcon(String selectedIcon) {
        this.selectedIcon = selectedIcon;
    }

    public String getUnSelectedIcon() {
        if (unSelectedIcon.isEmpty()) {
            return selectedIcon.isEmpty() ? "home" : selectedIcon;
        }
        return unSelectedIcon;
    }

    public void setUnSelectedIcon(String unSelectedIcon) {
        this.unSelectedIcon = unSelectedIcon;
    }

    public Object getParams() {
        return params;
    }

    public void setParams(Object params) {
        this.params = params;
    }

    public long getCache() {
        return cache;
    }

    public void setCache(long cache) {
        this.cache = cache;
    }

    public int getMessage() {
        return message;
    }

    public void setMessage(int message) {
        this.message = message;
    }

    public boolean isDot() {
        return dot;
    }

    public void setDot(boolean dot) {
        this.dot = dot;
    }

    public String getStatusBarColor() {
        return statusBarColor;
    }

    public void setStatusBarColor(String statusBarColor) {
        this.statusBarColor = statusBarColor;
    }

    public Object getView() {
        return view;
    }

    public void setView(Object view) {
        this.view = view;
    }
}
