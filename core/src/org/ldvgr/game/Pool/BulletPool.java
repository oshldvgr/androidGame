package org.ldvgr.game.Pool;

import org.ldvgr.game.base.SpritesPool;
import org.ldvgr.game.sprite.game.Bullet;

public class BulletPool extends SpritesPool<Bullet> {
    @Override
    protected Bullet newObject() {
        return new Bullet();
    }
}
