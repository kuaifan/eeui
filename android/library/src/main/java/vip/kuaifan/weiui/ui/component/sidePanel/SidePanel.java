package vip.kuaifan.weiui.ui.component.sidePanel;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.alibaba.weex.plugin.annotation.WeexComponent;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.dom.WXDomObject;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXVContainer;

import java.util.HashMap;
import java.util.Map;

import vip.kuaifan.weiui.R;
import vip.kuaifan.weiui.extend.module.weiuiConstants;
import vip.kuaifan.weiui.ui.component.sidePanel.view.SlidingMenu;

/**
 * Created by WDM on 2018/3/4.
 */

@WeexComponent(names = {"weiui_side_panel"})
public class SidePanel extends WXVContainer<ViewGroup> {

    private static final String TAG = "SidePanel";

    private View mView;

    private int viewTop;

    private SlidingMenu v_sliding;

    private LinearLayout v_sliding_menu;

    private FrameLayout v_container;

    public SidePanel(WXSDKInstance instance, WXDomObject node, WXVContainer parent) {
        super(instance, node, parent);
    }

    @Override
    protected ViewGroup initComponentHostView(@NonNull Context context) {
        mView = ((Activity) context).getLayoutInflater().inflate(R.layout.layout_weiui_side_panel, null);
        initPagerView();
        //
        return (ViewGroup) mView;
    }

    @Override
    public void addSubView(View view, int index) {
        if (view == null) {
            return;
        }
        ViewGroup parentViewGroup = (ViewGroup) view.getParent();
        if (parentViewGroup != null ) {
            parentViewGroup.removeView(view);
        }
        if (view instanceof SidePanelMenuView) {
            v_sliding_menu.addView(view);
            return;
        }
        v_container.addView(view);
    }

    @Override
    public ViewGroup.LayoutParams getChildLayoutParams(WXComponent child, View childView, int width, int height, int left, int right, int top, int bottom) {
        ViewGroup.LayoutParams lp = childView == null ? null : childView.getLayoutParams();
        if (lp == null) {
            lp = new FrameLayout.LayoutParams(width, height);
        } else {
            lp.width = width;
            lp.height = height;
        }
        if (lp instanceof ViewGroup.MarginLayoutParams) {
            if (child instanceof SidePanelMenu) {
                ((ViewGroup.MarginLayoutParams) lp).setMargins(left, 0, right, bottom);
            } else {
                ((ViewGroup.MarginLayoutParams) lp).setMargins(left, viewTop, right, bottom);
                viewTop+= height;
            }
        }
        return lp;
    }

    private void initPagerView() {
        v_sliding = mView.findViewById(R.id.v_sliding);
        v_sliding_menu = mView.findViewById(R.id.v_sliding_menu);
        v_container = mView.findViewById(R.id.v_container);
    }

    public View.OnClickListener menuClick = (view) -> {
        menuHide();
        int position = (int) view.getTag();
        if (getDomObject().getEvents().contains(weiuiConstants.Event.ITEM_CLICK)) {
            Map<String, Object> data = new HashMap<>();
            data.put("position", position);
            fireEvent(weiuiConstants.Event.ITEM_CLICK, data);
        }
    };

    public View.OnLongClickListener menuLongClick = (view) -> {
        int position = (int) view.getTag();
        if (getDomObject().getEvents().contains(weiuiConstants.Event.ITEM_LONG_CLICK)) {
            Map<String, Object> data = new HashMap<>();
            data.put("position", position);
            fireEvent(weiuiConstants.Event.ITEM_LONG_CLICK, data);
        }
        return true;
    };

    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/

    /**
     * 显示菜单
     */
    @JSMethod
    public void menuShow() {
        if (v_sliding != null) {
            v_sliding.switchLeft(true);
        }
    }

    /**
     * 隐藏菜单
     */
    @JSMethod
    public void menuHide() {
        if (v_sliding != null) {
            v_sliding.switchLeft(false);
        }
    }

    /**
     * 切换菜单显示/隐藏
     */
    @JSMethod
    public void menuToggle() {
        if (v_sliding != null) {
            v_sliding.toggle();
        }
    }

    /**
     * 菜单是否显示
     * @return
     */
    @JSMethod
    public boolean getMenuShow() {
        return v_sliding != null && v_sliding.getLeftShow();
    }

    /**
     * 设置菜单是否显示滚动条
     * @param scrollbar
     */
    @JSMethod
    public void setMenuScrollbar(Boolean scrollbar) {
        if (v_sliding != null) {
            v_sliding.setLeftVerticalScrollBarEnabled(scrollbar);
        }
    }

    /**
     * 设置菜单的宽度
     * @param width
     */
    @JSMethod
    public void setMenuWidth(int width) {
        if (v_sliding != null) {
            v_sliding.setLeftWidth(width);
        }
    }

    /**
     * 设置菜单的背景颜色
     * @param color
     */
    @JSMethod
    public void setMenuBackgroundColor(String color) {
        if (v_sliding != null) {
            v_sliding.setLeftBackgroundColor(Color.parseColor(color));
        }
    }
}
