package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class GameModel {
	private final Player player;
	private final List<Level> levels;
	private int currentLevel = 0;
	
	public GameModel(Point playerStart, List<String> jsonText) {
		player = new Player(this,playerStart.x,playerStart.y,10);
		levels = new ArrayList<>();
		
		int offset=0;
		for(String text : jsonText) {
			addLevel(LevelFactory.makeLevel(text,offset));
			offset += getLevel(getNumLevels()-1).getDimensions().width;
		}
	}
	
	public void update(float time) {
		player.update(time);
		
		levels.get(currentLevel-1).update(time);
		levels.get(currentLevel).update(time);
		
		if(levels.get(currentLevel).isDone()) {
			currentLevel++;
		}
	}
	
	public void addLevel(Level l) {
		levels.add(l);
	}
	
	public Level getCurrentLevel() {
		return levels.get(currentLevel);
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