package vip.kuaifan.weiui.ui.component.sidePanel;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import vip.kuaifan.weiui.extend.integration.fastjson.JSONObject;

import com.alibaba.weex.plugin.annotation.WeexComponent;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.dom.WXDomObject;
import com.taobao.weex.ui.component.WXVContainer;
import com.taobao.weex.utils.WXUtils;

import java.util.Map;


import vip.kuaifan.weiui.extend.module.weiuiJson;
import vip.kuaifan.weiui.extend.module.weiuiScreenUtils;

@WeexComponent(names = {"weiui_side_panel_menu"})
public class SidePanelMenu extends WXVContainer<SidePanelMenuView> {

    private int tagNum;

    public SidePanelMenu(WXSDKInstance instance, WXDomObject node, WXVContainer parent) {
        super(instance, node, parent);
    }

    @Override
    protected SidePanelMenuView initComponentHostView(@NonNull Context context) {
        SidePanelMenuView view = new SidePanelMenuView(context);
        if (getParent() instanceof SidePanel) {
            return view;
        }
        return null;
    }

    @Override
    public void addSubView(View view, int index) {
        if (view == null || getRealView() == null) {
            return;
        }

        int count = getRealView().getChildCount();
        index = index >= count ? -1 : index;
        if (index == -1) {
            getRealView().addView(view);
        } else {
            getRealView().addView(view, index);
        }
        SidePanel panel = (SidePanel) getParent();
        view.setTag(tagNum);
        view.setOnClickListener(panel.menuClick);
        view.setOnLongClickListener(panel.menuLongClick);
        tagNum++;
    }

    @Override
    protected boolean setProperty(String key, Object param) {
        switch (key) {
            case "weiui":
                JSONObject uiObject = weiuiJson.parseObject(WXUtils.getString(param, null));
                if (uiObject.size() > 0) {
                    SidePanel panel = (SidePanel) getParent();
                    for (Map.Entry<String, Object> entry : uiObject.entrySet()) {
                        switch (entry.getKey()) {
                            case "scrollbar":
                                panel.setMenuScrollbar(WXUtils.getBoolean(entry.getValue(), false));
                                break;

                            case "width":
                                panel.setMenuWidth(weiuiScreenUtils.weexPx2dp(getInstance(), entry.getValue(), 380));
                                break;

                            case "backgroundColor":
                                panel.setMenuBackgroundColor(WXUtils.getString(entry.getValue(), "#ffffff"));
                                break;
                        }
                    }
                }
                return true;
        }
        return super.setProperty(key, param);
    }
}
