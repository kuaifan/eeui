package vip.kuaifan.weiui.ui.component.tabbar.entity;

import vip.kuaifan.weiui.extend.view.tablayout.listener.CustomTabEntity;
import vip.kuaifan.weiui.ui.component.tabbar.bean.TabbarBean;

public class TabbarEntity implements CustomTabEntity {

    private TabbarBean mTabbarBean = new TabbarBean();

    public TabbarEntity(TabbarBean barBean) {
        mTabbarBean = barBean;
    }

    @Override
    public String getTabName() {
        return mTabbarBean.getTabName();
    }

    @Override
    public String getTabTitle() {
        return mTabbarBean.getTitle();
    }

    @Override
    public int getTabMessage() {
        return mTabbarBean.getMessage();
    }

    @Override
    public boolean isTabDot() {
        return mTabbarBean.isDot();
    }

    @Override
    public int getTabSelectedIcon() {
        return 0;
    }

    @Override
    public int getTabUnselectedIcon() {
        return 0;
    }

    @Override
    public String getTabSelectedIconUrl() {
        return mTabbarBean.getSelectedIcon();
    }

    @Override
    public String getTabUnselectedIconUrl() {
        return mTabbarBean.getUnSelectedIcon();
    }
}
