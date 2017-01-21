package model;

public class Player {
	private static final float TOLERANCE = 10;
	
	private float x,y;
	private float tX, tY;
	private float theta = 0;
	private final float speed;
	private Source heldItem = null;
	
	public Player(float initX, float initY, float speed) {
		this.x = initX;
		this.y = initY;
		this.speed = speed;
	}
	
	public synchronized void update(float time) {
		float distance = (float) Math.sqrt((x-tX)*(x-tX)+(y-tY)*(y-tY));
		
		if(distance < TOLERANCE) {
			x += time*speed*Math.sin(theta);
			y += time*speed*Math.cos(theta);
		} 
	}

	public synchronized void setTargetPoint(float x, float y) {
		this.tX = x;
		this.tY = y;
		this.theta = (float) Math.atan2(this.x-x, this.y-y);
	}
	
	public synchronized double getX() {
		return x;
	}
	
	public synchronized double getY() {
		return y;
	}
	
	//Item system
	public synchronized boolean pickupItem(Source source) {
		if(heldItem == null) {
			return false;
		} else {
			heldItem = source;
			return true;
		}
	}
	
	public synchronized boolean isHoldingItem() {
		return heldItem == null;
	}
	
	public synchronized Source getHeldItem() {
		return heldItem;
	}
}