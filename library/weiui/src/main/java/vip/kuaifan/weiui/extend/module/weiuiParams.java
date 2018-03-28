package vip.kuaifan.weiui.extend.module;

import android.annotation.SuppressLint;

import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.utils.WXUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import vip.kuaifan.weiui.extend.module.utilcode.util.Utils;

/**
 * Created by WDM on 2018/3/14.
 */

public class weiuiParams {

    /**
     * 获取参数组指定位数
     * @param i
     * @param var
     * @return
     */
    public static Object getParameter(int i, Object... var) {
        if (var == null) {
            return null;
        }
        if (i < 0) {
            i = var.length + i;
        }
        if (var.length >= i) {
            return var[i];
        }
        return null;
    }

    public static long getParamLong(int i, Object... var) {
        return weiuiParse.parseLong(getParamString(i, var));
    }

    public static int getParamInt(int i, Object... var) {
        return WXUtils.getNumberInt(getParameter(i, var), 0);
    }

    public static String getParamString(int i, Object... var) {
        return WXUtils.getString(getParameter(i, var), "");
    }

    public static boolean getParamBoolean(int i, Object... var) {
        return getParamBoolean(i, false, var);
    }

    public static boolean getParamBoolean(int i, boolean def, Object... var) {
        return WXUtils.getBoolean(getParameter(i, var), def);
    }

    public static String getParamPackageName(int i, Object... var) {
        String packageName = getParamString(i, var);
        if (packageName.isEmpty()) {
            packageName = Utils.getApp().getPackageName();
        }
        return packageName;
    }

    public static JSCallback getParamJSCallback(int i, Object... var) {
        JSCallback callback = null;
        if (weiuiParams.getParameter(i, var) instanceof JSCallback) {
            callback = (JSCallback) weiuiParams.getParameter(i, var);
        }
        return callback;
    }

    @SuppressLint("SimpleDateFormat")
    public static DateFormat getParamDateFormat(int i, Object... var) {
        String pattern = getParamString(i, var);
        if (pattern.isEmpty()) {
            pattern = "yyyy-MM-dd HH:mm:ss";
        }
        return new SimpleDateFormat(pattern);
    }

}
