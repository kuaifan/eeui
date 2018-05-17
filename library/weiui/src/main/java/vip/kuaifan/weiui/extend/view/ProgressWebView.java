package vip.kuaifan.weiui.extend.view;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import vip.kuaifan.weiui.R;
import vip.kuaifan.weiui.extend.module.weiuiCommon;
import vip.kuaifan.weiui.extend.module.weiuiCommonWebView;


/**
 * 带进度条的WebView
 */
@SuppressWarnings("deprecation")
public class ProgressWebView extends WebView {

    private ProgressBar progressbar;
    private TitleCall mTitleCall;
    private StatusCall mStatusCall;
    private boolean progressbarVisibility;

    @SuppressLint("AddJavascriptInterface")
    public ProgressWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        progressbar = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
        progressbar.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, 6, 0, 0));
        progressbar.setVisibility(GONE);
        progressbarVisibility = true;
        //
        Drawable drawable = context.getResources().getDrawable(R.drawable.progress_bar_states);
        progressbar.setProgressDrawable(drawable);
        addView(progressbar);
        setWebViewClient(new WebViewClient());
        setDownloadListener(new DownloadListener());
        setWebChromeClient(new WebChromeClient());
        addJavascriptApi(new weiuiCommonWebView(context, this), "webApi");
        initSetting();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initSetting() {
        WebSettings webSettings = getSettings();
        //开启 JavaScript
        webSettings.setJavaScriptEnabled(true);
        //开启 localStorage
        webSettings.setDomStorageEnabled(true);
        //启动缓存
        webSettings.setAppCacheEnabled(true);
        //设置缓存模式
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        //将图片调整到适合webview的大小
        webSettings.setUseWideViewPort(true);
        //缩放至屏幕的大小
        webSettings.setLoadWithOverviewMode(true);
        //支持缩放，默认为true。是下面那个的前提。
        webSettings.setSupportZoom(true);
        //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setBuiltInZoomControls(true);
        //隐藏原生的缩放控件
        webSettings.setDisplayZoomControls(false);
        //设置UA
        String ua = webSettings.getUserAgentString();
        webSettings.setUserAgentString(ua + ";android_kuaifan_weiui/" + weiuiCommon.getLocalVersionName(getContext()));
    }

    private class DownloadListener implements android.webkit.DownloadListener {

        @Override
        public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setData(Uri.parse(url));
            getContext().startActivity(intent);
        }
    }

    private class WebViewClient extends android.webkit.WebViewClient {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            if (mStatusCall != null) {
                mStatusCall.onStatusChanged(view, "start");
            }
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if (mStatusCall != null) {
                mStatusCall.onStatusChanged(view, "success");
            }
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            if (mStatusCall != null) {
                mStatusCall.onErrorChanged(view, errorCode, description, failingUrl);
            }
        }

        @SuppressLint("NewApi")
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            String url = String.valueOf(request.getUrl());
            if (url == null || url.isEmpty()) {
                return true;
            }
            if (!url.startsWith("http") && !url.startsWith("HTTP")) {
                return true;
            }
            view.loadUrl(url);
            return true;
        }
    }

    private class WebChromeClient extends android.webkit.WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (progressbarVisibility) {
                if (newProgress == 100) {
                    progressbar.setVisibility(GONE);
                } else {
                    if (progressbar.getVisibility() == GONE) {
                        progressbar.setVisibility(VISIBLE);
                    }
                    progressbar.setProgress(newProgress);
                }
            }
            super.onProgressChanged(view, newProgress);
        }
        
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            if (mStatusCall != null) {
                mStatusCall.onTitleChanged(view, title);
            }
            if (mTitleCall != null) {
                mTitleCall.onChanged(view, title);
            }
        }
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        LayoutParams lp = (LayoutParams) progressbar.getLayoutParams();
        lp.x = l;
        lp.y = t;
        progressbar.setLayoutParams(lp);
        super.onScrollChanged(l, t, oldl, oldt);
    }

    /**
     * 销毁浏览器
     */
    public void onDestroy() {
        loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
        clearHistory();
        ((ViewGroup) getParent()).removeView(this);
        destroy();
    }

    /**
     * 监听标题变化
     * @param call
     */
    public void setOnTitleClient(TitleCall call) {
        mTitleCall = call;
    }

    public interface TitleCall {
        void onChanged(WebView view, String title);
    }

    /**
     * 监听状态变化
     * @param call
     */
    public void setOnStatusClient(StatusCall call) {
        mStatusCall = call;
    }

    public interface StatusCall {
        void onStatusChanged(WebView view, String status);
        void onTitleChanged(WebView view, String title);
        void onErrorChanged(WebView view, int errorCode, String description, String failingUrl);
    }

    /**
     * 是否显示进度条
     * @param var
     */
    public void setProgressbarVisibility(boolean var) {
        progressbarVisibility = var;
    }

    /**
     * 添加JavaScript交互
     * @param obj
     */
    @SuppressLint("JavascriptInterface")
    public void addJavascriptApi(Object obj, String name) {
        addJavascriptInterface(obj, name);
    }

    /**
     * 全局样式
     * @return
     */
    public static String commonStyle() {
        return "body{background-color:#FFF;color:#000;font-family:Verdana,Arial,Helvetica,sans-serif;font-size:14px;line-height:1.3;scrollbar-3dlight-color:#F0F0EE;scrollbar-arrow-color:#676662;scrollbar-base-color:#F0F0EE;scrollbar-darkshadow-color:#DDD;scrollbar-face-color:#E0E0DD;scrollbar-highlight-color:#F0F0EE;scrollbar-shadow-color:#F0F0EE;scrollbar-track-color:#F5F5F5}\n" +
                "td,th{font-family:Verdana,Arial,Helvetica,sans-serif;font-size:14px}\n" +
                ".word-wrap{word-wrap:break-word;-ms-word-break:break-all;word-break:break-all;word-break:break-word;-ms-hyphens:auto;-moz-hyphens:auto;-webkit-hyphens:auto;hyphens:auto}\n" +
                ".mce-content-body .mce-reset{margin:0;padding:0;border:0;outline:0;vertical-align:top;background:0 0;text-decoration:none;color:#000;font-family:Arial;font-size:11px;text-shadow:none;float:none;position:static;width:auto;height:auto;white-space:nowrap;cursor:inherit;line-height:normal;font-weight:400;text-align:left;-webkit-tap-highlight-color:transparent;-moz-box-sizing:content-box;-webkit-box-sizing:content-box;box-sizing:content-box;direction:ltr;max-width:none}\n" +
                ".mce-object{border:1px dotted #3A3A3A;background:#D5D5D5 url(data:image/gif;base64,R0lGODlhEQANALMPAOXl5T8/P29vb7S0tFdXV/39/djY2N3d3crKyu/v7/f39/Ly8p2dnf///zMzMwAAACH5BAEAAA8ALAAAAAARAA0AAARF0MlJq3uutay75lmGNU9pjiNFCsipqhgxmO9HSgFTgtt9O4EBABWa3AiGwvBlfAgWisPOyCslDDSbSHTa+SzgpmdMbkQAADs=) no-repeat center}\n" +
                ".mce-preview-object{display:inline-block;position:relative;margin:0 2px 0 2px;line-height:0;border:1px solid gray}\n" +
                ".mce-preview-object[data-mce-selected=\"2\"] .mce-shim{display:none}\n" +
                ".mce-preview-object .mce-shim{position:absolute;top:0;left:0;width:100%;height:100%;background:url(data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7)}\n" +
                "figure.align-left{float:left}\n" +
                "figure.align-right{float:right}\n" +
                "figure.image.align-center{display:table;margin-left:auto;margin-right:auto}\n" +
                "figure.image{display:inline-block;border:1px solid gray;margin:0 2px 0 1px;background:#f5f2f0}\n" +
                "figure.image img{margin:8px 8px 0 8px}\n" +
                "figure.image figcaption{margin:6px 8px 6px 8px;text-align:center}\n" +
                ".mce-toc{border:1px solid gray}\n" +
                ".mce-toc h2{margin:4px}\n" +
                ".mce-toc li{list-style-type:none}\n" +
                ".mce-pagebreak{cursor:default;display:block;border:0;width:100%;height:5px;border:1px dashed #666;margin-top:15px;page-break-before:always}\n" +
                "@media print{.mce-pagebreak{border:0}\n" +
                "}\n" +
                ".mce-item-anchor{cursor:default;display:inline-block;-webkit-user-select:all;-webkit-user-modify:read-only;-moz-user-select:all;-moz-user-modify:read-only;user-select:all;user-modify:read-only;width:9px!important;height:9px!important;border:1px dotted #3A3A3A;background:#D5D5D5 url(data:image/gif;base64,R0lGODlhBwAHAIABAAAAAP///yH5BAEAAAEALAAAAAAHAAcAAAIMjGGJmMH9mHQ0AlYAADs=) no-repeat center}\n" +
                ".mce-nbsp,.mce-shy{background:#AAA}\n" +
                ".mce-shy::after{content:'-'}\n" +
                ".mce-match-marker{background:#AAA;color:#fff}\n" +
                ".mce-match-marker-selected{background:#39f;color:#fff}\n" +
                ".mce-spellchecker-word{border-bottom:2px solid rgba(208,2,27,.5);cursor:default}\n" +
                ".mce-spellchecker-grammar{border-bottom:2px solid green;cursor:default}\n" +
                ".mce-item-table,.mce-item-table caption,.mce-item-table td,.mce-item-table th{border:1px dashed #BBB}\n" +
                "td[data-mce-selected],th[data-mce-selected]{background-color:#2276d2!important}\n" +
                ".mce-edit-focus{outline:1px dotted #333}\n" +
                ".mce-content-body [contentEditable=false] [contentEditable=true]:focus{outline:2px solid #2276d2}\n" +
                ".mce-content-body [contentEditable=false] [contentEditable=true]:hover{outline:2px solid #2276d2}\n" +
                ".mce-content-body [contentEditable=false][data-mce-selected]{outline:2px solid #2276d2}\n" +
                ".mce-content-body [data-mce-selected=inline-boundary]{background:#bfe6ff}\n" +
                ".mce-content-body .mce-item-anchor[data-mce-selected]{background:#D5D5D5 url(data:image/gif;base64,R0lGODlhBwAHAIABAAAAAP///yH5BAEAAAEALAAAAAAHAAcAAAIMjGGJmMH9mHQ0AlYAADs=) no-repeat center}\n" +
                ".mce-content-body hr{cursor:default}\n" +
                ".mce-content-body table{-webkit-nbsp-mode:normal}\n" +
                ".ephox-snooker-resizer-bar{background-color:#2276d2;opacity:0}\n" +
                ".ephox-snooker-resizer-cols{cursor:col-resize}\n" +
                ".ephox-snooker-resizer-rows{cursor:row-resize}\n" +
                ".ephox-snooker-resizer-bar.ephox-snooker-resizer-bar-dragging{opacity:.2}\n";
    }
}