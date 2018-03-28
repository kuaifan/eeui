package vip.kuaifan.weiui.extend.integration.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import vip.kuaifan.weiui.extend.integration.glide.load.engine.Initializable;
import vip.kuaifan.weiui.extend.integration.glide.load.engine.Resource;
import vip.kuaifan.weiui.extend.integration.glide.load.engine.bitmap_recycle.BitmapPool;
import vip.kuaifan.weiui.extend.integration.glide.util.Preconditions;
import vip.kuaifan.weiui.extend.integration.glide.util.Util;

/**
 * A resource wrapping a {@link android.graphics.Bitmap} object.
 */
public class BitmapResource implements Resource<Bitmap>,
    Initializable {
  private final Bitmap bitmap;
  private final BitmapPool bitmapPool;

  /**
   * Returns a new {@link BitmapResource} wrapping the given {@link Bitmap} if the Bitmap is
   * non-null or null if the given Bitmap is null.
   *
   * @param bitmap     A Bitmap.
   * @param bitmapPool A non-null {@link vip.kuaifan.weiui.extend.integration.glide.load.engine.bitmap_recycle.BitmapPool}.
   */
  @Nullable
  public static BitmapResource obtain(@Nullable Bitmap bitmap, @NonNull BitmapPool bitmapPool) {
    if (bitmap == null) {
      return null;
    } else {
      return new BitmapResource(bitmap, bitmapPool);
    }
  }

  public BitmapResource(@NonNull Bitmap bitmap, @NonNull BitmapPool bitmapPool) {
    this.bitmap = Preconditions.checkNotNull(bitmap, "Bitmap must not be null");
    this.bitmapPool = Preconditions.checkNotNull(bitmapPool, "BitmapPool must not be null");
  }

  @NonNull
  @Override
  public Class<Bitmap> getResourceClass() {
    return Bitmap.class;
  }

  @NonNull
  @Override
  public Bitmap get() {
    return bitmap;
  }

  @Override
  public int getSize() {
    return Util.getBitmapByteSize(bitmap);
  }

  @Override
  public void recycle() {
    bitmapPool.put(bitmap);
  }

  @Override
  public void initialize() {
    bitmap.prepareToDraw();
  }
}
