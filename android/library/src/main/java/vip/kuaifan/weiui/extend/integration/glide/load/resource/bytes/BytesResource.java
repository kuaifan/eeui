package vip.kuaifan.weiui.extend.integration.glide.load.resource.bytes;

import android.support.annotation.NonNull;
import vip.kuaifan.weiui.extend.integration.glide.load.engine.Resource;
import vip.kuaifan.weiui.extend.integration.glide.util.Preconditions;

/**
 * An {@link vip.kuaifan.weiui.extend.integration.glide.load.engine.Resource} wrapping a byte array.
 */
public class BytesResource implements Resource<byte[]> {
  private final byte[] bytes;

  public BytesResource(byte[] bytes) {
    this.bytes = Preconditions.checkNotNull(bytes);
  }

  @NonNull
  @Override
  public Class<byte[]> getResourceClass() {
    return byte[].class;
  }

  /**
   * In most cases it will only be retrieved once (see linked methods).
   *
   * @return the same array every time, do not mutate the contents. Not a copy returned, because
   * copying the array can be prohibitively expensive and/or lead to OOMs.
   * @see vip.kuaifan.weiui.extend.integration.glide.load.ResourceEncoder
   * @see vip.kuaifan.weiui.extend.integration.glide.load.resource.transcode.ResourceTranscoder
   * @see vip.kuaifan.weiui.extend.integration.glide.request.SingleRequest#onResourceReady
   */
  @NonNull
  @Override
  @SuppressWarnings("PMD.MethodReturnsInternalArray")
  public byte[] get() {
    return bytes;
  }

  @Override
  public int getSize() {
    return bytes.length;
  }

  @Override
  public void recycle() {
    // Do nothing.
  }
}
