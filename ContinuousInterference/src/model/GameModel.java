package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import interfaces.CompletionListener;

public class GameModel {
	private final Player player;
	private final List<Level> levels;
<<<<<<< HEAD
	private final CompletionListener cL;
	private final int gameWidth;
	
	public GameModel(Point playerStart, List<String> jsonText, CompletionListener cL) {
		player = new Player(this,playerStart.x,playerStart.y,10);
		levels = new ArrayList<>();
		this.cL = cL;
		
		int offset=0;
		for(String text : jsonText) {
			addLevel(LevelFactory.makeLevel(text,offset));
			offset += getLevel(getNumLevels()-1).getDimensions().width;
		}
		gameWidth = offset;
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
		int currentLevel = getCurrentLevelIndex();
		
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
=======
	private int currentLevel = 0;

	public GameModel(Point playerStart, List<String> jsonText) {
		player = new Player(this, playerStart.x, playerStart.y, 50);
		levels = new ArrayList<>();

		for (String text : jsonText) {
			addLevel(LevelFactory.makeLevel(text));
>>>>>>> cdb63bf... Added: game
		}
			
		return null;
	}
<<<<<<< HEAD
	
	public void update(float time) {
		player.update(time);
		
		for(Level l : getActiveLevels()) {
			l.update(time);
		}
		
		if(player.getX() > gameWidth) {
			cL.notifyCompleted();
=======

	public void update(float time, float deltaTime) {
		player.update(deltaTime);

		// XXX: Hacky hack
		if (currentLevel > 0) {
			levels.get(currentLevel - 1).update(time);
		}
		levels.get(currentLevel).update(time);

		if (levels.get(currentLevel).isDone()) {
			currentLevel++;
>>>>>>> cdb63bf... Added: game
		}
	}

	public void addLevel(Level l) {
		levels.add(l);
	}
<<<<<<< HEAD
	
=======

	public Level getCurrentLevel() {
		return levels.get(currentLevel);
	}

>>>>>>> cdb63bf... Added: game
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