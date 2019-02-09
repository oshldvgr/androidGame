package org.ldvgr.game.sprite.game;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import org.ldvgr.game.Pool.BulletPool;


public class EnemyShip extends Ship {

    private Vector2 v0 = new Vector2();


    public EnemyShip(Sound shootSound, BulletPool bulletPool) {
        super();
        this.velocity.set(v0);
        this.shootSound = shootSound;
        this.bulletPool = bulletPool;
        this.bulletVelocity = new Vector2();
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        this.pos.mulAdd(velocity, delta);
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
            int helthl
    ) {
        this.regions = regions;
        this.v0.set(v0);
        this.bulletRegion = bulletRegion;
        this.bulletHieght = bulletHeight;
        this.bulletVelocity.set(0, bulletVelocityY);
        this.damage = bulletDamage;
        this.reloadInterval = reloadInterval;
        setHightProportion(height);
        this.helth = helth;
        reloadTimer = reloadInterval;
        velocity.set(v0);

    }
}

