package model;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import logic.ChannelSet;
import logic.LogicListener;

public class Level implements LogicListener {
	private Dimension dimensions;
	private List<Obstacle> obstacles = new ArrayList<>();
	
<<<<<<< HEAD
	private List<Source> sources;
	private List<Target> targets;
=======
	private LevelMap levelMap;
	private List<Source> sources = new ArrayList<>();
	private List<Target> targets = new ArrayList<>();
>>>>>>> cdb63bf... Added: game

	private ChannelSet channels;
	private boolean done = false;
	private int offset;
	
	public Level(int width, int height, int offset) {
		channels = new ChannelSet();
		dimensions = new Dimension(width,height);
		this.offset = offset;
	}
	
	public void update(float time) {
		for(Target t: targets) {
			t.update(time);
		}
	}
	
<<<<<<< HEAD
=======
	public LevelMap getLevelMap() {
		//return levelMap;
		return new LevelMap(getDimensions(), getObstacles());
	}
	
>>>>>>> cdb63bf... Added: game
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
		
		for(Source s: sources) {
			s.destroy();
		}
	}
	
	public boolean isDone() {
		return done;
	}
	
	public boolean inLevel(float x) {
		return x>offset && x<offset+dimensions.getWidth();
	}
}