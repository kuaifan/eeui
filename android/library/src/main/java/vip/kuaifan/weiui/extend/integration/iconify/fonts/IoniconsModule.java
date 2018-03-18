package vip.kuaifan.weiui.extend.integration.iconify.fonts;

import vip.kuaifan.weiui.extend.integration.iconify.Icon;
import vip.kuaifan.weiui.extend.integration.iconify.IconFontDescriptor;

public class IoniconsModule implements IconFontDescriptor {

    @Override
    public String ttfFileName() {
        return "iconify/android-iconify-ionicons.ttf";
    }

    @Override
    public Icon[] characters() {
        return vip.kuaifan.weiui.extend.integration.iconify.fonts.IoniconsIcons.values();
    }
}
