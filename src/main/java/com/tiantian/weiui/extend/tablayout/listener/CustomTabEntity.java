package com.tiantian.weiui.extend.tablayout.listener;

import android.support.annotation.DrawableRes;

public interface CustomTabEntity {
    String getTabTitle();

    @DrawableRes
    int getTabSelectedIcon();

    @DrawableRes
    int getTabUnselectedIcon();

    String getTabSelectedIconUrl();

    String getTabUnselectedIconUrl();
}