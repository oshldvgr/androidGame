package org.ldvgr.game.Pool;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import org.ldvgr.game.base.SpritesPool;
import org.ldvgr.game.sprite.game.Explosion;

public class ExplosionPool extends SpritesPool<Explosion> {

    private TextureRegion region;
    private Sound explosionSound;

    public ExplosionPool(TextureAtlas atlas) {
        this.region = atlas.findRegion("explosion");
        this.explosionSound = Gdx.audio.newSound(Gdx.files.internal("explosion.mp3"));
    }

    @Override
    protected Explosion newObject() {
        return new Explosion(region, 9, 9, 74, explosionSound);
    }

    public void dispose() {
        explosionSound.dispose();
        super.dispose();
    }
}
