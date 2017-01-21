package model;

import java.awt.Point;

public class Source {
	private Point location;
	private Function f;
	
	public Source(Point loc, Function f) {
		this.location = loc;
		this.f = f;
	}
	
	public double getActivation(long time) {
		return f.evaluate(time);
	}
	
	public Function getFunction() {
		return f;
	}
	
	public Point getLocation() {
		return location;
	}
}
