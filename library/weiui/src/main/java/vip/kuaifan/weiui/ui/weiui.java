package vip.kuaifan.weiui.ui;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.taobao.weex.InitConfig.Builder;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.common.WXException;

import vip.kuaifan.weiui.extend.adapter.ImageAdapter;
import vip.kuaifan.weiui.extend.integration.iconify.Iconify;
import vip.kuaifan.weiui.extend.integration.iconify.fonts.IoniconsModule;

import java.util.LinkedList;

import vip.kuaifan.weiui.extend.integration.iconify.fonts.TbIconfontModule;
import vip.kuaifan.weiui.extend.integration.swipebacklayout.BGASwipeBackHelper;
import vip.kuaifan.weiui.extend.module.weiuiIhttp;
import vip.kuaifan.weiui.ui.component.banner.Banner;
import vip.kuaifan.weiui.ui.component.button.Button;
import vip.kuaifan.weiui.ui.component.grid.Grid;
import vip.kuaifan.weiui.ui.component.icon.Icon;
import vip.kuaifan.weiui.ui.component.marquee.Marquee;
import vip.kuaifan.weiui.ui.component.navbar.Navbar;
import vip.kuaifan.weiui.ui.component.navbar.NavbarItem;
import vip.kuaifan.weiui.ui.component.recyler.Recyler;
import vip.kuaifan.weiui.ui.component.ripple.Ripple;
import vip.kuaifan.weiui.ui.component.scrollText.ScrollText;
import vip.kuaifan.weiui.ui.component.sidePanel.SidePanel;
import vip.kuaifan.weiui.ui.component.sidePanel.SidePanelMenu;
import vip.kuaifan.weiui.ui.component.tabbar.Tabbar;
import vip.kuaifan.weiui.ui.component.tabbar.TabbarPage;
import vip.kuaifan.weiui.ui.component.webView.WebView;
import vip.kuaifan.weiui.ui.module.weiuiModule;

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

        try {
            WXSDKEngine.registerModule("weiui", weiuiModule.class);
            WXSDKEngine.registerComponent("weiui_banner", Banner.class);
            WXSDKEngine.registerComponent("weiui_button", Button.class);
            WXSDKEngine.registerComponent("weiui_grid", Grid.class);
            WXSDKEngine.registerComponent("weiui_icon", Icon.class);
            WXSDKEngine.registerComponent("weiui_marquee", Marquee.class);
            WXSDKEngine.registerComponent("weiui_navbar", Navbar.class);
            WXSDKEngine.registerComponent("weiui_navbar_item", NavbarItem.class);
            WXSDKEngine.registerComponent("weiui_recyler", Recyler.class);
            WXSDKEngine.registerComponent("weiui_list", Recyler.class);
            WXSDKEngine.registerComponent("weiui_ripple", Ripple.class);
            WXSDKEngine.registerComponent("ripple", Ripple.class);
            WXSDKEngine.registerComponent("weiui_scroll_text", ScrollText.class);
            WXSDKEngine.registerComponent("weiui_side_panel", SidePanel.class);
            WXSDKEngine.registerComponent("weiui_side_panel_menu", SidePanelMenu.class);
            WXSDKEngine.registerComponent("weiui_tabbar", Tabbar.class);
            WXSDKEngine.registerComponent("weiui_tabbar_page", TabbarPage.class);
            WXSDKEngine.registerComponent("weiui_webview", WebView.class);
        } catch (WXException e) {
            e.printStackTrace();
        }
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
