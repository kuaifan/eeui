package vip.kuaifan.weiui.ui.component.icon;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.MotionEvent;

import vip.kuaifan.weiui.extend.integration.fastjson.JSONObject;
import vip.kuaifan.weiui.extend.integration.iconify.widget.IconTextView;

import com.alibaba.weex.plugin.annotation.WeexComponent;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.dom.WXDomObject;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXVContainer;
import com.taobao.weex.utils.WXUtils;

import java.util.Map;


import vip.kuaifan.weiui.extend.module.weiuiJson;
import vip.kuaifan.weiui.extend.module.weiuiScreenUtils;

/**
 * Created by WDM on 2018/3/13.
 */
@SuppressLint("SetTextI18n")
@WeexComponent(names = {"weiui_icon"})
public class Icon extends WXComponent<IconTextView> {

    private static final String TAG = "Icon";

    private IconTextView mIconTextView;

    private int mIconColor;

    private int mIconClickColor;

    public Icon(WXSDKInstance instance, WXDomObject dom, WXVContainer parent) {
        super(instance, dom, parent);
    }

    public Icon(WXSDKInstance instance, WXDomObject dom, WXVContainer parent, int type) {
        super(instance, dom, parent, type);
    }

    @Override
    protected IconTextView initComponentHostView(@NonNull Context context) {
        mIconTextView = new IconTextView(context);
        appleStyleAfterCreated();
        return mIconTextView;
    }

    @Override
    protected boolean setProperty(String key, Object param) {
        switch (key) {
            case "weiui":
                JSONObject uiObject = weiuiJson.parseObject(WXUtils.getString(param, null));
                if (uiObject.size() > 0) {
                    for (Map.Entry<String, Object> entry : uiObject.entrySet()) {
                        initProperty(entry.getKey(), entry.getValue());
                    }
                }
                return true;

        }
        return initProperty(key, param) || super.setProperty(key, param);
    }

    private boolean initProperty(String key, Object val) {
        switch (key) {
            case "icon":
            case "text":
                setIcon(WXUtils.getString(val, null));
                return true;

            case "iconSize":
            case "textSize":
                setIconSize(WXUtils.getNumberInt(val, 0));
                return true;

            case "iconColor":
            case "textColor":
                setIconColor(WXUtils.getString(val, null));
                return true;

            case "iconClickColor":
            case "textClickColor":
                setIconClickColor(WXUtils.getString(val, null));
                return true;

        }
        return false;
    }

    private void appleStyleAfterCreated() {
        mIconTextView.setGravity(Gravity.CENTER);
        setIcon("home");
        setIconSize(16);
        setIconColor("#242424");
    }

    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/

    /**
     * 设置图标
     * @param var
     */
    @JSMethod
    public void setIcon(String var) {
        if (var == null) {
            return;
        }
        if (var.isEmpty()) {
            mIconTextView.setText("");
            return;
        }
        if (!var.startsWith("ion-")) {
            var = "ion-" + var;
        }
        mIconTextView.setText("{" + var + "}");
    }

    /**
     * 设置图标大小
     * @param var
     */
    @JSMethod
    public void setIconSize(int var) {
        mIconTextView.setTextSize(weiuiScreenUtils.weexPx2dp(getInstance(), var));
    }

    /**
     * 设置图标颜色
     * @param var
     */
    @JSMethod
    public void setIconColor(String var) {
        if (var == null) {
            return;
        }
        mIconColor = Color.parseColor(var);
        mIconTextView.setTextColor(mIconColor);
    }

    /**
     * 设置图标点击颜色
     * @param var
     */
    @JSMethod
    public void setIconClickColor(String var) {
        if (var == null) {
            return;
        }
        int color = Color.parseColor(var);
        if (mIconClickColor == 0) {
            mIconTextView.setClickable(true);
            mIconTextView.setFocusable(true);
            mIconTextView.setOnTouchListener((view, event) -> {
                switch (event.getAction()) {
                    //离开
                    case MotionEvent.ACTION_UP:
                        mIconTextView.setTextColor(mIconColor);
                        break;

                    //按下
                    case MotionEvent.ACTION_DOWN:
                        mIconTextView.setTextColor(mIconClickColor);
                        break;
                }
                return false;
            });
        }
        mIconClickColor = color;
    }

}
