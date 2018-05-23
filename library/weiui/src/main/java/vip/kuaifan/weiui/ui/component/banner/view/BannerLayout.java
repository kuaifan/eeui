package vip.kuaifan.weiui.ui.component.banner.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Handler;
import android.os.Message;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Scroller;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import vip.kuaifan.weiui.R;

public class BannerLayout extends RelativeLayout {

    private ViewPager pager;

    private List<View> pageViews = new ArrayList<>();

    private LinearLayout indicatorContainer;

    private LoopPagerAdapter pagerAdapter;

    private int WHAT_AUTO_PLAY = 1000;

    private boolean isAutoPlay = true;

    private boolean isViewsMode = false;

    private int itemCount;

    private Drawable unSelectedDrawable;
    private Drawable selectedDrawable;
    private boolean indicatorShow = true;
    private int selectedIndicatorColor = 0xffff0000;
    private int unSelectedIndicatorColor = 0x88888888;
    private Shape indicatorShape = Shape.oval;
    private Position indicatorPosition = Position.centerBottom;
    private int selectedIndicatorHeight = 6;
    private int selectedIndicatorWidth = 6;
    private int unSelectedIndicatorHeight = 6;
    private int unSelectedIndicatorWidth = 6;
    private int indicatorSpace = 3;
    private int indicatorMargin = 10;
    private enum Shape { rect, oval }
    private enum Position {
        centerBottom,
        rightBottom,
        leftBottom,
        centerTop,
        rightTop,
        leftTop
    }

    private int autoPlayDuration = 4000;
    private int scrollDuration = 900;

    private int currentPosition;

    private ImageLoader imageLoader;

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startAutoPlay();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopAutoPlay();
    }

    private OnBannerItemClickListener onBannerItemClickListener;

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == WHAT_AUTO_PLAY) {
                if (pager != null && pagerAdapter != null && isAutoPlay) {
                    if (pagerAdapter.getCount() > 2){
                        pager.setCurrentItem(pager.getCurrentItem() + 1, true);
                    } else if (pagerAdapter.getCount() == 2) {
                        pager.setCurrentItem(pager.getCurrentItem() == 0 ? pager.getCurrentItem() + 1 : pager.getCurrentItem() - 1, true);
                    }
                    handler.sendEmptyMessageDelayed(WHAT_AUTO_PLAY, autoPlayDuration);
                }
            }
            return false;
        }
    });

    public BannerLayout(Context context) {
        this(context, null);
    }

    public BannerLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BannerLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
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

        int position = array.getInt(R.styleable.BannerLayoutStyle_indicatorPosition, Position.centerBottom.ordinal());
        for (Position position1 : Position.values()) {
            if (position == position1.ordinal()) {
                indicatorPosition = position1;
            }
        }
        indicatorSpace = (int) array.getDimension(R.styleable.BannerLayoutStyle_indicatorSpace, indicatorSpace);
        indicatorMargin = (int) array.getDimension(R.styleable.BannerLayoutStyle_indicatorMargin, indicatorMargin);
        autoPlayDuration = array.getInt(R.styleable.BannerLayoutStyle_autoPlayDuration, autoPlayDuration);
        scrollDuration = array.getInt(R.styleable.BannerLayoutStyle_scrollDuration, scrollDuration);
        isAutoPlay = array.getBoolean(R.styleable.BannerLayoutStyle_isAutoPlay, isAutoPlay);
        array.recycle();
    }

    @NonNull
    private View getView(View view, final int position) {
        view.setOnClickListener(v -> {
            if (onBannerItemClickListener != null) {
                onBannerItemClickListener.onItemClick(position);
            }
        });
        view.setOnLongClickListener(v -> {
            if (onBannerItemClickListener != null) {
                onBannerItemClickListener.onLongItemClick(position);
            }
            return true;
        });
        return view;
    }

    @NonNull
    private ImageView getImageView(String url, final int position) {
        ImageView imageView = new ImageView(getContext());
        imageView.setOnClickListener(v -> {
            if (onBannerItemClickListener != null) {
                onBannerItemClickListener.onItemClick(position);
            }
        });
        imageView.setOnLongClickListener(v -> {
            if (onBannerItemClickListener != null) {
                onBannerItemClickListener.onLongItemClick(position);
            }
            return true;
        });
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageLoader.displayImage(getContext(), url, imageView);
        return imageView;
    }

    private void setViewPager(final List<View> views) {
        if (itemCount == 0) {
            return;
        }
        //初始化pager
        pager = new ViewPager(getContext());
        //添加viewpager到SliderLayout
        addView(pager);
        setSliderTransformDuration(scrollDuration);
        //初始化indicatorContainer
        indicatorContainer = new LinearLayout(getContext());
        indicatorContainer.setGravity(Gravity.CENTER_VERTICAL);
        if (!indicatorShow || views.size() < 2) {
            indicatorContainer.setVisibility(GONE);
        }
        RelativeLayout.LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        //
        switch (indicatorPosition) {
            case centerBottom:
                params.addRule(RelativeLayout.CENTER_HORIZONTAL);
                params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                break;
            case centerTop:
                params.addRule(RelativeLayout.CENTER_HORIZONTAL);
                params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                break;
            case leftBottom:
                params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                break;
            case leftTop:
                params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                break;
            case rightBottom:
                params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                break;
            case rightTop:
                params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                break;
        }
        //设置margin
        params.setMargins(indicatorMargin, indicatorMargin, indicatorMargin, indicatorMargin);
        //添加指示器容器布局到SliderLayout
        addView(indicatorContainer, params);

        //初始化指示器，并添加到指示器容器布局
        for (int i = 0; i < itemCount; i++) {
            ImageView indicator = new ImageView(getContext());
            indicator.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            indicator.setPadding(indicatorSpace, indicatorSpace, indicatorSpace, indicatorSpace);
            indicator.setImageDrawable(unSelectedDrawable);
            indicatorContainer.addView(indicator);
        }
        pagerAdapter = new LoopPagerAdapter(views);
        pager.setAdapter(pagerAdapter);
        //设置当前item到Integer.MAX_VALUE中间的一个值，看起来像无论是往前滑还是往后滑都是ok的
        //如果不设置，用户往左边滑动的时候已经划不动了
        int targetItemPosition = pagerAdapter.getCount() < 3 ? 0 : (Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % itemCount);
        currentPosition = targetItemPosition;
        pager.setCurrentItem(targetItemPosition);
        switchIndicator(targetItemPosition % itemCount);
        pager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                currentPosition = position;
                switchIndicator(position % itemCount);
            }
        });
        if (isAutoPlay) {
            startAutoPlay();
        }
    }

    private void switchIndicator(int currentPosition) {
        for (int i = 0; i < indicatorContainer.getChildCount(); i++) {
            ((ImageView) indicatorContainer.getChildAt(i)).setImageDrawable(i == currentPosition ? selectedDrawable : unSelectedDrawable);
        }
    }

    public void setSliderTransformDuration(int duration) {
        try {
            Field mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            FixedSpeedScroller scroller = new FixedSpeedScroller(pager.getContext(), null, duration);
            mScroller.set(pager, scroller);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);

        if (visibility == VISIBLE) {
            startAutoPlay();
        } else {
            stopAutoPlay();
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                stopAutoPlay();
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                startAutoPlay();
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    public void setOnBannerItemClickListener(OnBannerItemClickListener onBannerItemClickListener) {
        this.onBannerItemClickListener = onBannerItemClickListener;
    }

    public interface OnBannerItemClickListener {
        void onItemClick(int position);
        void onLongItemClick(int position);
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        SavedState savedState = (SavedState) state;
        super.onRestoreInstanceState(savedState.getSuperState());
        currentPosition = savedState.currentPosition;
        requestLayout();
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SavedState savedState = new SavedState(superState);
        savedState.currentPosition = currentPosition;
        return savedState;
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

    private static class SavedState extends BaseSavedState {
        int currentPosition;

        SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            super(in);
            currentPosition = in.readInt();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeInt(currentPosition);
        }

        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
            @Override
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            @Override
            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
    }


    private class LoopPagerAdapter extends PagerAdapter {
        private List<View> views;

        LoopPagerAdapter(List<View> views) {
            this.views = views;
        }

        @Override
        public int getCount() {
            if (isViewsMode && this.views.size() < 3) {
                return this.views.size();
            }
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            if (views.size() > 0) {
                View view = views.get(position % views.size());
                /*if (container.equals(view.getParent())) {
                    container.removeView(view);
                }*/
                ViewGroup parentViewGroup = (ViewGroup) view.getParent();
                if (parentViewGroup != null ) {
                    parentViewGroup.removeView(view);
                }
                container.addView(view);
                return view;
            }
            return null;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        }
    }

    public class FixedSpeedScroller extends Scroller {

        private int mDuration = 1000;

        FixedSpeedScroller(Context context, Interpolator interpolator) {
            super(context, interpolator);
        }

        FixedSpeedScroller(Context context, Interpolator interpolator, int duration) {
            this(context, interpolator);
            mDuration = duration;
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            super.startScroll(startX, startY, dx, dy, mDuration);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy) {
            super.startScroll(startX, startY, dx, dy, mDuration);
        }
    }

    public interface ImageLoader extends Serializable {
        void displayImage(Context context, String path, ImageView imageView);
    }

    /************************************************************************************/
    /************************************************************************************/
    /************************************************************************************/

    /**
     * 设置图片加载器
     * @param imageLoader
     */
    public void setImageLoader(ImageLoader imageLoader) {
        this.imageLoader = imageLoader;
    }

    public ViewPager getPager() {
        if (pager != null) {
            return pager;
        }
        return null;
    }

    /**
     * 是否自动轮播
     * @param autoPlay
     */
    public void setAutoPlay(boolean autoPlay) {
        isAutoPlay = autoPlay;
    }

    /**
     * 添加任意视图
     * @param viewLists
     */
    public void setViews(final List<View> viewLists) {
        isViewsMode = true;
        List<View> views = new ArrayList<>();
        itemCount = viewLists.size();
        if (itemCount < 1) {
            throw new IllegalStateException("item count not equal zero");
        } else {
            for (int i = 0; i < viewLists.size(); i++) {
                views.add(getView(viewLists.get(i), i));
            }
        }
        pageViews = views;
    }

    /**
     * 添加网络图片路径
     * @param urls
     */
    public void setViewUrls(List<String> urls) {
        List<View> views = new ArrayList<>();
        itemCount = urls.size();
        //主要是解决当item为小于3个的时候滑动有问题，这里将其拼凑成3个以上
        if (itemCount < 1) {//当item个数0
            throw new IllegalStateException("item count not equal zero");
        } else if (itemCount < 2) { //当item个数为1
            views.add(getImageView(urls.get(0), 0));
            views.add(getImageView(urls.get(0), 0));
            views.add(getImageView(urls.get(0), 0));
        } else if (itemCount < 3) {//当item个数为2
            views.add(getImageView(urls.get(0), 0));
            views.add(getImageView(urls.get(1), 1));
            views.add(getImageView(urls.get(0), 0));
            views.add(getImageView(urls.get(1), 1));
        } else {
            for (int i = 0; i < urls.size(); i++) {
                views.add(getImageView(urls.get(i), i));
            }
        }
        pageViews = views;
    }

    /**
     * 开始自动轮播
     */
    public void startAutoPlay() {
        stopAutoPlay(); // 避免重复消息
        if (isAutoPlay) {
            handler.sendEmptyMessageDelayed(WHAT_AUTO_PLAY, autoPlayDuration);
        }
    }

    /**
     * 停止自动轮播
     */
    public void stopAutoPlay() {
        if (pager != null) {
            pager.setCurrentItem(pager.getCurrentItem(), false);
        }
        if (isAutoPlay) {
            handler.removeMessages(WHAT_AUTO_PLAY);
            if (pager != null) {
                pager.setCurrentItem(pager.getCurrentItem(), false);
            }
        }
    }

    /**
     * 通知数据集变化
     */
    public void notifyDataSetChanged() {
        indicatorDrawable();
        setViewPager(pageViews);
    }

    /**
     * 是否切换间隔时间
     * @param duration
     */
    public void setAutoPlayDuration(int duration) {
        autoPlayDuration = duration;
    }

    /**
     * 是否切换过程时间
     * @param duration
     */
    public void setScrollDuration(int duration) {
        scrollDuration = duration;
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
     * 设置指示器位置
     * @param position
     */
    public void setIndicatorPosition(int position) {
        for (Position position1 : Position.values()) {
            if (position == position1.ordinal()) {
                indicatorPosition = position1;
            }
        }
    }

    /**
     * 设置指示器边缘距离
     * @param margin
     */
    public void setIndicatorMargin(int margin) {
        indicatorMargin = margin;
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
}


