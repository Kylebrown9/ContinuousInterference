package model;

import java.awt.Point;

import waves.WaveEquation;

public class Source {
	private Point location;
	private WaveEquation wE;
	private boolean active = true;
	
	public Source(Point loc, WaveEquation wE) {
		this.location = loc;
		this.wE = wE;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public double getActivation(long time) {
		if(active) {
			return wE.evaluate(time,0);	
		} else {
			return 0;
		}
	}
	
	public WaveEquation getWaveEquation() {
		return wE;
	}
	
	public void setLocation(Point location) {
		this.location = location;
	}
	
	public Point getLocation() {
		return location;
	}
}
