package org.ldvgr.game.sprite.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class ExitButton extends ScaledTouchUpButton {

    public ExitButton(TextureAtlas atlas) {
        super(atlas.findRegion("quit_normal"));
        setHightProportion(0.1f);
        pos.set(0f, -0.1f);
    }

    @Override
    public void action() {
        System.out.println("Exit is pressed");
        Gdx.app.exit();
    }
}


