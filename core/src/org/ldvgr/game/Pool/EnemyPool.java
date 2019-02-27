package org.ldvgr.game.Pool;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import org.ldvgr.game.base.SpritesPool;
import org.ldvgr.game.sprite.game.EnemyShip;

public class EnemyPool extends SpritesPool<EnemyShip> {
    private Sound shootSound;
    private BulletPool bulletPool;

    public EnemyPool(BulletPool bulletPool) {
        this.shootSound = Gdx.audio.newSound(Gdx.files.internal("SHOOT017.mp3"));
        this.bulletPool = bulletPool;
    }

    @Override
    protected EnemyShip newObject() {
        return new EnemyShip(shootSound, bulletPool);
    }

    @Override
    public void dispose() {
        super.dispose();
        shootSound.dispose();
    }
}
