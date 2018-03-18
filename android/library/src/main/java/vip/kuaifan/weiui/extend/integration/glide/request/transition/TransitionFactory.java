package vip.kuaifan.weiui.extend.integration.glide.request.transition;

import vip.kuaifan.weiui.extend.integration.glide.load.DataSource;

/**
 * A factory class that can produce different {@link Transition}s based on the state of the
 * request.
 *
 * @param <R> The type of resource that needs to be animated into the target.
 */
public interface TransitionFactory<R> {

  /**
   * Returns a new {@link Transition}.
   *
   * @param dataSource      The {@link vip.kuaifan.weiui.extend.integration.glide.load.DataSource} the resource was loaded
   *                        from.
   * @param isFirstResource True if this is the first resource to be loaded into the target.
   */
  Transition<R> build(DataSource dataSource, boolean isFirstResource);
}
