package vip.kuaifan.weiui.extend.integration.iconify.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import vip.kuaifan.weiui.extend.integration.iconify.Iconify;
import vip.kuaifan.weiui.extend.integration.iconify.internal.HasOnViewAttachListener;

public class IconButton extends Button implements HasOnViewAttachListener {

    private HasOnViewAttachListenerDelegate delegate;

    public IconButton(Context context) {
        super(context);
        init();
    }

    public IconButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public IconButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        setTransformationMethod(null);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(Iconify.compute(getContext(), text, this), type);
    }


    @Override
    public void setOnViewAttachListener(OnViewAttachListener listener) {
        if (delegate == null) delegate = new HasOnViewAttachListenerDelegate(this);
        delegate.setOnViewAttachListener(listener);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        delegate.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        delegate.onDetachedFromWindow();
    }
}
