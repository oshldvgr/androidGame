package org.ldvgr.game.sprite.menu;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import org.ldvgr.game.ShooterGame;
import org.ldvgr.game.screens.GameScreen;

public class PlayButton extends ScaledTouchUpButton {

    public PlayButton(TextureAtlas atlas) {
        super(atlas.findRegion("play_normal"));
        setHightProportion(0.1f);
        pos.set(0.0f, 0.1f);
    }

    @Override
    public void action() {
        System.out.println("Play is pressed");
        ShooterGame.getInstance().setScreen(new GameScreen());
    }
}
