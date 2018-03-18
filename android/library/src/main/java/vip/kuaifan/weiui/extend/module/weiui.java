package vip.kuaifan.weiui.extend.module;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.taobao.weex.InitConfig;
import com.taobao.weex.WXSDKEngine;

import vip.kuaifan.weiui.extend.adapter.ImageAdapter;
import vip.kuaifan.weiui.extend.integration.iconify.Iconify;
import vip.kuaifan.weiui.extend.integration.iconify.fonts.IoniconsModule;

import java.util.LinkedList;

import vip.kuaifan.weiui.extend.integration.swipebacklayout.BGASwipeBackHelper;
import vip.kuaifan.weiui.PageActivity;
import vip.kuaifan.weiui.extend.bean.OpenWinBean;

/**
 * Created by WDM on 2018/2/28.
 */
@SuppressLint("StaticFieldLeak")
public class weiui {

    private static Application application;

    private static LinkedList<Activity> mActivityList = new LinkedList<>();

    public static Application getApplication() {
        return application;
    }

    public static void init(Application application) {
        register(application);
    }

    public static void register(Application app) {
        weiui.application = app;
        weiui.application.registerActivityLifecycleCallbacks(mCallbacks);

        weiuiIhttp.init(application);

        Iconify.with(new IoniconsModule());

        BGASwipeBackHelper.init(application, null);

        WXSDKEngine.initialize(application, new InitConfig.Builder().setImgAdapter(new ImageAdapter()).build());
    }

    private static Application.ActivityLifecycleCallbacks mCallbacks = new Application.ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(Activity activity, Bundle bundle) {
            setTopActivity(activity);
        }

        @Override
        public void onActivityStarted(Activity activity) {
            setTopActivity(activity);
        }

        @Override
        public void onActivityResumed(Activity activity) {
            setTopActivity(activity);
        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            mActivityList.remove(activity);
        }
    };

    public static void setTopActivity(final Activity activity) {
        if (mActivityList.contains(activity)) {
            if (!mActivityList.getLast().equals(activity)) {
                mActivityList.remove(activity);
                mActivityList.addLast(activity);
            }
        } else {
            mActivityList.addLast(activity);
        }
    }

    public static LinkedList<Activity> getActivityList() {
        return mActivityList;
    }
}
