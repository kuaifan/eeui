package com.tiantian.weiui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.taobao.weex.IWXRenderListener;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.IWXDebugProxy;
import com.taobao.weex.common.WXRenderStrategy;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseActivityWeex extends AppCompatActivity implements WXSDKInstance.NestedInstanceInterceptor, IWXRenderListener {
    private static final String TAG = "BaseActivityWeex";
    protected BroadcastReceiver mBroadcastReceiver;
    protected ViewGroup mContainer;
    protected WXSDKInstance mInstance;
    protected Uri mUri;
    private WxReloadListener mReloadListener;
    private WxRefreshListener mRefreshListener;
    private String mUrl;
    private String mPageName = TAG;
    protected Boolean isLocalUrl = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createWeexInstance();
        mInstance.onActivityCreate();
        registerBroadcastReceiver(mBroadcastReceiver, null);
    }

    protected final ViewGroup getContainer() {
        return mContainer;
    }

    protected final void setContainer(ViewGroup container) {
        mContainer = container;
    }

    protected void destoryWeexInstance() {
        if (mInstance != null) {
            mInstance.registerRenderListener(null);
            mInstance.destroy();
            mInstance = null;
        }
    }

    protected void createWeexInstance() {
        destoryWeexInstance();
        mInstance = new WXSDKInstance(this);
        mInstance.registerRenderListener(this);
    }

    protected void renderPageByURL(String url) {
        renderPageByURL(url, null);
    }

    protected void renderPageByURL(String url, String jsonInitData) {
        Map<String, Object> options = new HashMap<>();
        options.put(WXSDKInstance.BUNDLE_URL, url);
        mInstance.renderByUrl(
                getPageName(),
                url,
                options,
                jsonInitData,
                WXRenderStrategy.APPEND_ASYNC);
    }

    public String getPageName()  {
        return mPageName;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mInstance != null) {
            mInstance.onActivityStart();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mInstance != null) {
            mInstance.onActivityResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mInstance != null) {
            mInstance.onActivityPause();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mInstance != null) {
            mInstance.onActivityStop();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (mInstance != null) {
            mInstance.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (mInstance != null) {
            mInstance.onActivityResult(requestCode, resultCode, data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mInstance != null) {
            mInstance.onActivityDestroy();
        }
        unregisterBroadcastReceiver();

    }

    @Override
    public void onViewCreated(WXSDKInstance wxsdkInstance, View view) {
        if (mContainer != null) {
            mContainer.removeAllViews();
            mContainer.addView(view);
        }
    }

    @Override
    public void onRefreshSuccess(WXSDKInstance wxsdkInstance, int width, int height) {

    }

    public void runWithPermissionsCheck(int requestCode, String permission, Runnable runnable) {
        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                Toast.makeText(this, "please give me the permission", Toast.LENGTH_SHORT).show();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
            }
        } else {
            if (runnable != null) {
                runnable.run();
            }
        }
    }

    @Override
    public void onRenderSuccess(WXSDKInstance instance, int width, int height) {

    }

    @Override
    public void onException(WXSDKInstance instance, String errCode, String msg) {

    }

    public void setReloadListener(WxReloadListener reloadListener) {
        mReloadListener = reloadListener;
    }

    public void registerBroadcastReceiver(BroadcastReceiver receiver, IntentFilter filter) {
        mBroadcastReceiver = receiver != null ? receiver : new DefaultBroadcastReceiver();
        if (filter == null) {
            filter = new IntentFilter();
        }
        filter.addAction(IWXDebugProxy.ACTION_DEBUG_INSTANCE_REFRESH);
        filter.addAction(WXSDKEngine.JS_FRAMEWORK_RELOAD);
        LocalBroadcastManager.getInstance(getApplicationContext())
                .registerReceiver(mBroadcastReceiver, filter);
        if (mReloadListener == null) {
            setReloadListener(new WxReloadListener() {

                @Override
                public void onReload() {
                    createWeexInstance();
                    renderPage();
                }

            });
        }

        if (mRefreshListener == null) {
            setRefreshListener(new WxRefreshListener() {

                @Override
                public void onRefresh() {
                    createWeexInstance();
                    renderPage();
                }

            });
        }
    }

    public void unregisterBroadcastReceiver() {
        if (mBroadcastReceiver != null) {
            LocalBroadcastManager.getInstance(getApplicationContext())
                    .unregisterReceiver(mBroadcastReceiver);
            mBroadcastReceiver = null;
        }
        setReloadListener(null);
        setRefreshListener(null);
    }

    public void setRefreshListener(WxRefreshListener refreshListener) {
        mRefreshListener = refreshListener;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public void loadUrl(String url) {
        setUrl(url);
        renderPage();
    }

    protected void preRenderPage() {

    }

    protected void postRenderPage() {

    }

    protected void renderPage() {
        preRenderPage();
        renderPageByURL(mUrl);
        postRenderPage();
    }

    protected boolean isLocalPage() {
        boolean isLocalPage = true;
        if (mUri != null) {
            String scheme = mUri.getScheme();
            isLocalPage = !mUri.isHierarchical() ||
                    (!TextUtils.equals(scheme, "http") && !TextUtils.equals(scheme, "https"));
        }
        return isLocalPage;
    }

    public void setPageName(String pageName) {
        mPageName = pageName;
    }

    public interface WxReloadListener {
        void onReload();
    }

    public interface WxRefreshListener {
        void onRefresh();
    }

    public class DefaultBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (IWXDebugProxy.ACTION_DEBUG_INSTANCE_REFRESH.equals(intent.getAction())) {
                if (mRefreshListener != null) {
                    mRefreshListener.onRefresh();
                }
            } else if (WXSDKEngine.JS_FRAMEWORK_RELOAD.equals(intent.getAction())) {
                if (mReloadListener != null) {
                    mReloadListener.onReload();
                }
            }
        }
    }
}
