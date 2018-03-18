package vip.kuaifan.weiui.extend.integration.glide.load.engine;

import android.support.annotation.NonNull;
import vip.kuaifan.weiui.extend.integration.glide.load.Encoder;
import vip.kuaifan.weiui.extend.integration.glide.load.Options;
import vip.kuaifan.weiui.extend.integration.glide.load.engine.cache.DiskCache;
import java.io.File;

/**
 * Writes original source data or downsampled/transformed resource data to cache using the
 * provided {@link vip.kuaifan.weiui.extend.integration.glide.load.Encoder} or
 * {@link vip.kuaifan.weiui.extend.integration.glide.load.ResourceEncoder} and the given data or
 * {@link vip.kuaifan.weiui.extend.integration.glide.load.engine.Resource}.
 *
 * @param <DataType> The type of data that will be encoded (InputStream, ByteBuffer,
 *                  Resource<Bitmap> etc).
 */
class DataCacheWriter<DataType> implements DiskCache.Writer {
  private final Encoder<DataType> encoder;
  private final DataType data;
  private final Options options;

  DataCacheWriter(Encoder<DataType> encoder, DataType data, Options options) {
    this.encoder = encoder;
    this.data = data;
    this.options = options;
  }

  @Override
  public boolean write(@NonNull File file) {
    return encoder.encode(data, file, options);
  }
}
