package vip.kuaifan.weiui.ui.component.sidePanel;

import android.content.Context;
import android.support.annotation.NonNull;

import com.alibaba.weex.plugin.annotation.WeexComponent;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.dom.WXDomObject;
import com.taobao.weex.ui.component.WXVContainer;

import vip.kuaifan.weiui.extend.module.weiuiCommon;
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
            view.setName(weiuiParse.parseStr(getDomObject().getAttrs().get("name"), weiuiCommon.randomString(6)));
            view.setTag(panel.getMenuNum());
            view.setOnClickListener(panel.menuClick);
            view.setOnLongClickListener(panel.menuLongClick);
            panel.menuNumPlusOne();
            return view;
        }
        return null;
    }
}
