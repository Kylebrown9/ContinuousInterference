package model;

import java.util.List;

import logic.ChannelSet;
import logic.LogicListener;

public class Level implements LogicListener {
	private LevelMap levelMap;
	private List<Source> sources;
	private List<Target> targets;

	ChannelSet channels;
	private boolean done = false;
	
	public Level() {
		channels = new ChannelSet();
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
	
	public void notify(boolean signal) {
		if(signal) {
			done = true;
		}
	}
	
	public boolean isDone() {
		return done;
	}
}