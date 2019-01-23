package org.ldvgr.game.base.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import org.ldvgr.game.base.BaseScreen;

public class MenuScreen extends BaseScreen {
    private SpriteBatch batch;
    private Texture img;
    private Texture background;
    private Vector2 position;
    private Vector2 velocity;
    private final float velocityLen = 1.5f;
    private final double velocityLenSq = Math.pow(velocityLen, 2);
    private Vector2 newPosition;
    private Vector2 direction;


    @Override
    public void show() {
        super.show();
        batch = new SpriteBatch();
        background = new Texture("background.png");
        img = new Texture("badlogic.jpg");
        position = new Vector2(0, 0);
        newPosition = new Vector2();
        velocity = new Vector2();
        direction = new Vector2();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, 0, 0);
        batch.draw(img, position.x, position.y);
        batch.end();
        double distance = Math.pow((newPosition.x - position.x), 2) + Math.pow((newPosition.y - position.y), 2);
        if (distance >= velocityLenSq) {
            position.add(velocity);
        } else position.set(newPosition);
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        newPosition.x = screenX;
        newPosition.y = (Gdx.graphics.getHeight() - screenY);
        direction = newPosition.cpy().sub(position).nor();
        velocity = direction.cpy().scl(velocityLen);
        System.out.println("direction " + direction.x + ", " + direction.y);
        System.out.println("velocity " + velocity.x + ", " + velocity.y);
        return super.touchDown(screenX, screenY, pointer, button);
    }
}
