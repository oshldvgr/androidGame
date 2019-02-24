package org.ldvgr.game.sprite.game.Enemy;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import org.ldvgr.game.Pool.BulletPool;
import org.ldvgr.game.Pool.EnemyPool;
import org.ldvgr.game.Pool.ExplosionPool;
import org.ldvgr.game.base.Sprite;
import org.ldvgr.game.math.Rect;
import org.ldvgr.game.sprite.game.MainShip;
import org.ldvgr.game.sprite.game.Ship;


public class EnemyShip extends Ship {

    private MainShip mainShip;

    public EnemyShip(Rect worldBounds, Sound shootSound, BulletPool bulletPool, ExplosionPool explosionPool, MainShip mainShip) {
        super(worldBounds, shootSound, bulletPool, explosionPool);
        this.velocity = new Vector2();
        this.bulletVelocity = new Vector2();
        this.mainShip = mainShip;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        this.pos.mulAdd(velocity, delta);
        shootByTimer(delta);
        if (getBottom() < worldBounds.getBottom()) {
            destroy();
            mainShip.damage(damage);
        }
    }

    @Override
    public boolean isDamageCollisions(Sprite sprite) {
        return ((sprite.getLeft() >= getLeft()) & (sprite.getRight() <= getRight())
                & (sprite.getPos().y >= pos.y));
    }

    public static class Builder {
        private EnemyPool enemyPool;
        private TextureRegion[] regions;
        private TextureRegion bulletRegion;
        private float bulletHeight;
        private float bulletVelocityY;
        private int bulletDamage;
        private float reloadInterval;
        private float height;
        private int health;
        private Vector2 v0;

        Builder(EnemyPool enemyPool) {
            this.enemyPool = enemyPool;
        }

        public Builder setEnemyPool(EnemyPool enemyPool) {
            this.enemyPool = enemyPool;
            return this;
        }

        public Builder setRegions(TextureRegion[] regions) {
            this.regions = regions;
            return this;
        }

        public Builder setBulletRegion(TextureRegion bulletRegion) {
            this.bulletRegion = bulletRegion;
            return this;
        }

        public Builder setBulletHeight(float bulletHeight) {
            this.bulletHeight = bulletHeight;
            return this;
        }

        public Builder setBulletVelocity(float bulletVelocityY) {
            this.bulletVelocityY = bulletVelocityY;
            return this;
        }

        public Builder setBulletDamage(int bulletDamage) {
            this.bulletDamage = bulletDamage;
            return this;
        }

        public Builder setReloadInterval(float reloadInterval) {
            this.reloadInterval = reloadInterval;
            return this;
        }

        public Builder setHeight(float height) {
            this.height = height;
            return this;
        }

        public Builder setHealth(int health) {
            this.health = health;
            return this;
        }

        public Builder setVelocity(Vector2 v0) {
            this.v0 = v0;
            return this;
        }

        public EnemyShip build() {
            EnemyShip result = enemyPool.obtain();
            result.regions = this.regions;
            result.bulletRegion = this.bulletRegion;
            result.bulletHeight = this.bulletHeight;
            result.bulletVelocity.set(0, this.bulletVelocityY);
            result.damage = this.bulletDamage;
            result.reloadInterval = this.reloadInterval;
            result.setHeightProportion(this.height);
            result.health = this.health;
            result.velocity.set(this.v0);
            return result;
        }
    }
}

