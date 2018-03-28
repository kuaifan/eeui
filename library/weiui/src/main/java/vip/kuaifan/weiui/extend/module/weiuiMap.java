package vip.kuaifan.weiui.extend.module;

import org.json.JSONException;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 处理 Map
 */
public class weiuiMap {

    @SuppressWarnings("unchecked")
    public static Map<String, Object> objectToMap(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Map) {
            return (Map<String, Object>) obj;
        }
        Map<String, Object> map = new HashMap<>();
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            try {
                map.put(field.getName(), field.get(obj));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    public static Map<String, Object> jsonToMap(org.json.JSONObject json) throws JSONException {
        Map<String, Object> resultMap = new HashMap<>();
        Iterator<String> iterator = json.keys();
        while (iterator.hasNext()) {
            String key = iterator.next();
            Object value = json.get(key);
            if (value instanceof org.json.JSONObject) {
                resultMap.put(key, jsonToMap((org.json.JSONObject) value));
            } else {
                resultMap.put(key, value);
            }
        }
        return resultMap;
    }
}