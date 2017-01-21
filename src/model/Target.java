package model;

import java.awt.Point;

import logic.Channel;

public class Target {
	private Point loc;
	private double targetVal, tolerance;
	private Channel channel;
	
	public Target(Level level, String name, Point loc, double targetVal, double tolerance) {
		this.loc = loc;
		this.targetVal = targetVal;
		this.tolerance = tolerance;
		
		channel = level.getChannels().makeChannel(name, Channel.Type.VALUE);
		channel.update(false);
	}
	
	public Channel getChannel() {
		return channel;
	}
	
	public void update() {
		
	}
	
	private void setInput(double input) {
		channel.update(Math.abs(targetVal-input) < tolerance);
	}
	
	public Point getLocation() {
		return loc;
	}
}