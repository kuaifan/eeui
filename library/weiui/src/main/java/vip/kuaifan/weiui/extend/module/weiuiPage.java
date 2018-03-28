package vip.kuaifan.weiui.extend.module;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import vip.kuaifan.weiui.PageActivity;
import vip.kuaifan.weiui.extend.bean.PageBean;

/**
 * Created by WDM on 2018/3/25.
 */

public class weiuiPage {

    private static Map<String, PageBean> mPageBean = new HashMap<>();

    public static void setPageBean(String key, PageBean var) {
        mPageBean.put(key, var);
    }

    public static PageBean getPageBean(String key) {
        return mPageBean.get(key);
    }

    public static void removePageBean(String key) {
        if (key != null) {
            mPageBean.remove(key);
        }
    }

    public static void openWin(Context context, PageBean mBean) {
        if (mBean == null) {
            return;
        }
        if (mBean.getPageName() == null || mPageBean.get(mBean.getPageName()) != null) {
            mBean.setPageName("open_" + weiuiCommon.randomString(8));
        }
        mPageBean.put(mBean.getPageName(), mBean);
        //
        if (mBean.getCallback() != null) {
            Map<String, Object> ret = new HashMap<>();
            ret.put("pageName", mBean.getPageName());
            ret.put("status", "ready");
            mBean.getCallback().invokeAndKeepAlive(ret);
        }
        //
        Intent intent = new Intent(context, PageActivity.class);
        intent.putExtra("name", mBean.getPageName());
        context.startActivity(intent);
    }

    public static PageBean getWinInfo(String name) {
        if (name == null) {
            return null;
        }
        PageBean mBean = getPageBean(name);
        if (mBean == null) {
            return null;
        }
        Activity activity = mBean.getActivity();
        if (activity == null) {
            return null;
        }
        if (activity instanceof PageActivity) {
            return ((PageActivity) activity).getPageInfo();
        }
        return null;
    }

    public static void reloadWin(String name) {
        if (name == null) {
            return;
        }
        PageBean mBean = getPageBean(name);
        if (mBean == null) {
            return;
        }
        Activity activity = mBean.getActivity();
        if (activity == null) {
            return;
        }
        if (activity instanceof PageActivity) {
            ((PageActivity) activity).reload();
        }
    }

    public static void closeWin(String name) {
        if (name == null) {
            return;
        }
        PageBean mBean = getPageBean(name);
        if (mBean == null) {
            return;
        }
        Activity activity = mBean.getActivity();
        if (activity == null) {
            return;
        }
        activity.finish();
    }

    public static String rewriteUrl(Context context, String url) {
        if (url == null || url.startsWith("http") || url.startsWith("ftp://")) {
            return url;
        }
        if (context instanceof PageActivity) {
            PageBean info = ((PageActivity) context).getPageInfo();
            if (info != null) {
                try {
                    URL tmp = new URL(info.getUrl());
                    String newUrl = tmp.getProtocol() + "://" + tmp.getHost();
                    newUrl+= (tmp.getPort() > -1 && tmp.getPort() != 80) ? (":" + tmp.getPort()) : "";
                    if (url.startsWith("/")) {
                        return newUrl + url;
                    }else{
                        String path = "/";
                        int lastIndex = tmp.getPath().lastIndexOf("/");
                        if (lastIndex > -1){
                            path = tmp.getPath().substring(0, lastIndex + 1);
                        }
                        return newUrl + path + url;
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        }
        return url;
    }
}
