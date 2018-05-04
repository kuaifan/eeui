package vip.kuaifan.weiui.ui.component.button;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.weex.plugin.annotation.WeexComponent;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.dom.WXDomObject;
import com.taobao.weex.ui.component.WXVContainer;

import java.util.Map;

import vip.kuaifan.weiui.R;
import vip.kuaifan.weiui.extend.integration.iconify.widget.IconTextView;
import vip.kuaifan.weiui.extend.module.weiuiCommon;
import vip.kuaifan.weiui.extend.module.weiuiConstants;
import vip.kuaifan.weiui.extend.module.weiuiJson;
import vip.kuaifan.weiui.extend.module.weiuiParse;
import vip.kuaifan.weiui.extend.module.weiuiScreenUtils;

/**
 * Created by WDM on 2018/3/13.
 */
@WeexComponent(names = {"weiui_button", "wi_button"})
public class Button extends WXVContainer<ViewGroup> implements View.OnClickListener {

    private static final String TAG = "Button";

    private View mView;

    private boolean isDisabled;
    private boolean isLoading;

    private RelativeLayout l_button;
    private IconTextView v_loading;
    private View v_unclick;
    private TextView v_text;

    private int button_radius = weiuiScreenUtils.weexPx2dp(getInstance(), 8, 0);
    private int button_backgroundColor = 0xFF3EB4FF;
    private int button_borderWidth;
    private int button_borderColor;
    private int text_color = 0xFFFFFFFF;

    public Button(WXSDKInstance instance, WXDomObject dom, WXVContainer parent) {
        super(instance, dom, parent);
        if (Float.isNaN(dom.getStyleHeight())) {
            dom.setStyleHeight(weiuiScreenUtils.weexPx2dp(getInstance(), 80));
        }
    }

    @Override
    protected ViewGroup initComponentHostView(@NonNull Context context) {
        mView = ((Activity) context).getLayoutInflater().inflate(R.layout.layout_weiui_button, null);
        initPagerView();
        //
        if (getDomObject().getEvents().contains(weiuiConstants.Event.READY)) {
            fireEvent(weiuiConstants.Event.READY, null);
        }
        //
        return (ViewGroup) mView;
    }

    private void initPagerView() {
        l_button = mView.findViewById(R.id.l_button);
        v_loading = mView.findViewById(R.id.v_loading);
        v_unclick = mView.findViewById(R.id.v_unclick);
        v_text = mView.findViewById(R.id.v_text);
        mView.findViewById(R.id.l_click).setOnClickListener(this);
        updateStyle();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.l_click) {
            if (!isDisabled && !isLoading && getDomObject().getEvents().contains(weiuiConstants.Event.CLICK)) {
                fireEvent(weiuiConstants.Event.CLICK, null);
            }
        }
    }

    @Override
    protected boolean setProperty(String key, Object param) {
        return initProperty(key, param) || super.setProperty(key, param);
    }

    private boolean initProperty(String key, Object val) {
        switch (weiuiCommon.camelCaseName(key)) {
            case "weiui":
                JSONObject json = weiuiJson.parseObject(weiuiParse.parseStr(val, ""));
                if (json.size() > 0) {
                    for (Map.Entry<String, Object> entry : json.entrySet()) {
                        initProperty(entry.getKey(), entry.getValue());
                    }
                }
                return true;

            case "text":
                setText(val);
                return true;

            case "color":
                setTextColor(val);
                return true;

            case "fontSize":
                setTextSize(val);
                return true;

            case "backgroundColor":
                setBackgroundColor(val);
                return true;

            case "borderRadius":
                setRadius(val);
                return true;

            case "borderWidth":
                setBorder(val, null);
                return true;

            case "borderColor":
                setBorder(null, val);
                return true;

            case "disabled":
                setDisabled(val);
                return true;

            case "loading":
                setLoading(val);
                return true;

            case "model":
                setModel(val);
                return true;

            default:
                return false;
        }
    }

    private void updateStyle() {
        int buttonBackgroundColor = button_backgroundColor;
        int buttonBorderColor = button_borderColor;
        int textColor =  text_color;
        if (isDisabled) {
            buttonBackgroundColor = Color.parseColor("#1E000000");
            buttonBorderColor = Color.parseColor("#20000000");
            textColor = Color.parseColor("#ffffff");
        }
        v_loading.setVisibility(isLoading ? View.VISIBLE : View.GONE);
        v_unclick.setVisibility(isLoading || isDisabled ? View.VISIBLE : View.GONE);
        //
        GradientDrawable gd = new GradientDrawable();
        gd.setColor(buttonBackgroundColor);
        if (button_radius > 0) {
            gd.setCornerRadius(button_radius);
        }
        if (button_borderWidth > 0) {
            gd.setStroke(button_borderWidth, buttonBorderColor);
        }
        l_button.setBackground(gd);
        v_text.setTextColor(textColor);
    }

    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/

    /**
     * 设置文字
     * @param var
     */
    @JSMethod
    public void setText(Object var) {
        v_text.setText(weiuiParse.parseStr(var));
    }

    /**
     * 设置文字颜色
     * @param var
     */
    @JSMethod
    public void setTextColor(Object var) {
        text_color = Color.parseColor(weiuiParse.parseStr(var));
        updateStyle();
    }

    /**
     * 设置文字大小
     * @param var
     */
    @JSMethod
    public void setTextSize(Object var) {
        v_text.setTextSize(TypedValue.COMPLEX_UNIT_PX, weiuiScreenUtils.weexPx2dp(getInstance(), var, 24));
    }

    /**
     * 设置风格
     * @param var
     */
    @JSMethod
    public void setModel(Object var) {
        switch (weiuiParse.parseStr(var).toLowerCase()) {
            case "red":
                button_backgroundColor = Color.parseColor("#f44336");
                break;

            case "green":
                button_backgroundColor = Color.parseColor("#4caf50");
                break;

            case "blue":
                button_backgroundColor = Color.parseColor("#2196f3");
                break;

            case "pink":
                button_backgroundColor = Color.parseColor("#e91e63");
                break;

            case "yellow":
                button_backgroundColor = Color.parseColor("#ffeb3b");
                break;

            case "orange":
                button_backgroundColor = Color.parseColor("#ff9800");
                break;

            case "gray":
                button_backgroundColor = Color.parseColor("#9e9e9e");
                break;

            case "black":
                button_backgroundColor = Color.parseColor("#000000");
                break;

            case "white":
                button_backgroundColor = Color.parseColor("#ffffff");
                break;
        }
        if (weiuiParse.parseStr(var).toLowerCase().equals("white")) {
            setTextColor("#000000");
        }else{
            setTextColor("#ffffff");
        }
        updateStyle();
    }

    /**
     * 设置圆角
     * @param var
     */
    @JSMethod
    public void setRadius(Object var) {
        button_radius = weiuiScreenUtils.weexPx2dp(getInstance(), var, 0);
        updateStyle();
    }


    /**
     * 设置背景颜色
     * @param var
     */
    @JSMethod
    public void setBackgroundColor(Object var) {
        button_backgroundColor = Color.parseColor(weiuiParse.parseStr(var));
        updateStyle();
    }

    /**
     * 设置边框
     * @param width
     * @param color
     */
    @JSMethod
    public void setBorder(Object width, Object color) {
        if (width != null) {
            button_borderWidth = weiuiScreenUtils.weexPx2dp(getInstance(), width, 0);
        }
        if (color != null) {
            button_borderColor = Color.parseColor(weiuiParse.parseStr(color));
        }
        updateStyle();
    }

    /**
     * 设置是否禁用
     * @param var
     */
    @JSMethod
    public void setDisabled(Object var) {
        isDisabled = weiuiParse.parseBool(var, false);
        updateStyle();
    }

    /**
     * 设置加载中状态
     * @param var
     */
    @JSMethod
    public void setLoading(Object var) {
        isLoading = weiuiParse.parseBool(var, false);
        updateStyle();
    }
}
