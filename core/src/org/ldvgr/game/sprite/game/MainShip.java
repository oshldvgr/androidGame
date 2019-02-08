package org.ldvgr.game.sprite.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import org.ldvgr.game.Pool.BulletPool;
import org.ldvgr.game.base.Sprite;
import org.ldvgr.game.math.Rect;

public class MainShip extends Sprite {

    private static final int INVALID_POINTER = -1;

    private int leftPointer = INVALID_POINTER;
    private int rightPointer = INVALID_POINTER;
    private Vector2 velocity = new Vector2();
    private final Vector2 v0 = new Vector2(0.2f, 0f);
    private boolean isPressedLeft;
    private boolean isPressedRight;
    private BulletPool bulletPool;
    private TextureRegion bulletRegion;
    private Rect worldBounds;
    private Sound shootSound = Gdx.audio.newSound(Gdx.files.internal("SHOOT017.mp3"));
    private float reloadInterval;
    private float reloadTimer;

    public MainShip(TextureAtlas atlas, BulletPool bulletPool) {
        super(atlas.findRegion("main_ship"), 3, 1, 3);
        this.bulletPool = bulletPool;
        this.bulletRegion = atlas.findRegion("star1");
        setHightProportion(0.15f);
        frame = 1;
        this.reloadInterval = 0.2f;
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
        reloadTimer += delta;
        pos.mulAdd(velocity, delta);
        if (reloadTimer >= reloadInterval) {
            reloadTimer = 0f;
            shoot();
        }
        if (getLeft() < worldBounds.getLeft()) {
            setLeft(worldBounds.getLeft());
            stop();
        }
        ;
        if (getRight() > worldBounds.getRight()) {
            setRight(worldBounds.getRight());
            stop();
        }
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
            case Input.Keys.ENTER:
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

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        if (touch.x < 0) {
            if (leftPointer != INVALID_POINTER) {
                return false;
            }
            leftPointer = pointer;
            moveLeft();
        } else {
            if (rightPointer != INVALID_POINTER) {
                return false;
            }
            rightPointer = pointer;
            moveRight();
        }
        return super.touchDown(touch, pointer);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        if (pointer == leftPointer) {
            leftPointer = INVALID_POINTER;
            if (rightPointer != INVALID_POINTER) {
                moveRight();
            } else stop();
        } else if (pointer == rightPointer) {
            rightPointer = INVALID_POINTER;
            if (leftPointer != INVALID_POINTER) {
                moveLeft();
            } else stop();
        }
        return super.touchUp(touch, pointer);
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
        shootSound.play(0.01f);
    }

    public void dispose() {
        shootSound.dispose();
    }
}
