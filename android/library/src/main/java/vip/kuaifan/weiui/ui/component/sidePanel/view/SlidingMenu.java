package vip.kuaifan.weiui.ui.component.sidePanel.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Scroller;


public class SlidingMenu extends ViewGroup {
    private static final String TAG = "SlidingMenu";
    private View mLeftView;
    private View mContentView;
    private int mLeftWidth;
    private float mDownX;
    private float mDownY;
    private long mDownTime;
    private float widthPixels;
    private String mMoveSide;

    private Scroller mScroller;

    private boolean isLeftShow = false;

    private OnSwitchListener mOnSwitchListener;
    public interface OnSwitchListener { void onSwitchListener(boolean isShow); }

    public SlidingMenu(Context context) {
        this(context, null);
    }

    public SlidingMenu(Context context, AttributeSet attrs) {
        super(context, attrs);

        mScroller = new Scroller(context);
        widthPixels = context.getResources().getDisplayMetrics().widthPixels;
    }

    @Override
    protected void onFinishInflate() {
        // xml加载完成时的回调
        super.onFinishInflate();
        mLeftView = getChildAt(0);
        mContentView = getChildAt(1);

        mLeftView.setVerticalScrollBarEnabled(false);
        mLeftWidth = mLeftView.getLayoutParams().width;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 测量孩子

        // 父和子的测量关系
        // child.measure():期望孩子的大小该怎么设置
        // widthMeasureSpec:期望值--
        // 2. 头2位：代表的是模式
        // @ 1. UNSPECIFIED： 不确定，随意，自己去定-->0
        // @ 2. EXACTLY：精确的 ---> 200 希望宽度确定为200px
        // @ 3. AT_MOST：最大的---> <200
        // 3. 后30位：数值

        // int mode = MeasureSpec.getMode(widthMeasureSpec);获得头2位
        // int size = MeasureSpec.getSize(widthMeasureSpec);获得后30位的值
        // MeasureSpec.makeMeasureSpec(200, MeasureSpec.EXACTLY);组装32位的01010

        // widthMeasureSpec:父容器希望 自己的宽度是多大

        // 测量左侧
        int leftWidthMeasureSpec = MeasureSpec.makeMeasureSpec(mLeftWidth, MeasureSpec.EXACTLY);
        mLeftView.measure(leftWidthMeasureSpec, heightMeasureSpec);

        // 测量右侧
        mContentView.measure(widthMeasureSpec, heightMeasureSpec);

        // 设置自己的宽度和高度
        int measuredWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measuredHeight = MeasureSpec.getSize(heightMeasureSpec);
        // setMeasuredDimension(10, 10);
        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        // 相对性：父容器确定孩子的位置

        int width = mLeftView.getMeasuredWidth();
        int height = mLeftView.getMeasuredHeight();

        // 给左侧布局
        int lvLeft = -width;
        int lvTop = 0;
        int lvRight = 0;
        int lvBottom = height;
        mLeftView.layout(lvLeft, lvTop, lvRight, lvBottom);// 有width和height

        // 给右侧布局
        mContentView.layout(0, 0, mContentView.getMeasuredWidth(), mContentView.getMeasuredHeight());
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = ev.getX();
                mDownY = ev.getY();
                mDownTime = System.currentTimeMillis();
                break;

            case MotionEvent.ACTION_MOVE:
                float moveX = ev.getX();
                float moveY = ev.getY();

                if (moveX > mDownX) {
                    mMoveSide = "right";
                }else{
                    mMoveSide = "left";
                }

                if ((widthPixels / mDownX > 10 || isLeftShow) && Math.abs(moveX - mDownX) > Math.abs(moveY - mDownY)) {
                    // 水平方向移动
                    return true;
                }

                break;

            case MotionEvent.ACTION_UP:
                break;

            default:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = event.getX();
                mDownY = event.getY();
                mDownTime = System.currentTimeMillis();
                break;

            case MotionEvent.ACTION_MOVE:
                float moveX = event.getX();
                float moveY = event.getY();

                if (moveX > mDownX) {
                    mMoveSide = "right";
                }else{
                    mMoveSide = "left";
                }

                int diffX = (int) (mDownX - moveX + 0.5f);// 四舍五入

                int scrollX = getScrollX() + diffX;

                if (scrollX < 0 && scrollX < -mLeftView.getMeasuredWidth()) {
                    // 从左往右滑动
                    scrollTo(-mLeftView.getMeasuredWidth(), 0);
                } else if (scrollX > 0) {
                    scrollTo(0, 0);
                } else {
                    // 标准滑动
                    scrollBy(diffX, 0);
                }
                mDownX = moveX;
                mDownY = moveY;
                break;

            case MotionEvent.ACTION_UP:
                // 松开时的逻辑
                // 判断是要去打开，要去关闭
                if (System.currentTimeMillis() - mDownTime < 200 && mMoveSide != null) {
                    if (isLeftShow) {
                        if (mMoveSide.equals("left")) {
                            moveSwitchMenu(false, 300);
                            return true;
                        }
                    }else{
                        if (mMoveSide.equals("right")) {
                            moveSwitchMenu(true, 300);
                            return true;
                        }
                    }
                }
                int currentX = getScrollX();
                int width = mLeftView.getMeasuredWidth();
                if (isLeftShow) {
                    float middle = Math.min(-width / 2f, -(width - widthPixels / 5f));
                    moveSwitchMenu(currentX <= middle, 600);
                }else{
                    float middle = Math.max(-width / 2f, -widthPixels / 5f);
                    moveSwitchMenu(currentX <= middle, 600);
                }
                break;

            default:
                break;
        }
        return true;
    }

    private void moveSwitchMenu(boolean showLeft, int duration) {
        int currentX = getScrollX();
        if (currentX == 0 && isLeftShow == showLeft) {
            return;
        }
        switchMenu(showLeft, duration);
    }

    private void switchMenu(boolean showLeft, int duration) {
        isLeftShow = showLeft;
        int width = mLeftView.getMeasuredWidth();
        int currentX = getScrollX();
        if (!showLeft) {
            // 关闭
            // scrollTo(0, 0);
            // 起始点---》结束点
            // -100------->0 -100,-99,-98.....0

            int startX = currentX;
            int startY = 0;

            int endX = 0;
            int endY = 0;

            int dx = endX - startX;// 增量的值
            int dy = endY - startY;

            // 模拟数据变化
            mScroller.startScroll(startX, startY, dx, dy, Math.min(Math.abs(dx) * 10, duration));

        } else {
            // 打开
            // scrollTo(-width, 0);

            int startX = currentX;
            int startY = 0;

            int endX = -width;
            int endY = 0;

            int dx = endX - startX;// 增量的值
            int dy = endY - startY;

            // 模拟数据变化
            mScroller.startScroll(startX, startY, dx, dy, Math.min(Math.abs(dx) * 10, duration));
        }
        if (mOnSwitchListener != null) {
            mOnSwitchListener.onSwitchListener(isLeftShow);
        }
        invalidate();// UI刷新---> draw() -->drawChild() --> computeScroll()
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            // 更新位置
            scrollTo(mScroller.getCurrX(), 0);
            invalidate();
        }
    }

    public void toggle() {
        switchMenu(!isLeftShow, 600);
    }

    public void switchLeft(boolean showLeft) {
        switchMenu(showLeft, 600);
    }

    public boolean getLeftShow() {
        return isLeftShow;
    }

    public void setLeftWidth(int width) {
        mLeftView.setLayoutParams(new LinearLayout.LayoutParams(width, LinearLayout.LayoutParams.MATCH_PARENT));
        mLeftWidth = width;
    }

    public void setLeftVerticalScrollBarEnabled(boolean scrollbar) {
        mLeftView.setVerticalScrollBarEnabled(scrollbar);
    }

    public void setLeftBackgroundColor(int leftBackgroundColor) {
        mLeftView.setBackgroundColor(leftBackgroundColor);
    }

    public void setOnSwitchListener(OnSwitchListener mOnSwitchListener) {
        this.mOnSwitchListener = mOnSwitchListener;
    }
}
