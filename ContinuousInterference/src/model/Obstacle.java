package model;

import java.awt.Rectangle;

import logic.LogicListener;

public class Obstacle implements LogicListener {
	private Rectangle rect;
	private boolean active;
	private boolean permittive;
	
	public Obstacle(Rectangle rect, boolean permittive) {
		this.rect = rect;
		this.permittive = permittive;
	}
	
	public void setActive(boolean a) {
		active = a;
	}
	
	public boolean getActive() {
		return active;
	}
	
	public boolean getPermittive() {
		return permittive;
	}
	
	public Rectangle getRect() {
		return rect;
	}

	@Override
	public void notify(boolean signal) {
		setActive(!signal);
	}
}