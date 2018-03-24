package vip.kuaifan.weiui.ui.component.marquee;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.alibaba.weex.plugin.annotation.WeexComponent;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.dom.WXDomObject;
import com.taobao.weex.ui.component.WXVContainer;

import java.util.Map;

import vip.kuaifan.weiui.R;
import com.alibaba.fastjson.JSONObject;
import vip.kuaifan.weiui.extend.module.weiuiJson;
import vip.kuaifan.weiui.extend.module.weiuiParse;
import vip.kuaifan.weiui.extend.view.FocusedTextView;

/**
 * Created by WDM on 2018/3/5.
 */

@WeexComponent(names = {"weiui_marquee"})
public class Marquee extends WXVContainer<ViewGroup> {

    private static final String TAG = "Marquee";

    private View mView;

    private String mText = "";

    private LinearLayout v_body;

    private FocusedTextView v_autotext;

    public Marquee(WXSDKInstance instance, WXDomObject node, WXVContainer parent) {
        super(instance, node, parent);
    }

    @Override
    protected ViewGroup initComponentHostView(@NonNull Context context) {
        mView = ((Activity) context).getLayoutInflater().inflate(R.layout.layout_weiui_marquee, null);
        initPagerView();
        appleStyleAfterCreated();
        //
        return (ViewGroup) mView;
    }

    @Override
    public void addSubView(View view, int index) {

    }

    @Override
    protected boolean setProperty(String key, Object param) {
        return initProperty(key, param) || super.setProperty(key, param);
    }

    private boolean initProperty(String key, Object val) {
        switch (key) {
            case "weiui":
                JSONObject json = weiuiJson.parseObject(weiuiParse.parseStr(val, ""));
                if (json.size() > 0) {
                    for (Map.Entry<String, Object> entry : json.entrySet()) {
                        initProperty(entry.getKey(), entry.getValue());
                    }
                }
                return true;

            case "text":
                setText(weiuiParse.parseStr(val, ""));
                return true;

            case "color":
                setTextColor(weiuiParse.parseStr(val, "#000000"));
                return true;

            case "fontSize":
            case "font-size":
                setTextSize(val);
                return true;

            case "backgroundColor":
            case "background-color":
                setBackgroundColor(weiuiParse.parseStr(val, "#00ffffff"));
                return true;

            default:
                return false;
        }
    }

    private void initPagerView() {
        v_body = mView.findViewById(R.id.v_body);
        v_autotext = mView.findViewById(R.id.v_autotext);
    }

    private void appleStyleAfterCreated() {
        setTextSize(20);
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
    }

    /**
     * 添加文本
     * @param var
     */
    @JSMethod
    public void addText(String var) {
        if (var == null) {
            return;
        }
        setText(mText + var);
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
     * 设置文字大小
     * @param var
     */
    @JSMethod
    public void setTextSize(Object var) {
        v_autotext.setTextSize(weiuiParse.parseInt(var, 0));
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
        v_body.setBackgroundColor(Color.parseColor(var));
    }
}
