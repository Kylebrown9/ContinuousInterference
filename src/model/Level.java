package model;

import java.util.List;

public class Level {
	private Tile[][] floor;
	private List<Target> targets;
	private boolean done = false;
	
	public Level() {
		
	}
	
	public void setDone() {
		done = true;
	}
	
	public boolean isDone() {
		return done;
	}
	
	public Tile[][] getFloor() {
		return floor.clone();
	}
	
	class LevelDescriptor {
		
		private
	}
}
