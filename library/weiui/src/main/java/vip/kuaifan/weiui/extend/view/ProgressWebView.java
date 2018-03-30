package vip.kuaifan.weiui.extend.view;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
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
        return "*{box-sizing:border-box;font-style:normal;list-style:none;padding:0;margin:0;border:0}:after,:before{box-sizing:border-box;font-style:normal;list-style:none;padding:0;margin:0;border:0}*,:after,:before{-webkit-tap-highlight-color:transparent}a,abbr,address,b,blockquote,body,caption,cite,code,dd,del,dfn,div,dl,dt,em,fieldset,form,h1,h2,h3,h4,h5,h6,html,i,iframe,img,ins,kbd,label,legend,li,object,ol,p,pre,q,samp,small,span,strong,sub,sup,table,tbody,td,tfoot,th,thead,tr,ul,var{border:0 none;font-size:inherit;color:inherit;margin:0;padding:0;vertical-align:baseline;max-height:100000px}h1,h2,h3,h4,h5,h6{font-weight:400}em,strong{font-style:normal}li,ol,ul{list-style:none}a{text-decoration:none}";
    }
}