package model;

import waves.WaveEquation;

public class Source {
	private float x,y;
	private WaveEquation wE;
	private boolean active = true;
	private boolean destroyed = false;
	
	public Source(float x, float y, float h, float v) {
		this(x,y,new WaveEquation(h,v));
	}
	
	public Source(float x, float y, WaveEquation wE) {
		this.x = x;
		this.y = y;
		this.wE = wE;
	}
	
	public void destroy() {
		destroyed = true;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public boolean isActive() {
		if(destroyed) {
			return false;
		} else {
			return active;
		}
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
	
	public void setLocation(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
}
