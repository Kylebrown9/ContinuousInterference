package model;

import java.awt.Rectangle;

import logic.LogicListener;

public class Obstacle implements LogicListener {
	private Rectangle rect;
	private int id;
	private boolean active;
	private boolean permittive;
	
	public Obstacle(Rectangle rect, boolean permittive) {
		this(rect,permittive,0);
	}
	
	public Obstacle(Rectangle rect, boolean permittive, int id) {
		this.rect = rect;
		this.permittive = permittive;
		this.id = id;
	}
	
	public int getId() {
		return id;
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
		setActive(signal);
	}
}