package vip.kuaifan.weiui.ui.component.navbar;

import android.content.Context;
import android.support.annotation.NonNull;

import com.alibaba.weex.plugin.annotation.WeexComponent;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.dom.WXDomObject;
import com.taobao.weex.dom.flex.CSSJustify;
import com.taobao.weex.ui.component.WXVContainer;

/**
 * Created by WDM on 2018/3/6.
 */

@WeexComponent(names = {"weiui_navbar_item"})
public class NavbarItem extends WXVContainer<NavbarItemView> {

    private static final String TAG = "NavbarItem";

    public NavbarItem(WXSDKInstance instance, WXDomObject node, WXVContainer parent) {
        super(instance, node, parent);
        node.setJustifyContent(CSSJustify.CENTER);
    }

    @Override
    protected NavbarItemView initComponentHostView(@NonNull Context context) {
        if (getParent() instanceof Navbar) {
            NavbarItemView mNavbarItemView = new NavbarItemView(context);
            mNavbarItemView.setType(String.valueOf(getDomObject().getAttrs().get("type")));
            return mNavbarItemView;
        }
        return null;
    }
}
