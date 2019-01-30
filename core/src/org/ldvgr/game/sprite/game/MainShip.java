package org.ldvgr.game.sprite.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import org.ldvgr.game.Pool.BulletPool;
import org.ldvgr.game.base.Sprite;
import org.ldvgr.game.math.Rect;

public class MainShip extends Sprite {
    private Vector2 velocity = new Vector2();
    private final Vector2 v0 = new Vector2(0.2f, 0f);
    private boolean isPressedLeft;
    private boolean isPressedRight;
    private BulletPool bulletPool;
    private TextureRegion bulletRegion;
    private Rect worldBounds;

    public MainShip(TextureAtlas atlas, BulletPool bulletPool) {
        super(atlas.findRegion("main_ship"), 3, 1, 3);
        this.bulletPool = bulletPool;
        this.bulletRegion = atlas.findRegion("star1");
        setHightProportion(0.15f);
        frame = 1;
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        super.resize(worldBounds);
        setBottom(worldBounds.getBottom() + 0.02f);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        pos.mulAdd(velocity, delta);
    }

    public void keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                isPressedLeft = true;
                moveLeft();
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                isPressedRight = true;
                moveRight();
                break;
            case Input.Keys.UP:
                shoot();
                break;
        }
    }

    public void keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                isPressedLeft = false;
                if (isPressedRight) {
                    moveRight();
                } else {
                    stop();
                }
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                isPressedRight = false;
                if (isPressedLeft) {
                    moveLeft();
                } else {
                    stop();
                }
                break;
        }
    }

    private void moveRight() {
        velocity.set(v0);
    }

    private void moveLeft() {
        velocity.set(v0).rotate(180);
    }

    private void stop() {
        velocity.setZero();
    }

    private void shoot() {
        Bullet bullet = bulletPool.obtain();
        bullet.set(this,
                bulletRegion,
                pos,
                new Vector2(0f, 0.3f),
                0.01f,
                worldBounds,
                1);
    }


}
