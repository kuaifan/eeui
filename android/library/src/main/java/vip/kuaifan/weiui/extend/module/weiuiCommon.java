package vip.kuaifan.weiui.extend.module;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import vip.kuaifan.weiui.extend.integration.fastjson.JSONObject;
import com.taobao.weex.bridge.JSCallback;

import vip.kuaifan.weiui.PageActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class weiuiCommon {

    private static Map<String, Object> variate = new HashMap<>();

    /**
     * 保存缓存信息
     *
     * @param context
     * @param index
     * @param key
     * @param data
     * @return
     */
    public static JSONObject setCaches(Context context, String index, String key, String data) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(index, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, data);
        editor.apply();
        return weiuiJson.parseObject(data);
    }

    /**
     * 获取缓存信息
     *
     * @param context
     * @param index
     * @param key
     * @return
     */
    public static JSONObject getCaches(Context context, String index, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(index, Context.MODE_PRIVATE);
        String data = sharedPreferences.getString(key, "");
        return weiuiJson.parseObject(data);
    }

    /**
     * 保存缓存信息
     *
     * @param context
     * @param index
     * @param key
     * @param data
     * @return
     */
    public static String setCachesString(Context context, String index, String key, String data) {
        return setCachesString(context, index, key, data, 0);
    }

    /**
     * 删除缓存信息
     *
     * @param context
     * @param index
     * @param key
     * @return
     */
    public static String removeCachesString(Context context, String index, String key) {
        return setCachesString(context, index, key, "", 0);
    }

    /**
     * 保存缓存信息
     *
     * @param context
     * @param index
     * @param key
     * @param data
     * @return
     */
    public static String setCachesString(Context context, String index, String key, String data, long expired) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(index, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, data);
        editor.putLong(key + "::expired", expired > 0 ? (expired + timeStamp()) : expired);
        editor.apply();
        return data;
    }

    /**
     * 获取缓存信息
     *
     * @param context
     * @param index
     * @param key
     * @return
     */
    public static String getCachesString(Context context, String index, String key, String defaultVal) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(index, Context.MODE_PRIVATE);
        long expired = sharedPreferences.getLong(key + "::expired", 0);
        if (expired > 0 && expired < timeStamp()) {
            return defaultVal;
        }
        return sharedPreferences.getString(key, defaultVal);
    }

    /**
     * 获取缓存信息
     *
     * @param context
     * @param index
     * @param key
     * @return
     */
    public static String getCachesString(Context context, String index, String key) {
        return getCachesString(context, index, key, "");
    }

    /**
     * 设置全局变量
     *
     * @param key
     * @param val
     */
    public static void setVariate(String key, Object val) {
        variate.put(key, val);
    }

    /**
     * 获取全局变量
     *
     * @param key
     * @return
     */
    public static Object getVariate(String key) {
        try {
            return variate.get(key);
        } catch (Exception e) {
            return null;
        }
    }

    public static String getVariateStr(String key) {
        return getVariateStr(key, "");
    }

    public static String getVariateStr(String key, String defaultVal) {
        Object val = getVariate(key);
        if (val == null) {
            return defaultVal;
        }
        return String.valueOf(val);
    }

    public static int getVariateInt(String key) {
        Object val = getVariate(key);
        if (val == null) {
            return 0;
        }
        String str = String.valueOf(val);
        if (!isNumeric(str)) {
            return 0;
        }else{
            return Integer.parseInt(str);
        }
    }

    public static long getVariateLong(String key) {
        Object val = getVariate(key);
        if (val == null) {
            return 0;
        }
        String str = String.valueOf(val);
        if (!isNumeric(str)) {
            return 0;
        }else{
            return Long.valueOf(str);
        }
    }

    /**
     * 时间戳转换成日期格式字符串
     *
     * @param seconds 精确到秒的字符串
     * @param format
     * @return
     */
    public static String formatDate(String seconds, String format) {
        if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
            return "";
        }
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds + "000")));
    }

    /**
     * 时间戳获取年月日（如果是今年只返回月日）
     * @param seconds
     * @return
     */
    public static String yyyyMMddMini(String seconds) {
        String yyyy = weiuiCommon.formatDate(seconds, "yyyy");
        String MMdd = weiuiCommon.formatDate(seconds, "MM-dd");
        if (yyyy.equals(weiuiCommon.formatDate(String.valueOf(weiuiCommon.timeStamp()), "yyyy"))) {
            return MMdd;
        }else{
            return yyyy + "-" + MMdd;
        }
    }

    /**
     * 日期格式字符串转换成时间戳
     *
     * @param date_str 字符串日期
     * @param format   如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String formatTimeStamp(String date_str, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(date_str).getTime() / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 取得当前时间戳（精确到秒）
     *
     * @return
     */
    public static long timeStamp() {
        long time = System.currentTimeMillis();
        return time / 1000;
    }

    /**
     * 取今天最后一秒的时间戳
     * @return
     */
    public static long getTodayEndTime() {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime().getTime() / 1000;
    }

    /**
     * 字符串是否数字
     */
    public static boolean isNumeric(String str) {
        if (str.trim().equals("")) {
            return false;
        }
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }

    /**
     * 字符串是否包含
     *
     * @param string
     * @param find
     * @return
     */
    public static boolean strExists(String string, String find) {
        return string != null && string.contains(find);
    }

    /**
     * 左边是否包含
     *
     * @param string
     * @param find
     * @return
     */
    public static boolean leftExists(String string, String find) {
        return string != null && string.substring(0, find.length()).equals(find);
    }

    /**
     * 右边是否包含
     *
     * @param string
     * @param find
     * @return
     */
    public static boolean rightExists(String string, String find) {
        return string != null && string.substring(string.length() - find.length()).equals(find);
    }

    /**
     * 获取中间字符串
     *
     * @param string
     * @param start
     * @param end
     * @return
     */
    public static String getMiddle(String string, String start, String end) {
        String text = string;
        if (start != null && !start.equals("") && weiuiCommon.leftExists(text, start)) {
            text = text.substring(text.length() - start.length());
        }
        if (end != null && !end.equals("") && weiuiCommon.rightExists(text, end)) {
            text = text.substring(0, end.length());
        }
        return text;
    }

    /**
     * 获取地址文件名称
     * @param url
     * @return
     */
    public static String getUrlName(String url) {
        Uri uri = Uri.parse(url);
        List<String> pathSegList = uri.getPathSegments();
        String name = "";
        for (String key : pathSegList) {
            if (!key.isEmpty()) name = key;
        }
        return name;
    }

    /**
     * 生成随机字符串
     *
     * @param length
     * @return
     */
    public static String randomString(int length) {
        String base = "ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678oOLl9gqVvUuI1";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * EditText获取焦点并显示软键盘
     * @param activity
     * @param editText
     */
    public static void showSoftInputFromWindow(Activity activity, EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        //
        InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) {
            return;
        }
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 关闭键盘
     * @param activity
     * @param editText
     */
    public static void closeInputMethod(Activity activity, EditText editText) {
        editText.clearFocus();
        //
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) {
            return;
        }
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(editText.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 动态设置ListView高度
     * @param listView
     */
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            View listItem = listAdapter.getView(i,null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.invalidate();
    }

    /**
     * 获取状态栏高度
     *
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        return weiuiStatusBarUtils.getStatusBarHeight(context);
    }

    /**
     * 获取虚拟键盘高度
     *
     * @param context
     * @return
     */
    public static int getNavigationBarHeight(Context context) {
        return weiuiStatusBarUtils.getNavigationBarHeight(context);
    }

    /**
     * 设置view宽高
     *
     * @param view
     * @param width
     * @param height
     */
    public static void setViewWidthHeight(View view, int width, int height) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (width > -1) {
            params.width = width;
        }
        if (height > -1) {
            params.height = height;
        }
        view.setLayoutParams(params);
    }

    /**
     * 设置 Margins
     *
     * @param v
     * @param l
     * @param t
     * @param r
     * @param b
     */
    public static void setMargins(View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }

    /**
     * 等比计算高度
     *
     * @param newWidth
     * @param oldWidth
     * @param oldHeight
     * @return
     */
    public static double scaleHeight(double newWidth, double oldWidth, double oldHeight) {
        return newWidth / oldWidth * oldHeight;
    }

    public static String NS(String str, String str2) {
        if (str == null || (str + "").equals("null")) {
            str = str2;
        }
        if (str == null || (str + "").equals("null")) {
            str = "";
        }
        return str.trim();
    }

    public static String NS(String str) {
        return NS(str, "");
    }

    public static String formatNum(double i) {
        DecimalFormat decimalFormat= new DecimalFormat(".0");
        String str = String.valueOf(i);
        String unit = "";
        if (i > 100000000) {
            str = decimalFormat.format((i / 100000000));
            unit = "亿";
        }else if (i > 10000000) {
            str = decimalFormat.format((i / 10000000));
            unit = "千万";
        }else if (i > 1000000) {
            str = decimalFormat.format((i / 1000000));
            unit = "百万";
        }else if (i > 10000) {
            str = decimalFormat.format((i / 10000));
            unit = "万";
        }
        if (rightExists(str, ".0")) {
            str = str.substring(0, str.length() - 2);
        }
        return str + unit;
    }

    /**
     * 保存图片
     * @param context
     * @param bmp
     */
    public static void saveImageToGallery(Context context, Bitmap bmp) {
        // 首先保存图片
        File file = Environment.getExternalStorageDirectory();
        File appDir = new File(file, "DailyVideo");
        if (!appDir.exists()) {
            appDir.mkdirs();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File currentFile = new File(appDir, fileName);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(currentFile);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(new File(currentFile.getPath()))));
    }

    /**
     * 大陆号码或香港号码均可
     */
    public static boolean isPhoneLegal(String str)throws PatternSyntaxException {
        return isChinaPhoneLegal(str) || isHKPhoneLegal(str);
    }

    /**
     * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数
     * 此方法中前三位格式有：
     * 13+任意数
     * 15+除4的任意数
     * 18+除1和4的任意数
     * 17+除9的任意数
     * 147
     */
    public static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {
        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 香港手机号码8位数，5|6|8|9开头+7位任意数
     */
    public static boolean isHKPhoneLegal(String str)throws PatternSyntaxException {
        String regExp = "^(5|6|8|9)\\d{7}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 获取本地软件版本号
     * @param ctx
     * @return
     */
    public static int getLocalVersion(Context ctx) {
        int localVersion = 0;
        try {
            PackageInfo packageInfo = ctx.getApplicationContext().getPackageManager().getPackageInfo(ctx.getPackageName(), 0);
            localVersion = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }

    /**
     * 获取本地软件版本号名称
     * @param ctx
     * @return
     */
    public static String getLocalVersionName(Context ctx) {
        String localVersion = "";
        try {
            PackageInfo packageInfo = ctx.getApplicationContext().getPackageManager().getPackageInfo(ctx.getPackageName(), 0);
            localVersion = packageInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return localVersion;
    }

    /**
     * 比较版本号的大小,前者大则返回一个正数,后者大返回一个负数,相等则返回0
     *
     * @param version1
     * @param version2
     */
    public static int compareVersion(String version1, String version2) throws Exception {
        if (version1 == null || version2 == null) {
            throw new Exception("compareVersion error:illegal params.");
        }
        String[] versionArray1 = version1.split("\\.");
        String[] versionArray2 = version2.split("\\.");
        int idx = 0;
        int minLength = Math.min(versionArray1.length, versionArray2.length);
        int diff = 0;
        while (idx < minLength
                && (diff = versionArray1[idx].length() - versionArray2[idx].length()) == 0
                && (diff = versionArray1[idx].compareTo(versionArray2[idx])) == 0) {
            ++idx;
        }
        diff = (diff != 0) ? diff : versionArray1.length - versionArray2.length;
        return diff;
    }

    /**
     * 获取activity上所有的控件
     * @param view
     * @return
     */
    public static List<View> getAllChildViews(View view) {
        List<View> allchildren = new ArrayList<>();
        if (view instanceof ViewGroup) {
            ViewGroup vp = (ViewGroup) view;
            for (int i = 0; i < vp.getChildCount(); i++) {
                View viewchild = vp.getChildAt(i);
                allchildren.add(viewchild);
                allchildren.addAll(getAllChildViews(viewchild));
            }
        }
        return allchildren;
    }



    /**
     * 字符串MD5加密
     * @param string
     * @return
     */
    public static String md5(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            StringBuilder result = new StringBuilder();
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result.append(temp);
            }
            return result.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 秒转化为天小时分秒字符串
     *
     * @param seconds
     * @return String
     */
    public static String formatSeconds(long seconds) {
        String timeStr = seconds + "秒";
        if (seconds > 60) {
            long second = seconds % 60;
            long min = seconds / 60;
            timeStr = min + "分" + second + "秒";
            if (min > 60) {
                min = (seconds / 60) % 60;
                long hour = (seconds / 60) / 60;
                //timeStr = hour + "时" + min + "分" + second + "秒";
                timeStr = hour + "时" + min + "分";
                if (hour > 24) {
                    hour = ((seconds / 60) / 60) % 24;
                    long day = (((seconds / 60) / 60) / 24);
                    //timeStr = day + "天" + hour + "时" + min + "分" + second + "秒";
                    timeStr = day + "天" + hour + "时" + min + "分";
                }
            }
        }
        return timeStr;
    }

    /**
     * 获取IMEI
     * @param ctx
     * @return
     */
    @SuppressLint({"MissingPermission", "NewApi", "HardwareIds"})
    public static String getImei(Context ctx) {
        TelephonyManager tm = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
        String imei;
        try {
            assert tm != null;
            imei = tm.getDeviceId();
        }catch (Exception e) {
            try {
                imei = tm.getImei();
            }catch (Exception ee) {
                imei = "";
            }
        }
        return NS(imei);
    }

    /**
     * 跨域异步请求 AJAX
     * @param context
     * @param json
     * @param callback
     */
    public static void ajax(Context context, JSONObject json, JSCallback callback) {
        if (json == null || json.getString("url") == null) {
            return;
        }
        //
        String url = weiuiJson.getString(json, "url", "");
        String name = weiuiJson.getString(json, "name", "");
        String method = weiuiJson.getString(json, "method", "get").toLowerCase();
        String dataType = weiuiJson.getString(json, "dataType", "json").toLowerCase();
        int timeout = weiuiJson.getInt(json, "timeout", 15000);
        JSONObject headers = weiuiJson.parseObject(json.getString("headers"));
        JSONObject data = weiuiJson.parseObject(json.getString("data"));
        JSONObject files = weiuiJson.parseObject(json.getString("files"));
        //
        if (name.isEmpty()) {
            if (context instanceof PageActivity) {
                name = ((PageActivity) context).getPageInfo().getPageName();
            }else{
                name = weiuiCommon.randomString(8);
            }
        }
        //
        Map<String, Object> mData = new HashMap<>();
        mData.put("setting:timeout", timeout);
        if (headers.size() > 0) {
            for (Map.Entry<String, Object> entry : headers.entrySet()) {
                mData.put("header:" + entry.getKey(), entry.getValue());
            }
        }
        if (data.size() > 0) {
            for (Map.Entry<String, Object> entry : data.entrySet()) {
                mData.put(entry.getKey(), entry.getValue());
            }
        }
        if (files.size() > 0) {
            for (Map.Entry<String, Object> entry : headers.entrySet()) {
                mData.put("file:" + entry.getKey(), entry.getValue());
            }
        }
        //
        weiuiIhttp.resultCall mResultCall = new weiuiIhttp.resultCall() {
            @Override
            public void success(String data) {
                if (callback != null) {
                    Map<String, Object> ret = new HashMap<>();
                    ret.put("status", "success");
                    if (dataType.equals("json")) {
                        ret.put("result", weiuiJson.parseObject(data));
                    }else{
                        ret.put("result", data);
                    }
                    callback.invokeAndKeepAlive(ret);
                }
            }

            @Override
            public void error(String error) {
                if (callback != null) {
                    Map<String, Object> ret = new HashMap<>();
                    ret.put("status", "error");
                    ret.put("result", error);
                    callback.invokeAndKeepAlive(ret);
                }
            }

            @Override
            public void complete() {
                if (callback != null) {
                    Map<String, Object> ret = new HashMap<>();
                    ret.put("status", "complete");
                    ret.put("result", null);
                    callback.invoke(ret);
                }
            }
        };
        //
        if (callback != null) {
            JSONObject readyData = new JSONObject();
            readyData.put("name", name);
            readyData.put("url", url);
            Map<String, Object> ret = new HashMap<>();
            ret.put("status", "ready");
            ret.put("result", readyData);
            callback.invokeAndKeepAlive(ret);
        }
        //
        if (method.equals("post")) {
            weiuiIhttp.post(name, url, mData, mResultCall);
        }else{
            weiuiIhttp.get(name, url, mData, mResultCall);
        }
    }

    /**
     * 取消跨域异步请求
     * @param name
     */
    public static void ajaxCancel(String name) {
        if (name == null || name.isEmpty()) {
            weiuiIhttp.cancel();
        }else{
            weiuiIhttp.cancel(name);
        }
    }
}