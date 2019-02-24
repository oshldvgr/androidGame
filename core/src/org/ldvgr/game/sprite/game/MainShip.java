package org.ldvgr.game.sprite.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import org.ldvgr.game.Pool.BulletPool;
import org.ldvgr.game.Pool.ExplosionPool;
import org.ldvgr.game.base.Sprite;
import org.ldvgr.game.math.Rect;

public class MainShip extends Ship {

    private static final int INVALID_POINTER = -1;
    private int leftPointer = INVALID_POINTER;
    private int rightPointer = INVALID_POINTER;

    private final Vector2 v0 = new Vector2(0.2f, 0f);
    private boolean isPressedLeft;
    private boolean isPressedRight;


    public MainShip(TextureAtlas atlas, Rect worldBounds, BulletPool bulletPool, ExplosionPool explosionPool) {
        super(atlas.findRegion("main_ship"), 3, 1, 3, worldBounds,
                Gdx.audio.newSound(Gdx.files.internal("SHOOT017.mp3")),
                bulletPool, explosionPool);

        this.bulletRegion = atlas.findRegion("star1");
        setHeightProportion(0.15f);
        frame = 1;
        this.reloadInterval = 0.2f;
        this.bulletVelocity = new Vector2(0, 0.5f);
        this.bulletHeight = 0.01f;
        this.damage = 1;
        this.health = 2;
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setBottom(worldBounds.getBottom() + 0.02f);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        pos.mulAdd(velocity, delta);

        shootByTimer(delta);

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

    @Override
    public boolean isDamageCollisions(Sprite sprite) {
        return ((sprite.getLeft() >= getLeft()) & (sprite.getRight() <= getRight())
                & (sprite.getPos().y <= pos.y));
    }

}
