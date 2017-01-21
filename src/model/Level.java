package model;

import java.util.List;

public class Level {
	private Tile[][] floor;
	private List<Source> sources;
	private List<Target> targets;
	private boolean done = false;
	
	public Level() {
		
	}
	
	public List<Source> getSources() {
		return sources;
	}
	
	public List<Target> getTargets() {
		return targets;
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
}
