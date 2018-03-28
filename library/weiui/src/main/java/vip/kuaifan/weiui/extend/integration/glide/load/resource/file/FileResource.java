package vip.kuaifan.weiui.extend.integration.glide.load.resource.file;

import vip.kuaifan.weiui.extend.integration.glide.load.resource.SimpleResource;
import java.io.File;

/**
 * A simple {@link vip.kuaifan.weiui.extend.integration.glide.load.engine.Resource} that wraps a {@link File}.
 */
// Public API.
@SuppressWarnings("WeakerAccess")
public class FileResource extends SimpleResource<File> {
  public FileResource(File file) {
    super(file);
  }
}
