package vip.kuaifan.weiui.extend.integration.glide.load.engine;

import vip.kuaifan.weiui.extend.integration.glide.load.Key;

interface EngineJobListener {

  void onEngineJobComplete(EngineJob<?> engineJob, Key key, EngineResource<?> resource);

  void onEngineJobCancelled(EngineJob<?> engineJob, Key key);
}
