package vip.kuaifan.weiui.ui.component.grid.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import vip.kuaifan.weiui.R;
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

    private int pageCount;
    private int curIndex = 0;
    private int pageSize = 9;
    private int rowSize = 3;
    private int columnsSize = 3;

    private boolean dividerShow = true;
    private int dividerColor = 0xFFE8E8E8;
    private int dividerWidth = 1;

    private Drawable unSelectedDrawable;
    private Drawable selectedDrawable;
    private boolean indicatorShow = true;
    private Shape indicatorShape = Shape.oval;
    private int selectedIndicatorColor = 0xffff0000;
    private int unSelectedIndicatorColor = 0x88888888;
    private int selectedIndicatorHeight = 6;
    private int selectedIndicatorWidth = 6;
    private int unSelectedIndicatorHeight = 6;
    private int unSelectedIndicatorWidth = 6;
    private int indicatorSpace = 3;
    private enum Shape { rect, oval }

    public GridPager(Context context) {
        this(context, null);
    }

    public GridPager(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GridPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(mContext);
        View mView = inflater.inflate(R.layout.layout_weiui_grid_pager, this);
        v_pager = mView.findViewById(R.id.v_pager);
        l_dots = mView.findViewById(R.id.l_dots);
    }

    @SuppressLint("CustomViewStyleable")
    private void init(AttributeSet attrs, int defStyle) {

        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.BannerLayoutStyle, defStyle, 0);
        selectedIndicatorColor = array.getColor(R.styleable.BannerLayoutStyle_selectedIndicatorColor, selectedIndicatorColor);
        unSelectedIndicatorColor = array.getColor(R.styleable.BannerLayoutStyle_unSelectedIndicatorColor, unSelectedIndicatorColor);

        int shape = array.getInt(R.styleable.BannerLayoutStyle_indicatorShape, Shape.oval.ordinal());
        for (Shape shape1 : Shape.values()) {
            if (shape1.ordinal() == shape) {
                indicatorShape = shape1;
                break;
            }
        }
        selectedIndicatorHeight = (int) array.getDimension(R.styleable.BannerLayoutStyle_selectedIndicatorHeight, selectedIndicatorHeight);
        selectedIndicatorWidth = (int) array.getDimension(R.styleable.BannerLayoutStyle_selectedIndicatorWidth, selectedIndicatorWidth);
        unSelectedIndicatorHeight = (int) array.getDimension(R.styleable.BannerLayoutStyle_unSelectedIndicatorHeight, unSelectedIndicatorHeight);
        unSelectedIndicatorWidth = (int) array.getDimension(R.styleable.BannerLayoutStyle_unSelectedIndicatorWidth, unSelectedIndicatorWidth);


        indicatorSpace = (int) array.getDimension(R.styleable.BannerLayoutStyle_indicatorSpace, indicatorSpace);
        array.recycle();
    }

    private void indicatorDrawable() {
        //绘制未选中状态图形
        LayerDrawable unSelectedLayerDrawable;
        LayerDrawable selectedLayerDrawable;
        GradientDrawable unSelectedGradientDrawable;
        unSelectedGradientDrawable = new GradientDrawable();

        //绘制选中状态图形
        GradientDrawable selectedGradientDrawable;
        selectedGradientDrawable = new GradientDrawable();
        switch (indicatorShape) {
            case rect:
                unSelectedGradientDrawable.setShape(GradientDrawable.RECTANGLE);
                selectedGradientDrawable.setShape(GradientDrawable.RECTANGLE);
                break;
            case oval:
                unSelectedGradientDrawable.setShape(GradientDrawable.OVAL);
                selectedGradientDrawable.setShape(GradientDrawable.OVAL);
                break;
        }
        unSelectedGradientDrawable.setColor(unSelectedIndicatorColor);
        unSelectedGradientDrawable.setSize(unSelectedIndicatorWidth, unSelectedIndicatorHeight);
        unSelectedLayerDrawable = new LayerDrawable(new Drawable[]{unSelectedGradientDrawable});
        unSelectedDrawable = unSelectedLayerDrawable;

        selectedGradientDrawable.setColor(selectedIndicatorColor);
        selectedGradientDrawable.setSize(selectedIndicatorWidth, selectedIndicatorHeight);
        selectedLayerDrawable = new LayerDrawable(new Drawable[]{selectedGradientDrawable});
        selectedDrawable = selectedLayerDrawable;
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
        void onClick(int pos, int position, int index);
        void onLongClick(int pos, int position, int index);
    }

    /**
     * 通知数据集变化
     */
    public void notifyDataSetChanged() {
        indicatorDrawable();
        this.pageSize = this.rowSize * this.columnsSize;
        pageCount = (int) Math.ceil(viewDatas.size() * 1.0 / pageSize);
        mPagerList = new ArrayList<>();
        //
        List<View> newDatas = viewDatas;
        int remainder =  newDatas.size() % pageSize;
        if (remainder > columnsSize) {
            int columnsRemainder =  newDatas.size() % columnsSize;
            if (columnsRemainder > 0) {
                for (int r = 0; r < columnsSize - columnsRemainder; r++) {
                    View temp = new View(mContext);
                    temp.setTag("__replenish");
                    newDatas.add(temp);
                }
            }
        }
        //
        for (int i = 0; i < pageCount; i++) {
            View mView = inflater.inflate(R.layout.layout_weiui_grid_recyler, v_pager, false);
            RecyclerView v_recycler = mView.findViewById(R.id.v_recycler);
            GridAdapter mGridAdapter = new GridAdapter(mContext, viewDatas, i, pageSize, columnsSize);
            mGridAdapter.setDivider(dividerShow, dividerColor, dividerWidth);
            v_recycler.setLayoutManager(new GridLayoutManager(mContext, columnsSize));
            v_recycler.setAdapter(mGridAdapter);
            //
            mGridAdapter.setOnItemClickListener(new GridAdapter.OnItemClickListener() {
                @Override
                public void onClick(int pos, int position) {
                    if (mOnPageItemClickListener != null) {
                        mOnPageItemClickListener.onClick(pos, position, pageSize * pos + position);
                    }
                }

                @Override
                public void onLongClick(int pos, int position) {
                    if (mOnPageItemClickListener != null) {
                        mOnPageItemClickListener.onLongClick(pos, position, pageSize * pos + position);
                    }
                }
            });
            mPagerList.add(mView);
        }
        //设置适配器
        v_pager.setAdapter(new GridPagerAdapter<>(mPagerList));
        //设置底部圆点
        l_dots.removeAllViews();
        if (pageCount > 1 && indicatorShow) {
            for (int i = 0; i < pageCount; i++) {
                ImageView indicator = new ImageView(getContext());
                indicator.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                indicator.setPadding(indicatorSpace, indicatorSpace, indicatorSpace, indicatorSpace);
                indicator.setImageDrawable(unSelectedDrawable);
                l_dots.addView(indicator);
            }
            switchIndicator(0);
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
            switchIndicator(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private void switchIndicator(int currentPosition) {
        for (int i = 0; i < l_dots.getChildCount(); i++) {
            ((ImageView) l_dots.getChildAt(i)).setImageDrawable(i == currentPosition ? selectedDrawable : unSelectedDrawable);
        }
    }

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
     * @param show
     */
    public void setIndicatorShow(boolean show) {
        indicatorShow = show;
    }

    /**
     * 设置指示器形状
     * @param shape
     */
    public void setIndicatorShape(int shape) {
        for (Shape shape1 : Shape.values()) {
            if (shape == shape1.ordinal()) {
                indicatorShape = shape1;
            }
        }
    }

    /**
     * 设置指示器间距
     * @param space
     */
    public void setIndicatorSpace(int space) {
        indicatorSpace = space;
    }

    /**
     * 设置指示器已选颜色
     * @param color
     */
    public void setSelectedIndicatorColor(int color) {
        selectedIndicatorColor = color;
    }

    /**
     * 设置指示器未选颜色
     * @param color
     */
    public void setUnSelectedIndicatorColor(int color) {
        unSelectedIndicatorColor = color;
    }

    /**
     * 设置指示器高
     * @param height
     */
    public void setIndicatorHeight(int height) {
        selectedIndicatorHeight = height;
        unSelectedIndicatorHeight = height;
    }

    /**
     * 设置指示器宽
     * @param width
     */
    public void setIndicatorWidth(int width) {
        selectedIndicatorWidth = width;
        unSelectedIndicatorWidth = width;
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
