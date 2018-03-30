package vip.kuaifan.weiui.extend.view;

/**
 * Created by WDM on 2017/11/16.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.util.AttributeSet;
import android.view.View;

public class CircleView extends View {
    private Paint mBgPaint = new Paint();
    private boolean lockScale = true;
    PaintFlagsDrawFilter pfd = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);

    public CircleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mBgPaint.setColor(Color.WHITE);
        mBgPaint.setAntiAlias(true);
    }

    public CircleView(Context context) {
        super(context);
        mBgPaint.setColor(Color.WHITE);
        mBgPaint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if (lockScale) {
            int max = Math.max(measuredWidth, measuredHeight);
            setMeasuredDimension(max, max);
        }else{
            setMeasuredDimension(measuredWidth, measuredHeight);
        }
    }

    @Override
    public void setBackgroundColor(int color) {
        mBgPaint.setColor(color);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.setDrawFilter(pfd);//给Canvas加上抗锯齿标志
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, Math.max(getWidth(), getHeight()) / 2, mBgPaint);
        super.draw(canvas);
    }

    /**
     * 锁定宽高比例
     * @param lockScale
     */
    public void setLockScale(boolean lockScale) {
        this.lockScale = lockScale;
    }
}