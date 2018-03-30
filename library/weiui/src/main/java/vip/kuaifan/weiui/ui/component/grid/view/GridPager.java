package vip.kuaifan.weiui.ui.component.grid.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import vip.kuaifan.weiui.R;
import vip.kuaifan.weiui.extend.view.CircleView;
import vip.kuaifan.weiui.ui.component.grid.adapter.GridAdapter;
import vip.kuaifan.weiui.ui.component.grid.adapter.GridPagerAdapter;

public class GridPager extends RelativeLayout {

    private LayoutInflater inflater;
    private Context mContext;
    private ViewPager v_pager;
    private LinearLayout l_dots;

    private List<View> viewDatas = new ArrayList<>();
    private List<View> mPagerList;

    private OnPageItemClickListener mOnPageItemClickListener;
    private LinearLayout.LayoutParams mDotParams;

    private int pageCount;
    private int curIndex = 0;
    private int pageSize = 9;
    private int rowSize = 3;
    private int columnsSize = 3;

    private boolean dividerShow = true;
    private int dividerColor = 0xFFE8E8E8;
    private int dividerWidth = 1;

    private boolean indicatorShow = true;
    private int indicatorUnSelectedColor = 0xFFE0E0E0;
    private int indicatorSelectedColor = 0XFFFF0000;
    private int indicatorWidth = 6;
    private int indicatorHeight = 6;

    public GridPager(Context context) {
        super(context);
        initView(context);
    }

    public GridPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public GridPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(mContext);
        View mView = inflater.inflate(R.layout.layout_weiui_grid_pager, this);
        v_pager = mView.findViewById(R.id.v_pager);
        l_dots = mView.findViewById(R.id.l_dots);
    }

    /****************************************************************************************/
    /****************************************************************************************/
    /****************************************************************************************/

    /**
     * 设置点击长按事件
     * @param listener
     */
    public void setOnPageItemClickListener(OnPageItemClickListener listener) {
        this.mOnPageItemClickListener = listener;
    }

    public interface OnPageItemClickListener {
        void onClick(int pos, int position);
        void onLongClick(int pos, int position);
    }

    /**
     * 通知数据集变化
     */
    public void notifyDataSetChanged() {
        this.pageSize = this.rowSize * this.columnsSize;
        pageCount = (int) Math.ceil(viewDatas.size() * 1.0 / pageSize);
        mPagerList = new ArrayList<>();
        //
        List<View> newDatas = viewDatas;
        int remainder =  newDatas.size() % columnsSize;
        if (remainder > 0) {
            for (int r = 0; r < columnsSize - remainder; r++) {
                View temp = new View(mContext);
                temp.setTag("__replenish");
                newDatas.add(temp);
            }
        }
        //
        for (int i = 0; i < pageCount; i++) {
            View mView = inflater.inflate(R.layout.layout_weiui_grid_recyler, v_pager, false);
            RecyclerView v_recycler = mView.findViewById(R.id.v_recycler);
            GridAdapter mGridAdapter = new GridAdapter(mContext, newDatas, i, pageSize, columnsSize);
            mGridAdapter.setDivider(dividerShow, dividerColor, dividerWidth);
            v_recycler.setLayoutManager(new GridLayoutManager(mContext, columnsSize));
            v_recycler.setAdapter(mGridAdapter);
            //
            mGridAdapter.setOnItemClickListener(new GridAdapter.OnItemClickListener() {
                @Override
                public void onClick(int pos, int position) {
                    if (mOnPageItemClickListener != null) {
                        mOnPageItemClickListener.onClick(pos, position);
                    }
                }

                @Override
                public void onLongClick(int pos, int position) {
                    if (mOnPageItemClickListener != null) {
                        mOnPageItemClickListener.onLongClick(pos, position);
                    }
                }
            });
            mPagerList.add(mView);
        }
        //设置适配器
        v_pager.setAdapter(new GridPagerAdapter<>(mPagerList));
        //设置底部圆点
        if (mDotParams == null) {
            mDotParams = new LinearLayout.LayoutParams((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, indicatorWidth, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, indicatorHeight, getResources().getDisplayMetrics()));
            mDotParams.rightMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, indicatorWidth / 2, getResources().getDisplayMetrics());
            mDotParams.leftMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, indicatorWidth / 2, getResources().getDisplayMetrics());
        }
        l_dots.removeAllViews();
        if (pageCount > 1 && indicatorShow) {
            for (int i = 0; i < pageCount; i++) {
                CircleView view = new CircleView(mContext);
                view.setLockScale(false);
                view.setBackgroundColor(indicatorUnSelectedColor);
                view.setSelected(false);
                l_dots.addView(view, mDotParams);
            }
            l_dots.getChildAt(0).setBackgroundColor(indicatorSelectedColor);
            l_dots.getChildAt(0).setSelected(true);
            //
            v_pager.addOnPageChangeListener(dotsOnPageChangeListener);
        }
    }

    private ViewPager.OnPageChangeListener dotsOnPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            // 取消圆点选中
            l_dots.getChildAt(curIndex).setBackgroundColor(indicatorUnSelectedColor);
            l_dots.getChildAt(curIndex).setSelected(false);
            // 圆点选中
            l_dots.getChildAt(position).setBackgroundColor(indicatorSelectedColor);
            l_dots.getChildAt(position).setSelected(true);
            //
            curIndex = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    /****************************************************************************************/
    /****************************************************************************************/
    /****************************************************************************************/

    /**
     * 添加对象
     * @param view
     */
    public void addData(View view) {
        if (view != null) {
            viewDatas.add(view);
        }
    }

    /**
     * 删除对象
     * @param view
     */
    public void removeData(View view) {
        if (view != null) {
            viewDatas.remove(view);
        }
    }

    /**
     * 设置每页行数
     * @param num
     * @return
     */
    public void setRowSize(int num) {
        this.rowSize = num;
        this.pageSize = this.rowSize * this.columnsSize;
    }

    /**
     * 设置每页列数
     * @param num
     * @return
     */
    public void setColumnsSize(int num) {
        this.columnsSize = num;
        this.pageSize = this.rowSize * this.columnsSize;
    }

    /**
     * 设置是否显示分隔线
     * @param dividerShow
     * @return
     */
    public void setDivider(boolean dividerShow) {
        this.dividerShow = dividerShow;
    }

    /**
     * 设置分隔线颜色
     * @param color
     * @return
     */
    public void setDividerColor(int color) {
        this.dividerColor = color;
    }

    /**
     * 设置分隔线尺寸
     * @param width
     * @return
     */
    public void setDividerWidth(int width) {
        this.dividerWidth = width;
    }

    /**
     * 是否显示指示器
     * @param indicatorShow
     */
    public void setIndicator(boolean indicatorShow) {
        this.indicatorShow = indicatorShow;
    }

    /**
     * 指示器未选择颜色
     * @param indicatorUnSelectedColor
     */
    public void setIndicatorUnSelectedColor(int indicatorUnSelectedColor) {
        this.indicatorUnSelectedColor = indicatorUnSelectedColor;
    }

    /**
     * 指示器已选颜色
     * @param indicatorSelectedColor
     */
    public void setIndicatorSelectedColor(int indicatorSelectedColor) {
        this.indicatorSelectedColor = indicatorSelectedColor;
    }

    /**
     * 指示器宽度
     * @param indicatorWidth
     */
    public void setIndicatorWidth(int indicatorWidth) {
        this.indicatorWidth = indicatorWidth;
    }

    /**
     * 指示器高度
     * @param indicatorHeight
     */
    public void setIndicatorHeight(int indicatorHeight) {
        this.indicatorHeight = indicatorHeight;
    }

    /**
     * 设置当前页
     * @param curIndex
     */
    public void setCurIndex(int curIndex) {
        v_pager.setCurrentItem(Math.min(Math.max(curIndex, 0), pageCount));
    }

    /**
     * 获取当前页
     * @return
     */
    public int getCurIndex() {
        return curIndex;
    }
}
