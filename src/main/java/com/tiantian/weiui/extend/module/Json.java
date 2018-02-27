package com.tiantian.weiui.extend.module;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

/**
 * 处理 JSON 没有返回 null
 */
public class Json {

    public static JSONObject parseObject(String string) {
        if (string == null) {
            return new JSONObject();
        }
        try {
            JSONObject tmp = JSONObject.parseObject(string);
            if (tmp == null)
                tmp = new JSONObject();
            return tmp;
        }catch (JSONException e) {
            return new JSONObject();
        }
    }

    public static JSONArray parseArray(String string) {
        if (string == null) {
            return new JSONArray();
        }
        try {
            JSONArray tmp = JSONArray.parseArray(string);
            if (tmp == null)
                tmp = new JSONArray();
            return tmp;
        }catch (JSONException e) {
            return new JSONArray();
        }
    }

    public static String getString(JSONObject json, String key) {
        if (json == null) {
            return "";
        }
        String var = json.getString(key);
        if (var == null) {
            return "";
        }
        return var;
    }

    public static String getString(String string, String key) {
        return getString(parseObject(string), key);
    }

    public static int getInt(JSONObject json, String key) {
        if (json == null) {
            return 0;
        }
        return json.getIntValue(key);
    }

    public static int getInt(String string, String key) {
        return getInt(parseObject(string), key);
    }

    public static long getLong(JSONObject json, String key) {
        if (json == null) {
            return 0;
        }
        return json.getLongValue(key);
    }

    public static long getLong(String string, String key) {
        return getLong(parseObject(string), key);
    }

    public static boolean getBoolean(JSONObject json, String key) {
        if (json == null) {
            return false;
        }
        return json.getBooleanValue(key);
    }

    public static boolean getBoolean(String string, String key) {
        return getBoolean(parseObject(string), key);
    }
}