package vip.kuaifan.weiui.ui.component.navbar;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import vip.kuaifan.weiui.extend.integration.fastjson.JSONObject;

import com.alibaba.weex.plugin.annotation.WeexComponent;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.dom.WXDomObject;
import com.taobao.weex.dom.flex.CSSFlexDirection;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXVContainer;

import java.util.Map;

import vip.kuaifan.weiui.PageActivity;
import vip.kuaifan.weiui.R;
import vip.kuaifan.weiui.extend.module.weiuiConstants;

import vip.kuaifan.weiui.extend.module.weiuiJson;
import vip.kuaifan.weiui.extend.module.weiuiParse;
import vip.kuaifan.weiui.extend.module.weiuiScreenUtils;

/**
 * Created by WDM on 2018/3/5.
 */

@WeexComponent(names = {"weiui_navbar"})
public class Navbar extends WXVContainer<ViewGroup> {

    private static final String TAG = "Navbar";

    private View mView;

    private ImageView v_back;

    private LinearLayout v_left_box, v_middle_box, v_right_box;

    private LinearLayout v_left, v_middle, v_right;

    private int leftBoxWidth, rightBoxWidth, maxBoxWidth;

    public Navbar(WXSDKInstance instance, WXDomObject node, WXVContainer parent) {
        super(instance, node, parent);
        node.setFlexDirection(CSSFlexDirection.ROW);
        if (Float.isNaN(node.getStyleHeight())) {
            node.setStyleHeight(weiuiScreenUtils.weexPx2dp(getInstance(), 100));
        }
    }

    @Override
    protected ViewGroup initComponentHostView(@NonNull Context context) {
        mView = ((Activity) context).getLayoutInflater().inflate(R.layout.layout_weiui_navbar, null);
        initPagerView();
        //
        return (ViewGroup) mView;
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
            ((ViewGroup.MarginLayoutParams) lp).setMargins(0, top, right, bottom);
        }
        return lp;
    }

    @Override
    public void addSubView(View view, int index) {
        if (view instanceof NavbarItemView) {
            NavbarItemView newView = (NavbarItemView) view;
            ViewGroup parentViewGroup = (ViewGroup) newView.getParent();
            if (parentViewGroup != null ) {
                parentViewGroup.removeView(newView);
            }
            View tempView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.layout_weiui_navbar_item, null);
            ViewGroup.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
            ((FrameLayout) tempView.findViewById(R.id.v_container)).addView(newView, params);
            switch (newView.getType()) {
                case "back":
                    showBack();
                    break;

                case "left":
                    v_left.addView(tempView, params);
                    break;

                case "title":
                case "middle":
                    tempView.findViewById(R.id.v_layout).setBackgroundResource(R.color.weiui_transparent);
                    v_middle.addView(tempView, params);
                    break;

                case "right":
                    v_right.addView(tempView, params);
                    break;
            }
        }
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

            case "type":
            case "titleType":
            case "middleType":
                setTitleType(weiuiParse.parseStr(val, "center"));
                return true;

            default:
                return false;
        }
    }


    @Override
    public void destroy() {
        super.destroy();
        if (v_left_box != null) {
            v_left_box.getViewTreeObserver().removeOnPreDrawListener(boxDrawListener("left"));
        }
        if (v_right_box != null) {
            v_right_box.getViewTreeObserver().removeOnPreDrawListener(boxDrawListener("right"));
        }
    }

    private ViewTreeObserver.OnPreDrawListener boxDrawListener(String type) {
        return () -> {
            if (type.equals("left")) {
                leftBoxWidth = v_left_box.getWidth();
            }else if (type.equals("right")) {
                rightBoxWidth = v_right_box.getWidth();
            }
            int max = Math.max(leftBoxWidth, rightBoxWidth);
            if (max != maxBoxWidth) {
                maxBoxWidth = max;
                v_middle_box.setPadding(maxBoxWidth, 0, maxBoxWidth, 0);
            }
            return true;
        };
    }

    private void initPagerView() {
        v_back = mView.findViewById(R.id.v_back);
        v_back.setOnClickListener((View view)->{
            if (getDomObject().getEvents().contains(weiuiConstants.Event.GO_BACK_OVERRIDE)) {
                fireEvent(weiuiConstants.Event.GO_BACK_OVERRIDE, null);
            }else{
                if (getContext() instanceof PageActivity) {
                    ((PageActivity) getContext()).onBackPressedSkipBackPressedClose();
                }else{
                    ((Activity) getContext()).onBackPressed();
                }
            }
            if (getDomObject().getEvents().contains(weiuiConstants.Event.GO_BACK)) {
                fireEvent(weiuiConstants.Event.GO_BACK, null);
            }
        });
        //
        v_left_box = mView.findViewById(R.id.v_left_box);
        v_middle_box = mView.findViewById(R.id.v_middle_box);
        v_right_box = mView.findViewById(R.id.v_right_box);

        v_left = mView.findViewById(R.id.v_left);
        v_middle = mView.findViewById(R.id.v_middle);
        v_right = mView.findViewById(R.id.v_right);
        //
        v_left_box.getViewTreeObserver().addOnPreDrawListener(boxDrawListener("left"));
        v_right_box.getViewTreeObserver().addOnPreDrawListener(boxDrawListener("right"));
    }

    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/

    /**
     * 显示返回
     */
    @JSMethod
    public void showBack() {
        v_back.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏返回
     */
    @JSMethod
    public void hideBack() {
        v_back.setVisibility(View.GONE);
    }

    /**
     * 设置标题方式
     * @param titleType
     */
    @JSMethod
    public void setTitleType(String titleType) {
        switch (titleType) {
            case "left":
                v_middle.setGravity(Gravity.START | Gravity.CENTER_VERTICAL);
                break;
            case "right":
                v_middle.setGravity(Gravity.END | Gravity.CENTER_VERTICAL);
                break;
            default:
                v_middle.setGravity(Gravity.CENTER);
                break;
        }
    }
}
