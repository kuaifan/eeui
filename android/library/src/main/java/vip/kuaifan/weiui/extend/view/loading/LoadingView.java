package vip.kuaifan.weiui.extend.view.loading;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;

import java.io.InputStream;

public class LoadingView extends View {
    private Paint paint = new Paint();
    private RectF rect = new RectF();
    //属性
    private String text;
    private int textSize = dip2px(this.getContext(), 14);
    private int textColor = 0xFFFFFFFF;
    private int icon;
    private int iconWidth = dip2px(this.getContext(), 42);
    private int iconHeight = dip2px(this.getContext(), 42);
    private int cornerRadius = dip2px(this.getContext(), 4);
    private int backgroundColor = 0xBE000000;
    private int spacing = dip2px(this.getContext(), 10);//文字与图标的间距
    private int paddingLeft = dip2px(this.getContext(), 10);
    private int paddingTop = dip2px(this.getContext(), 10);
    private int paddingRight = dip2px(this.getContext(), 10);
    private int paddingBottom = dip2px(this.getContext(), 10);
    private int cycle = 600;//周期
    //运行时参数
    private Bitmap iconBitmap;
    private int currentdegrees;

    public LoadingView(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }

    /**
     * 计算组件宽度
     */
    private int measureWidth(int measureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = getDefaultWidth();
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    /**
     * 计算组件高度
     */
    private int measureHeight(int measureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = getDefaultHeight();
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    /**
     * 计算默认宽度
     */
    private int getDefaultWidth() {
        int width = 0;
        if (this.text != null && !this.text.trim().equals("")) {
            paint.setTextSize(this.textSize);
            width = (int) this.paint.measureText(this.text);
        }
        if (this.icon != 0) {
            width = this.iconWidth > width ? this.iconWidth : width;
        }
        width += this.paddingLeft;
        width += this.paddingRight;
        return width;
    }

    /**
     * 计算默认宽度
     */
    private int getDefaultHeight() {
        int height = 0;
        if (this.text != null && !this.text.trim().equals("")) {
            paint.setTextSize(this.textSize);
            FontMetricsInt fontMetrics = paint.getFontMetricsInt();
            int txtHeight = fontMetrics.bottom - fontMetrics.ascent;
            height += txtHeight;
            height += this.spacing;
        }
        if (this.icon != 0) {
            height += this.iconHeight;
        }
        height += this.paddingTop;
        height += this.paddingBottom;
        return height;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBackground(canvas);
        drawText(canvas);
        drawIcon(canvas);

    }

    /**
     * 画背景
     */
    private void drawBackground(Canvas canvas) {
        rect.left = 0;
        rect.top = 0;
        rect.right = this.getWidth();
        rect.bottom = this.getHeight();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(this.backgroundColor);
        canvas.drawRoundRect(rect, cornerRadius, cornerRadius, paint);
    }

    /**
     * 画icon
     */
    private void drawIcon(Canvas canvas) {
        if (this.iconBitmap == null) {
            this.iconBitmap = loadIcon();
        }
        if (this.iconBitmap == null)
            return;
        int contentWidth = this.getWidth() - this.paddingLeft - this.paddingRight;
        int contentHeight = this.getHeight() - this.paddingTop - this.paddingBottom;
        if (this.text != null && !this.text.trim().equals("")) {
            paint.setTextSize(this.textSize);
            FontMetricsInt fontMetrics = paint.getFontMetricsInt();
            int txtHeight = fontMetrics.bottom - fontMetrics.ascent;
            contentHeight -= txtHeight;
            contentHeight -= this.spacing;
        }
        int left = this.paddingLeft + (contentWidth / 2 - this.iconBitmap.getWidth() / 2);
        int top = this.paddingTop + (contentHeight / 2 - this.iconBitmap.getHeight() / 2);
        rect.left = left;
        rect.top = top;
        rect.right = left + this.iconWidth;
        rect.bottom = top + this.iconHeight;
        canvas.save();
        canvas.clipRect(rect);
        canvas.rotate(currentdegrees, left + rect.width() / 2, top + rect.height() / 2);
        canvas.drawBitmap(this.iconBitmap, left, top, null);
        canvas.restore();
    }

    /**
     * 画文字
     */
    private void drawText(Canvas canvas) {
        if (this.text == null || this.text.trim().equals(""))
            return;
        paint.setTextSize(this.textSize);
        paint.setColor(this.textColor);
        FontMetricsInt fontMetrics = paint.getFontMetricsInt();
        int txtHeight = fontMetrics.bottom - fontMetrics.ascent;
        int txtWidth = (int) this.paint.measureText(this.text);
        int left = this.paddingLeft + ((this.getWidth() - this.paddingLeft - this.paddingRight) / 2 - txtWidth / 2);
        int top = this.paddingTop;
        if (this.iconBitmap != null) {
            top += this.iconHeight;
            top += this.spacing;
        }
        top = top + (this.getHeight() - top - this.paddingBottom) / 2 - txtHeight / 2;
        canvas.drawText(text, left, top - fontMetrics.ascent, paint);
    }

    /**
     * 加载icon
     */
    private Bitmap loadIcon() {
        if (this.icon == 0)
            return null;
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.ARGB_4444;
        InputStream is = getResources().openRawResource(this.icon);
        Bitmap bitmap = BitmapFactory.decodeStream(is, null, opt);
        bitmap = resizeBitmap(bitmap, this.iconWidth, this.iconHeight);
        return bitmap;
    }

    /**
     * 调整图片大小
     */
    public static Bitmap resizeBitmap(Bitmap bitmap, int newWidth, int newHeight) {
        if (bitmap != null) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            float scaleWidth = ((float) newWidth) / width;
            float scaleHeight = ((float) newHeight) / height;
            Matrix matrix = new Matrix();
            matrix.postScale(scaleWidth, scaleHeight);
            Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
            return resizedBitmap;
        } else {
            return null;
        }
    }

    /**
     * 开启动画
     */
    public void startAnimation() {
        RotateAnimation animation = new RotateAnimation();
        animation.setInterpolator(new LinearInterpolator());
        animation.setDuration(this.cycle);
        animation.setRepeatCount(-1);
        startAnimation(animation);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 从 sp 的单位 转成为 px(像素)
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 旋转动画实现类
     */
    private class RotateAnimation extends Animation {
        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            currentdegrees = (int) (359 * interpolatedTime);
            invalidate();
        }
    }

    /**
     * 触碰事件
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public int getIconWidth() {
        return iconWidth;
    }

    public void setIconWidth(int iconWidth) {
        this.iconWidth = iconWidth;
    }

    public int getIconHeight() {
        return iconHeight;
    }

    public void setIconHeight(int iconHeight) {
        this.iconHeight = iconHeight;
    }

    public int getCornerRadius() {
        return cornerRadius;
    }

    public void setCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public int getSpacing() {
        return spacing;
    }

    public void setSpacing(int spacing) {
        this.spacing = spacing;
    }

    public int getPaddingLeft() {
        return paddingLeft;
    }

    public void setPaddingLeft(int paddingLeft) {
        this.paddingLeft = paddingLeft;
    }

    public int getPaddingTop() {
        return paddingTop;
    }

    public void setPaddingTop(int paddingTop) {
        this.paddingTop = paddingTop;
    }

    public int getPaddingRight() {
        return paddingRight;
    }

    public void setPaddingRight(int paddingRight) {
        this.paddingRight = paddingRight;
    }

    public int getPaddingBottom() {
        return paddingBottom;
    }

    public void setPaddingBottom(int paddingBottom) {
        this.paddingBottom = paddingBottom;
    }

    public void setPadding(int padding) {
        paddingLeft = padding;
        paddingTop = padding;
        paddingRight = padding;
        paddingBottom = padding;
    }

    public int getCycle() {
        return cycle;
    }

    public void setCycle(int cycle) {
        this.cycle = cycle;
    }
}
