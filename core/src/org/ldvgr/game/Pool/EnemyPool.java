package org.ldvgr.game.Pool;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import org.ldvgr.game.base.SpritesPool;
import org.ldvgr.game.math.Rect;
import org.ldvgr.game.sprite.game.Enemy.EnemyShip;
import org.ldvgr.game.sprite.game.MainShip;

public class EnemyPool extends SpritesPool<EnemyShip> {
    private Sound shootSound;
    private BulletPool bulletPool;
    private Rect worldBounds;
    private ExplosionPool explosionPool;
    private MainShip mainShip;

    public EnemyPool(BulletPool bulletPool, Rect worldBounds, ExplosionPool explosionPool, MainShip mainShip) {
        this.shootSound = Gdx.audio.newSound(Gdx.files.internal("SHOOT017.mp3"));
        this.bulletPool = bulletPool;
        this.worldBounds = worldBounds;
        this.explosionPool = explosionPool;
        this.mainShip = mainShip;
    }

    @Override
    protected EnemyShip newObject() {
        return new EnemyShip(worldBounds, shootSound, bulletPool, explosionPool, mainShip);
    }

    @Override
    public void dispose() {
        super.dispose();
        shootSound.dispose();
    }
}
