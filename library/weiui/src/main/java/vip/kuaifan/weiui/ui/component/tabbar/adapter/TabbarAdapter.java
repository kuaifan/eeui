package vip.kuaifan.weiui.ui.component.tabbar.adapter;

import java.util.ArrayList;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import vip.kuaifan.weiui.extend.module.weiuiParse;

public class TabbarAdapter extends PagerAdapter {

    private ArrayList<View> viewLists;

    public TabbarAdapter(ArrayList<View> viewLists) {
        super();
        this.viewLists = viewLists;
    }

    public void setListViews(ArrayList<View> viewLists) {
        this.viewLists = viewLists;
    }

    @Override
    public int getCount() {
        return viewLists.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return weiuiParse.parseStr(viewLists.get(position).getTag(), "未命名");
    }

    public int getItemPosition(@NonNull Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        if (getCount() > 1) {
            if (position <= viewLists.size() - 1) {
                container.removeView(viewLists.get(position));
            }
        }
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(viewLists.get(position), 0);
        return viewLists.get(position);
    }
}