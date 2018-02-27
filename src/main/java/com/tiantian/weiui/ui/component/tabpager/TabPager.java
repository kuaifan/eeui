package com.tiantian.weiui.ui.component.tabpager;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tiantian.weiui.extend.tablayout.CommonTabLayout;
import com.tiantian.weiui.extend.tablayout.listener.CustomTabEntity;
import com.tiantian.weiui.extend.tablayout.listener.OnTabSelectListener;
import com.taobao.weex.IWXRenderListener;
import com.taobao.weex.common.WXRenderStrategy;
import com.taobao.weex.utils.WXUtils;
import com.tiantian.weiui.R;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.dom.WXDomObject;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXVContainer;
import com.tiantian.weiui.extend.module.Common;
import com.tiantian.weiui.extend.module.Json;
import com.tiantian.weiui.ui.component.tabpager.adapter.TabPagerAdapter;
import com.tiantian.weiui.extend.view.NoAnimationViewPager;
import com.tiantian.weiui.ui.component.tabpager.bean.WXSDKBean;
import com.tiantian.weiui.ui.component.tabpager.entity.TabEntity;
import com.tiantian.weiui.ui.module.weiuiEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class TabPager extends WXComponent<View> {

    private static final String TAG = "TabPager";

    private View mView;

    private NoAnimationViewPager viewpager;

    private TabPagerAdapter mTabPagerAdapter;

    private CommonTabLayout navigation;

    private ArrayList<CustomTabEntity> tabList = new ArrayList<>();

    private ArrayList<View> viewList = new ArrayList<>();

    private Map<Integer, WXSDKBean> WXSDKList = new HashMap<>();

    public TabPager(WXSDKInstance instance, WXDomObject dom, WXVContainer parent) {
        super(instance, dom, parent);
    }

    @Override
    protected View initComponentHostView(@NonNull Context context) {
        mView = ((Activity) context).getLayoutInflater().inflate(R.layout.layout_tabpager, null);
        initPagerView();
        //
        return mView;
    }

    @Override
    protected boolean setProperty(String key, Object param) {
        switch (key) {
            case "pages":
                tabList = new ArrayList<>();
                viewList = new ArrayList<>();
                JSONArray pagesArray = Json.parseArray(WXUtils.getString(param, null));
                if (pagesArray.size() > 0) {
                    for (int i = 0; i < pagesArray.size(); i++) {
                        JSONObject item = pagesArray.getJSONObject(i);
                        String title = Json.getString(item, "title");
                        String url = Json.getString(item, "url");
                        String selectedIcon = Json.getString(item, "selectedIcon");
                        String unSelectedIcon = Json.getString(item, "unSelectedIcon");
                        addPageView(i, title, url, selectedIcon, unSelectedIcon);
                    }
                    navigation.setTabData(tabList);
                    for (int i = 0; i < pagesArray.size(); i++) {
                        JSONObject item = pagesArray.getJSONObject(i);
                        int message = Json.getInt(item, "message");
                        if (message > 0) {
                            navigation.showMsg(i, message);
                        } else {
                            boolean dot = Json.getBoolean(item, "dot");
                            if (dot) {
                                navigation.showDot(i);
                            } else {
                                navigation.hideMsg(i);
                            }
                        }
                    }
                }
                return true;

            case "tabHeight":
                Common.setViewWidthHeight(navigation, -1, navigation.dp2px(Common.parseInt(WXUtils.getString(param, null))));
                return true;

            case "ui":
                JSONObject uiObject = Json.parseObject(WXUtils.getString(param, null));
                if (uiObject.size() > 0) {
                    for (Map.Entry<String, Object> entry : uiObject.entrySet()) {
                        switch (entry.getKey()) {
                            case "height":
                            case "setHeight":
                                Common.setViewWidthHeight(navigation, -1, navigation.dp2px((int) entry.getValue()));
                                break;

                            case "indicatorStyle":
                            case "setIndicatorStyle":
                                navigation.setIndicatorStyle((int) entry.getValue());
                                break;

                            case "tabPadding":
                            case "setTabPadding":
                                navigation.setTabPadding((float) entry.getValue());
                                break;

                            case "tabSpaceEqual":
                            case "setTabSpaceEqual":
                                navigation.setTabSpaceEqual((boolean) entry.getValue());
                                break;

                            case "tabWidth":
                            case "setTabWidth":
                                navigation.setTabWidth((float) entry.getValue());
                                break;

                            case "indicatorColor":
                            case "setIndicatorColor":
                                navigation.setIndicatorColor((int) entry.getValue());
                                break;

                            case "indicatorHeight":
                            case "setIndicatorHeight":
                                navigation.setIndicatorHeight((float) entry.getValue());
                                break;

                            case "indicatorWidth":
                            case "setIndicatorWidth":
                                navigation.setIndicatorWidth((float) entry.getValue());
                                break;

                            case "indicatorCornerRadius":
                            case "setIndicatorCornerRadius":
                                navigation.setIndicatorCornerRadius((float) entry.getValue());
                                break;

                            case "indicatorGravity":
                            case "setIndicatorGravity":
                                navigation.setIndicatorGravity((int) entry.getValue());
                                break;

                            case "indicatorAnimDuration":
                            case "setIndicatorAnimDuration":
                                navigation.setIndicatorAnimDuration((long) entry.getValue());
                                break;

                            case "indicatorAnimEnable":
                            case "setIndicatorAnimEnable":
                                navigation.setIndicatorAnimEnable((boolean) entry.getValue());
                                break;

                            case "indicatorBounceEnable":
                            case "setIndicatorBounceEnable":
                                navigation.setIndicatorBounceEnable((boolean) entry.getValue());
                                break;

                            case "underlineColor":
                            case "setUnderlineColor":
                                navigation.setUnderlineColor((int) entry.getValue());
                                break;

                            case "underlineHeight":
                            case "setUnderlineHeight":
                                navigation.setUnderlineHeight((float) entry.getValue());
                                break;

                            case "underlineGravity":
                            case "setUnderlineGravity":
                                navigation.setUnderlineGravity((int) entry.getValue());
                                break;

                            case "dividerColor":
                            case "setDividerColor":
                                navigation.setDividerColor((int) entry.getValue());
                                break;

                            case "dividerWidth":
                            case "setDividerWidth":
                                navigation.setDividerWidth((float) entry.getValue());
                                break;

                            case "dividerPadding":
                            case "setDividerPadding":
                                navigation.setDividerPadding((float) entry.getValue());
                                break;

                            case "textsize":
                            case "setTextsize":
                                navigation.setTextsize((float) entry.getValue());
                                break;

                            case "textSelectColor":
                            case "setTextSelectColor":
                                navigation.setTextSelectColor((int) entry.getValue());
                                break;

                            case "textUnselectColor":
                            case "setTextUnselectColor":
                                navigation.setTextUnselectColor((int) entry.getValue());
                                break;

                            case "textBold":
                            case "setTextBold":
                                navigation.setTextBold((int) entry.getValue());
                                break;

                            case "iconVisible":
                            case "setIconVisible":
                                navigation.setIconVisible((boolean) entry.getValue());
                                break;

                            case "iconGravity":
                            case "setIconGravity":
                                navigation.setIconGravity((int) entry.getValue());
                                break;

                            case "iconWidth":
                            case "setIconWidth":
                                navigation.setIconWidth((float) entry.getValue());
                                break;

                            case "iconHeight":
                            case "setIconHeight":
                                navigation.setIconHeight((float) entry.getValue());
                                break;

                            case "iconMargin":
                            case "setIconMargin":
                                navigation.setIconMargin((float) entry.getValue());
                                break;

                            case "textAllCaps":
                            case "setTextAllCaps":
                                navigation.setTextAllCaps((boolean) entry.getValue());
                                break;
                        }
                    }
                }
                return true;
        }
        return super.setProperty(key, param);
    }

    /**
     * 初始化组件
     */
    private void initPagerView() {
        viewpager = mView.findViewById(R.id.viewpager);
        navigation = mView.findViewById(R.id.navigation);
        //
        mTabPagerAdapter = new TabPagerAdapter(viewList);
        viewpager.setAdapter(mTabPagerAdapter);
        viewpager.setCurrentItem(0);
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        //
        navigation.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewpager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        //
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                navigation.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 添加页面
     * @param position
     * @param title
     * @param url
     * @param selectedIcon
     * @param unSelectedIcon
     */
    private void addPageView(int position, String title, String url, String selectedIcon, String unSelectedIcon) {
        View view = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.layout_tabpager_fragment, null);
        final FrameLayout v_container = view.findViewById(R.id.v_container);
        final ProgressBar v_progress = view.findViewById(R.id.v_progress);
        //
        WXSDKBean bean = new WXSDKBean();
        bean.setContainer(v_container);
        bean.setProgress(v_progress);
        WXSDKList.put(position, bean);
        addWXSDKView(position, url);
        //
        tabList.add(new TabEntity(title, selectedIcon, unSelectedIcon));
        viewList.add(view);
        mTabPagerAdapter.setListViews(viewList);
        mTabPagerAdapter.notifyDataSetChanged();
    }

    /**
     * 添加浏览器
     * @param position
     * @param url
     */
    private void addWXSDKView(int position, String url) {
        final WXSDKBean bean = WXSDKList.get(position);
        if (bean == null) {
            return;
        }
        if (url == null) {
            if (bean.getInstance() == null) {
                return;
            }
            url = bean.getInstance().getBundleUrl();
        }
        //
        if (bean.getInstance() != null) {
            bean.getInstance().registerRenderListener(null);
            bean.getInstance().destroy();
        }
        //
        bean.getProgress().setVisibility(View.VISIBLE);
        bean.setInstance(new WXSDKInstance(getContext()));
        bean.getInstance().registerRenderListener(new IWXRenderListener() {
            @Override
            public void onViewCreated(WXSDKInstance instance, View view) {
                bean.getProgress().setVisibility(View.GONE);
                bean.getContainer().removeAllViews();
                bean.getContainer().addView(view);
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
        Map<String, Object> options = new HashMap<>();
        options.put(WXSDKInstance.BUNDLE_URL, url);
        bean.getInstance().renderByUrl("TabPager:" + position, url, options, null, WXRenderStrategy.APPEND_ASYNC);
    }

    /**
     * 删除所有页面
     */
    private void removePageAll() {
        tabList = new ArrayList<>();
        navigation.setTabData(tabList);
        //
        viewList = new ArrayList<>();
        viewpager.removeAllViews();
        mTabPagerAdapter.setListViews(viewList);
        mTabPagerAdapter.notifyDataSetChanged();
    }

    /**
     * 删除指定页面
     * @param position
     */
    private void removePageAt(int position) {
        if (viewpager.getCurrentItem() >= tabList.size() - 1) {
            viewpager.setCurrentItem(viewpager.getCurrentItem() - 1);
        }
        tabList.remove(position);
        navigation.setTabData(tabList);
        //
        viewList.remove(position);
        viewpager.removeAllViews();
        mTabPagerAdapter.setListViews(viewList);
        mTabPagerAdapter.notifyDataSetChanged();
    }

    /**
     * JS 行为
     * @param action
     * @param params
     */
    public void setAction(String action, JSONObject params) {
        if (!TextUtils.isEmpty(action)) {
            try {
                int position =  Json.getInt(params, "position");
                if (action.equals(weiuiEvent.Action.TabPager.showmsg.name())) {
                    navigation.showMsg(position, Json.getInt(params, "num"));
                } else if (action.equals(weiuiEvent.Action.TabPager.showdot.name())) {
                    navigation.showDot(position);
                } else if (action.equals(weiuiEvent.Action.TabPager.hidemsg.name())) {
                    navigation.hideMsg(position);
                } else if (action.equals(weiuiEvent.Action.TabPager.removepageall.name())) {
                    removePageAll();
                } else if (action.equals(weiuiEvent.Action.TabPager.removepageat.name())) {
                    removePageAt(position);
                } else if (action.equals(weiuiEvent.Action.TabPager.setcurrentitem.name())) {
                    if (viewpager != null && tabList.size() - 1 >= position) {
                        viewpager.setCurrentItem(position);
                    }
                } else if (action.equals(weiuiEvent.Action.TabPager.load.name())) {
                    if (WXSDKList != null && WXSDKList.size() - 1 >= position) {
                        String url = Json.getString(params, "url");
                        addWXSDKView(position, url);
                    }
                } else if (action.equals(weiuiEvent.Action.TabPager.reload.name())) {
                    if (WXSDKList != null && WXSDKList.size() - 1 >= position) {
                        addWXSDKView(position, null);
                    }
                }
            }catch (Exception ignored) { }
        }
    }
}
