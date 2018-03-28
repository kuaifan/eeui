package vip.kuaifan.weiui.extend.module;

import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.utils.WXUtils;

import vip.kuaifan.weiui.extend.module.utilcode.util.ScreenUtils;

/**
 * Created by WDM on 2018/3/13.
 */

public class weiuiScreenUtils {

    public static int weexPx2dp(WXSDKInstance mInstance, Object pxValue, int defaultValue) {
        float width;
        if (mInstance == null) {
            width = WXSDKManager.getInstanceViewPortWidth(null);
        }else{
            width = mInstance.getInstanceViewPortWidth();
        }
        return (int) (ScreenUtils.getScreenWidth() / width * WXUtils.getNumberInt(removePxString(pxValue), defaultValue));
    }

    public static int weexPx2dp(WXSDKInstance mInstance, Object pxValue) {
        return weexPx2dp(mInstance, pxValue, 0);
    }

    public static int weexDp2px(WXSDKInstance mInstance, Object dpValue) {
        float width;
        if (mInstance == null) {
            width = WXSDKManager.getInstanceViewPortWidth(null);
        }else{
            width = mInstance.getInstanceViewPortWidth();
        }
        return (int) (width / ScreenUtils.getScreenWidth() * WXUtils.getNumberInt(dpValue, 0));
    }

    /******************************************************************************************/
    /******************************************************************************************/
    /******************************************************************************************/

    private static String removePxString(Object pxValue) {
        String temp = WXUtils.getString(pxValue, null);
        if (temp != null && !temp.isEmpty()) {
            temp = temp.replace("px", "");
        }
        return temp;
    }
}
