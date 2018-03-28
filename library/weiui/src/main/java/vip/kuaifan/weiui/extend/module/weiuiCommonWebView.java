package vip.kuaifan.weiui.extend.module;

import android.app.Activity;
import android.content.Context;
import android.webkit.JavascriptInterface;

import vip.kuaifan.weiui.extend.view.ProgressWebView;


public class weiuiCommonWebView {

    private Context context;
    private ProgressWebView webView;

    public weiuiCommonWebView(Context context, ProgressWebView webView) {
        this.context = context;
        this.webView = webView;
    }

    /**
     * 关闭页面
     */
    @JavascriptInterface
    public void closeWin() {
        if (context == null) {
            return;
        }
        ((Activity) context).finish();
        context = null;
    }
}
