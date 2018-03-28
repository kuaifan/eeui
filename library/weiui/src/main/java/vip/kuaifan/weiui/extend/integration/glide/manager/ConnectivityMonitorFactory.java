package vip.kuaifan.weiui.extend.integration.glide.manager;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 * A factory class that produces a functional
 * {@link vip.kuaifan.weiui.extend.integration.glide.manager.ConnectivityMonitor}.
 */
public interface ConnectivityMonitorFactory {

  @NonNull
  ConnectivityMonitor build(
      @NonNull Context context,
      @NonNull ConnectivityMonitor.ConnectivityListener listener);
}
