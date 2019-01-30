package org.ldvgr.game.base;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public abstract class SpritesPool<T extends Sprite> {
    protected List<T> activeObjects = new ArrayList<T>();
    protected List<T> freeObjects = new ArrayList<T>();

    protected abstract T newObject();

    public T obtain() {
        T object;
        if (freeObjects.isEmpty()) {
            object = newObject();
        } else {
            object = freeObjects.remove(freeObjects.size() - 1);
        }
        activeObjects.add(object);
        return object;
    }

    public void updateActiveSprites(float delta) {
        for (T activeObject : activeObjects) {
            activeObject.update(delta);
        }
    }

    public void drawActiveSprites(SpriteBatch batch) {
        for (T activeObject : activeObjects) {
            activeObject.draw(batch);
        }
    }

    public void freeAllDestroyedActiveSprites() {
        for (int i = 0; i < activeObjects.size(); i++) {
            T activeObject = activeObjects.get(i);
            if (activeObject.isDestroid()) {
                free(activeObject);
                i--;
            }
        }
    }

    public void free(T object) {
        activeObjects.remove(object);
        freeObjects.add(object);
        object.flushDestroy();
    }

    public void dispose() {
        activeObjects.clear();
        freeObjects.clear();
    }
}
