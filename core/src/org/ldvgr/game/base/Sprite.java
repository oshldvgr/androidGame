package org.ldvgr.game.base;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import org.ldvgr.game.math.Rect;
import org.ldvgr.game.utils.Regions;

public class Sprite extends Rect {
    protected float angle;
    protected float scale = 1f;
    protected TextureRegion[] regions;
    protected int frame;
    private boolean isDestroid;

    public Sprite() {
    }

    public Sprite(TextureRegion region, int rows, int cols, int frames) {
        if (region == null) {
            throw new NullPointerException("Create Sprite with null region");
        }
        this.regions = Regions.split(region, rows, cols, frames);
    }

    public Sprite(TextureRegion region) {
        if (region == null) {
            throw new NullPointerException("Create Sprite with null region");
        }
        regions = new TextureRegion[1];
        regions[0] = region;
    }


    public void draw(SpriteBatch batch) {
        batch.draw(
                regions[frame],//текущий регион
                getLeft(), getBottom(),// точка отрисовки
                halfWidth, halfHeight, // точка вращения
                getWidth(), getHeight(), //ширина и высота
                scale, scale, //масштаб по осям
                angle // угол вращения
        );
    }

    public void setHightProportion(float height) {
        setHeight(height);
        float aspect = regions[frame].getRegionWidth() / (float) regions[frame].getRegionHeight();
        setWidth(aspect * height);
    }

    public void resize(Rect worldBounds) {
    }

    public void update(float delta) {
    }

    public boolean touchDown(Vector2 touch, int pointer) {
        return false;
    }

    public boolean touchUp(Vector2 touch, int pointer) {
        return false;
    }

    public float getAngle() {
        return angle;
    }

    public float getScale() {
        return scale;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public void destroy() {
        this.isDestroid = true;
    }

    public void flushDestroy() {
        this.isDestroid = false;
    }

    public boolean isDestroid() {
        return isDestroid;
    }
}
