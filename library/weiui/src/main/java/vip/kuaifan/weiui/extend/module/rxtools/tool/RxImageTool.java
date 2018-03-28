package vip.kuaifan.weiui.extend.module.rxtools.tool;

import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.Matrix;

/**
 *
 * @author vondear
 * @date 2016/1/24
 * 图像工具类
 */

public class RxImageTool {

    static ObjectAnimator invisToVis;
    static ObjectAnimator visToInvis;

    /**
     * Resize the bitmap
     *
     * @param bitmap 图片引用
     * @param width 宽度
     * @param height 高度
     * @return 缩放之后的图片引用
     */
    public static Bitmap zoomBitmap(Bitmap bitmap, int width, int height) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Matrix matrix = new Matrix();
        float scaleWidth = ((float) width / w);
        float scaleHeight = ((float) height / h);
        matrix.postScale(scaleWidth, scaleHeight);
        return Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
    }

}
