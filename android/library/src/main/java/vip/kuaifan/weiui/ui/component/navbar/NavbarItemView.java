
package vip.kuaifan.weiui.ui.component.navbar;

import android.content.Context;
import android.util.AttributeSet;
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
}