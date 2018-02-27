package com.tiantian.weiui.ui.component.tabpager.entity;

import com.tiantian.weiui.extend.tablayout.listener.CustomTabEntity;

public class TabEntity implements CustomTabEntity {
    public String title;

    public int selectedIcon;
    public int unSelectedIcon;

    public String selectedIconUrl;
    public String unSelectedIconUrl;

    public TabEntity(String title, int selectedIcon, int unSelectedIcon) {
        this.title = title;
        this.selectedIcon = selectedIcon;
        this.unSelectedIcon = unSelectedIcon;
    }

    public TabEntity(String title, String selectedIconUrl, String unSelectedIconUrl) {
        this.title = title;
        this.selectedIconUrl = selectedIconUrl;
        this.unSelectedIconUrl = unSelectedIconUrl;
    }

    @Override
    public String getTabTitle() {
        return title;
    }

    @Override
    public int getTabSelectedIcon() {
        return selectedIcon;
    }

    @Override
    public int getTabUnselectedIcon() {
        return unSelectedIcon;
    }

    @Override
    public String getTabSelectedIconUrl() {
        return selectedIconUrl;
    }

    @Override
    public String getTabUnselectedIconUrl() {
        return unSelectedIconUrl;
    }
}
