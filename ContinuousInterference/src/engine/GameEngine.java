package engine;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.List;

import interfaces.CompletionListener;
import interfaces.EngineInterface;
import interfaces.EventHandler;
import interfaces.TimeProvider;
import model.GameModel;
import model.Player;

public class GameEngine extends UpdateThread implements EngineInterface, EventHandler {
	private GameModel model;
	private TimeProvider tProv;
	private Player player;
	
	public GameEngine(Point start, List<String> fileContents, TimeProvider tProv, CompletionListener cL) {
		model = new GameModel(start,fileContents,cL);
		player = model.getPlayer();
		this.tProv = tProv;
	}

	/**
	 * 
	 * @param deltaTime
	 *            seconds since last update
	 */
	public void update(float deltaTime) {
		model.update(tProv.getTime(), deltaTime);
	}

	public void notifyKeyEvent(int keycode) {
		// LibGDX key code for space
		if (keycode == 62) {
			if (player.isHoldingItem()) {
				player.pickup();
			} else {
				player.drop();
			}
		}
	}

	public void notifyClick(float x, float y) {
		player.setTargetPoint(x, y);
	}

	public GameModel getModel() {
		return model;
	}

	public EventHandler getHandle() {
		return this;
	}
}