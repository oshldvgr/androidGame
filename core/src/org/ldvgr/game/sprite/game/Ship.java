package org.ldvgr.game.sprite.game;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import org.ldvgr.game.Pool.BulletPool;
import org.ldvgr.game.base.Sprite;
import org.ldvgr.game.math.Rect;

public class Ship extends Sprite {

    protected Rect worldBounds;
    protected BulletPool bulletPool;
    protected TextureRegion bulletRegion;
    protected Vector2 velocity = new Vector2();
    protected float reloadInterval;
    protected float reloadTimer;
    protected Sound shootSound;

    protected Vector2 bulletVelocity;
    protected float bulletHieght;
    protected int damage;
    protected int helth;

    public Ship(TextureRegion region, int rows, int cols, int frames) {
        super(region, rows, cols, frames);
    }

    public Ship() {
        super();
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        super.resize(worldBounds);
    }

    public void shoot() {
        Bullet bullet = bulletPool.obtain();
        bullet.set(this,
                bulletRegion,
                pos,
                bulletVelocity,
                bulletHieght,
                worldBounds, damage);
        shootSound.play(0.01f);
    }


    public void dispose() {
        shootSound.dispose();
    }
}
