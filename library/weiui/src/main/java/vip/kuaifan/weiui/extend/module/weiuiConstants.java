package vip.kuaifan.weiui.extend.module;

public class weiuiConstants {

    public interface Event {

        //生命周期
        String LIFECYCLE = "lifecycle";

        //组件已经加载
        String READY = "ready";

        //返回事件
        String GO_BACK = "goBack";

        //返回事件重写
        String GO_BACK_OVERRIDE = "goBackOverride";

        //点击事件
        String CLICK = "click";

        //项目点击事件
        String ITEM_CLICK = "itemClick";

        //项目长按事件
        String ITEM_LONG_CLICK = "itemLongClick";

        //项目Swipe菜单点击事件
        String ITEM_SWIPE_CLICK = "itemSwipeClick";

        //滑动事件
        String SWITCH_LISTENER = "switchListener";

        //视图滚动回调（滚动完成后调用）
        String SCROLLED = "scrolled";

        //视图滚动状态发生变化
        String SCROLL_STATE_CHANGED = "scrollStateChanged";

        //上拉加载更多
        String PULLLOAD_LISTENER = "pullLoadListener";

        //下拉刷新
        String REFRESH_LISTENER = "refreshListener";

        //页面切换时调用，滑动被停止之前一直调用
        String PAGE_SCROLLED = "pageScrolled";

        //页面切换完成调用
        String PAGE_SELECTED = "pageSelected";

        //页面状态改变的时候调用
        String PAGE_SCROLL_STATE_CHANGED = "pageScrollStateChanged";

        //标签被选择
        String TAB_SELECT = "tabSelect";

        //标签被再次选择
        String TAB_RESELECT = "tabReselect";

        //页面创建完毕
        String VIEW_CREATED = "viewCreated";

        //状态发生改变
        String STATE_CHANGED = "stateChanged";
    }

}
