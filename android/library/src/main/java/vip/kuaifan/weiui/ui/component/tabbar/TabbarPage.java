package vip.kuaifan.weiui.ui.component.tabbar;

import android.content.Context;
import android.support.annotation.NonNull;

import vip.kuaifan.weiui.extend.integration.fastjson.JSONObject;

import com.alibaba.weex.plugin.annotation.WeexComponent;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.dom.WXDomObject;
import com.taobao.weex.ui.component.WXVContainer;

import java.util.HashMap;
import java.util.Map;

import vip.kuaifan.weiui.extend.module.weiuiJson;
import vip.kuaifan.weiui.extend.module.weiuiParse;
import vip.kuaifan.weiui.ui.component.tabbar.bean.TabbarBean;


/**
 * Created by WDM on 2018/3/9.
 */

@WeexComponent(names = {"weiui_tabbar_page"})
public class TabbarPage extends WXVContainer<TabbarPageView> {

    private TabbarPageView mView;

    public TabbarPage(WXSDKInstance instance, WXDomObject node, WXVContainer parent) {
        super(instance, node, parent);
    }

    @Override
    protected TabbarPageView initComponentHostView(@NonNull Context context) {
        if (getParent() instanceof Tabbar) {
            mView = new TabbarPageView(context);
            formatAttrs(getDomObject().getAttrs());
            return mView;
        }
        return null;
    }

    private void formatAttrs(Map<String, Object> attr) {
        if (attr != null) {
            TabbarBean barBean = mView.getBarBean();
            for (String key : attr.keySet()) {
                Object value = attr.get(key);
                switch (key) {
                    case "weiui":
                        JSONObject json = weiuiJson.parseObject(weiuiParse.parseStr(value, null));
                        if (json.size() > 0) {
                            Map<String, Object> data = new HashMap<>();
                            for (Map.Entry<String, Object> entry : json.entrySet()) {
                                data.put(entry.getKey(), entry.getValue());
                            }
                            formatAttrs(data);
                        }
                        break;
                }
                barBean = setBarAttr(barBean, key, value);
            }
            mView.setBarBean(barBean);
        }
    }

    public static TabbarBean setBarAttr(TabbarBean barBean, String key, Object value) {
        if (barBean == null) {
            barBean = new TabbarBean();
        }
        switch (key) {
            case "tabName":
                barBean.setTabName(weiuiParse.parseStr(value, barBean.getTabName()));
                break;

            case "title":
                barBean.setTitle(weiuiParse.parseStr(value, barBean.getTitle()));
                break;

            case "unSelectedIcon":
                barBean.setUnSelectedIcon(weiuiParse.parseStr(value, ""));
                break;

            case "selectedIcon":
                barBean.setSelectedIcon(weiuiParse.parseStr(value, ""));
                break;

            case "message":
                barBean.setMessage(weiuiParse.parseInt(value, 0));
                break;

            case "dot":
                barBean.setDot(weiuiParse.parseBool(value, false));
                break;
        }
        return barBean;
    }
}
