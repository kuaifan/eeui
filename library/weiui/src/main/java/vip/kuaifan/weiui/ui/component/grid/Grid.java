package vip.kuaifan.weiui.ui.component.grid;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.alibaba.fastjson.JSONObject;
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
import vip.kuaifan.weiui.extend.module.weiuiJson;
import vip.kuaifan.weiui.extend.module.weiuiParse;
import vip.kuaifan.weiui.extend.module.weiuiScreenUtils;
import vip.kuaifan.weiui.ui.component.grid.view.GridPager;

@WeexComponent(names = {"weiui_grid", "wi_grid"})
public class Grid extends WXVContainer<ViewGroup> {

    private static final String TAG = "Grid";

    private View mView;

    private GridPager v_gridPager;

    private Handler mHandler = new Handler();

    private int addIdentify;

    public Grid(WXSDKInstance instance, WXDomObject node, WXVContainer parent) {
        super(instance, node, parent);
    }

    @Override
    protected ViewGroup initComponentHostView(@NonNull Context context) {
        mView = ((Activity) context).getLayoutInflater().inflate(R.layout.layout_weiui_grid, null);
        initPagerView();
        //
        return (ViewGroup) mView;
    }

    @Override
    public void addSubView(View view, int index) {
        if (view == null) {
            return;
        }
        v_gridPager.addData(view);
        notifyDataSetChanged();
    }

    @Override
    public void remove(WXComponent child, boolean destroy) {
        if (child == null || child.getHostView() == null) {
            return;
        }
        v_gridPager.removeData(child.getHostView());
        notifyDataSetChanged();
        super.remove(child,destroy);
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
            top = weiuiScreenUtils.weexPx2dp(getInstance(), child.getDomObject().getStyles().get("marginTop"), 0);
            ((ViewGroup.MarginLayoutParams) lp).setMargins(left, top, right, bottom);
        }
        return lp;
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

            case "row":
            case "rowSize":
            case "row-size":
                setRowSize(weiuiParse.parseInt(val, 3));
                return true;

            case "columns":
            case "columnsSize":
            case "columns-size":
                setColumnsSize(weiuiParse.parseInt(val, 3));
                return true;

            case "divider":
                setDivider(weiuiParse.parseBool(val, true));
                return true;

            case "dividerColor":
            case "divider-color":
                setDividerColor(weiuiParse.parseStr(val, "#e8e8e8"));
                return true;

            case "dividerWidth":
            case "divider-width":
                setDividerWidth(weiuiParse.parseInt(val, 1));
                return true;

            case "indicator":
                setIndicator(weiuiParse.parseBool(val, true));
                return true;

            case "indicatorUnSelectedColor":
            case "indicator-un-selected-color":
                setIndicatorUnSelectedColor(weiuiParse.parseStr(val, "#E0E0E0"));
                return true;

            case "indicatorSelectedColor":
            case "indicator-selected-color":
                setIndicatorSelectedColor(weiuiParse.parseStr(val, "#ff0000"));
                return true;

            case "indicatorWidth":
            case "indicator-width":
                setIndicatorWidth(weiuiParse.parseInt(val, 8));
                return true;

            case "indicatorHeight":
            case "indicator-height":
                setIndicatorHeight(weiuiParse.parseInt(val, 8));
                return true;

            default:
                return false;
        }
    }
    
    private void notifyDataSetChanged() {
        addIdentify++;
        int tempId = addIdentify;
        mHandler.postDelayed(()-> {
            if (tempId == addIdentify) {
                v_gridPager.post(()-> v_gridPager.notifyDataSetChanged());
            }
        }, 100);
    }

    private void initPagerView() {
        v_gridPager = mView.findViewById(R.id.v_gridPager);
        v_gridPager.setOnPageItemClickListener(new GridPager.OnPageItemClickListener() {
            @Override
            public void onClick(int pos, int position) {
                if (getDomObject().getEvents().contains(weiuiConstants.Event.ITEM_CLICK)) {
                    Map<String, Object> data = new HashMap<>();
                    data.put("page", pos);
                    data.put("position", position);
                    fireEvent(weiuiConstants.Event.ITEM_CLICK, data);
                }
            }

            @Override
            public void onLongClick(int pos, int position) {
                if (getDomObject().getEvents().contains(weiuiConstants.Event.ITEM_LONG_CLICK)) {
                    Map<String, Object> data = new HashMap<>();
                    data.put("page", pos);
                    data.put("position", position);
                    fireEvent(weiuiConstants.Event.ITEM_LONG_CLICK, data);
                }
            }
        });
    }

    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/

    /**
     * 设置每页行数
     * @param var
     */
    @JSMethod
    public void setRowSize(int var) {
        v_gridPager.setRowSize(var);
        notifyDataSetChanged();
    }

    /**
     * 设置每页列数
     * @param var
     */
    @JSMethod
    public void setColumnsSize(int var) {
        v_gridPager.setColumnsSize(var);
        notifyDataSetChanged();
    }

    /**
     * 设置是否显示分隔线
     * @param var
     */
    @JSMethod
    public void setDivider(boolean var) {
        v_gridPager.setDivider(var);
        notifyDataSetChanged();
    }

    /**
     * 设置分隔线颜色
     * @param var
     */
    @JSMethod
    public void setDividerColor(String var) {
        v_gridPager.setDividerColor(Color.parseColor(var));
        notifyDataSetChanged();
    }

    /**
     * 设置分隔线尺寸
     * @param var
     */
    @JSMethod
    public void setDividerWidth(int var) {
        v_gridPager.setDividerWidth(weiuiScreenUtils.weexPx2dp(getInstance(), var, 1));
        notifyDataSetChanged();
    }

    /**
     * 设置当前页
     * @param var
     */
    @JSMethod
    public void setCurrentIndex(int var) {
        v_gridPager.setCurIndex(var);
    }

    /**
     * 获取当前页
     * @return
     */
    @JSMethod(uiThread = false)
    public int getCurrentIndex() {
        return v_gridPager.getCurIndex();
    }

    /**
     * 设置是否显示指示器
     * @param indicatorShow
     */
    @JSMethod
    public void setIndicator(boolean indicatorShow) {
        v_gridPager.setIndicator(indicatorShow);
        notifyDataSetChanged();
    }

    /**
     * 设置指示器未选颜色
     * @param indicatorUnSelectedColor
     */
    @JSMethod
    public void setIndicatorUnSelectedColor(String indicatorUnSelectedColor) {
        v_gridPager.setIndicatorUnSelectedColor(Color.parseColor(indicatorUnSelectedColor));
        notifyDataSetChanged();
    }

    /**
     * 设置指示器已选颜色
     * @param indicatorSelectedColor
     */
    @JSMethod
    public void setIndicatorSelectedColor(String indicatorSelectedColor) {
        v_gridPager.setIndicatorSelectedColor(Color.parseColor(indicatorSelectedColor));
        notifyDataSetChanged();
    }

    /**
     * 设置指示器宽度
     * @param indicatorWidth
     */
    @JSMethod
    public void setIndicatorWidth(int indicatorWidth) {
        v_gridPager.setIndicatorWidth(weiuiScreenUtils.weexPx2dp(getInstance(), indicatorWidth, 6));
        notifyDataSetChanged();
    }

    /**
     * 设置指示器高度
     * @param indicatorHeight
     */
    @JSMethod
    public void setIndicatorHeight(int indicatorHeight) {
        v_gridPager.setIndicatorHeight(weiuiScreenUtils.weexPx2dp(getInstance(), indicatorHeight, 6));
        notifyDataSetChanged();
    }
}
