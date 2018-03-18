package vip.kuaifan.weiui.extend.integration.glide.load.resource.bitmap;

import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import vip.kuaifan.weiui.extend.integration.glide.load.engine.Initializable;
import vip.kuaifan.weiui.extend.integration.glide.load.engine.bitmap_recycle.BitmapPool;
import vip.kuaifan.weiui.extend.integration.glide.load.resource.drawable.DrawableResource;
import vip.kuaifan.weiui.extend.integration.glide.util.Util;

/**
 * A {@link vip.kuaifan.weiui.extend.integration.glide.load.engine.Resource} that wraps an
 * {@link android.graphics.drawable.BitmapDrawable}
 *
 * <p> This class ensures that every call to {@link #get()}} always returns a new
 * {@link android.graphics.drawable.BitmapDrawable} to avoid rendering issues if used in multiple
 * views and is also responsible for returning the underlying {@link android.graphics.Bitmap} to the
 * given {@link vip.kuaifan.weiui.extend.integration.glide.load.engine.bitmap_recycle.BitmapPool} when the resource is
 * recycled. </p>
 */
public class BitmapDrawableResource extends DrawableResource<BitmapDrawable>
    implements Initializable {
  private final BitmapPool bitmapPool;

  // Public API.
  @SuppressWarnings("WeakerAccess")
  public BitmapDrawableResource(BitmapDrawable drawable, BitmapPool bitmapPool) {
    super(drawable);
    this.bitmapPool = bitmapPool;
  }

  @NonNull
  @Override
  public Class<BitmapDrawable> getResourceClass() {
    return BitmapDrawable.class;
  }

  @Override
  public int getSize() {
    return Util.getBitmapByteSize(drawable.getBitmap());
  }

  @Override
  public void recycle() {
    bitmapPool.put(drawable.getBitmap());
  }

  @Override
  public void initialize() {
    drawable.getBitmap().prepareToDraw();
  }
}
