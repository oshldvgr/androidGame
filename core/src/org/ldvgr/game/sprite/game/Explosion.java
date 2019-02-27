package org.ldvgr.game.sprite.game;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import org.ldvgr.game.base.Sprite;

public class Explosion extends Sprite {

    private float animateInterval = 0.015f;
    private float animateTimer;
    private Sound explosionSound;

    public Explosion(TextureRegion region, int rows, int cols, int frames, Sound sound) {
        super(region, rows, cols, frames);
        this.explosionSound = sound;

    }

    public void set(float height, Vector2 pos) {
        this.pos.set(pos);
        setHightProportion(height);
        explosionSound.play(0.8f);
    }

    @Override
    public void update(float delta) {
        animateTimer += delta;
        if (animateTimer >= animateInterval) {
            animateTimer = 0.f;
            if (++frame == regions.length) {
                destroy();
            }
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        frame = 0;
    }


}
