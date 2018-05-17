package vip.kuaifan.weiui.extend.module;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Handler;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Selection;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.bridge.JSCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vip.kuaifan.weiui.R;
import vip.kuaifan.weiui.extend.integration.swipebacklayout.BGAKeyboardUtil;


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

    public static void input(Context context, Object object, JSCallback callback) {
        JSONObject json = weiuiJson.parseObject(object);
        if (json.size() == 0) {
            json.put("message", object);
        }
        //
        JSONArray inputs = weiuiJson.parseArray(json.get("inputs"));
        if (inputs.size() == 0) {
            JSONObject input = weiuiJson.parseObject(json.get("input"));
            if (input.size() > 0) {
                inputs.add(input);
            }else{
                inputs = weiuiJson.parseArray("[{type:'text'}]");
            }
        }
        JSONArray buttons = weiuiJson.parseArray(json.get("buttons"));
        if (buttons.size() == 0) {
            buttons = weiuiJson.parseArray("[{title:'取消'}, {title:'确定'}]");
        }
        JSONObject newJson = new JSONObject();
        newJson.put("title", json.get("title"));
        newJson.put("message", json.get("message"));
        newJson.put("inputs", inputs);
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
        JSONArray inputs = weiuiJson.parseArray(json.get("inputs"));
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
        //
        List<EditText> editTextList = new ArrayList<>();
        if (inputs.size() > 0) {
            View mView = View.inflate(context, R.layout.dialog_input, null);
            LinearLayout mInput = mView.findViewById(R.id.l_input);
            LinearLayout.LayoutParams mLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            mLayoutParams.setMargins(0, 12, 0, 0);
            for (int i = 0; i < inputs.size(); i++) {
                JSONObject temp = weiuiJson.parseObject(inputs.get(i));
                if (temp.size() == 0) {
                    temp.put("type", inputs.get(i));
                }
                //
                boolean autoFocus = false;
                EditText mEditText = new EditText(context);
                for (Map.Entry<String, Object> entry : temp.entrySet()) {
                    switch (weiuiCommon.camelCaseName(entry.getKey())) {
                        case "type":
                            switch (weiuiParse.parseStr(entry.getValue())) {
                                case "datetime":
                                    mEditText.setInputType(InputType.TYPE_DATETIME_VARIATION_NORMAL);
                                    break;
                                case "date":
                                    mEditText.setInputType(InputType.TYPE_DATETIME_VARIATION_DATE);
                                    break;
                                case "time":
                                    mEditText.setInputType(InputType.TYPE_DATETIME_VARIATION_TIME);
                                    break;
                                case "email":
                                    mEditText.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                                    break;
                                case "password":
                                    mEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                                    break;
                                case "passnumber":
                                    mEditText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                                    break;
                                case "tel":
                                    mEditText.setInputType(InputType.TYPE_CLASS_PHONE);
                                    break;
                                case "url":
                                    mEditText.setInputType(InputType.TYPE_TEXT_VARIATION_URI);
                                    break;
                                case "number":
                                    mEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
                                    break;
                            }
                            break;

                        case "value":
                            mEditText.setText(weiuiParse.parseStr(entry.getValue()));
                            break;

                        case "maxLength":
                            mEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(weiuiParse.parseInt(entry.getValue()))});
                            break;

                        case "placeholder":
                            mEditText.setHint(weiuiParse.parseStr(entry.getValue()));
                            break;

                        case "singleLine":
                            mEditText.setSingleLine(weiuiParse.parseBool(entry.getValue()));
                            break;

                        case "autoFocus":
                            autoFocus = true;
                            break;

                        case "textSize":
                            mEditText.setTextSize(weiuiParse.parseFloat(entry.getValue()));
                            break;

                        case "textColor":
                            mEditText.setTextColor(Color.parseColor(weiuiParse.parseStr(entry.getValue())));
                            break;

                        case "backgroundColor":
                            mEditText.setBackgroundColor(Color.parseColor(weiuiParse.parseStr(entry.getValue())));
                            break;

                        case "ems":
                            mEditText.setEms(weiuiParse.parseInt(entry.getValue()));
                            break;

                        case "maxEms":
                            mEditText.setMaxEms(weiuiParse.parseInt(entry.getValue()));
                            break;

                        case "lines":
                            mEditText.setLines(weiuiParse.parseInt(entry.getValue()));
                            break;

                        case "maxLines":
                            mEditText.setMaxLines(weiuiParse.parseInt(entry.getValue()));
                            break;
                    }
                }
                //
                Editable etable = mEditText.getText();
                Selection.setSelection(etable, etable.length());
                //
                editTextList.add(i, mEditText);
                mInput.addView(mEditText, mLayoutParams);
                //
                if (autoFocus) {
                    BGAKeyboardUtil.openKeyboard(context, mEditText);
                }
            }
            localBuilder.setView(mView);
        }
        //
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
                        //
                        JSONArray inputData = new JSONArray();
                        if (editTextList.size() > 0) {
                            for (int e = 0; e < editTextList.size(); e++) {
                                inputData.add(editTextList.get(e).getText().toString());
                            }
                            new Handler().postDelayed(() -> BGAKeyboardUtil.closeKeyboard((Activity) context), 100);
                        }
                        data.put("data", inputData);
                        //
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