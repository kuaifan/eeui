package vip.kuaifan.weiui.ui.component.recyler;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.alibaba.weex.plugin.annotation.WeexComponent;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.dom.WXDomObject;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXVContainer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vip.kuaifan.weiui.R;
import vip.kuaifan.weiui.extend.module.weiuiCommon;
import vip.kuaifan.weiui.extend.module.weiuiConstants;

import vip.kuaifan.weiui.extend.module.weiuiJson;
import vip.kuaifan.weiui.extend.module.weiuiParse;
import vip.kuaifan.weiui.extend.module.weiuiScreenUtils;
import vip.kuaifan.weiui.ui.component.recyler.adapter.RecylerAdapter;
import vip.kuaifan.weiui.ui.component.recyler.bean.SwipeButtonBean;
import vip.kuaifan.weiui.ui.component.recyler.listener.RecylerOnBottomScrollListener;
import vip.kuaifan.weiui.ui.component.recyler.view.SwipeItemLayout;


@WeexComponent(names = {"weiui_recyler", "weiui_list", "wi_recyler", "wi_list"})
public class Recyler extends WXVContainer<ViewGroup> implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "Recyler";

    private View mView;

    private SwipeRefreshLayout v_swipeRefresh;
    private RecyclerView v_recyler;

    private boolean isSwipeRefresh;
    private boolean isRefreshAuto;
    private int gridRow = 1;
    private int lastVisibleItem = 0;
    private boolean hasMore = false;
    private boolean isLoading = false;
    private GridLayoutManager mLayoutManager;
    private RecylerAdapter mAdapter;
    private Runnable listUpdateRunnable;
    private Handler mHandler = new Handler();
    private int footIdentify;

    public Recyler(WXSDKInstance instance, WXDomObject dom, WXVContainer parent) {
        super(instance, dom, parent);
    }

    @Override
    protected ViewGroup initComponentHostView(@NonNull Context context) {
        mView = ((Activity) context).getLayoutInflater().inflate(R.layout.layout_weiui_recyler, null);
        initPagerView();
        //
        listUpdateRunnable = () -> {
            if (getHostView() != null && mAdapter != null) {
                mAdapter.notifyDataSetChanged();
            }
        };
        //
        formatAttrs(getDomObject().getAttrs());
        if (isRefreshAuto) {
            setRefreshing(true);
        }
        //
        if (getDomObject().getEvents().contains(weiuiConstants.Event.READY)) {
            fireEvent(weiuiConstants.Event.READY, null);
        }
        //
        return (ViewGroup) mView;
    }

    private void formatAttrs(Map<String, Object> attr) {
        if (attr != null) {
            for (String key : attr.keySet()) {
                Object value = attr.get(key);
                switch (weiuiCommon.camelCaseName(key)) {
                    case "weiui":
                        JSONObject json = weiuiJson.parseObject(weiuiParse.parseStr(value, null));
                        if (json.size() > 0) {
                            Map<String, Object> data = new HashMap<>();
                            for (Map.Entry<String, Object> entry : json.entrySet()) {
                                data.put(entry.getKey(), entry.getValue());
                            }
                            formatAttrs(data);
                        }
                        break;

                    case "refreshAuto":
                        isRefreshAuto = weiuiParse.parseBool(value);
                        break;
                }
            }
        }
    }

    @Override
    public void addSubView(View view, int index) {
        if (view == null || mAdapter == null) {
            return;
        }
        mAdapter.updateList(index, view, hasMore);
        mAdapter.notifyItemInserted(index);
        notifyUpdateFoot();
    }

    @Override
    public void remove(WXComponent child, boolean destroy) {
        if (child == null || child.getHostView() == null || mAdapter == null) {
            return;
        }
        mAdapter.removeList(child.getHostView(), hasMore);
        notifyUpdateList();
        super.remove(child, destroy);
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
    public void destroy() {
        if (getHostView() != null) {
            getHostView().removeCallbacks(listUpdateRunnable);
        }
        super.destroy();
    }

    @Override
    public void onRefresh() {
        isLoading = true;
        v_swipeRefresh.setRefreshing(true);
        if (getDomObject().getEvents().contains(weiuiConstants.Event.REFRESH_LISTENER)) {
            Map<String, Object> data = new HashMap<>();
            data.put("realLastPosition", mAdapter.getRealLastPosition());
            data.put("lastVisibleItem", lastVisibleItem);
            fireEvent(weiuiConstants.Event.REFRESH_LISTENER, data);
        }
    }

    @Override
    protected boolean setProperty(String key, Object param) {
        return initProperty(key, param) || super.setProperty(key, param);
    }

    private boolean initProperty(String key, Object val) {
        switch (weiuiCommon.camelCaseName(key)) {
            case "weiui":
                JSONObject json = weiuiJson.parseObject(weiuiParse.parseStr(val, ""));
                if (json.size() > 0) {
                    for (Map.Entry<String, Object> entry : json.entrySet()) {
                        initProperty(entry.getKey(), entry.getValue());
                    }
                }
                return true;

            case "swipe":
                List<SwipeButtonBean> swipes = new ArrayList<>();
                JSONArray swipeArray = weiuiJson.parseArray(weiuiParse.parseStr(val, null));
                for (int i = 0; i < swipeArray.size(); i++) {
                    JSONObject swipeObject = weiuiJson.parseObject(weiuiParse.parseStr(swipeArray.get(i), null));
                    SwipeButtonBean bean = new SwipeButtonBean();
                    for (Map.Entry<String, Object> entry : swipeObject.entrySet()) {
                        switch (weiuiCommon.camelCaseName(entry.getKey())) {
                            case "width":
                                bean.setWidth(weiuiScreenUtils.weexPx2dp(getInstance(), entry.getValue(), 0));
                                break;

                            case "title":
                                bean.setText(weiuiParse.parseStr(entry.getValue(), "按钮" + i));
                                break;

                            case "fontSize":
                                bean.setSize(weiuiScreenUtils.weexPx2dp(getInstance(), entry.getValue(), 24));
                                break;

                            case "padding":
                                bean.setPadding(weiuiScreenUtils.weexPx2dp(getInstance(), entry.getValue(), 5));
                                break;

                            case "color":
                                bean.setColor(weiuiParse.parseStr(entry.getValue(), "#000000"));
                                break;

                            case "backgroundColor":
                                bean.setBackgroundColor(weiuiParse.parseStr(entry.getValue(), "#ffffff"));
                                break;
                        }
                    }
                    swipes.add(bean);
                }
                mAdapter.setSwipeItems(swipes);
                v_recyler.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(getContext()));
                return true;

            case "row":
                gridRow = weiuiParse.parseInt(val, 1);
                mLayoutManager.setSpanCount(gridRow);
                return true;

            case "pullTips":
                mAdapter.setPullTips(weiuiParse.parseBool(val, true));
                return true;

            case "pullTipsDefault":
                mAdapter.setPullTipsDefault(weiuiParse.parseStr(val, ""));
                return true;

            case "pullTipsLoad":
                mAdapter.setPullTipsLoad(weiuiParse.parseStr(val, ""));
                return true;

            case "pullTipsNo":
                mAdapter.setPullTipsNo(weiuiParse.parseStr(val, ""));
                return true;

            case "dividerColor":
                mAdapter.setDividerColor(weiuiParse.parseStr(val, ""));
                return true;

            case "dividerHeight":
                mAdapter.setDividerHeight(weiuiScreenUtils.weexPx2dp(getInstance(), val, 0));
                return true;

            case "itemSpaceTop":
                mAdapter.setItemSpaceTop(weiuiScreenUtils.weexPx2dp(getInstance(), val, 0));
                return true;

            case "itemSpaceRight":
                mAdapter.setItemSpaceRight(weiuiScreenUtils.weexPx2dp(getInstance(), val, 0));
                return true;

            case "itemSpaceBottom":
                mAdapter.setItemSpaceBottom(weiuiScreenUtils.weexPx2dp(getInstance(), val, 0));
                return true;

            case "itemSpaceLeft":
                mAdapter.setItemSpaceLeft(weiuiScreenUtils.weexPx2dp(getInstance(), val, 0));
                return true;

            case "itemBackgroundColor":
                mAdapter.setItemBackgroundColor(Color.parseColor(weiuiParse.parseStr(val, "")));
                return true;

            case "itemDefaultAnimator":
                itemDefaultAnimator(weiuiParse.parseBool(val, false));
                return true;

            default:
                return false;
        }
    }

    private void initPagerView() {
        v_swipeRefresh = mView.findViewById(R.id.v_swipeRefresh);
        v_recyler = mView.findViewById(R.id.v_recyler);
        //
        if (getDomObject().getEvents().contains(weiuiConstants.Event.REFRESH_LISTENER)) {
            v_swipeRefresh.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
            v_swipeRefresh.setOnRefreshListener(this);
            isSwipeRefresh = true;
        }else{
            v_swipeRefresh.setEnabled(false);
        }
        //
        mAdapter = new RecylerAdapter(getContext());
        mLayoutManager = new GridLayoutManager(getContext(), gridRow);
        v_recyler.setHasFixedSize(true);
        v_recyler.setLayoutManager(mLayoutManager);
        v_recyler.setAdapter(mAdapter);
        v_recyler.setItemAnimator(new DefaultItemAnimator());
        itemDefaultAnimator(false);
        v_recyler.addOnScrollListener(new RecylerOnBottomScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (!isLoading && !mAdapter.isFadeFooter() && lastVisibleItem + 1 == mAdapter.getItemCount()) {
                        loadData();
                    }
                    if (!isLoading && mAdapter.isFadeFooter() && lastVisibleItem + 2 == mAdapter.getItemCount()) {
                        loadData();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
                if (isSwipeRefresh) {
                    v_swipeRefresh.setEnabled(mLayoutManager.findFirstCompletelyVisibleItemPosition() == 0);
                }
            }

        });
        mAdapter.setOnItemClickListener(new RecylerAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                if (getDomObject().getEvents().contains(weiuiConstants.Event.ITEM_CLICK)) {
                    Map<String, Object> data = new HashMap<>();
                    data.put("position", position);
                    fireEvent(weiuiConstants.Event.ITEM_CLICK, data);
                }
            }

            @Override
            public void onLongClick(int position) {
                if (getDomObject().getEvents().contains(weiuiConstants.Event.ITEM_LONG_CLICK)) {
                    Map<String, Object> data = new HashMap<>();
                    data.put("position", position);
                    fireEvent(weiuiConstants.Event.ITEM_LONG_CLICK, data);
                }
            }

            @Override
            public void onSwipeClick(int position, int swipePosition, String swipeText) {
                if (getDomObject().getEvents().contains(weiuiConstants.Event.ITEM_SWIPE_CLICK)) {
                    Map<String, Object> data = new HashMap<>();
                    data.put("position", position);
                    data.put("swipePosition", swipePosition);
                    data.put("swipeText", swipeText);
                    fireEvent(weiuiConstants.Event.ITEM_SWIPE_CLICK, data);
                }
            }
        });
    }

    private void notifyUpdateList() {
        if (getHostView() == null || listUpdateRunnable == null) {
            return;
        }
        if (Looper.getMainLooper().getThread().getId() != Thread.currentThread().getId()) {
            getHostView().removeCallbacks(listUpdateRunnable);
            getHostView().post(listUpdateRunnable);
        } else {
            listUpdateRunnable.run();
        }
    }

    private void notifyUpdateFoot() {
        footIdentify++;
        int tempId = footIdentify;
        mHandler.postDelayed(()-> {
            if (tempId == footIdentify) {
                v_recyler.post(()-> {
                    if (getHostView() != null && mAdapter != null) {
                        mAdapter.notifyItemChanged(mAdapter.getItemCount() - 1);
                    }
                });
            }
        }, 100);
    }

    /**
     * 加载数据
     */
    private void loadData() {
        isLoading = true;
        if (hasMore) {
            if (getDomObject().getEvents().contains(weiuiConstants.Event.PULLLOAD_LISTENER)) {
                Map<String, Object> data = new HashMap<>();
                data.put("realLastPosition", mAdapter.getRealLastPosition());
                data.put("lastVisibleItem", lastVisibleItem);
                fireEvent(weiuiConstants.Event.PULLLOAD_LISTENER, data);
            }
        }else{
            mAdapter.updateList(-1, null, false);
            notifyUpdateFoot();
        }
    }

    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/

    /**
     * 设置下拉刷新状态
     * @param var
     */
    @JSMethod
    public void setRefreshing(boolean var){
        if (var) {
            if (!v_swipeRefresh.isRefreshing()) {
                onRefresh();
            }
        }else{
            isLoading = false;
            v_swipeRefresh.post(()-> v_swipeRefresh.setRefreshing(false));
        }
    }

    /**
     * 下拉刷新结束标记
     */
    @JSMethod
    public void refreshed() {
        isLoading = false;
        v_swipeRefresh.post(()-> v_swipeRefresh.setRefreshing(false));
    }

    /**
     * 设置是否有上拉加载更多的数据
     * @param var
     */
    @JSMethod
    public void setHasMore(boolean var){
        hasMore = var;
        if (mAdapter != null) {
            mAdapter.updateList(-1, null, hasMore);
            notifyUpdateFoot();
        }
    }

    /**
     * 上拉加载结束标记
     */
    @JSMethod
    public void pullloaded() {
        isLoading = false;
    }

    /**
     * 打开关闭局部刷新默认动画
     */
    @JSMethod
    public void itemDefaultAnimator(boolean open) {
        if (v_recyler != null) {
            if (open) {
                v_recyler.getItemAnimator().setAddDuration(120);
                v_recyler.getItemAnimator().setChangeDuration(250);
                v_recyler.getItemAnimator().setMoveDuration(250);
                v_recyler.getItemAnimator().setRemoveDuration(120);
                ((SimpleItemAnimator) v_recyler.getItemAnimator()).setSupportsChangeAnimations(true);
            }else{
                v_recyler.getItemAnimator().setAddDuration(0);
                v_recyler.getItemAnimator().setChangeDuration(0);
                v_recyler.getItemAnimator().setMoveDuration(0);
                v_recyler.getItemAnimator().setRemoveDuration(0);
                ((SimpleItemAnimator) v_recyler.getItemAnimator()).setSupportsChangeAnimations(false);
            }
        }
    }

    /**
     * 滚动到指定位置
     */
    @JSMethod
    public void scrollToPosition(int position) {
        if (v_recyler != null && mAdapter != null) {
            if (position == -1) {
                position = mAdapter.getItemCount() - 1;
            }
            v_recyler.scrollToPosition(position);
        }
    }

    /**
     * 平滑滚动到指定位置
     */
    @JSMethod
    public void smoothScrollToPosition(int position) {
        if (v_recyler != null && mAdapter != null) {
            if (position == -1) {
                position = mAdapter.getItemCount() - 1;
            }
            v_recyler.smoothScrollToPosition(position);
        }
    }
}
