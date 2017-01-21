package engine;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.List;

import interfaces.EngineInterface;
import interfaces.EventHandler;
import interfaces.TimeProvider;
import model.GameModel;
import model.Player;

public class GameEngine extends UpdateThread implements EngineInterface, EventHandler {
	private GameModel model;
	private TimeProvider tProv;
	private Player player;
	
	public GameEngine(Point start, List<String> fileContents, TimeProvider tProv) {
		model = new GameModel(start,fileContents);
		player = model.getPlayer();
		this.tProv = tProv;
	}

	public void update() {
		model.update(tProv.getTime());
	}

	public void notifyKeyEvent(KeyEvent ke) {
		//TODO: implement pickup items event trigger
	}

	public void notifyClick(float x, float y) {
		player.setTargetPoint(x,y);
	}
	
	public GameModel getModel() {
		return model;
	}

	public EventHandler getHandle() {
		return this;
	}
}