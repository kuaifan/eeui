package vip.kuaifan.weiui.extend.integration.iconify.fonts;

import vip.kuaifan.weiui.extend.integration.iconify.Icon;
import vip.kuaifan.weiui.extend.integration.iconify.IconFontDescriptor;

public class TbIconfontModule implements IconFontDescriptor {

    @Override
    public String ttfFileName() {
        return "iconify/android-iconify-taobao-iconfont.ttf";
    }

    @Override
    public Icon[] characters() {
        return TbIconfontIcons.values();
    }
}
