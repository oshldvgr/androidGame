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
    private static final int ENEMY_SMALL_BULLET_DAMAGE = -1;
    private static final float ENEMY_SMALL_RELOAD_INTERVAL = 3f;
    private static final int ENEMY_SMALL_HEALTH = 1;

    private static final float ENEMY_MED_HEIGHT = 0.1f;
    private static final float ENEMY_MED_BULLET_HEIGHT = 0.015f;
    private static final float ENEMY_MED_BULLET_VY = -0.15f;
    private static final int ENEMY_MED_BULLET_DAMAGE = -4;
    private static final float ENEMY_MED_RELOAD_INTERVAL = 2f;
    private static final int ENEMY_MED_HEALTH = 5;

    private static final float ENEMY_BIG_HEIGHT = 0.1f;
    private static final float ENEMY_BIG_BULLET_HEIGHT = 0.02f;
    private static final float ENEMY_BIG_BULLET_VY = -0.3f;
    private static final int ENEMY_BIG_BULLET_DAMAGE = -8;
    private static final float ENEMY_BIG_RELOAD_INTERVAL = 4f;
    private static final int ENEMY_BIG_HEALTH = 10;

    private static final Vector2 ENEMY_SMALL_VELOCITY = new Vector2(0, -0.2f);
    private TextureRegion[] enemySmallRegions;

    private static final Vector2 ENEMY_MED_VELOCITY = new Vector2(0, -0.3f);
    private TextureRegion[] enemyMedRegions;

    private static final Vector2 ENEMY_BIG_VELOCITY = new Vector2(0, -0.1f);
    private TextureRegion[] enemyBigRegions;

    private TextureRegion bulletRegion;


    private EnemyPool enemyPool;
    private float generateInterval = 4f;
    private float generateTimer;
    private Rect worldBounds;


    public EnemyEmitter(EnemyPool enemyPool, TextureAtlas atlas, Rect worldBounds) {
        this.enemyPool = enemyPool;
        TextureRegion textureRegion = atlas.findRegion("enemy0");
        this.enemySmallRegions = Regions.split(textureRegion, 1, 2, 2);
        this.bulletRegion = atlas.findRegion("bulletEnemy");
        this.worldBounds = worldBounds;
    }

    public void generate(float delta) {
        generateTimer += delta;
        if (generateTimer > generateInterval) {
            generateTimer = 0f;
            EnemyShip enemy = enemyPool.obtain();
            float probability = Rnd.nextFloat(0f, 1f);
            if (probability <= 0.4f) {
                enemy.set(
                        enemySmallRegions,
                        ENEMY_SMALL_VELOCITY,
                        bulletRegion,
                        ENEMY_SMALL_BULLET_HEIGHT,
                        ENEMY_SMALL_BULLET_VY,
                        ENEMY_SMALL_BULLET_DAMAGE,
                        ENEMY_SMALL_RELOAD_INTERVAL,
                        ENEMY_SMALL_HEIGHT,
                        ENEMY_SMALL_HEALTH
                );
            } else if (probability <= 0.75f) {
                enemy.set(
                        enemyMedRegions,
                        ENEMY_MED_VELOCITY,
                        bulletRegion,
                        ENEMY_MED_BULLET_HEIGHT,
                        ENEMY_MED_BULLET_VY,
                        ENEMY_MED_BULLET_DAMAGE,
                        ENEMY_MED_RELOAD_INTERVAL,
                        ENEMY_MED_HEIGHT,
                        ENEMY_MED_HEALTH);
            } else {
                enemy.set(
                        enemyBigRegions,
                        ENEMY_BIG_VELOCITY,
                        bulletRegion,
                        ENEMY_BIG_BULLET_HEIGHT,
                        ENEMY_BIG_BULLET_VY,
                        ENEMY_BIG_BULLET_DAMAGE,
                        ENEMY_BIG_RELOAD_INTERVAL,
                        ENEMY_BIG_HEIGHT,
                        ENEMY_BIG_HEALTH);
            }

            enemy.pos.x = (Rnd.nextFloat(worldBounds.getLeft() + enemy.getHalfWidth(), worldBounds.getRight() - enemy.getHalfWidth()));
            enemy.setTop(worldBounds.getTop());

        }


    }
}
