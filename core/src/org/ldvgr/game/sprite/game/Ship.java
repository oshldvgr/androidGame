package org.ldvgr.game.sprite.game;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import org.ldvgr.game.Pool.BulletPool;
import org.ldvgr.game.Pool.ExplosionPool;
import org.ldvgr.game.base.Sprite;
import org.ldvgr.game.math.Rect;

public abstract class Ship extends Sprite {

    private float reloadTimer;
    private ExplosionPool explosionPool;
    private BulletPool bulletPool;
    private Sound shootSound;

    protected Rect worldBounds;
    protected TextureRegion bulletRegion;
    protected Vector2 velocity = new Vector2();
    protected float reloadInterval;

    protected Vector2 bulletVelocity;
    protected float bulletHeight;
    protected int damage;
    protected int health;

    public Ship(TextureRegion region, int rows, int cols, int frames, Sound shootSound, BulletPool bulletPool, ExplosionPool explosionPool) {
        super(region, rows, cols, frames);
        this.explosionPool = explosionPool;
        this.shootSound = shootSound;
        this.bulletPool = bulletPool;
    }

    public Ship(Rect worldBounds, Sound shootSound, BulletPool bulletPool, ExplosionPool explosionPool) {
        this.explosionPool = explosionPool;
        this.worldBounds = worldBounds;
        this.bulletPool = bulletPool;
        this.shootSound = shootSound;
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        super.resize(worldBounds);
    }

    private void shoot() {
        Bullet bullet = bulletPool.obtain();
        bullet.set(this,
                bulletRegion,
                pos,
                bulletVelocity,
                bulletHeight,
                worldBounds,
                damage);
        shootSound.play(0.01f);
    }

    protected void boom() {
        Explosion explosion = explosionPool.obtain();
        explosion.set(getHeight(), pos);
    }

    protected void shootByTimer(float delta) {
        reloadTimer += delta;
        if (reloadTimer >= reloadInterval) {
            reloadTimer = 0f;
            shoot();
        }
    }
}
