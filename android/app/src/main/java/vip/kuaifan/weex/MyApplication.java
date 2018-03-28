package vip.kuaifan.weex;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.alibaba.weex.plugin.loader.WeexPluginContainer;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXSDKEngine;

import vip.kuaifan.weiui.extend.module.weiui;
import vip.kuaifan.weiui.umeng.weiui_umeng;

public class MyApplication extends Application {

    protected void attachBaseContext(Context ctx) {
        super.attachBaseContext(ctx);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //
        WXEnvironment.setOpenDebugLog(true);
        WXEnvironment.setApkDebugable(true);
        WXSDKEngine.addCustomOptions("appName", "WEIUI");
        WXSDKEngine.addCustomOptions("appGroup", "WEIUI");
        //
        weiui.init(this);
        weiui_umeng.init("5ab9d815f29d980730000365", "0f6e074e1443674fc7ba4327bf93939a");
        //
        WeexPluginContainer.loadAll(this);
    }
}
