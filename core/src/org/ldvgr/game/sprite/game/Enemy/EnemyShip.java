package org.ldvgr.game.sprite.game.Enemy;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import org.ldvgr.game.Pool.BulletPool;
import org.ldvgr.game.Pool.ExplosionPool;
import org.ldvgr.game.math.Rect;
import org.ldvgr.game.sprite.game.Ship;


public class EnemyShip extends Ship {

    public EnemyShip(Rect worldBounds, Sound shootSound, BulletPool bulletPool, ExplosionPool explosionPool) {
        super(worldBounds, shootSound, bulletPool, explosionPool);
        this.velocity = new Vector2();
        this.bulletVelocity = new Vector2();
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        this.pos.mulAdd(velocity, delta);

        shootByTimer(delta);

        if (getBottom() < worldBounds.getBottom()) {
            destroy();
            boom();
        }
    }

    public void set(
            TextureRegion[] regions,
            Vector2 v0,
            TextureRegion bulletRegion,
            float bulletHeight,
            float bulletVelocityY,
            int bulletDamage,
            float reloadInterval,
            float height,
            int health
    ) {
        this.regions = regions;
        this.bulletRegion = bulletRegion;
        this.bulletHeight = bulletHeight;
        this.bulletVelocity.set(0, bulletVelocityY);
        this.damage = bulletDamage;
        this.reloadInterval = reloadInterval;
        setHeightProportion(height);
        this.health = health;
        this.velocity.set(v0);
    }
}

