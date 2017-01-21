package engine;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.List;

import interfaces.EngineInterface;
import interfaces.EventHandler;
import interfaces.TimeProvider;
import model.GameModel;

public class GameEngine extends UpdateThread implements EngineInterface, EventHandler {
	private GameModel model;
	
	public GameEngine(Point start, List<String> fileContents, TimeProvider tProv) {
		
	}

	public void update() {
		
	}

	public void notifyKeyEvent(KeyEvent ke) {
		
	}

	public void notifyMouseEvent(MouseEvent me) {
		
	}
	
	public GameModel getModel() {
		return model;
	}

	public EventHandler getHandle() {
		return this;
	}
}
