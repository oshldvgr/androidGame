package org.ldvgr.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import org.ldvgr.game.Pool.BulletPool;
import org.ldvgr.game.Pool.EnemyPool;
import org.ldvgr.game.Pool.ExplosionPool;
import org.ldvgr.game.base.BaseScreen;
import org.ldvgr.game.math.Rect;
import org.ldvgr.game.sprite.Background;
import org.ldvgr.game.sprite.Star;
import org.ldvgr.game.sprite.game.Explosion;
import org.ldvgr.game.sprite.game.MainShip;
import org.ldvgr.game.utils.EnemyEmitter;

public class GameScreen extends BaseScreen {
    TextureAtlas atlas;
    TextureAtlas sampleATLAS;
    private Texture backgr;
    private Background background;
    private Star stars[];
    private MainShip mainShip;
    private BulletPool bulletPool;
    private ExplosionPool explosions;
    private EnemyPool enemyPool;
    private EnemyEmitter enemyEmitter;
    Music music;


    @Override
    public void show() {
        super.show();
        backgr = new Texture("background.png");
        background = new Background(new TextureRegion(backgr));
        atlas = new TextureAtlas("texture1.atlas");
        sampleATLAS = new TextureAtlas("mainAtlas.tpack");
        stars = new Star[60];
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new Star(atlas);
        }
        bulletPool = new BulletPool();
        mainShip = new MainShip(atlas, bulletPool);
        explosions = new ExplosionPool(sampleATLAS);
        enemyPool = new EnemyPool(bulletPool);
        enemyEmitter = new EnemyEmitter(enemyPool, sampleATLAS, worldBounds);

        music = Gdx.audio.newMusic(Gdx.files.internal("magic_space.mp3"));
        music.setLooping(true);
        music.setVolume(0.3f);
        music.play();

    }

    public void update(float delta) {
        mainShip.update(delta);
        for (Star star : stars) {
            star.update(delta);
        }
        bulletPool.updateActiveSprites(delta);
        explosions.updateActiveSprites(delta);
        enemyPool.updateActiveSprites(delta);
        enemyEmitter.generate(delta);
    }

    public void deleteAllDestroid() {
        bulletPool.freeAllDestroyedActiveSprites();
        explosions.freeAllDestroyedActiveSprites();
        enemyPool.freeAllDestroyedActiveSprites();
    }

    public void draw() {

        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        background.draw(batch);
        for (Star star : stars) {
            star.draw(batch);
        }
        mainShip.draw(batch);
        bulletPool.drawActiveSprites(batch);
        explosions.drawActiveSprites(batch);
        enemyPool.drawActiveSprites(batch);
        batch.end();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        deleteAllDestroid();
        draw();
    }

    @Override
    public void dispose() {
        backgr.dispose();
        atlas.dispose();
        bulletPool.dispose();
        explosions.dispose();
        enemyPool.dispose();
        music.dispose();
        super.dispose();
    }

    @Override
    public void resize(Rect worldBounds) {
        background.resize(worldBounds);
        mainShip.resize(worldBounds);
        for (Star star : stars) {
            star.resize(worldBounds);
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        mainShip.keyDown(keycode);
        return super.keyDown(keycode);
    }

    @Override
    public boolean keyUp(int keycode) {
        mainShip.keyUp(keycode);
        return super.keyUp(keycode);
    }


    @Override
    public void touchDown(Vector2 touch, int pointer) {
        Explosion explosion = explosions.obtain();
        explosion.set(0.15f, touch);
        mainShip.touchDown(touch, pointer);

    }

    @Override
    public void touchUp(Vector2 touch, int pointer) {
        mainShip.touchUp(touch, pointer);
    }
}
