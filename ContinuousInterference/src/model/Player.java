package model;

public class Player {
	private double x,y;
	private double theta = 0;
	private long leftOver = 0;
	private final long movePeriod;
	private boolean moving = false;
	private Source heldItem = null;
	
	public Player(double initX, double initY, long movePeriod) {
		this.x = initX;
		this.y = initY;
		this.movePeriod = movePeriod;
	}
	
	public synchronized void update(long time) {
		long totalTime = time+leftOver;
		if(moving) {
			x += (totalTime/movePeriod)*Math.sin(theta);
			y += (totalTime/movePeriod)*Math.cos(theta);
			leftOver = totalTime%movePeriod;
		} else {
			leftOver = 0;
		}
	}
	
	public synchronized void setTheta(double theta) {
		this.theta = theta;
	}

	public synchronized void setMoving(boolean isMoving) {
		moving = true;
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