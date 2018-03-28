package vip.kuaifan.weiui.extend.integration.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import vip.kuaifan.weiui.extend.integration.glide.load.engine.bitmap_recycle.BitmapPool;
import java.security.MessageDigest;

/**
 * Scales the image uniformly (maintaining the image's aspect ratio) so that one of the dimensions
 * of the image will be equal to the given dimension and the other will be less than the given
 * dimension.
 */
public class FitCenter extends BitmapTransformation {
  private static final String ID = "vip.kuaifan.weiui.extend.integration.glide.load.resource.bitmap.FitCenter";
  private static final byte[] ID_BYTES = ID.getBytes(CHARSET);

  @Override
  protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth,
      int outHeight) {
    return TransformationUtils.fitCenter(pool, toTransform, outWidth, outHeight);
  }

  @Override
  public boolean equals(Object o) {
    return o instanceof FitCenter;
  }

  @Override
  public int hashCode() {
    return ID.hashCode();
  }

  @Override
  public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
    messageDigest.update(ID_BYTES);
  }
}


