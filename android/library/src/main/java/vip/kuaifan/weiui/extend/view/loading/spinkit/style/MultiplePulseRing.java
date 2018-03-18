package vip.kuaifan.weiui.extend.view.loading.spinkit.style;

import vip.kuaifan.weiui.extend.view.loading.spinkit.sprite.Sprite;
import vip.kuaifan.weiui.extend.view.loading.spinkit.sprite.SpriteContainer;

/**
 * Created by ybq.
 */
public class MultiplePulseRing extends SpriteContainer {

    @Override
    public Sprite[] onCreateChild() {
        return new Sprite[]{
                new PulseRing(),
                new PulseRing(),
                new PulseRing(),
        };
    }

    @Override
    public void onChildCreated(Sprite... sprites) {
        for (int i = 0; i < sprites.length; i++) {
            sprites[i].setAnimationDelay(200 * (i + 1));
        }
    }
}
