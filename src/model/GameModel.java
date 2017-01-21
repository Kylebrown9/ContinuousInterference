package model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class GameModel {
	private final double INITIALX=0;
	private final double INITIALY=0;
	
	private final Player player;
	private final List<Level> levels;
	
	public GameModel(BufferedImage[] images) {
		player = new Player(INITIALX,INITIALY);
		levels = new ArrayList<>();
		
		for(BufferedImage image : images) {
			levels.add(new Level(image));
		}
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