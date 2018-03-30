package vip.kuaifan.weiui.extend.module;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.bridge.JSCallback;

import java.util.HashMap;
import java.util.Map;


public class weiuiAlertDialog {


    public static void alert(Context context, Object object, JSCallback callback) {
        JSONObject json = weiuiJson.parseObject(object);
        if (json.size() == 0) {
            json.put("message", object);
        }
        //
        JSONObject newJson = new JSONObject();
        newJson.put("title", json.get("title"));
        newJson.put("message", json.get("message"));
        newJson.put("buttons", "[{title:'" + weiuiJson.getString(json, "button", "确定") + "'}]");
        newJson.put("cancelable", json.get("cancelable"));
        Builder(context, newJson, new JSCallback() {
            @Override
            public void invoke(Object data) {
                Map<String, Object> res = weiuiMap.objectToMap(data);
                if (weiuiParse.parseStr(res.get("status")).equals("click")) {
                    if (callback != null) {
                        callback.invoke(null);
                    }
                }
            }

            @Override
            public void invokeAndKeepAlive(Object data) {

            }
        });
    }

    public static void confirm(Context context, Object object, JSCallback callback) {
        JSONObject json = weiuiJson.parseObject(object);
        if (json.size() == 0) {
            json.put("message", object);
        }
        //
        JSONArray buttons = weiuiJson.parseArray(json.get("buttons"));
        if (buttons.size() == 0) {
            buttons = weiuiJson.parseArray("[{title:'取消'}, {title:'确定'}]");
        }
        JSONObject newJson = new JSONObject();
        newJson.put("title", json.get("title"));
        newJson.put("message", json.get("message"));
        newJson.put("buttons", buttons);
        newJson.put("cancelable", json.get("cancelable"));
        Builder(context, newJson, callback);
    }

    private static void Builder(Context context, Object object, JSCallback callback) {
        JSONObject json = weiuiJson.parseObject(object);
        if (json.size() == 0) {
            json.put("message", object);
        }
        //
        String title = weiuiJson.getString(json, "title");
        String message = weiuiJson.getString(json, "message");
        JSONArray buttons = weiuiJson.parseArray(json.get("buttons"));
        boolean cancelable = weiuiJson.getBoolean(json, "cancelable", true);
        //
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(context);
        if (!title.isEmpty()) {
            localBuilder.setTitle(title);
        }
        if (!message.isEmpty()) {
            localBuilder.setMessage(message);
        }
        int j = 0;
        for (int i = 0; i < buttons.size(); i++) {
            JSONObject temp = weiuiJson.parseObject(buttons.get(i));
            if (temp.size() == 0) {
                temp.put("title", buttons.get(i));
            }
            String btnTitle = weiuiJson.getString(temp, "title");
            String btnType = weiuiJson.getString(temp, "type");
            if (!btnTitle.isEmpty()) {
                if (btnType.isEmpty()) {
                    switch (btnTitle.toLowerCase()) {
                        case "否":
                        case "取消":
                        case "no":
                        case "cancel":
                            btnType = "negative";
                            break;
                        default:
                            btnType = "positive";
                            break;
                    }
                }
                int position = i;
                DialogInterface.OnClickListener mOnClickListener = (paramAnonymousDialogInterface, paramAnonymousInt) -> {
                    if (callback != null) {
                        Map<String, Object> data = new HashMap<>();
                        data.put("status", "click");
                        data.put("position", position);
                        data.put("title", btnTitle);
                        callback.invoke(data);
                    }
                };
                if (j <= 2) {
                    if (j == 2) {
                        btnType = "neutral";
                    }
                    switch (btnType.toLowerCase()) {
                        case "positive":
                            localBuilder.setPositiveButton(btnTitle, mOnClickListener);
                            break;

                        case "negative":
                            localBuilder.setNegativeButton(btnTitle, mOnClickListener);
                            break;

                        case "neutral":
                            localBuilder.setNeutralButton(btnTitle, mOnClickListener);
                            break;
                    }
                }
                j++;
            }
        }
        //
        AlertDialog dialog = localBuilder.setCancelable(cancelable).create();
        if (callback != null) {
            dialog.setOnShowListener(dialog12 -> {
                Map<String, Object> data = new HashMap<>();
                data.put("status", "show");
                data.put("position", -1);
                data.put("title", "");
                callback.invokeAndKeepAlive(data);
            });
            dialog.setOnCancelListener(dialog1 -> {
                Map<String, Object> data = new HashMap<>();
                data.put("status", "cancel");
                data.put("position", -1);
                data.put("title", "");
                callback.invoke(data);
            });
        }
        dialog.show();
        dialog.getButton(dialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#8C8C8C"));
    }
}