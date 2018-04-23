package com.lljjcoder.weiui.library.citypickerview.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.text.TextUtils;
import android.util.Log;

/**
 * 封装第三方log打印
 * Created by liji on 2016/4/13.
 */
public class JLogUtils {
    
    //文件后缀名
    public static final String SUFFIX = ".java";
    
    //所在的类名
    private static String className;
    
    //
    private static String fileName;
    
    //所在的方法名
    private static String methodName;
    
    //所在行号
    private static String lineNumber;
    
    public static final int V = 0x1;
    
    public static final int D = 0x2;
    
    public static final int I = 0x3;
    
    public static final int W = 0x4;
    
    public static final int E = 0x5;
    
    public static final int A = 0x6;
    
    //打印json格式化数据
    public static final int JSON = 0x7;
    
    //是否打印输出log
    private static boolean IS_SHOW_LOG = true;
    
    public static final int JSON_INDENT = 4;
    
    /**
     * 栈信息，获取当前栈的信息
     */
    private static final int STACK_TRACE_INDEX = 3;
    
    //默认的空字符串打印内容
    public static final String NULL_MSG = "Log with null object";
    
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");
    
    //log TAG默认设置值
    private static String TAG = "com.liji";
    
    /**
     * 设置是否显示log
     *
     * @param SHOW_LOG
     */
    public static void setDebug(boolean SHOW_LOG) {
        IS_SHOW_LOG = SHOW_LOG;
    }
    
    /**
     * Log D级别
     *
     * @param logMsg
     */
    public static void D(String logMsg) {
        if (IS_SHOW_LOG) {
            printLog(D, null, logMsg);
        }
    }
    
    /**
     * Log D级别 可设置Tag
     *
     * @param Tag
     * @param logMsg
     */
    public static void D(String Tag, String logMsg) {
        if (IS_SHOW_LOG) {
            printLog(D, Tag, logMsg);
        }
    }
    
    /**
     * Log E级别
     *
     * @param errorMsg
     */
    public static void E(String errorMsg) {
        if (IS_SHOW_LOG) {
            printLog(E, null, errorMsg);
        }
    }
    
    /**
     * Log E级别
     *
     * @param exception
     */
    public static void E(Exception exception) {
        if (IS_SHOW_LOG) {
            exception.printStackTrace();
        }
    }
    
    /**
     * Log E级别 可设置Tag
     *
     * @param Tag
     * @param errorMsg
     */
    public static void E(String Tag, String errorMsg) {
        if (IS_SHOW_LOG) {
            printLog(E, Tag, errorMsg);
            
        }
    }
    
    /**
     * 格式化打印json
     *
     * @param jsonStr
     */
    public static void Json(String jsonStr) {
        if (IS_SHOW_LOG) {
            printLog(JSON, null, jsonStr);
        }
    }
    
    /**
     * 格式化打印json，可以设置Tag
     *
     * @param Tag
     * @param jsonStr
     */
    public static void Json(String Tag, String jsonStr) {
        if (IS_SHOW_LOG) {
            printLog(JSON, Tag, jsonStr);
            
        }
    }
    
    /**
     * 获取超链接内容，TAG，打印内容，头部显示信息
     *
     * @param tagStr
     * @param obj
     * @return
     */
    private static String[] wrapperContent(String tagStr, String obj) {
        
        //获取栈信息
        StackTraceElement[] sElements = new Throwable().getStackTrace();
        StackTraceElement targetElement = sElements[STACK_TRACE_INDEX];
        
        //完整路径类名：com.frame.android.MainActivity.java
        String className = targetElement.getClassName();
        
        String[] classNameInfo = className.split("\\.");
        
        //获取MainActivity.java
        if (classNameInfo.length > 0) {
            className = classNameInfo[classNameInfo.length - 1] + SUFFIX;
        }
        
        //当前方法名称
        String methodName = targetElement.getMethodName();
        
        //当前所在行数
        int lineNumber = targetElement.getLineNumber();
        
        if (lineNumber < 0) {
            lineNumber = 0;
        }
        
        //将当前方法名的第一个字母改成大写并返回完整方法名
        String methodNameShort = methodName.substring(0, 1).toUpperCase() + methodName.substring(1);
        
        //获取Tag ,没有设置Tag则使用当前类名，若当前类名为null则使用默认的Tag
        String tag = (tagStr == null ? className : tagStr);
        
        if (TextUtils.isEmpty(tag)) {
            tag = TAG;
        }
        
        //设置打印内容，若为空则显示默认的打印内容
        String msg = (obj == null) ? NULL_MSG : obj;
        
        //设置超链接
        String headString = "[ (" + className + ":" + lineNumber + ") # " + methodNameShort + " ] ";
        
        return new String[] { tag, msg, headString };
        
    }
    
    /**
     * 打印日志
     *
     * @param type
     * @param Tag
     * @param logMsg
     */
    private static void printLog(int type, String Tag, String logMsg) {
        
        String[] contents = wrapperContent(Tag, logMsg);
        String tag = contents[0];
        String msg = contents[1];
        String headString = contents[2];
        
        switch (type) {
            
            //打印基本的log信息
            case D:
            case A:
            case V:
            case I:
            case E:
            case W:
                printDefaultLog(type, tag, headString + msg);
                break;
            //打印json格式化数据
            case JSON:
                printJsonLog(TAG, msg, headString);
                break;
                
        }
        
    }
    
    /**
     * 打印格式化json字符串
     *
     * @param tag
     * @param msg
     */
    private static void printJsonLog(String tag, String msg, String headString) {
        String message;
        try {
            if (msg.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(msg);
                message = jsonObject.toString(JSON_INDENT);
            }
            else if (msg.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(msg);
                message = jsonArray.toString(JSON_INDENT);
            }
            else {
                message = msg;
            }
        }
        catch (JSONException e) {
            message = msg;
        }
        
        printLine(tag, true);
        message = headString + LINE_SEPARATOR + message;
        String[] lines = message.split(LINE_SEPARATOR);
        for (String line : lines) {
            Log.d(tag, "║ " + line);
        }
        printLine(tag, false);
    }
    
    /**
     * 调用系统api，打印log
     *
     * @param type
     * @param tag
     * @param logMsg
     */
    private static void printDefaultLog(int type, String tag, String logMsg) {
        switch (type) {
            case A:
                Log.wtf(tag, logMsg);
                break;
            case I:
                Log.i(tag, logMsg);
                break;
            case V:
                Log.v(tag, logMsg);
                break;
            case D:
                Log.d(tag, logMsg);
                break;
            case E:
                Log.e(tag, logMsg);
                break;
        }
        
    }
    
    public static void printLine(String tag, boolean isTop) {
        if (isTop) {
            Log.d(tag, "╔═══════════════════════════════════════════════════════════════════════════════════════");
        }
        else {
            Log.d(tag, "╚═══════════════════════════════════════════════════════════════════════════════════════");
        }
    }
    
}
