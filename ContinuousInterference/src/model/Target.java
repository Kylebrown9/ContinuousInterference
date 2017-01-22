package model;

import logic.Channel;
import waves.LineUtils;

public class Target {
	private float x,y;
	private double targetVal, tolerance;
	private Level level;
	private Channel channel;
	
	public Target(Level level, String name, float x, float y, float targetVal, float tolerance) {
		this(level,name,x,y,targetVal,tolerance,false);
	}
	
	public Target(Level level, String name, float x, float y, float targetVal, float tolerance, boolean isPermanent) {
		this.x = x;
		this.y = y;
		this.targetVal = targetVal;
		this.tolerance = tolerance;
		this.level = level;
		
		channel = level.getChannels().makeChannel(name,
				isPermanent? Channel.Type.PERMANENT : Channel.Type.VALUE);
		channel.update(false);
	}
	
	public Channel getChannel() {
		return channel;
	}
	
	public void update(float t) {
		float input = 0;
		boolean sourceUnobstructed;
		
		for(Source s : level.getSources()) {
			sourceUnobstructed = true;
			
			if(!s.isActive()) {
				continue;
			}
			
			for(Obstacle o : level.getLevelMap().getObstacles()) {
				if(!o.getActive() || o.getPermittive()) {
					continue;
				}
				
				if(LineUtils.lineIntersectsRectangle(s.getX(),s.getY(), x, y, o.getRect())) {
					sourceUnobstructed = false;
				}
			}
			
			if(sourceUnobstructed) {
				float dX = s.getX()-x;
				float dY = s.getY()-y;
				float r = (float) Math.sqrt(dX*dX + dY*dY);
				
				input += (float) s.getWaveEquation().evaluate(t, r);
			}
		}
		
		setInput(input);
	}
	
	private void setInput(double input) {
		channel.update(Math.abs(targetVal-input) < tolerance);
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
}