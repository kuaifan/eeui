package vip.kuaifan.weiui.extend.module;

import vip.kuaifan.weiui.extend.integration.fastjson.JSONArray;
import vip.kuaifan.weiui.extend.integration.fastjson.JSONObject;

/**
 * 处理 JSON
 */
public class weiuiJson {

    public static JSONObject parseObject(Object string) {
        if (string == null) {
            return new JSONObject();
        }
        if (string instanceof JSONObject) {
            return (JSONObject) string;
        }
        try {
            JSONObject tmp = JSONObject.parseObject(String.valueOf(string));
            if (tmp == null)
                tmp = new JSONObject();
            return tmp;
        }catch (Exception e) {
            return new JSONObject();
        }
    }

    public static JSONArray parseArray(Object string) {
        if (string == null) {
            return new JSONArray();
        }
        if (string instanceof JSONArray) {
            return (JSONArray) string;
        }
        try {
            JSONArray tmp = JSONArray.parseArray(String.valueOf(string));
            if (tmp == null)
                tmp = new JSONArray();
            return tmp;
        }catch (Exception e) {
            return new JSONArray();
        }
    }

    /************************************************************/

    public static String getString(JSONObject json, String key, String defaultVal) {
        if (json == null) {
            return defaultVal;
        }
        String var = json.getString(key);
        if (var == null) {
            return defaultVal;
        }
        return var;
    }

    public static String getString(JSONObject json, String key) {
        return getString(json, key, "");
    }

    public static String getString(String string, String key) {
        return getString(parseObject(string), key);
    }

    /************************************************************/

    public static float getFloat(JSONObject json, String key, float defaultVal) {
        if (json == null) {
            return defaultVal;
        }
        Float var = json.getFloat(key);
        if (var == null) {
            return defaultVal;
        }
        return var;
    }

    public static float getFloat(JSONObject json, String key) {
        return getFloat(json, key, 0f);
    }

    public static float getFloat(String string, String key) {
        return getFloat(parseObject(string), key);
    }

    /************************************************************/

    public static int getInt(JSONObject json, String key, int defaultVal) {
        if (json == null) {
            return defaultVal;
        }
        Integer var = json.getInteger(key);
        if (var == null) {
            return defaultVal;
        }
        return var;
    }

    public static int getInt(JSONObject json, String key) {
        return getInt(json, key, 0);
    }

    public static int getInt(String string, String key) {
        return getInt(parseObject(string), key);
    }

    /************************************************************/

    public static long getLong(JSONObject json, String key, long defaultVal) {
        if (json == null) {
            return defaultVal;
        }
        Long var = json.getLong(key);
        if (var == null) {
            return defaultVal;
        }
        return var;
    }

    public static long getLong(JSONObject json, String key) {
        return getLong(json, key, 0);
    }

    public static long getLong(String string, String key) {
        return getLong(parseObject(string), key);
    }

    /************************************************************/

    public static boolean getBoolean(JSONObject json, String key, boolean defaultVal) {
        if (json == null) {
            return defaultVal;
        }
        Boolean var = json.getBoolean(key);
        if (var == null) {
            return defaultVal;
        }
        return var;
    }

    public static boolean getBoolean(JSONObject json, String key) {
        return getBoolean(json, key, false);
    }

    public static boolean getBoolean(String string, String key) {
        return getBoolean(parseObject(string), key);
    }
}