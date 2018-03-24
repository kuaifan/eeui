package vip.kuaifan.weiui.ui.component.tabbar;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import vip.kuaifan.weiui.extend.module.weiuiConstants;

import vip.kuaifan.weiui.extend.module.weiuiParse;
import vip.kuaifan.weiui.extend.module.weiuiScreenUtils;
import vip.kuaifan.weiui.extend.view.tablayout.CommonTabLayout;
import vip.kuaifan.weiui.extend.view.tablayout.listener.CustomTabEntity;
import vip.kuaifan.weiui.extend.view.tablayout.listener.OnTabSelectListener;

import com.alibaba.weex.plugin.annotation.WeexComponent;
import com.taobao.weex.IWXRenderListener;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.common.OnWXScrollListener;
import com.taobao.weex.common.WXRenderStrategy;
import com.taobao.weex.dom.flex.CSSFlexDirection;
import com.taobao.weex.ui.component.WXComponent;
import vip.kuaifan.weiui.R;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.dom.WXDomObject;
import com.taobao.weex.ui.component.WXVContainer;
import vip.kuaifan.weiui.extend.module.weiuiCommon;
import vip.kuaifan.weiui.extend.module.weiuiJson;
import vip.kuaifan.weiui.ui.component.tabbar.adapter.TabbarAdapter;
import vip.kuaifan.weiui.extend.view.NoAnimationViewPager;
import vip.kuaifan.weiui.ui.component.tabbar.bean.TabbarBean;
import vip.kuaifan.weiui.ui.component.tabbar.bean.WXSDKBean;
import vip.kuaifan.weiui.ui.component.tabbar.entity.TabbarEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@WeexComponent(names = {"weiui_tabbar"})
public class Tabbar extends WXVContainer<ViewGroup> {

    private static final String TAG = "Tabbar";

    private View mView;

    private String tabbarType;

    private NoAnimationViewPager mViewPager;

    private TabbarAdapter mTabPagerAdapter;

    private CommonTabLayout mTabLayoutTop;

    private CommonTabLayout mTabLayoutBottom;

    private ArrayList<CustomTabEntity> mTabEntity = new ArrayList<>();

    private ArrayList<View> mViewList = new ArrayList<>();

    private Map<String, WXSDKBean> WXSDKList = new HashMap<>();

    public Tabbar(WXSDKInstance instance, WXDomObject node, WXVContainer parent) {
        super(instance, node, parent);
        node.setFlexDirection(CSSFlexDirection.ROW);
    }

    @Override
    protected ViewGroup initComponentHostView(@NonNull Context context) {
        mView = ((Activity) context).getLayoutInflater().inflate(R.layout.layout_weiui_tabbar, null);
        initPagerView();
        //
        return (ViewGroup) mView;
    }

    @Override
    public void addSubView(View view, int index) {
        if (view == null || getRealView() == null) {
            return;
        }
        if (view instanceof TabbarPageView) {
            TabbarPageView newView = (TabbarPageView) view;
            TabbarBean barBean = newView.getBarBean();
            barBean.setView(newView);
            addPageView(barBean);
            setTabData();
        }
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

            case "tabPages":
                JSONArray pagesArray = weiuiJson.parseArray(weiuiParse.parseStr(val, null));
                if (pagesArray.size() > 0) {
                    for (int i = 0; i < pagesArray.size(); i++) {
                        JSONObject item = pagesArray.getJSONObject(i);
                        if (item != null) {
                            TabbarBean barBean = new TabbarBean();
                            for (Map.Entry<String, Object> entry : item.entrySet()) {
                                barBean = TabbarPage.setBarAttr(barBean, entry.getKey(), entry.getValue());
                            }
                            barBean.setView(weiuiJson.getString(item, "url"));
                            addPageView(barBean);
                        }
                    }
                    setTabData();
                }
                return true;

            case "tabType":
                setTabType(val);
                return true;

            case "setHeight":
            case "tabHeight":
            case "setTabHeight":
                setTabHeight(val);
                return true;

            case "indicatorStyle":
            case "setIndicatorStyle":
                mTabLayoutTop.setIndicatorStyle(weiuiParse.parseInt(val, 0));
                mTabLayoutBottom.setIndicatorStyle(weiuiParse.parseInt(val, 0));
                return true;

            case "tabPadding":
            case "setTabPadding":
                mTabLayoutTop.setTabPadding(weiuiScreenUtils.weexPx2dp(getInstance(), val, 0));
                mTabLayoutBottom.setTabPadding(weiuiScreenUtils.weexPx2dp(getInstance(), val, 0));
                return true;

            case "tabSpaceEqual":
            case "setTabSpaceEqual":
                mTabLayoutTop.setTabSpaceEqual(weiuiParse.parseBool(val, false));
                mTabLayoutBottom.setTabSpaceEqual(weiuiParse.parseBool(val, false));
                return true;

            case "tabWidth":
            case "setTabWidth":
                mTabLayoutTop.setTabWidth(weiuiScreenUtils.weexPx2dp(getInstance(), val, 0));
                mTabLayoutBottom.setTabWidth(weiuiScreenUtils.weexPx2dp(getInstance(), val, 0));
                return true;

            case "indicatorColor":
            case "setIndicatorColor":
                mTabLayoutTop.setIndicatorColor(Color.parseColor(String.valueOf(val)));
                mTabLayoutBottom.setIndicatorColor(Color.parseColor(String.valueOf(val)));
                return true;

            case "indicatorHeight":
            case "setIndicatorHeight":
                mTabLayoutTop.setIndicatorHeight(weiuiScreenUtils.weexPx2dp(getInstance(), val, 0));
                mTabLayoutBottom.setIndicatorHeight(weiuiScreenUtils.weexPx2dp(getInstance(), val, 0));
                return true;

            case "indicatorWidth":
            case "setIndicatorWidth":
                mTabLayoutTop.setIndicatorWidth(weiuiScreenUtils.weexPx2dp(getInstance(), val, 0));
                mTabLayoutBottom.setIndicatorWidth(weiuiScreenUtils.weexPx2dp(getInstance(), val, 0));
                return true;

            case "indicatorCornerRadius":
            case "setIndicatorCornerRadius":
                mTabLayoutTop.setIndicatorCornerRadius(weiuiScreenUtils.weexPx2dp(getInstance(), val, 0));
                mTabLayoutBottom.setIndicatorCornerRadius(weiuiScreenUtils.weexPx2dp(getInstance(), val, 0));
                return true;

            case "indicatorGravity":
            case "setIndicatorGravity":
                if (weiuiParse.parseInt(val, 0) == 1) {
                    mTabLayoutTop.setIndicatorGravity(Gravity.TOP);
                    mTabLayoutBottom.setIndicatorGravity(Gravity.TOP);
                }else{
                    mTabLayoutTop.setIndicatorGravity(Gravity.BOTTOM);
                    mTabLayoutBottom.setIndicatorGravity(Gravity.BOTTOM);
                }
                return true;

            case "indicatorAnimDuration":
            case "setIndicatorAnimDuration":
                mTabLayoutTop.setIndicatorAnimDuration(weiuiParse.parseLong(String.valueOf(val)));
                mTabLayoutBottom.setIndicatorAnimDuration(weiuiParse.parseLong(String.valueOf(val)));
                return true;

            case "indicatorAnimEnable":
            case "setIndicatorAnimEnable":
                mTabLayoutTop.setIndicatorAnimEnable(weiuiParse.parseBool(val, true));
                mTabLayoutBottom.setIndicatorAnimEnable(weiuiParse.parseBool(val, true));
                return true;

            case "indicatorBounceEnable":
            case "setIndicatorBounceEnable":
                mTabLayoutTop.setIndicatorBounceEnable(weiuiParse.parseBool(val, true));
                mTabLayoutBottom.setIndicatorBounceEnable(weiuiParse.parseBool(val, true));
                return true;

            case "underlineColor":
            case "setUnderlineColor":
                mTabLayoutTop.setUnderlineColor(Color.parseColor(String.valueOf(val)));
                mTabLayoutBottom.setUnderlineColor(Color.parseColor(String.valueOf(val)));
                return true;

            case "underlineHeight":
            case "setUnderlineHeight":
                mTabLayoutTop.setUnderlineHeight(weiuiScreenUtils.weexPx2dp(getInstance(), val, 0));
                mTabLayoutBottom.setUnderlineHeight(weiuiScreenUtils.weexPx2dp(getInstance(), val, 0));
                return true;

            case "underlineGravity":
            case "setUnderlineGravity":
                if (weiuiParse.parseInt(val, 0) == 1) {
                    mTabLayoutTop.setUnderlineGravity(Gravity.TOP);
                    mTabLayoutBottom.setUnderlineGravity(Gravity.TOP);
                }else{
                    mTabLayoutTop.setUnderlineGravity(Gravity.BOTTOM);
                    mTabLayoutBottom.setUnderlineGravity(Gravity.BOTTOM);
                }
                return true;

            case "dividerColor":
            case "setDividerColor":
                mTabLayoutTop.setDividerColor(Color.parseColor(String.valueOf(val)));
                mTabLayoutBottom.setDividerColor(Color.parseColor(String.valueOf(val)));
                return true;

            case "dividerWidth":
            case "setDividerWidth":
                mTabLayoutTop.setDividerWidth(weiuiScreenUtils.weexPx2dp(getInstance(), val, 0));
                mTabLayoutBottom.setDividerWidth(weiuiScreenUtils.weexPx2dp(getInstance(), val, 0));
                return true;

            case "dividerPadding":
            case "setDividerPadding":
                mTabLayoutTop.setDividerPadding(weiuiScreenUtils.weexPx2dp(getInstance(), val, 0));
                mTabLayoutBottom.setDividerPadding(weiuiScreenUtils.weexPx2dp(getInstance(), val, 0));
                return true;

            case "textsize":
            case "setTextsize":
                setTabTextsize(val);
                return true;

            case "textSelectColor":
            case "setTextSelectColor":
                setTabTextSelectColor(val);
                return true;

            case "textUnselectColor":
            case "setTextUnselectColor":
                setTabTextUnselectColor(val);
                return true;

            case "textBold":
            case "setTextBold":
                setTabTextBold(val);
                return true;

            case "iconVisible":
            case "setIconVisible":
                setTabIconVisible(val);
                return true;

            case "iconGravity":
            case "setIconGravity":
                if (weiuiParse.parseInt(val, 0) == 1) {
                    mTabLayoutTop.setIconGravity(Gravity.TOP);
                    mTabLayoutBottom.setIconGravity(Gravity.TOP);
                }else{
                    mTabLayoutTop.setIconGravity(Gravity.BOTTOM);
                    mTabLayoutBottom.setIconGravity(Gravity.BOTTOM);
                }
                return true;

            case "iconWidth":
            case "setIconWidth":
                setTabIconWidth(val);
                return true;

            case "iconHeight":
            case "setIconHeight":
                setTabIconHeight(val);
                return true;

            case "iconMargin":
            case "setIconMargin":
                mTabLayoutTop.setIconMargin(weiuiScreenUtils.weexPx2dp(getInstance(), val, 0));
                mTabLayoutBottom.setIconMargin(weiuiScreenUtils.weexPx2dp(getInstance(), val, 0));
                return true;

            case "textAllCaps":
            case "setTextAllCaps":
                mTabLayoutTop.setTextAllCaps(weiuiParse.parseBool(val, false));
                mTabLayoutBottom.setTextAllCaps(weiuiParse.parseBool(val, false));
                return true;

            case "@styleScope":
                if (tabbarType == null) {
                    setTabType("bottom");
                }
                return false;

            default:
                return false;
        }
    }

    /**
     * 初始化组件
     */
    private void initPagerView() {
        mViewPager = mView.findViewById(R.id.viewpager);
        mTabLayoutTop = mView.findViewById(R.id.navigation_top);
        mTabLayoutBottom = mView.findViewById(R.id.navigation_bottom);
        //
        mTabPagerAdapter = new TabbarAdapter(mViewList);
        mViewPager.setAdapter(mTabPagerAdapter);
        mViewPager.setCurrentItem(0);
        mViewPager.addOnPageChangeListener(mOnPageChangeListener);
        mTabLayoutTop.setOnTabSelectListener(mOnTabSelectListener);
        mTabLayoutBottom.setOnTabSelectListener(mOnTabSelectListener);
    }

    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (getDomObject().getEvents().contains(weiuiConstants.Event.PAGE_SCROLLED)) {
                Map<String, Object> data = new HashMap<>();
                data.put("position", position);
                data.put("positionOffset", positionOffset);
                data.put("positionOffsetPixels", positionOffsetPixels);
                fireEvent(weiuiConstants.Event.PAGE_SCROLLED, data);
            }
        }

        @Override
        public void onPageSelected(int position) {
            mTabLayoutTop.setCurrentTab(position);
            mTabLayoutBottom.setCurrentTab(position);
            //
            if (getDomObject().getEvents().contains(weiuiConstants.Event.PAGE_SELECTED)) {
                Map<String, Object> data = new HashMap<>();
                data.put("position", position);
                fireEvent(weiuiConstants.Event.PAGE_SELECTED, data);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (getDomObject().getEvents().contains(weiuiConstants.Event.PAGE_SCROLL_STATE_CHANGED)) {
                Map<String, Object> data = new HashMap<>();
                data.put("state", state);
                fireEvent(weiuiConstants.Event.PAGE_SCROLL_STATE_CHANGED, data);
            }
        }
    };

    private OnTabSelectListener mOnTabSelectListener = new OnTabSelectListener() {
        @Override
        public void onTabSelect(int position) {
            mViewPager.setCurrentItem(position);
            if (getDomObject().getEvents().contains(weiuiConstants.Event.TAB_SELECT)) {
                Map<String, Object> data = new HashMap<>();
                data.put("position", position);
                fireEvent(weiuiConstants.Event.TAB_SELECT, data);
            }
        }

        @Override
        public void onTabReselect(int position) {
            if (getDomObject().getEvents().contains(weiuiConstants.Event.TAB_RESELECT)) {
                Map<String, Object> data = new HashMap<>();
                data.put("position", position);
                fireEvent(weiuiConstants.Event.TAB_RESELECT, data);
            }
        }
    };

    /**
     * 添加页面
     * @param barBean
     */
    private void addPageView(TabbarBean barBean) {
        View view = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.layout_weiui_tabbar_page, null);
        SwipeRefreshLayout v_swipeRefresh = view.findViewById(R.id.v_swipeRefresh);
        FrameLayout v_container = view.findViewById(R.id.v_container);
        ProgressBar v_progress = view.findViewById(R.id.v_progress);
        //
        WXSDKBean sdkBean = new WXSDKBean();
        sdkBean.setSwipeRefresh(v_swipeRefresh);
        sdkBean.setContainer(v_container);
        sdkBean.setProgress(v_progress);
        sdkBean.setView(barBean.getView());
        if (barBean.getView() instanceof String) {
            sdkBean.setType("urlView");
            WXSDKList.put(barBean.getTabName(), sdkBean);
            addWXSDKView(barBean.getTabName());
        }else if (barBean.getView() instanceof TabbarPageView) {
            sdkBean.setType("pageView");
            WXSDKList.put(barBean.getTabName(), sdkBean);
            addWXPageView(barBean.getTabName());
        }
        //
        if (getDomObject().getEvents().contains(weiuiConstants.Event.REFRESH_LISTENER)) {
            sdkBean.getSwipeRefresh().setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
            sdkBean.getSwipeRefresh().setOnRefreshListener(() -> {
                Map<String, Object> data = new HashMap<>();
                data.put("tabName", barBean.getTabName());
                data.put("position", getTabPosition(barBean.getTabName()));
                fireEvent(weiuiConstants.Event.REFRESH_LISTENER, data);
            });
            sdkBean.setSwipeRefreshEnable(true);
        }else{
            sdkBean.setSwipeRefreshEnable(false);
            sdkBean.getSwipeRefresh().setEnabled(false);
        }
        //
        mTabEntity.add(new TabbarEntity(barBean));
        mViewList.add(view);
        mTabPagerAdapter.setListViews(mViewList);
        mTabPagerAdapter.notifyDataSetChanged();
    }

    /**
     * 添加、刷新 浏览器页面
     * @param tabName
     */
    private void addWXSDKView(String tabName) {
        WXSDKBean sdkBean = WXSDKList.get(tabName);
        if (sdkBean == null || !sdkBean.getType().equals("urlView")) {
            return;
        }
        String url = String.valueOf(sdkBean.getView());
        if (url == null) {
            return;
        }
        //
        if (sdkBean.getInstance() != null) {
            sdkBean.getInstance().registerRenderListener(null);
            sdkBean.getInstance().destroy();
        }
        //
        sdkBean.getProgress().setVisibility(View.VISIBLE);
        sdkBean.setInstance(new WXSDKInstance(getContext()));
        sdkBean.getInstance().registerRenderListener(new IWXRenderListener() {
            @Override
            public void onViewCreated(WXSDKInstance instance, View view) {
                sdkBean.getProgress().setVisibility(View.GONE);
                sdkBean.getContainer().removeAllViews();
                sdkBean.getContainer().addView(view);
                //
                if (getDomObject().getEvents().contains(weiuiConstants.Event.VIEW_CREATED)) {
                    Map<String, Object> data = new HashMap<>();
                    data.put("tabName", tabName);
                    data.put("url", url);
                    fireEvent(weiuiConstants.Event.VIEW_CREATED, data);
                }
            }
            @Override
            public void onRenderSuccess(WXSDKInstance instance, int width, int height) {

            }
            @Override
            public void onRefreshSuccess(WXSDKInstance instance, int width, int height) {

            }
            @Override
            public void onException(WXSDKInstance instance, String errCode, String msg) {

            }
        });
        //
        sdkBean.getInstance().registerOnWXScrollListener(new OnWXScrollListener() {
            @Override
            public void onScrolled(View view, int x, int y) {
                if (getDomObject().getEvents().contains(weiuiConstants.Event.SCROLLED)) {
                    Map<String, Object> data = new HashMap<>();
                    data.put("tabName", tabName);
                    data.put("x", x);
                    data.put("y", y);
                    fireEvent(weiuiConstants.Event.SCROLLED, data);
                }
            }

            @Override
            public void onScrollStateChanged(View view, int x, int y, int newState) {
                if (getDomObject().getEvents().contains(weiuiConstants.Event.SCROLL_STATE_CHANGED)) {
                    Map<String, Object> data = new HashMap<>();
                    data.put("tabName", tabName);
                    data.put("x", x);
                    data.put("y", y);
                    data.put("newState", newState);
                    fireEvent(weiuiConstants.Event.SCROLL_STATE_CHANGED, data);
                }
                if (sdkBean.getSwipeRefreshEnable()) {
                    if (y == 0) {
                        sdkBean.getSwipeRefresh().setEnabled(true);
                    }else{
                        sdkBean.getSwipeRefresh().setEnabled(false);
                    }
                }
            }
        });
        //
        Map<String, Object> options = new HashMap<>();
        options.put(WXSDKInstance.BUNDLE_URL, url);
        sdkBean.getInstance().renderByUrl("Tabbar:" + tabName, url, options, null, WXRenderStrategy.APPEND_ASYNC);
    }

    /**
     * 添加子页面
     * @param tabName
     */
    private void addWXPageView(String tabName) {
        WXSDKBean sdkBean = WXSDKList.get(tabName);
        if (sdkBean == null || !sdkBean.getType().equals("pageView")) {
            return;
        }
        TabbarPageView view = (TabbarPageView) sdkBean.getView();
        if (view == null) {
            return;
        }
        //
        ViewGroup parentViewGroup = (ViewGroup) view.getParent();
        if (parentViewGroup != null ) {
            parentViewGroup.removeView(view);
        }
        sdkBean.getProgress().setVisibility(View.GONE);
        sdkBean.getContainer().removeAllViews();
        sdkBean.getContainer().addView(view);
        //
        if (getDomObject().getEvents().contains(weiuiConstants.Event.VIEW_CREATED)) {
            Map<String, Object> data = new HashMap<>();
            data.put("tabName", tabName);
            data.put("url", null);
            fireEvent(weiuiConstants.Event.VIEW_CREATED, data);
        }
    }

    /**
     * 设置tab数据
     */
    private void setTabData() {
        mTabLayoutTop.setTabData(mTabEntity);
        mTabLayoutBottom.setTabData(mTabEntity);
        //
        if (mTabEntity != null && mTabEntity.size() > 0) {
            for (int i = 0; i < mTabEntity.size(); i++) {
                CustomTabEntity temp = mTabEntity.get(i);
                String tabName = temp.getTabName();
                int message = temp.getTabMessage();
                if (message > 0) {
                    showMsg(tabName, message);
                } else {
                    boolean dot = temp.isTabDot();
                    if (dot) {
                        showDot(tabName);
                    } else {
                        hideMsg(tabName);
                    }
                }
            }
        }
    }

    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/

    /**
     * 根据名称获取位置
     * @param tabName
     * @return
     */
    @JSMethod(uiThread = false)
    public int getTabPosition(String tabName) {
        if (mTabEntity != null && mTabEntity.size() > 0) {
            for (int i = 0; i < mTabEntity.size(); i++) {
                String temp = mTabEntity.get(i).getTabName();
                if (temp != null && temp.equals(tabName)) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * 根据位置获取名称
     * @param position
     * @return
     */
    @JSMethod(uiThread = false)
    public String getTabName(int position) {
        if (mTabEntity != null && mTabEntity.size() > 0) {
            for (int i = 0; i < mTabEntity.size(); i++) {
                if (i == position) {
                    return mTabEntity.get(i).getTabName();
                }
            }
        }
        return null;
    }

    /**
     * 显示未读信息
     * @param tabName
     * @param message
     */
    @JSMethod
    public void showMsg(String tabName, int message) {
        int position = getTabPosition(tabName);
        if (position > -1) {
            mTabLayoutTop.showMsg(position, message);
            mTabLayoutBottom.showMsg(position, message);
        }
    }

    /**
     * 显示未读红点
     * @param tabName
     */
    @JSMethod
    public void showDot(String tabName){
        int position = getTabPosition(tabName);
        if (position > -1) {
            mTabLayoutTop.showDot(position);
            mTabLayoutBottom.showDot(position);
        }
    }

    /**
     * 隐藏未读信息、未读红点
     * @param tabName
     */
    @JSMethod
    public void hideMsg(String tabName){
        int position = getTabPosition(tabName);
        if (position > -1) {
            mTabLayoutTop.hideMsg(position);
            mTabLayoutBottom.hideMsg(position);
        }
    }

    /**
     * 删除所有页面
     */
    @JSMethod
    public void removePageAll() {
        mTabEntity = new ArrayList<>();
        setTabData();
        //
        mViewList = new ArrayList<>();
        mViewPager.removeAllViews();
        mTabPagerAdapter.setListViews(mViewList);
        mTabPagerAdapter.notifyDataSetChanged();
    }

    /**
     * 删除指定页面
     * @param tabName
     */
    @JSMethod
    public void removePageAt(String tabName) {
        int position = getTabPosition(tabName);
        if (position > -1) {
            if (mViewPager.getCurrentItem() >= mTabEntity.size() - 1) {
                mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1);
            }
            mTabEntity.remove(position);
            setTabData();
            //
            mViewList.remove(position);
            mViewPager.removeAllViews();
            mTabPagerAdapter.setListViews(mViewList);
            mTabPagerAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 切换页面
     * @param tabName
     */
    @JSMethod
    public void setCurrentItem(String tabName) {
        int position = getTabPosition(tabName);
        if (position > -1) {
            mViewPager.setCurrentItem(position);
        }
    }

    /**
     * 设置下拉刷新状态
     * @param tabName
     * @param refreshing
     */
    @JSMethod
    public void setRefreshing(String tabName, boolean refreshing){
        WXSDKBean temp = WXSDKList.get(tabName);
        if (temp != null) {
            temp.getSwipeRefresh().setRefreshing(refreshing);
        }
    }

    /**
     * 跳转页面
     * @param tabName
     * @param url
     */
    @JSMethod
    public void goUrl(String tabName, String url) {
        WXSDKBean bean = WXSDKList.get(tabName);
        if (bean != null) {
            bean.setView(url);
            WXSDKList.put(tabName, bean);
            addWXSDKView(tabName);
        }
    }

    /**
     * 刷新页面
     * @param tabName
     */
    @JSMethod
    public void reload(String tabName) {
        addWXSDKView(tabName);
    }

    /**
     * 设置导航栏显示位置
     * @param var
     */
    @JSMethod
    public void setTabType(Object var) {
        String tabType = weiuiParse.parseStr(var, "bottom");
        tabbarType = tabType.toLowerCase();
        mTabLayoutTop.setVisibility(View.GONE);
        mTabLayoutBottom.setVisibility(View.GONE);
        //
        if (tabbarType.contains("top")) {
            mTabLayoutTop.setVisibility(View.VISIBLE);
        }
        if (tabbarType.contains("bottom")) {
            mTabLayoutBottom.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 设置导航栏高度
     * @param var
     */
    @JSMethod
    public void setTabHeight(Object var) {
        weiuiCommon.setViewWidthHeight(mTabLayoutTop, -1, weiuiScreenUtils.weexPx2dp(getInstance(), weiuiParse.parseInt(var, 0)));
        weiuiCommon.setViewWidthHeight(mTabLayoutBottom, -1, weiuiScreenUtils.weexPx2dp(getInstance(), weiuiParse.parseInt(var, 0)));
    }

    /**
     * 设置导航栏字体大小
     * @param var
     */
    @JSMethod
    public void setTabTextsize(Object var) {
        mTabLayoutTop.setTextsize(weiuiParse.parseFloat(var, 0f));
        mTabLayoutBottom.setTextsize(weiuiParse.parseFloat(var, 0f));
    }

    /**
     * 设置导航栏字体加粗
     * @param var
     */
    @JSMethod
    public void setTabTextBold(Object var) {
        mTabLayoutTop.setTextBold(weiuiParse.parseInt(var, 0));
        mTabLayoutBottom.setTextBold(weiuiParse.parseInt(var, 0));
    }

    /**
     * 设置导航栏未选字体颜色
     * @param var
     */
    @JSMethod
    public void setTabTextUnselectColor(Object var) {
        mTabLayoutTop.setTextUnselectColor(weiuiParse.parseInt(var, 0));
        mTabLayoutBottom.setTextUnselectColor(weiuiParse.parseInt(var, 0));
    }

    /**
     * 设置导航栏选择字体颜色
     * @param var
     */
    @JSMethod
    public void setTabTextSelectColor(Object var) {
        mTabLayoutTop.setTextSelectColor(weiuiParse.parseInt(var, 0));
        mTabLayoutBottom.setTextSelectColor(weiuiParse.parseInt(var, 0));
    }

    /**
     * 设置导航栏图标可见
     * @param var
     */
    @JSMethod
    public void setTabIconVisible(Object var) {
        mTabLayoutTop.setIconVisible(weiuiParse.parseBool(var, true));
        mTabLayoutBottom.setIconVisible(weiuiParse.parseBool(var, true));
    }

    /**
     * 设置导航栏图标宽度
     * @param var
     */
    @JSMethod
    public void setTabIconWidth(Object var) {
        mTabLayoutTop.setIconWidth(weiuiScreenUtils.weexPx2dp(getInstance(), weiuiParse.parseInt(var, 0)));
        mTabLayoutBottom.setIconWidth(weiuiScreenUtils.weexPx2dp(getInstance(), weiuiParse.parseInt(var, 0)));
    }

    /**
     * 设置导航栏图标高度
     * @param var
     */
    @JSMethod
    public void setTabIconHeight(Object var) {
        mTabLayoutTop.setIconHeight(weiuiScreenUtils.weexPx2dp(getInstance(), weiuiParse.parseInt(var, 0)));
        mTabLayoutBottom.setIconHeight(weiuiScreenUtils.weexPx2dp(getInstance(), weiuiParse.parseInt(var, 0)));
    }
}
