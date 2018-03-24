package vip.kuaifan.weiui.ui.component.sidePanel;

import android.content.Context;
import android.support.annotation.NonNull;

import com.alibaba.weex.plugin.annotation.WeexComponent;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.dom.WXAttr;
import com.taobao.weex.dom.WXDomObject;
import com.taobao.weex.ui.component.WXVContainer;

import com.alibaba.fastjson.JSONObject;
import vip.kuaifan.weiui.extend.module.weiuiCommon;
import vip.kuaifan.weiui.extend.module.weiuiJson;
import vip.kuaifan.weiui.extend.module.weiuiParse;

@WeexComponent(names = {"weiui_side_panel_menu"})
public class SidePanelMenu extends WXVContainer<SidePanelMenuView> {

    public SidePanelMenu(WXSDKInstance instance, WXDomObject node, WXVContainer parent) {
        super(instance, node, parent);
    }

    @Override
    protected SidePanelMenuView initComponentHostView(@NonNull Context context) {
        SidePanelMenuView view = new SidePanelMenuView(context);
        if (getParent() instanceof SidePanel) {
            SidePanel panel = (SidePanel) getParent();
            view.setName(getName(getDomObject().getAttrs()));
            view.setTag(panel.getMenuNum());
            view.setOnClickListener(panel.menuClick);
            view.setOnLongClickListener(panel.menuLongClick);
            panel.menuNumPlusOne();
            return view;
        }
        return null;
    }

    private String getName(WXAttr attr) {
        JSONObject json = weiuiJson.parseObject(attr.get("weiui"));
        return weiuiJson.getString(json, "name", weiuiParse.parseStr(attr.get("name"), weiuiCommon.randomString(6)));
    }
}
