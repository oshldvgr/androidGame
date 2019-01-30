package org.ldvgr.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import org.ldvgr.game.ShooterGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		float aspect = 3f / 4f;
		config.width = 400;
		config.height = (int) (config.width / aspect);
        //config.resizable = false;
		new LwjglApplication(ShooterGame.getInstance(), config);
	}
}
