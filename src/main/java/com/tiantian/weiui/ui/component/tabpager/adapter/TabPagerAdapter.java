package com.tiantian.weiui.ui.component.tabpager.adapter;

import java.util.ArrayList;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class TabPagerAdapter extends PagerAdapter {

    private ArrayList<View> viewLists;

    public TabPagerAdapter(ArrayList<View> viewLists) {
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

    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (getCount() > 1) {
            if (position <= viewLists.size() - 1) {
                container.removeView(viewLists.get(position));
            }
        }
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(viewLists.get(position), 0);
        return viewLists.get(position);
    }
}