package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class GameModel {
	private final Player player;
	private final List<Level> levels;
	
	public GameModel(Point playerStart, List<String> jsonText) {
		player = new Player(playerStart.x,playerStart.y,10);
		levels = new ArrayList<>();
		
		for(String text : jsonText) {
			addLevel(LevelFactory.makeLevel(text));
		}
	}
	
	public void update(float time) {
		//TODO: update all model components
	}
	
	public void addLevel(Level l) {
		levels.add(l);
	}
	
	public Level getLevel(int i) {
		return levels.get(i);
	}
	
	public int getNumLevels() {
		return levels.size();
	}
	
	public Player getPlayer() {
		return player;
	}
}