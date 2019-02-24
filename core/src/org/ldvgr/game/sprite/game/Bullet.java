package org.ldvgr.game.sprite.game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import org.ldvgr.game.base.Sprite;
import org.ldvgr.game.math.Rect;

public class Bullet extends Sprite {
    private Rect worldBounds;
    private Vector2 velocity = new Vector2();
    private int damage;
    private Ship owner;

    public Bullet() {
        regions = new TextureRegion[1];
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(velocity, delta);
        if (isOutside(worldBounds)) {
            destroy();
        }
    }

    public void set(
            Ship owner,
            TextureRegion region,
            Vector2 pos0,
            Vector2 v0,
            float height,
            Rect worldBounds,
            int damage) {
        this.owner = owner;
        this.regions[0] = region;
        this.pos.set(pos0);
        this.velocity.set(v0);
        setHeightProportion(height);
        this.worldBounds = worldBounds;
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public Ship getOwner() {
        return owner;
    }
}

