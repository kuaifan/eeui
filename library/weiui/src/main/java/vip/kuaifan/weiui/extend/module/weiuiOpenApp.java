package vip.kuaifan.weiui.extend.module;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_SHORT;

@SuppressLint("ShowToast")
public class weiuiOpenApp {

    /**
     * 打开微信
     */
    public static void openWeChat(Context context) {
        try {
            Intent intent = new Intent();
            ComponentName cmp= new ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI");
            intent.setAction(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setComponent(cmp);
            context.startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(context, "无法跳转到微信，请手动启动微信！", LENGTH_SHORT);
        }
    }

    /**
     * 打开QQ
     */
    public static void openQQ(Context context) {
        try {
            Intent intent = new Intent();
            ComponentName cmp= new ComponentName("com.tencent.mobileqq", "com.tencent.mobileqq.activity.SplashActivity");
            intent.setAction(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setComponent(cmp);
            context.startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(context, "无法跳转到QQ，请手动启动手机QQ！", LENGTH_SHORT);
        }
    }

    /**
     * 打开支付宝扫一扫
     */
    public static void openAlipay(Context context) {
        try {
            Uri uri = Uri.parse("alipayqr://platformapi/startapp?saId=10000007");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            context.startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(context, "无法跳转到支付宝，请手动启动手机支付宝！", LENGTH_SHORT);
        }
    }

    /**
     * 打开京东
     */
    public static void openJd(Context context) {
        try {
            Intent intent = new Intent();
            ComponentName cmp= new ComponentName("com.jingdong.app.mall", "com.jingdong.app.mall.main.MainActivity");
            intent.setAction(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setComponent(cmp);
            context.startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(context, "无法跳转到京东APP，请手动启动京东APP！", LENGTH_SHORT);
        }
    }
}
