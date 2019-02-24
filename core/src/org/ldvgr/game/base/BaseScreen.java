package org.ldvgr.game.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;

import org.ldvgr.game.math.MatrixUtils;
import org.ldvgr.game.math.Rect;

public class BaseScreen implements Screen, InputProcessor {
    protected SpriteBatch batch;
    private Rect screenBounds; //прямоугольник экрана
    protected Rect worldBounds; // прямоугольник игрового пространства
    private Rect glBounds; // квадрат OpenGL;
    private Matrix4 worldToGl; // матрица перехода из игровых координат в OpenGL отрисовку
    private Matrix3 screenToWorlds; //из пикселей в координаты мира
    private Vector2 touch;
    @Override
    public void show() {
        System.out.println("show");
        Gdx.input.setInputProcessor(this);
        batch = new SpriteBatch();
        this.screenBounds = new Rect();
        this.worldBounds = new Rect();
        this.glBounds = new Rect(0, 0, 1f, 1f);
        this.worldToGl = new Matrix4();
        touch = new Vector2();
        screenToWorlds = new Matrix3();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        System.out.println("resize w=" + width + " h=" + height);
        screenBounds.setSize(width, height);
        screenBounds.setBottom(0);
        screenBounds.setLeft(0);
        float aspect = (float) width / height;
        worldBounds.setSize(1f * aspect, 1f);
        MatrixUtils.calcTransitionMatrix(worldToGl, worldBounds, glBounds);
        batch.setProjectionMatrix(worldToGl);
        MatrixUtils.calcTransitionMatrix(screenToWorlds, screenBounds, worldBounds);
        resize(worldBounds);

    }

    public void resize(Rect worldBounds) {
    }

    @Override
    public void pause() {
        System.out.println("pause");
    }

    @Override
    public void resume() {
        System.out.println("resume");
    }

    @Override
    public void hide() {
        System.out.println("hide");
        dispose();
    }

    @Override
    public void dispose() {
        System.out.println("dispose");
        batch.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        System.out.println("keyDown " + keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        System.out.println("keyUp " + keycode);
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        System.out.println("keyTyped " + character);
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println("touchDown " + screenX + "; " + screenY);
        touch.set(screenX, screenBounds.getHeight() - screenY).mul(screenToWorlds);
        touchDown(touch, pointer);
        return true;
    }

    public void touchDown(Vector2 touch, int pointer) {
        System.out.println("touchDown touch.x= " + touch.x + "; touch.y= " + touch.y);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        System.out.println("touchUp " + screenX + "; " + screenY);
        touch.set(screenX, screenBounds.getHeight() - screenY).mul(screenToWorlds);
        touchUp(touch, pointer);
        return false;
    }

    public void touchUp(Vector2 touch, int pointer) {
        System.out.println("touchUp touch.x= " + touch.x + "; touch.y= " + touch.y);
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        System.out.println("touchDragged" + screenX + "; " + screenY);
        touch.set(screenX, screenBounds.getHeight() - screenY).mul(screenToWorlds);
        touchDragged(touch, pointer);
        return false;
    }

    public void touchDragged(Vector2 touch, int pointer) {
        System.out.println("touchDragged touch.x= " + touch.x + "; touch.y= " + touch.y);
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
