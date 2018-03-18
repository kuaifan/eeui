package vip.kuaifan.weiui.ui.component.tabbar.bean;

import vip.kuaifan.weiui.extend.module.weiuiCommon;

/**
 * Created by WDM on 2018/3/10.
 */

public class TabbarBean {

    private String name = weiuiCommon.randomString(8);
    private String title = "New Page";
    private String selectedIcon = "";
    private String unSelectedIcon = "";
    private int message = 0;
    private boolean dot = false;
    private Object view;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSelectedIcon() {
        if (selectedIcon.isEmpty()) {
            return unSelectedIcon.isEmpty() ? "ion-home" : unSelectedIcon;
        }
        return selectedIcon;
    }

    public void setSelectedIcon(String selectedIcon) {
        this.selectedIcon = selectedIcon;
    }

    public String getUnSelectedIcon() {
        if (unSelectedIcon.isEmpty()) {
            return selectedIcon.isEmpty() ? "ion-home" : selectedIcon;
        }
        return unSelectedIcon;
    }

    public void setUnSelectedIcon(String unSelectedIcon) {
        this.unSelectedIcon = unSelectedIcon;
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

    public Object getView() {
        return view;
    }

    public void setView(Object view) {
        this.view = view;
    }
}
