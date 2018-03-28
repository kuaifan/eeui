package vip.kuaifan.weiui.extend.integration.glide.load.resource.gif;

import android.support.annotation.NonNull;
import vip.kuaifan.weiui.extend.integration.glide.load.engine.Initializable;
import vip.kuaifan.weiui.extend.integration.glide.load.resource.drawable.DrawableResource;

/**
 * A resource wrapping an {@link vip.kuaifan.weiui.extend.integration.glide.load.resource.gif.GifDrawable}.
 */
public class GifDrawableResource extends DrawableResource<GifDrawable>
    implements Initializable {
  // Public API.
  @SuppressWarnings("WeakerAccess")
  public GifDrawableResource(GifDrawable drawable) {
    super(drawable);
  }

  @NonNull
  @Override
  public Class<GifDrawable> getResourceClass() {
    return GifDrawable.class;
  }

  @Override
  public int getSize() {
    return drawable.getSize();
  }

  @Override
  public void recycle() {
    drawable.stop();
    drawable.recycle();
  }

  @Override
  public void initialize() {
    drawable.getFirstFrame().prepareToDraw();
  }
}
