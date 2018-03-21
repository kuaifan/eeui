package vip.kuaifan.weiui.ui.component.scrollText;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import vip.kuaifan.weiui.extend.integration.fastjson.JSONObject;

import com.alibaba.weex.plugin.annotation.WeexComponent;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.dom.WXDomObject;
import com.taobao.weex.ui.component.WXVContainer;
import com.taobao.weex.utils.WXUtils;

import java.util.HashMap;
import java.util.Map;

import vip.kuaifan.weiui.R;
import vip.kuaifan.weiui.extend.module.weiuiConstants;

import vip.kuaifan.weiui.extend.module.weiuiJson;
import vip.kuaifan.weiui.extend.module.weiuiScreenUtils;
import vip.kuaifan.weiui.extend.view.AutoScrollTextView;

/**
 * Created by WDM on 2018/3/5.
 */

@WeexComponent(names = {"weiui_scroll_text"})
public class ScrollText extends WXVContainer<ViewGroup> implements View.OnClickListener {

    private static final String TAG = "ScrollText";

    private View mView;

    private String mText = "";

    private AutoScrollTextView v_autotext;

    public ScrollText(WXSDKInstance instance, WXDomObject node, WXVContainer parent) {
        super(instance, node, parent);
    }

    @Override
    protected ViewGroup initComponentHostView(@NonNull Context context) {
        mView = ((Activity) context).getLayoutInflater().inflate(R.layout.layout_weiui_scroll_text, null);
        initPagerView();
        //
        return (ViewGroup) mView;
    }

    @Override
    public void addSubView(View view, int index) {

    }

    @Override
    protected boolean setProperty(String key, Object param) {
        switch (key) {
            case "weiui":
                JSONObject uiObject = weiuiJson.parseObject(WXUtils.getString(param, null));
                if (uiObject.size() > 0) {
                    for (Map.Entry<String, Object> entry : uiObject.entrySet()) {
                        switch (entry.getKey()) {
                            case "text":
                                setText(WXUtils.getString(entry.getValue(), ""));
                                v_autotext.startScroll();
                                break;

                            case "speed":
                                setSpeed(WXUtils.getFloat(entry.getValue(), 2f));
                                break;

                            case "fontSize":
                                setTextSize(weiuiScreenUtils.weexPx2dp(getInstance(), entry.getValue(), 12));
                                break;

                            case "color":
                                setTextColor(WXUtils.getString(entry.getValue(), "#000000"));
                                break;

                            case "backgroundColor":
                                setBackgroundColor(WXUtils.getString(entry.getValue(), "#00ffffff"));
                                break;
                        }
                    }
                }
                setText(null);
                v_autotext.startScroll();
                return true;

            case "text":
                setText(WXUtils.getString(param, ""));
                v_autotext.startScroll();
                return true;
        }
        return super.setProperty(key, param);
    }

    private void initPagerView() {
        v_autotext = mView.findViewById(R.id.v_autotext);
        v_autotext.setOnClickListener(this);
        setText(null);
    }

    @Override
    public void onClick(View view) {
        if (isStarting()) {
            stopScroll();
        } else {
            startScroll();
        }
        if (getDomObject().getEvents().contains(weiuiConstants.Event.ITEM_CLICK)) {
            Map<String, Object> data = new HashMap<>();
            data.put("isStarting", isStarting());
            fireEvent(weiuiConstants.Event.ITEM_CLICK, data);
        }
    }

    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/

    /**
     * 设置文本
     * @param var
     */
    @JSMethod
    public void setText(String var) {
        if (var != null) {
            mText = var;
        }
        v_autotext.setText(mText);
        v_autotext.init(((Activity) getContext()).getWindowManager());
    }

    /**
     * 开始滚动文字
     */
    @JSMethod
    public void startScroll() {
        v_autotext.startScroll();
    }

    /**
     * 停止滚动文字
     */
    @JSMethod
    public void stopScroll() {
        v_autotext.stopScroll();
    }

    /**
     * 获取滚动状态
     */
    @JSMethod(uiThread = false)
    public boolean isStarting() {
        return v_autotext.isStarting;
    }

    /**
     * 获取文本
     * @return
     */
    @JSMethod(uiThread = false)
    public String getText() {
        return mText;
    }

    /**
     * 设置文字滚动速度
     * @param var
     */
    @JSMethod
    public void setSpeed(float var) {
        v_autotext.setSpeed(var);
    }

    /**
     * 设置文字大小
     * @param var
     */
    @JSMethod
    public void setTextSize(int var) {
        v_autotext.setTextSize(var);
    }

    /**
     * 设置文字颜色
     * @param var
     */
    @JSMethod
    public void setTextColor(String var) {
        v_autotext.setTextColor(Color.parseColor(var));
    }

    /**
     * 设置文字背景颜色
     * @param var
     */
    @JSMethod
    public void setBackgroundColor(String var) {
        v_autotext.setBackgroundColor(Color.parseColor(var));
    }
}
