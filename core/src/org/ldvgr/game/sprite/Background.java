package org.ldvgr.game.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import org.ldvgr.game.base.Sprite;
import org.ldvgr.game.math.Rect;

public class Background extends Sprite {
    public Background(TextureRegion region) {
        super(region);
    }

    @Override
    public void resize(Rect worldBounds) {
        pos.set(worldBounds.pos);
        setHeightProportion(worldBounds.getHeight());
    }


}
