package org.ldvgr.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import org.ldvgr.game.base.BaseScreen;
import org.ldvgr.game.math.Rect;
import org.ldvgr.game.sprite.Background;
import org.ldvgr.game.sprite.menu.ExitButton;
import org.ldvgr.game.sprite.menu.PlayButton;

public class MenuScreen extends BaseScreen {
    TextureAtlas atlas;
    private Texture backgr;
    private Background background;
    private PlayButton play;
    private ExitButton exit;

    @Override
    public void show() {
        super.show();
        backgr = new Texture("menuBg.png");
        background = new Background(new TextureRegion(backgr));
        atlas = new TextureAtlas("texture1.atlas");
        play = new PlayButton(atlas);
        exit = new ExitButton(atlas);
    }

    public void update(float delta) {

    }

    public void draw() {

        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        play.draw(batch);
        exit.draw(batch);
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
    }


    @Override
    public void touchUp(Vector2 touch, int pointer) {
        play.touchUp(touch, pointer);
        exit.touchUp(touch, pointer);
    }

    @Override
    public void touchDown(Vector2 touch, int pointer) {
        play.touchDown(touch, pointer);
        exit.touchDown(touch, pointer);
    }
}
