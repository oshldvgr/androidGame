package org.ldvgr.game;

import com.badlogic.gdx.Game;

import org.ldvgr.game.base.screens.MenuScreen;

public class ShooterGame extends Game {
    @Override
    public void create() {
        setScreen(new MenuScreen());

    }
}
