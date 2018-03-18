package vip.kuaifan.weiui.extend.integration.glide;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import vip.kuaifan.weiui.extend.integration.glide.manager.RequestManagerRetriever;
import vip.kuaifan.weiui.extend.integration.glide.module.AppGlideModule;
import java.util.Set;

/**
 * Allows {@link AppGlideModule}s to exclude {@link vip.kuaifan.weiui.extend.integration.glide.annotation.GlideModule}s to
 * ease the migration from {@link vip.kuaifan.weiui.extend.integration.glide.annotation.GlideModule}s to Glide's annotation
 * processing system and optionally provides a
 * {@link vip.kuaifan.weiui.extend.integration.glide.manager.RequestManagerRetriever.RequestManagerFactory} impl.
 */
abstract class GeneratedAppGlideModule extends AppGlideModule {
  /**
   * This method can be removed when manifest parsing is no longer supported.
   */
  @NonNull
  abstract Set<Class<?>> getExcludedModuleClasses();

  @Nullable
  RequestManagerRetriever.RequestManagerFactory getRequestManagerFactory() {
    return null;
  }
}
