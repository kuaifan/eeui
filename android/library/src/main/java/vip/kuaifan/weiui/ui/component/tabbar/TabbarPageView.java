package vip.kuaifan.weiui.ui.component.tabbar;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import vip.kuaifan.weiui.ui.component.tabbar.bean.TabbarBean;

/**
 * Created by WDM on 2018/3/9.
 */

public class TabbarPageView extends FrameLayout {

    private TabbarBean mTabbarBean = new TabbarBean();

    public TabbarPageView(Context context) {
        super(context);
    }

    public TabbarPageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TabbarPageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setBarBean(TabbarBean barBean) {
        mTabbarBean = barBean;
    }

    public TabbarBean getBarBean() {
        return mTabbarBean;
    }
}