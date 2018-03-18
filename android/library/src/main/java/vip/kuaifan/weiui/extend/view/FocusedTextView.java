package vip.kuaifan.weiui.extend.view;

/**
 * Created by WDM on 2017/11/13.
 */

import android.content.Context;
import android.util.AttributeSet;

public class FocusedTextView extends android.support.v7.widget.AppCompatTextView {

    public FocusedTextView(Context context) {
        super(context);
    }

    public FocusedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setMarqueeRepeatLimit(-1);
    }

    public FocusedTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setMarqueeRepeatLimit(-1);
    }

    /**
     * 欺骗系统,让系统认为FocusedTextView得到了焦点了
     */
    public boolean isFocused() {
        return true;
    }
}