package vip.kuaifan.weiui.ui.component.tabbar;

import android.content.Context;
import android.support.annotation.NonNull;

import vip.kuaifan.weiui.extend.integration.fastjson.JSONObject;

import com.alibaba.weex.plugin.annotation.WeexComponent;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.dom.WXDomObject;
import com.taobao.weex.ui.component.WXVContainer;
import com.taobao.weex.utils.WXUtils;

import java.util.HashMap;
import java.util.Map;

import vip.kuaifan.weiui.extend.module.weiuiJson;
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
                        JSONObject json = weiuiJson.parseObject(WXUtils.getString(value, null));
                        if (json.size() > 0) {
                            Map<String, Object> data = new HashMap<>();
                            for (Map.Entry<String, Object> entry : json.entrySet()) {
                                data.put(entry.getKey(), entry.getValue());
                            }
                            formatAttrs(data);
                        }
                        break;

                    case "name":
                        barBean.setName(WXUtils.getString(value, barBean.getName()));
                        break;

                    case "title":
                        barBean.setTitle(WXUtils.getString(value, barBean.getTitle()));
                        break;

                    case "selectedIcon":
                        barBean.setSelectedIcon(WXUtils.getString(value, ""));
                        break;

                    case "unSelectedIcon":
                        barBean.setUnSelectedIcon(WXUtils.getString(value, ""));
                        break;

                    case "message":
                        barBean.setMessage(WXUtils.getNumberInt(value, 0));
                        break;

                    case "dot":
                        barBean.setDot(WXUtils.getBoolean(value, false));
                        break;
                }
            }
            mView.setBarBean(barBean);
        }
    }
}
