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
import com.taobao.weex.dom.flex.CSSFlexDirection;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXVContainer;

import java.util.HashMap;
import java.util.Map;

import vip.kuaifan.weiui.PageActivity;
import vip.kuaifan.weiui.R;
import com.alibaba.fastjson.JSONObject;
import vip.kuaifan.weiui.extend.module.weiuiConstants;
import vip.kuaifan.weiui.extend.module.weiuiJson;
import vip.kuaifan.weiui.extend.module.weiuiParse;
import vip.kuaifan.weiui.extend.module.weiuiScreenUtils;
import vip.kuaifan.weiui.ui.component.sidePanel.view.SlidingMenu;

/**
 * Created by WDM on 2018/3/4.
 */

@WeexComponent(names = {"weiui_side_panel", "wi_side_panel"})
public class SidePanel extends WXVContainer<ViewGroup> {

    private static final String TAG = "SidePanel";

    private View mView;

    private SlidingMenu v_sliding;

    private LinearLayout v_sliding_menu;

    private LinearLayout v_container;

    private int menuNum;

    int getMenuNum() {
        return menuNum;
    }

    void menuNumPlusOne() {
        menuNum++;
    }

    public SidePanel(WXSDKInstance instance, WXDomObject node, WXVContainer parent) {
        super(instance, node, parent);
        node.setFlexDirection(CSSFlexDirection.ROW);
    }

    @Override
    protected ViewGroup initComponentHostView(@NonNull Context context) {
        mView = ((Activity) context).getLayoutInflater().inflate(R.layout.layout_weiui_side_panel, null);
        initPagerView();
        //
        if (getContext() instanceof PageActivity) {
            ((PageActivity) getContext()).setSwipeBackEnable(false);
        }
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
    protected boolean setProperty(String key, Object param) {
        return initProperty(key, param) || super.setProperty(key, param);
    }

    private boolean initProperty(String key, Object val) {
        switch (key) {
            case "weiui":
                JSONObject json = weiuiJson.parseObject(weiuiParse.parseStr(val, ""));
                if (json.size() > 0) {
                    for (Map.Entry<String, Object> entry : json.entrySet()) {
                        initProperty(entry.getKey(), entry.getValue());
                    }
                }
                return true;

            case "width":
                setMenuWidth(weiuiScreenUtils.weexPx2dp(getInstance(), val, 380));
                return true;

            case "scrollbar":
                setMenuScrollbar(weiuiParse.parseBool(val, false));
                return true;

            case "backgroundColor":
            case "background-color":
                setMenuBackgroundColor(weiuiParse.parseStr(val, "#ffffff"));
                return true;

            default:
                return false;
        }
    }

    @Override
    public ViewGroup.LayoutParams getChildLayoutParams(WXComponent child, View childView, int width, int height, int left, int right, int top, int bottom) {
        ViewGroup.LayoutParams lp = childView == null ? null : childView.getLayoutParams();
        if (lp == null) {
            lp = new FrameLayout.LayoutParams(width, FrameLayout.LayoutParams.WRAP_CONTENT);
        } else {
            lp.width = width;
            lp.height = FrameLayout.LayoutParams.WRAP_CONTENT;
        }
        if (lp instanceof ViewGroup.MarginLayoutParams) {
            left = weiuiScreenUtils.weexPx2dp(getInstance(), child.getDomObject().getStyles().get("marginLeft"), 0);
            ((ViewGroup.MarginLayoutParams) lp).setMargins(left, top, right, bottom);
        }
        return lp;
    }

    private void initPagerView() {
        v_sliding = mView.findViewById(R.id.v_sliding);
        v_sliding_menu = mView.findViewById(R.id.v_sliding_menu);
        v_container = mView.findViewById(R.id.v_container);
        //
        v_sliding.setOnSwitchListener(isShow -> {
            if (getDomObject().getEvents().contains(weiuiConstants.Event.SWITCH_LISTENER)) {
                Map<String, Object> data = new HashMap<>();
                data.put("show", isShow);
                fireEvent(weiuiConstants.Event.SWITCH_LISTENER, data);
            }
        });
        //
        if (getContext() instanceof PageActivity) {
            ((PageActivity) getContext()).setOnBackPressed(() -> {
                if (getMenuShow()) {
                    menuHide();
                    return true;
                }
                return false;
            });
        }
    }

    View.OnClickListener menuClick = (view) -> {
        menuHide();
        int position = (int) view.getTag();
        if (getDomObject().getEvents().contains(weiuiConstants.Event.ITEM_CLICK)
                && view instanceof SidePanelMenuView) {
            Map<String, Object> data = new HashMap<>();
            data.put("name", ((SidePanelMenuView) view).getName());
            data.put("position", position);
            fireEvent(weiuiConstants.Event.ITEM_CLICK, data);
        }
    };

    View.OnLongClickListener menuLongClick = (view) -> {
        int position = (int) view.getTag();
        if (getDomObject().getEvents().contains(weiuiConstants.Event.ITEM_LONG_CLICK)
                && view instanceof SidePanelMenuView) {
            Map<String, Object> data = new HashMap<>();
            data.put("name", ((SidePanelMenuView) view).getName());
            data.put("position", position);
            fireEvent(weiuiConstants.Event.ITEM_LONG_CLICK, data);
        }
        return true;
    };

    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/

    /**
     * 显示侧边栏
     */
    @JSMethod
    public void menuShow() {
        if (v_sliding != null) {
            v_sliding.switchLeft(true);
        }
    }

    /**
     * 隐藏侧边栏
     */
    @JSMethod
    public void menuHide() {
        if (v_sliding != null) {
            v_sliding.switchLeft(false);
        }
    }

    /**
     * 切换侧边栏显示/隐藏
     */
    @JSMethod
    public void menuToggle() {
        if (v_sliding != null) {
            v_sliding.toggle();
        }
    }

    /**
     * 侧边栏是否显示
     * @return
     */
    @JSMethod(uiThread = false)
    public boolean getMenuShow() {
        return v_sliding != null && v_sliding.getLeftShow();
    }

    /**
     * 设置侧边栏的宽度
     * @param width
     */
    @JSMethod
    public void setMenuWidth(int width) {
        if (v_sliding != null) {
            v_sliding.setLeftWidth(width);
        }
    }

    /**
     * 设置侧边栏是否显示滚动条
     * @param scrollbar
     */
    @JSMethod
    public void setMenuScrollbar(Boolean scrollbar) {
        if (v_sliding != null) {
            v_sliding.setLeftVerticalScrollBarEnabled(scrollbar);
        }
    }

    /**
     * 设置侧边栏的背景颜色
     * @param color
     */
    @JSMethod
    public void setMenuBackgroundColor(String color) {
        if (v_sliding != null) {
            v_sliding.setLeftBackgroundColor(Color.parseColor(color));
        }
    }
}
