/*
 * Copyright 2007 ZXing authors
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

package vip.kuaifan.weiui.extend.integration.zxing.pdf417.detector;

import vip.kuaifan.weiui.extend.integration.zxing.ResultPoint;
import vip.kuaifan.weiui.extend.integration.zxing.common.BitMatrix;

import java.util.List;

/**
 * @author Guenther Grau
 */
public final class PDF417DetectorResult {

  private final BitMatrix bits;
  private final List<ResultPoint[]> points;

  public PDF417DetectorResult(BitMatrix bits, List<ResultPoint[]> points) {
    this.bits = bits;
    this.points = points;
  }

  public BitMatrix getBits() {
    return bits;
  }

  public List<ResultPoint[]> getPoints() {
    return points;
  }

}
