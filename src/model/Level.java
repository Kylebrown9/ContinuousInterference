package model;

import java.util.List;

import logic.Channel;

public class Level {
	private LevelMap levelMap;
	private List<Source> sources;
	private List<Target> targets;
	private List<Channel> channels;
	private boolean done = false;
	
	public Level() {
		
	}
	
	public LevelMap getLevelMap() {
		return levelMap;
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
}
