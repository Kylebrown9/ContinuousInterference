package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MainGameScreen;
import com.mygdx.game.MyGame;

public class DesktopLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = (int) MainGameScreen.VP_WIDTH;
		config.height = (int) MainGameScreen.VP_HEIGHT;
		new LwjglApplication(new MyGame(), config);
	}
}
