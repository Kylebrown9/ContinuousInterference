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
	
	public int getCurrentLevelIndex() {
		for(int i=0; i<levels.size(); i++) {
			if(levels.get(i).inLevel(player.getX())) {
				return i;
			}
		}
			
		return 0;
	}
	
	public List<Level> getActiveLevels() {
		List<Level> activeLevels = new ArrayList<>();
		currentLevel = getCurrentLevelIndex();
		
		if(levels.get(currentLevel-1) != null)
			activeLevels.add(levels.get(currentLevel-1));
		
		if(levels.get(currentLevel) != null)
			activeLevels.add(levels.get(currentLevel));
		
		if(levels.get(currentLevel+1) != null)
			activeLevels.add(levels.get(currentLevel+1));
		
		return activeLevels;
	}
	
	public Level getCurrentLevel() {
		for(int i=0; i<levels.size(); i++) {
			if(levels.get(i).inLevel(player.getX())) {
				return levels.get(i);
			}
		}
			
		return null;
	}
	
	public void update(float time) {
		player.update(time);
		
		for(Level l : getActiveLevels()) {
			l.update(time);
		}
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