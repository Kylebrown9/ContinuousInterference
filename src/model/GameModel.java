package model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GameModel {
	private final float INITIALX=0;
	private final float INITIALY=0;
	
	private final Player player;
	private final List<Level> levels;
	
	public GameModel(List<String> jsonText) {
		player = new Player(INITIALX,INITIALY,10);
		levels = new ArrayList<>();
		
		for(String text : jsonText) {
			addLevel(LevelFactory.makeLevel(text));
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