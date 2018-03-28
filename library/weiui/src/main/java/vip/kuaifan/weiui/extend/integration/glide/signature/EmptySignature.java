package vip.kuaifan.weiui.extend.integration.glide.signature;

import android.support.annotation.NonNull;
import vip.kuaifan.weiui.extend.integration.glide.load.Key;
import java.security.MessageDigest;

/**
 * An empty key that is always equal to all other empty keys.
 */
public final class EmptySignature implements Key {
  private static final EmptySignature EMPTY_KEY = new EmptySignature();

  @NonNull
  public static EmptySignature obtain() {
    return EMPTY_KEY;
  }

  private EmptySignature() {
    // Empty.
  }

  @Override
  public String toString() {
    return "EmptySignature";
  }

  @Override
  public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
    // Do nothing.
  }
}
