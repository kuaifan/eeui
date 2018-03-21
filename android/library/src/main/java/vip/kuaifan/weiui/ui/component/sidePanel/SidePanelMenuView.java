
package vip.kuaifan.weiui.ui.component.sidePanel;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class SidePanelMenuView extends FrameLayout {

    private String name = "";

    public SidePanelMenuView(Context context) {
        super(context);
    }

    public SidePanelMenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SidePanelMenuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}