package org.ldvgr.game.base.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import org.ldvgr.game.base.BaseScreen;
import org.ldvgr.game.math.Rect;
import org.ldvgr.game.sprite.Background;
import org.ldvgr.game.sprite.Star;

public class GameScreen extends BaseScreen {
    TextureAtlas atlas;
    private Texture backgr;
    private Background background;
    private Star stars[];

    @Override
    public void show() {
        super.show();
        backgr = new Texture("background.png");
        background = new Background(new TextureRegion(backgr));
        atlas = new TextureAtlas("texture1.atlas");
        stars = new Star[256];
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new Star(atlas);
        }
    }

    public void update(float delta) {
        for (Star star : stars) {
            star.update(delta);
        }
    }

    public void draw() {

        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        for (Star star : stars) {
            star.draw(batch);
        }
        batch.end();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();
    }

    @Override
    public void dispose() {
        backgr.dispose();
        atlas.dispose();
        super.dispose();
    }

    @Override
    public void resize(Rect worldBounds) {
        background.resize(worldBounds);
        for (Star star : stars) {
            star.resize(worldBounds);
        }
    }
}
