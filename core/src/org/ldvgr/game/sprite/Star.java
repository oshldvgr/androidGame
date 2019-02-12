package org.ldvgr.game.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import org.ldvgr.game.base.Sprite;
import org.ldvgr.game.math.Rect;
import org.ldvgr.game.math.Rnd;

public class Star extends Sprite {
    private Vector2 velocity = new Vector2();
    private Rect worldBounds;

    public Star(TextureAtlas atlas) {
        super(atlas.findRegion("star"));
        setHeightProportion(0.01f);
        velocity.set(Rnd.nextFloat(-0.005f, 0.1f), Rnd.nextFloat(-0.01f, -0.1f));
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        float posX = Rnd.nextFloat(worldBounds.getLeft(), worldBounds.getRight());
        float posY = Rnd.nextFloat(worldBounds.getBottom(), worldBounds.getTop());
        pos.set(posX, posY);
    }

    private void chekAndHandleBounds() {
        if (getRight() < worldBounds.getLeft()) setLeft(worldBounds.getRight());
        if (getLeft() > worldBounds.getRight()) setRight(worldBounds.getLeft());
        if (getTop() < worldBounds.getBottom()) setBottom(worldBounds.getTop());
        if (getBottom() > worldBounds.getTop()) setTop(worldBounds.getBottom());

    }

    @Override
    public void update(float delta) {
        pos.mulAdd(velocity, delta);
        chekAndHandleBounds();
    }
}
