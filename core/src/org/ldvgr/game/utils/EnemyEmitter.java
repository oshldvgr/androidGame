package org.ldvgr.game.utils;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import org.ldvgr.game.Pool.EnemyPool;
import org.ldvgr.game.math.Rect;
import org.ldvgr.game.math.Rnd;
import org.ldvgr.game.sprite.game.EnemyShip;

public class EnemyEmitter {

    private static final float ENEMY_SMALL_HEIGHT = 0.1f;
    private static final float ENEMY_SMALL_BULLET_HEIGHT = 0.01f;
    private static final float ENEMY_SMALL_BULLET_VY = -0.3f;
    private static final int ENEMY_SMALL_BULLET_DAMAGE = -1;
    private static final float ENEMY_SMALL_RELOAD_INTERVAL = 3f;
    private static final int ENEMY_SMALL_HEALTH = 1;

    private Vector2 enemySmallVelocity = new Vector2(0, -0.2f);
    private TextureRegion[] enemySmallRegions;

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
            enemy.set(
                    enemySmallRegions,
                    enemySmallVelocity,
                    bulletRegion,
                    ENEMY_SMALL_BULLET_HEIGHT,
                    ENEMY_SMALL_BULLET_VY,
                    ENEMY_SMALL_BULLET_DAMAGE,
                    ENEMY_SMALL_RELOAD_INTERVAL,
                    ENEMY_SMALL_HEIGHT,
                    ENEMY_SMALL_HEALTH
            );

            enemy.pos.x = (Rnd.nextFloat(worldBounds.getLeft() + enemy.getHalfWidth(), worldBounds.getRight() - enemy.getHalfWidth()));
            enemy.setTop(worldBounds.getTop());

        }


    }
}
