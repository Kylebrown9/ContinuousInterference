package model;

import java.awt.Point;
import java.util.function.Function;

import waves.WaveEquation;

public class Source {
	private Point location;
	private WaveEquation wE;
	
	public Source(Point loc, WaveEquation wE) {
		this.location = loc;
		this.wE = wE;
	}
	
	public double getActivation(long time) {
		return wE.evaluate(time,0);
	}
	
	public WaveEquation getFunction() {
		return wE;
	}
	
	public Point getLocation() {
		return location;
	}
}
