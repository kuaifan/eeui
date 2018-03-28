package vip.kuaifan.weiui.extend.module.utilcode.util;

import android.app.Activity;
import android.app.Application;

import java.util.List;

import vip.kuaifan.weiui.extend.module.weiui;

/**
 * Created by WDM on 2018/3/13.
 */

public class Utils {

    public static Application getApp() {
        return weiui.getApplication();
    }

    public static List<Activity> getActivityList() {
        return weiui.getActivityList();
    }
}
