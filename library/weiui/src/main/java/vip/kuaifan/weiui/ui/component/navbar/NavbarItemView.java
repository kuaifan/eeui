
package vip.kuaifan.weiui.ui.component.navbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.FrameLayout;

public class NavbarItemView extends FrameLayout {

    private String type = "";

    public NavbarItemView(Context context) {
        super(context);
    }

    public NavbarItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NavbarItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void selectableItemBackground() {
        try {
            setClickable(true);
            setFocusable(true);
            TypedValue typedValue = new TypedValue();
            getContext().getTheme().resolveAttribute(android.R.attr.selectableItemBackground, typedValue, true);
            int[] attribute = new int[]{android.R.attr.selectableItemBackground};
            TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(typedValue.resourceId, attribute);
            setForeground(typedArray.getDrawable(0));
            typedArray.recycle();
        }catch (Exception ignored) {
            //
        }
    }
}