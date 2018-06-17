package vip.kuaifan.weiui.extend.module.utilcode;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;

import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.WXSDKInstance;

import vip.kuaifan.weiui.extend.module.utilcode.util.AppUtils;
import vip.kuaifan.weiui.extend.module.utilcode.util.DeviceUtils;
import vip.kuaifan.weiui.extend.module.utilcode.util.KeyboardUtils;
import vip.kuaifan.weiui.extend.module.utilcode.util.NetworkUtils;
import vip.kuaifan.weiui.extend.module.utilcode.util.PermissionUtils;
import vip.kuaifan.weiui.extend.module.utilcode.util.PhoneUtils;
import vip.kuaifan.weiui.extend.module.utilcode.util.ProcessUtils;
import vip.kuaifan.weiui.extend.module.utilcode.util.ScreenUtils;
import vip.kuaifan.weiui.extend.module.utilcode.util.TimeUtils;
import vip.kuaifan.weiui.extend.module.utilcode.util.ToastUtils;
import vip.kuaifan.weiui.extend.module.weiuiJson;
import vip.kuaifan.weiui.extend.module.weiuiParams;
import vip.kuaifan.weiui.extend.module.weiuiScreenUtils;

/**
 * Created by WDM on 2018/3/13.
 */

public class utilcodeModule {

    /**
     * App 相关
     * @param method
     * @param var
     * @return
     */
    public static Object AppUtils(String method, Object... var) {
        if (method == null) {
            return null;
        }
        switch (method) {
            case "uninstallApp":
                AppUtils.uninstallApp(weiuiParams.getParamPackageName(0, var));
                break;

            case "uninstallAppSilent":
                AppUtils.uninstallAppSilent(weiuiParams.getParamPackageName(0, var));
                break;

            case "isAppInstalled":
                return AppUtils.isAppInstalled(weiuiParams.getParamString(0, var));

            case "isAppRoot":
                return AppUtils.isAppRoot();

            case "launchApp":
                AppUtils.launchApp(weiuiParams.getParamString(0, var));
                break;

            case "exitApp":
                AppUtils.exitApp();
                break;

            case "getAppPackageName":
                return AppUtils.getAppPackageName();

            case "getAppName":
                return AppUtils.getAppName();

            case "getAppPath":
                return AppUtils.getAppPath();

            case "getAppVersionName":
                return AppUtils.getAppVersionName();

            case "getAppVersionCode":
                return AppUtils.getAppVersionCode();

            case "isAppSystem":
                return AppUtils.isAppSystem(weiuiParams.getParamPackageName(0, var));

            case "getAppSignatureSHA1":
                return AppUtils.getAppSignatureSHA1(weiuiParams.getParamPackageName(0, var));

            case "isAppForeground":
                return AppUtils.isAppForeground(weiuiParams.getParamPackageName(0, var));
        }
        return null;
    }

    /**
     * 设备相关
     * @param method
     * @return
     */
    @SuppressLint("MissingPermission")
    public static Object DeviceUtils(String method) {
        if (method == null) {
            return null;
        }
        switch (method) {
            case "isDeviceRooted":
                return DeviceUtils.isDeviceRooted();

            case "getSDKVersionCode":
                return DeviceUtils.getSDKVersionCode();

            case "getSDKVersionName":
                return DeviceUtils.getSDKVersionName();

            case "getAndroidID":
                return DeviceUtils.getAndroidID();

            case "getMacAddress":
                return DeviceUtils.getMacAddress();

            case "getManufacturer":
                return DeviceUtils.getManufacturer();

            case "getModel":
                return DeviceUtils.getModel();

            case "shutdown":
                DeviceUtils.shutdown();
                break;

            case "reboot":
                DeviceUtils.reboot();
                break;

            case "reboot2Recovery":
                DeviceUtils.reboot2Recovery();
                break;

            case "reboot2Bootloader":
                DeviceUtils.reboot2Bootloader();
                break;
        }
        return null;
    }

    /**
     * 键盘相关
     * @param activity
     * @param method
     * @return
     */
    public static Object KeyboardUtils(Activity activity, String method) {
        if (method == null) {
            return null;
        }
        switch (method) {
            case "showSoftInput":
                KeyboardUtils.showSoftInput(activity);
                break;

            case "hideSoftInput":
                KeyboardUtils.hideSoftInput(activity);
                break;

            case "toggleSoftInput":
                KeyboardUtils.toggleSoftInput();
                break;

            case "isSoftInputVisible":
                return KeyboardUtils.isSoftInputVisible(activity);
        }
        return null;
    }
    
    /**
     * 权限相关
     * @param method
     * @param var
     * @return
     */
    @SuppressLint("MissingPermission")
    public static Object NetworkUtils(String method, Object... var) {
        if (method == null) {
            return null;
        }
        switch (method) {
            case "openWirelessSettings":
                NetworkUtils.openWirelessSettings();
                break;

            case "isConnected":
                return NetworkUtils.isConnected();

            case "isAvailableByPing":
                return NetworkUtils.isAvailableByPing();

            case "getMobileDataEnabled":
                return NetworkUtils.getMobileDataEnabled();

            case "setMobileDataEnabled":
                NetworkUtils.setMobileDataEnabled(weiuiParams.getParamBoolean(0, var));
                break;

            case "isMobileData":
                return NetworkUtils.isMobileData();

            case "is4G":
                return NetworkUtils.is4G();

            case "getWifiEnabled":
                return NetworkUtils.getWifiEnabled();

            case "setWifiEnabled":
                NetworkUtils.setWifiEnabled(weiuiParams.getParamBoolean(0, var));
                break;

            case "isWifiConnected":
                return NetworkUtils.isWifiConnected();

            case "isWifiAvailable":
                return NetworkUtils.isWifiAvailable();

            case "getNetworkOperatorName":
                return NetworkUtils.getNetworkOperatorName();

            case "getNetworkType":
                return NetworkUtils.getNetworkType();

            case "getIPAddress":
                return NetworkUtils.getIPAddress(weiuiParams.getParamBoolean(0, true, var));

            case "getDomainAddress":
                return NetworkUtils.getDomainAddress(weiuiParams.getParamString(0, var));
        }
        return null;
    }

    /**
     * 权限相关
     * @param method
     * @param var
     * @return
     */
    public static Object PermissionUtils(String method, Object... var) {
        if (method == null) {
            return null;
        }
        switch (method) {
            case "getPermissions":
                return PermissionUtils.getPermissions();

            case "isGranted":
                return PermissionUtils.isGranted(weiuiParams.getParamString(0, var));

            case "launchAppDetailsSettings":
                PermissionUtils.launchAppDetailsSettings();
                break;
        }
        return null;
    }

    /**
     * 手机相关
     * @param method
     * @param var
     * @return
     */
    @SuppressLint("MissingPermission")
    public static Object PhoneUtils(String method, Object... var) {
        if (method == null) {
            return null;
        }
        switch (method) {
            case "isPhone":
                return PhoneUtils.isPhone();

            case "getDeviceId":
                return PhoneUtils.getDeviceId();

            case "getIMEI":
                return PhoneUtils.getIMEI();

            case "getMEID":
                return PhoneUtils.getMEID();

            case "getIMSI":
                return PhoneUtils.getIMSI();

            case "getPhoneType":
                return PhoneUtils.getPhoneType();

            case "isSimCardReady":
                return PhoneUtils.isSimCardReady();

            case "getSimOperatorName":
                return PhoneUtils.getSimOperatorName();

            case "getSimOperatorByMnc":
                return PhoneUtils.getSimOperatorByMnc();

            case "getPhoneStatus":
                return PhoneUtils.getPhoneStatus();

            case "dial":
                PhoneUtils.dial(weiuiParams.getParamString(0, var));
                break;

            case "call":
                PhoneUtils.call(weiuiParams.getParamString(0, var));
                break;

            case "sendSms":
                PhoneUtils.sendSms(weiuiParams.getParamString(0, var), weiuiParams.getParamString(1, var));
                break;

            case "sendSmsSilent":
                PhoneUtils.sendSmsSilent(weiuiParams.getParamString(0, var), weiuiParams.getParamString(1, var));
                break;
        }
        return null;
    }

    /**
     * 进程相关
     * @param method
     * @param var
     * @return
     */
    @SuppressLint("MissingPermission")
    public static Object ProcessUtils(String method, Object... var) {
        if (method == null) {
            return null;
        }
        switch (method) {
            case "getForegroundProcessName":
                return ProcessUtils.getForegroundProcessName();

            case "getAllBackgroundProcesses":
                return ProcessUtils.getAllBackgroundProcesses();

            case "killAllBackgroundProcesses":
                return ProcessUtils.killAllBackgroundProcesses();

            case "killBackgroundProcesses":
                return ProcessUtils.killBackgroundProcesses(weiuiParams.getParamString(0, var));
        }
        return null;
    }

    /**
     * 屏幕相关
     * @param method
     * @param var
     * @return
     */
    @SuppressLint("MissingPermission")
    public static Object ScreenUtils(Activity activity, String method, Object... var) {
        if (method == null) {
            return null;
        }
        switch (method) {
            case "getScreenWidth":
                return ScreenUtils.getScreenWidth();

            case "getScreenHeight":
                return ScreenUtils.getScreenHeight();

            case "getScreenDensity":
                return ScreenUtils.getScreenDensity();

            case "getScreenDensityDpi":
                return ScreenUtils.getScreenDensityDpi();

            case "setFullScreen":
                ScreenUtils.setFullScreen(activity);
                break;

            case "setLandscape":
                ScreenUtils.setLandscape(activity);
                break;

            case "setPortrait":
                ScreenUtils.setPortrait(activity);
                break;

            case "isLandscape":
                return ScreenUtils.isLandscape();

            case "isPortrait":
                return ScreenUtils.isPortrait();

            case "getScreenRotation":
                return ScreenUtils.getScreenRotation(activity);

            case "screenShot":
                return ScreenUtils.screenShot(activity);

            case "isScreenLock":
                return ScreenUtils.isScreenLock();

            case "setSleepDuration":
                ScreenUtils.setSleepDuration(weiuiParams.getParamInt(0, var));
                break;

            case "getSleepDuration":
                return ScreenUtils.getSleepDuration();

            case "isTablet":
                return ScreenUtils.isTablet();
        }
        return null;
    }

    /**
     * 时间相关
     * @param method
     * @param var
     * @return
     */
    public static Object TimeUtils(String method, Object... var) {
        if (method == null) {
            return null;
        }
        switch (method) {
            case "millis2String":
                return TimeUtils.millis2String(weiuiParams.getParamLong(0, var), weiuiParams.getParamDateFormat(1, var));

            case "string2Millis":
                return TimeUtils.string2Millis(weiuiParams.getParamString(0, var), weiuiParams.getParamDateFormat(1, var));

            case "getNowMills":
                return TimeUtils.getNowMills();

            case "getNowString":
                return TimeUtils.getNowString();

            case "getNowDate":
                return TimeUtils.getNowDate();

            case "isToday":
                return TimeUtils.isToday(weiuiParams.getParamString(0, var), weiuiParams.getParamDateFormat(1, var));

            case "isLeapYear":
                return TimeUtils.isLeapYear(weiuiParams.getParamString(0, var), weiuiParams.getParamDateFormat(1, var));

            case "getChineseWeek":
                return TimeUtils.getChineseWeek(weiuiParams.getParamString(0, var), weiuiParams.getParamDateFormat(1, var));

            case "getUSWeek":
                return TimeUtils.getUSWeek(weiuiParams.getParamString(0, var), weiuiParams.getParamDateFormat(1, var));

            case "getWeekIndex":
                return TimeUtils.getWeekIndex(weiuiParams.getParamString(0, var), weiuiParams.getParamDateFormat(1, var));

            case "getWeekOfMonth":
                return TimeUtils.getWeekOfMonth(weiuiParams.getParamString(0, var), weiuiParams.getParamDateFormat(1, var));

            case "getWeekOfYear":
                return TimeUtils.getWeekOfYear(weiuiParams.getParamString(0, var), weiuiParams.getParamDateFormat(1, var));

            case "getChineseZodiac":
                return TimeUtils.getChineseZodiac(weiuiParams.getParamString(0, var), weiuiParams.getParamDateFormat(1, var));

            case "getZodiac":
                return TimeUtils.getZodiac(weiuiParams.getParamString(0, var), weiuiParams.getParamDateFormat(1, var));
        }
        return null;
    }

    /**
     * 吐司(Toast)相关
     * @param mInstance
     * @param obj
     */
    public static void Toast(WXSDKInstance mInstance, String obj) {
        if (obj == null) {
            ToastClose();
            return;
        }
        //
        ToastUtils.setGravity(-1, -1, -1);
        ToastUtils.setMsgColor(0xFDFFFFFF);
        ToastUtils.setBgColor(0xE6000000);
        //
        JSONObject param = weiuiJson.parseObject(obj);
        String message = param.getString("message");
        String gravity = param.getString("gravity");
        String messageColor = param.getString("messageColor");
        String backgroundColor = param.getString("backgroundColor");
        Boolean isLong = param.getBooleanValue("long");
        if (message == null) {
            message = obj;
        }
        if (gravity != null) {
            int x = param.getIntValue("x");
            int y = param.getIntValue("y");
            if (x != 0) {
                x = weiuiScreenUtils.weexPx2dp(mInstance, x);
            }
            if (y != 0) {
                y = weiuiScreenUtils.weexPx2dp(mInstance, y);
            }
            switch (gravity.toLowerCase()) {
                case "top":
                    ToastUtils.setGravity(Gravity.TOP, x, y);
                    break;

                case "center":
                case "middle":
                    ToastUtils.setGravity(Gravity.CENTER, x, y);
                    break;
                case "bottom":
                    ToastUtils.setGravity(Gravity.BOTTOM, x, y);
                    break;
            }
        }
        if (messageColor != null) {
            ToastUtils.setMsgColor(Color.parseColor(messageColor));
        }
        if (backgroundColor != null) {
            ToastUtils.setBgColor(Color.parseColor(backgroundColor));
        }
        if (isLong) {
            ToastUtils.showLong(message);
        }else{
            ToastUtils.showShort(message);
        }
    }

    /**
     * 取消吐司(Toast)显示
     */
    public static void ToastClose() {
        ToastUtils.cancel();
    }
}
