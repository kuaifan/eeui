/*
 * Copyright 2010 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package vip.kuaifan.weiui.extend.integration.zxing.oned;

import vip.kuaifan.weiui.extend.integration.zxing.BarcodeFormat;
import vip.kuaifan.weiui.extend.integration.zxing.EncodeHintType;
import vip.kuaifan.weiui.extend.integration.zxing.Writer;
import vip.kuaifan.weiui.extend.integration.zxing.WriterException;
import vip.kuaifan.weiui.extend.integration.zxing.common.BitMatrix;

import java.util.Map;

/**
 * This object renders a UPC-A code as a {@link BitMatrix}.
 *
 * @author qwandor@google.com (Andrew Walbran)
 */
public final class UPCAWriter implements Writer {

  private final EAN13Writer subWriter = new EAN13Writer();

  @Override
  public BitMatrix encode(String contents, BarcodeFormat format, int width, int height)
      throws WriterException {
    return encode(contents, format, width, height, null);
  }

  @Override
  public BitMatrix encode(String contents,
                          BarcodeFormat format,
                          int width,
                          int height,
                          Map<EncodeHintType,?> hints) throws WriterException {
    if (format != BarcodeFormat.UPC_A) {
      throw new IllegalArgumentException("Can only encode UPC-A, but got " + format);
    }
    // Transform a UPC-A code into the equivalent EAN-13 code and write it that way
    return subWriter.encode('0' + contents, BarcodeFormat.EAN_13, width, height, hints);
  }

}
