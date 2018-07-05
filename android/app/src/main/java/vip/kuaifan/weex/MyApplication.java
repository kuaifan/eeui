package vip.kuaifan.weex;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.lljjcoder.weiui.ui.weiui_citypicker;
import com.luck.picture.lib.weiui.ui.weiui_picture;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXSDKEngine;

import io.rong.imlib.weiui.ui.weiui_rongim;
import vip.kuaifan.weiui.extend.module.weiui;
import vip.kuaifan.weiui.umeng.ui.weiui_umeng;

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
        weiui_citypicker.init();
        weiui_picture.init();
        weiui_rongim.init();
        weiui_umeng.init("5ab9d815f29d980730000365", "0f6e074e1443674fc7ba4327bf93939a");
    }
}
