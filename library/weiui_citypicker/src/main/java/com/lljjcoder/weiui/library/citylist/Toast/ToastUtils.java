package com.lljjcoder.weiui.library.citylist.Toast;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

/**
 * author：admin on 2016/5/4 10:49
 * email：fangkaijin@gmail.com
 */
public class ToastUtils {

    private static AlarmDailog alarmDialog;

    public static
    void showShortToast (Context context, String showMsg ) {
        if ( null != alarmDialog ) {
            alarmDialog = null;
        }
        alarmDialog = new AlarmDailog(context);
        alarmDialog.setShowText(showMsg);
        alarmDialog.setDuration(Toast.LENGTH_SHORT);
        alarmDialog.show();

    }

    public static void showLongToast(Context context, String showMsg)
    {
        if (null != alarmDialog)
        {
            alarmDialog = null;
        }
        alarmDialog = new AlarmDailog(context);
        alarmDialog.setShowText(showMsg);
        alarmDialog.show();
    }

    public static void showMomentToast(final Activity activity, final Context context, final String showMsg)
    {
        activity.runOnUiThread(new Runnable() {
            public void run() {
                if (null == alarmDialog)
                {
                    alarmDialog = new AlarmDailog(context);
                    alarmDialog.setShowText(showMsg);
                    alarmDialog.setDuration(Toast.LENGTH_SHORT);
                    alarmDialog.show();
                }
                else
                {
                    alarmDialog.setShowText(showMsg);
                    alarmDialog.show();
                }
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        if(null != alarmDialog)
                        {
                            alarmDialog.cancel();
                        }
                    }
                }, 2000);
            }
        });
    }
}
