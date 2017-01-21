package model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GameModel {
	private final double INITIALX=0;
	private final double INITIALY=0;
	
	private final Player player;
	private final List<Level> levels;
	
	public GameModel(List<File> jsonFiles) {
		player = new Player(INITIALX,INITIALY,10);
		levels = new ArrayList<>();
		
		for(File f : jsonFiles) {
			addLevel(LevelFactory.makeLevel(f));
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