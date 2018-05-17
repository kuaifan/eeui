package vip.kuaifan.weiui.ui;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.taobao.weex.InitConfig.Builder;
import com.taobao.weex.WXSDKEngine;

import vip.kuaifan.weiui.extend.adapter.ImageAdapter;
import vip.kuaifan.weiui.extend.integration.iconify.Iconify;
import vip.kuaifan.weiui.extend.integration.iconify.fonts.IoniconsModule;

import java.util.LinkedList;

import vip.kuaifan.weiui.extend.integration.iconify.fonts.TbIconfontModule;
import vip.kuaifan.weiui.extend.integration.swipebacklayout.BGASwipeBackHelper;
import vip.kuaifan.weiui.extend.module.weiuiIhttp;

/**
 * Created by WDM on 2018/3/27.
 */

public class weiui {

    public static boolean debug = true;

    private static Application application;

    private static LinkedList<Activity> mActivityList = new LinkedList<>();

    public static Application getApplication() {
        return application;
    }

    public static LinkedList<Activity> getActivityList() {
        return mActivityList;
    }

    public static void init(Application application) {
        register(application);
    }

    public static void init(Application application, boolean debug) {
        register(application);
        setDebug(debug);
    }

    public static void setDebug(boolean debug) {
        weiui.debug = debug;
    }

    private static void setTopActivity(final Activity activity) {
        if (mActivityList.contains(activity)) {
            if (!mActivityList.getLast().equals(activity)) {
                mActivityList.remove(activity);
                mActivityList.addLast(activity);
            }
        } else {
            mActivityList.addLast(activity);
        }
    }

    private static void register(Application app) {
        weiui.application = app;
        weiui.application.registerActivityLifecycleCallbacks(mCallbacks);

        weiuiIhttp.init(application);

        Iconify.with(new IoniconsModule()).with(new TbIconfontModule());

        BGASwipeBackHelper.init(application, null);

        Builder mBuilder = new Builder();
        mBuilder.setImgAdapter(new ImageAdapter());
        WXSDKEngine.initialize(application, mBuilder.build());
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
}
