package vip.kuaifan.weiui.umeng;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.taobao.weex.bridge.JSCallback;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.message.UHandler;
import com.umeng.message.entity.UMessage;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import vip.kuaifan.weiui.extend.module.weiuiCommon;
import vip.kuaifan.weiui.extend.module.weiuiMap;
import vip.kuaifan.weiui.ui.weiui;

/**
 * Created by WDM on 2018/3/27.
 */

public class weiui_umeng {

    private static final String TAG = "weiui_umeng";

    private static String umeng_token = "";

    private static List<notificationClickHandlerBean> mNotificationClickHandler = new ArrayList<>();

    public static void init(String key, String secret) {
        init(key, secret, null);
    }

    public static void init(String key, String secret, String channel) {
        //初始化
        UMConfigure.init(weiui.getApplication(), key, channel, UMConfigure.DEVICE_TYPE_PHONE, secret);
        UMConfigure.setLogEnabled(false);
        //注册统计
        MobclickAgent.setScenarioType(weiui.getApplication(), MobclickAgent.EScenarioType.E_UM_NORMAL);
        weiui.getApplication().registerActivityLifecycleCallbacks(mCallbacks);
        //注册推送
        PushAgent mPushAgent = PushAgent.getInstance(weiui.getApplication());
        mPushAgent.register(new IUmengRegisterCallback() {
            @Override
            public void onSuccess(String deviceToken) {
                umeng_token = deviceToken;
            }

            @Override
            public void onFailure(String s, String s1) {
                umeng_token = "";
            }
        });
        //打开通知动作
        mPushAgent.setNotificationClickHandler(new UHandler() {
            @Override
            public void handleMessage(Context context, UMessage uMessage) {
                try {
                    clickHandleMessage(uMessage);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //
                LinkedList<Activity> mLinkedList = weiui.getActivityList();
                Activity mActivity = mLinkedList.getLast();
                if (mActivity != null) {
                    Intent intent = new Intent(context, mActivity.getClass());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            }
        });
    }

    public static void addNotificationClickHandler(Context context, JSCallback callback) {
        mNotificationClickHandler.add(new notificationClickHandlerBean(context, callback));
    }

    public static String getToken() {
        return umeng_token;
    }

    private static class notificationClickHandlerBean {
        Context context;
        JSCallback callback;

        notificationClickHandlerBean(Context context, JSCallback callback) {
            this.context = context;
            this.callback = callback;
        }
    }

    private static void clickHandleMessage(UMessage uMessage) throws JSONException {
        mNotificationClickHandler = weiuiCommon.removeNull(mNotificationClickHandler);
        if (mNotificationClickHandler.size() == 0) {
            return;
        }
        LinkedList<Activity> mLinkedList = weiui.getActivityList();
        for (int i = 0; i < mNotificationClickHandler.size(); i++) {
            notificationClickHandlerBean mBean = mNotificationClickHandler.get(i);
            if (mBean != null) {
                boolean isCallBack = false;
                for (int j = 0; j < mLinkedList.size(); j++) {
                    if (mLinkedList.get(j).equals(mBean.context)) {
                        Map<String, Object> data = weiuiMap.jsonToMap(uMessage.getRaw());
                        data.put("status", "click");
                        mBean.callback.invokeAndKeepAlive(data);
                        isCallBack = true;
                        break;
                    }
                }
                if (!isCallBack) {
                    mNotificationClickHandler.set(i, null);
                }
            }
        }
    }

    private static ActivityLifecycleCallbacks mCallbacks = new ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {
            MobclickAgent.onResume(activity);
        }

        @Override
        public void onActivityPaused(Activity activity) {
            MobclickAgent.onPause(activity);
        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {

        }
    };

}
