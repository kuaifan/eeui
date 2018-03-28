package vip.kuaifan.weiui.extend.view.tablayout.listener;

import android.support.annotation.DrawableRes;

public interface CustomTabEntity {

    String getTabName();

    String getTabTitle();

    int getTabMessage();

    boolean isTabDot();

    @DrawableRes
    int getTabSelectedIcon();

    @DrawableRes
    int getTabUnselectedIcon();

    String getTabSelectedIconUrl();

    String getTabUnselectedIconUrl();
}