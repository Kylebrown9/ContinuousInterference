package model;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import logic.ChannelSet;
import logic.LogicListener;

public class Level implements LogicListener {
	private Dimension dimensions;
	private List<Obstacle> obstacles = new ArrayList<>();
	
	private LevelMap levelMap;
	private List<Source> sources;
	private List<Target> targets;

	ChannelSet channels;
	private boolean done = false;
	
	public Level(int width, int height) {
		channels = new ChannelSet();
		dimensions = new Dimension(width,height);
	}
	
	public void update(float time) {
		
	}
	
	public LevelMap getLevelMap() {
		return levelMap;
	}
	
	public void addSource(Source s) {
		sources.add(s);
	}
	
	public List<Source> getSources() {
		return sources;
	}
	
	public void addTarget(Target t) {
		targets.add(t);
	}
	
	public List<Target> getTargets() {
		return targets;
	}
	
	public ChannelSet getChannels() {
		return channels;
	}
	
	public void addObstacle(Obstacle obstacle) {
		obstacles.add(obstacle);
	}
	
	public Dimension getDimensions() {
		return dimensions;
	}
	
	public List<Obstacle> getObstacles() {
		return obstacles;
	}
	
	public void notify(boolean signal) {
		if(signal) {
			done = true;
		}
	}
	
	public boolean isDone() {
		return done;
	}
}