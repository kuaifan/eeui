package vip.kuaifan.weiui.extend.module;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager.LayoutParams;

import vip.kuaifan.weiui.R;

public class weiuiDialog extends Dialog {

    private Context mContext;

    private LayoutParams mLayoutParams;

    public LayoutParams getLayoutParams() {
        return mLayoutParams;
    }

    public weiuiDialog(Context context, int themeResId) {
        super(context, themeResId);
        initView(context);
    }

    public weiuiDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initView(context);
    }

    public weiuiDialog(Context context) {
        super(context);
        initView(context);
    }

    /**
     * @param context
     * @param alpha   透明度 0.0f--1f(不透明)
     * @param gravity 方向(Gravity.BOTTOM,Gravity.TOP,Gravity.LEFT,Gravity.RIGHT)
     */
    public weiuiDialog(Context context, float alpha, int gravity) {
        super(context);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = this.getWindow();
        if (window != null) {
            window.setBackgroundDrawableResource(R.drawable.transparent_bg);
            mContext = context;
            mLayoutParams = window.getAttributes();
            mLayoutParams.alpha = alpha;
            window.setAttributes(mLayoutParams);
            if (mLayoutParams != null) {
                mLayoutParams.height = android.view.ViewGroup.LayoutParams.MATCH_PARENT;
                mLayoutParams.gravity = gravity;
            }
        }

    }

    private void initView(Context context) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = this.getWindow();
        if (window != null) {
            window.setBackgroundDrawableResource(R.drawable.transparent_bg);
            mContext = context;
            mLayoutParams = window.getAttributes();
            mLayoutParams.alpha = 1f;
            window.setAttributes(mLayoutParams);
            if (mLayoutParams != null) {
                mLayoutParams.height = android.view.ViewGroup.LayoutParams.MATCH_PARENT;
                mLayoutParams.gravity = Gravity.CENTER;
            }
        }
    }

    /**
     * 隐藏头部导航栏状态栏
     */
    public void skipTools() {
        if (Build.VERSION.SDK_INT < 19) {
            return;
        }
        Window window = getWindow();
        if (window != null) {
            window.setFlags(LayoutParams.FLAG_FULLSCREEN, LayoutParams.FLAG_FULLSCREEN);
        }
    }

    /**
     * 设置全屏显示
     */
    public void setFullScreen() {
        Window window = getWindow();
        if (window != null) {
            window.getDecorView().setPadding(0, 0, 0, 0);
            LayoutParams lp = window.getAttributes();
            lp.width = LayoutParams.MATCH_PARENT;
            lp.height = LayoutParams.MATCH_PARENT;
            window.setAttributes(lp);
        }
    }

    /**
     * 设置宽度match_parent
     */
    public void setFullScreenWidth() {
        Window window = getWindow();
        if (window != null) {
            window.getDecorView().setPadding(0, 0, 0, 0);
            LayoutParams lp = window.getAttributes();
            lp.width = LayoutParams.MATCH_PARENT;
            lp.height = LayoutParams.WRAP_CONTENT;
            window.setAttributes(lp);
        }
    }

    /**
     * 设置高度为match_parent
     */
    public void setFullScreenHeight() {
        Window window = getWindow();
        if (window != null) {
            window.getDecorView().setPadding(0, 0, 0, 0);
            LayoutParams lp = window.getAttributes();
            lp.width = LayoutParams.WRAP_CONTENT;
            lp.height = LayoutParams.MATCH_PARENT;
            window.setAttributes(lp);
        }
    }

    public void setOnWhole() {
        Window window = getWindow();
        if (window != null) {
            window.setType(LayoutParams.TYPE_SYSTEM_ALERT);
        }
    }
}
