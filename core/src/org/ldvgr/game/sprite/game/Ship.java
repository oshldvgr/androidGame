package org.ldvgr.game.sprite.game;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import org.ldvgr.game.Pool.BulletPool;
import org.ldvgr.game.Pool.ExplosionPool;
import org.ldvgr.game.base.Sprite;
import org.ldvgr.game.math.Rect;

public abstract class Ship extends Sprite {

    private float reloadTimer = 0f;
    private ExplosionPool explosionPool;
    private BulletPool bulletPool;
    private Sound shootSound;
    private float damageInterval = 0.1f;
    private float damageTimer = damageInterval;

    protected Rect worldBounds;
    protected TextureRegion bulletRegion;
    protected Vector2 velocity = new Vector2();
    protected float reloadInterval;

    protected Vector2 bulletVelocity;
    protected float bulletHeight;
    protected int damage;
    protected int health;

    public Ship(TextureRegion region, int rows, int cols, int frames, Rect worldBounds,
                Sound shootSound, BulletPool bulletPool, ExplosionPool explosionPool) {
        super(region, rows, cols, frames);
        this.worldBounds = worldBounds;
        this.explosionPool = explosionPool;
        this.shootSound = shootSound;
        this.bulletPool = bulletPool;
    }

    public Ship(Rect worldBounds, Sound shootSound, BulletPool bulletPool, ExplosionPool explosionPool) {
        this.worldBounds = worldBounds;
        this.explosionPool = explosionPool;
        this.shootSound = shootSound;
        this.bulletPool = bulletPool;
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
        // shootSound.play(0.005f);
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

    @Override
    public void update(float delta) {
        super.update(delta);
        damageTimer += delta;
        if (damageTimer >= damageInterval) {
            frame = 0;
        }

    }

    public abstract boolean isDamageCollisions(Sprite sprite);

    public void damage(int damage) {
        frame = 1;
        damageTimer = 0;

        health = health - damage;

        System.out.println("Осталось жизней у " + this + ": " + health);
        if (health <= 0) {
            destroy();
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        boom();
    }

    public int getDamage() {
        return damage;
    }
}
