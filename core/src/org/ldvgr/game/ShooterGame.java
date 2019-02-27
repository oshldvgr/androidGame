package org.ldvgr.game;

import com.badlogic.gdx.Game;

import org.ldvgr.game.screens.MenuScreen;

public class ShooterGame extends Game {

    private static final ShooterGame INSTANCE = new ShooterGame();

    public static ShooterGame getInstance() {
        return INSTANCE;
    }

    @Override
    public void create() {
        setScreen(new MenuScreen());
    }
}
