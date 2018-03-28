package vip.kuaifan.weiui.extend.module.rxtools;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;

import com.taobao.weex.bridge.JSCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vip.kuaifan.weiui.extend.module.rxtools.tool.RxCameraTool;
import vip.kuaifan.weiui.extend.module.rxtools.tool.RxLocationTool;
import vip.kuaifan.weiui.extend.module.rxtools.tool.RxVibrateTool;
import vip.kuaifan.weiui.extend.module.weiuiParams;

/**
 * Created by WDM on 2018/3/14.
 */

public class rxtoolsModule {

    /**
     * 摄像机相关
     * @param method
     * @return
     */
    public static void RxCameraTool(String method) {
        if (method == null) {
            return;
        }
        switch (method) {
            case "openFlashLight":
                RxCameraTool.openFlashLight();
                break;

            case "closeFlashLight":
                RxCameraTool.closeFlashLight();
                break;
        }
    }

    /**
     * 定位相关
     * @param context
     * @param method
     * @param var
     * @return
     */
    public static Object RxLocationTool(Context context, String method, Object... var) {
        if (method == null) {
            return null;
        }
        switch (method) {
            case "isGpsEnabled":
                return RxLocationTool.isGpsEnabled(context);

            case "isLocationEnabled":
                return RxLocationTool.isLocationEnabled(context);

            case "openGpsSettings":
                RxLocationTool.openGpsSettings(context);
                break;

            case "registerLocation":
                return RxLocationTool.registerLocation(context,
                        weiuiParams.getParamLong(0, var),
                        weiuiParams.getParamLong(1, var),
                        mLocationChangeListener(weiuiParams.getParamJSCallback(2, var)));

            case "unRegisterLocation":
                RxLocationTool.unRegisterLocation();
                mLocationChangeListenerCancel();
                break;

            case "getAddress":
                return RxLocationTool.getAddress(context,
                        weiuiParams.getParamLong(0, var),
                        weiuiParams.getParamLong(1, var));

            case "getCountryName":
                return RxLocationTool.getCountryName(context,
                        weiuiParams.getParamLong(0, var),
                        weiuiParams.getParamLong(1, var));

            case "getLocality":
                return RxLocationTool.getLocality(context,
                        weiuiParams.getParamLong(0, var),
                        weiuiParams.getParamLong(1, var));

            case "getStreet":
                return RxLocationTool.getStreet(context,
                        weiuiParams.getParamLong(0, var),
                        weiuiParams.getParamLong(1, var));
        }
        return null;
    }

    /**
     * 震动相关
     * @param context
     * @param method
     * @param var
     */
    public static void RxVibrateTool(Context context, String method, Object... var) {
        if (method == null) {
            return;
        }
        switch (method) {
            case "vibrateOnce":
                RxVibrateTool.vibrateOnce(context, weiuiParams.getParamInt(0, var));
                break;

            case "vibrateStop":
                RxVibrateTool.vibrateStop();
                break;
        }
    }

    /************************************************************************************************************/
    /************************************************************************************************************/
    /************************************************************************************************************/


    private static List<JSCallback> mLocationChangeListenerList = new ArrayList<>();

    private static RxLocationTool.OnLocationChangeListener mLocationChangeListener(JSCallback callback) {
        if (callback != null) {
            mLocationChangeListenerList.add(callback);
        }
        return new RxLocationTool.OnLocationChangeListener() {
            @Override
            public void getLastKnownLocation(Location location) {
                if (callback != null) {
                    Map<String, Object> ret = new HashMap<>();
                    ret.put("status", "lastKnown");
                    ret.put("lat", location.getLatitude());
                    ret.put("long", location.getLongitude());
                    callback.invokeAndKeepAlive(ret);
                }
            }

            @Override
            public void onLocationChanged(Location location) {
                if (callback != null) {
                    Map<String, Object> ret = new HashMap<>();
                    ret.put("status", "changed");
                    ret.put("lat", location.getLatitude());
                    ret.put("long", location.getLongitude());
                    callback.invokeAndKeepAlive(ret);
                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }
        };
    }

    private static void mLocationChangeListenerCancel() {
        if (mLocationChangeListenerList.size() > 0) {
            for (int i = 0; i < mLocationChangeListenerList.size(); i++) {
                JSCallback callback = mLocationChangeListenerList.get(i);
                if (callback != null) {
                    Map<String, Object> ret = new HashMap<>();
                    ret.put("status", "unregister");
                    callback.invoke(ret);
                }
            }
            mLocationChangeListenerList = new ArrayList<>();
        }
    }
}
