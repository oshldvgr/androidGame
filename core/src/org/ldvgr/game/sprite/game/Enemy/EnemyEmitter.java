package org.ldvgr.game.sprite.game.Enemy;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import org.ldvgr.game.Pool.EnemyPool;
import org.ldvgr.game.math.Rect;
import org.ldvgr.game.math.Rnd;
import org.ldvgr.game.utils.Regions;

public class EnemyEmitter {

    private static final float ENEMY_SMALL_HEIGHT = 0.1f;
    private static final float ENEMY_SMALL_BULLET_HEIGHT = 0.01f;
    private static final float ENEMY_SMALL_BULLET_VY = -0.3f;
    private static final int ENEMY_SMALL_BULLET_DAMAGE = 1;
    private static final float ENEMY_SMALL_RELOAD_INTERVAL = 1f;
    private static final int ENEMY_SMALL_HEALTH = 1;
    private static final Vector2 ENEMY_SMALL_VELOCITY = new Vector2(0, -0.2f);
    private final TextureRegion[] enemySmallRegions;

    private static final float ENEMY_MED_HEIGHT = 0.1f;
    private static final float ENEMY_MED_BULLET_HEIGHT = 0.015f;
    private static final float ENEMY_MED_BULLET_VY = -0.25f;
    private static final int ENEMY_MED_BULLET_DAMAGE = 4;
    private static final float ENEMY_MED_RELOAD_INTERVAL = 2f;
    private static final int ENEMY_MED_HEALTH = 5;
    private static final Vector2 ENEMY_MED_VELOCITY = new Vector2(0, -0.15f);
    private TextureRegion[] enemyMedRegions;

    private static final float ENEMY_BIG_HEIGHT = 0.2f;
    private static final float ENEMY_BIG_BULLET_HEIGHT = 0.02f;
    private static final float ENEMY_BIG_BULLET_VY = -0.3f;
    private static final int ENEMY_BIG_BULLET_DAMAGE = 8;
    private static final float ENEMY_BIG_RELOAD_INTERVAL = 2f;
    private static final int ENEMY_BIG_HEALTH = 10;
    private static final Vector2 ENEMY_BIG_VELOCITY = new Vector2(0, -0.05f);
    private TextureRegion[] enemyBigRegions;

    private TextureRegion bulletRegion;

    private EnemyPool enemyPool;
    private float generateInterval = 4f;
    private float generateTimer;
    private Rect worldBounds;


    public EnemyEmitter(EnemyPool enemyPool, TextureAtlas atlas, Rect worldBounds) {
        this.enemyPool = enemyPool;
        this.enemySmallRegions = Regions.split(atlas.findRegion("enemy0"), 1, 2, 2);
        this.enemyMedRegions = Regions.split(atlas.findRegion("enemy1"), 1, 2, 2);
        this.enemyBigRegions = Regions.split(atlas.findRegion("enemy2"), 1, 2, 2);
        this.bulletRegion = atlas.findRegion("bulletEnemy");
        this.worldBounds = worldBounds;
    }

    public void generate(float delta) {
        generateTimer += delta;
        if (generateTimer > generateInterval) {
            generateTimer = 0f;
            EnemyShip enemy;
            float probability = Rnd.nextFloat(0f, 1f);
            if (probability <= 0.4f) {
                enemy = new EnemyShip.Builder(enemyPool)
                        .setRegions(enemySmallRegions)
                        .setVelocity(ENEMY_SMALL_VELOCITY)
                        .setBulletRegion(bulletRegion)
                        .setBulletHeight(ENEMY_SMALL_BULLET_HEIGHT)
                        .setBulletVelocity(ENEMY_SMALL_BULLET_VY)
                        .setBulletDamage(ENEMY_SMALL_BULLET_DAMAGE)
                        .setReloadInterval(ENEMY_SMALL_RELOAD_INTERVAL)
                        .setHeight(ENEMY_SMALL_HEIGHT)
                        .setHealth(ENEMY_SMALL_HEALTH)
                        .build();
            } else if (probability <= 0.75f) {
                enemy = new EnemyShip.Builder(enemyPool)
                        .setRegions(enemyMedRegions)
                        .setVelocity(ENEMY_MED_VELOCITY)
                        .setBulletRegion(bulletRegion)
                        .setHeight(ENEMY_MED_HEIGHT)
                        .setBulletHeight(ENEMY_MED_BULLET_HEIGHT)
                        .setBulletVelocity(ENEMY_MED_BULLET_VY)
                        .setBulletDamage(ENEMY_MED_BULLET_DAMAGE)
                        .setReloadInterval(ENEMY_MED_RELOAD_INTERVAL)
                        .setHeight(ENEMY_MED_HEIGHT)
                        .setHealth(ENEMY_MED_HEALTH)
                        .build();
            } else {
                enemy = new EnemyShip.Builder(enemyPool)
                        .setRegions(enemyBigRegions)
                        .setVelocity(ENEMY_BIG_VELOCITY)
                        .setBulletRegion(bulletRegion)
                        .setHeight(ENEMY_BIG_HEIGHT)
                        .setBulletHeight(ENEMY_BIG_BULLET_HEIGHT)
                        .setBulletVelocity(ENEMY_BIG_BULLET_VY)
                        .setBulletDamage(ENEMY_BIG_BULLET_DAMAGE)
                        .setReloadInterval(ENEMY_BIG_RELOAD_INTERVAL)
                        .setHeight(ENEMY_BIG_HEIGHT)
                        .setHealth(ENEMY_BIG_HEALTH)
                        .build();
            }

            enemy.pos.x = (Rnd.nextFloat(worldBounds.getLeft() + enemy.getHalfWidth(), worldBounds.getRight() - enemy.getHalfWidth()));
            enemy.setTop(worldBounds.getTop());

        }


    }
}
