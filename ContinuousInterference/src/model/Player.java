package model;

public class Player {
	public static final float RADIUS = 20;
	public static final float TOLERANCE = 10;
	public static final float PICKUP_RANGE = 100;
	
	private GameModel gM;
	private float x,y;
	private float tX, tY;
	private float theta = 0;
	private final float speed;
	private Source heldItem = null;
	
	public Player(GameModel gM, float initX, float initY, float speed) {
		this.gM = gM;
		this.x = initX;
		this.y = initY;
		this.speed = speed;
	}
	
	public synchronized void update(float time) {
		float distance = (float) Math.sqrt((x-tX)*(x-tX)+(y-tY)*(y-tY));
		
		if(distance < TOLERANCE) {
			tryMove((float)(time*speed*Math.sin(theta)),(float)(time*speed*Math.cos(theta)));
		} 
	}
	
	private void tryMove(float x, float y) {
		Level curLevel = gM.getCurrentLevel();
		
		for(Obstacle obs: curLevel.getObstacles()) {
			if(obs.getRect().contains((int)x, (int)y)) {
				return;
			}
		}
		
		this.x = x;
		this.y = y;
	}

	public synchronized void setTargetPoint(float x, float y) {
		this.tX = x;
		this.tY = y;
		this.theta = (float) Math.atan2(this.x-x, this.y-y);
	}
	
	public synchronized float getX() {
		return x;
	}
	
	public synchronized float getY() {
		return y;
	}
	
	public synchronized void pickup() {
		float dX, dY;
		for(Source s: gM.getCurrentLevel().getSources()) {
			dX = s.getX()-x;
			dY = s.getY()-y;
			if(Math.sqrt(dX*dX + dY*dY) < PICKUP_RANGE) {
				pickupItem(s);
				return;
			}
		}
	}
	
	public synchronized void drop() {
		dropItem();
	}
	
	public synchronized boolean isHoldingItem() {
		return heldItem == null;
	}
	
	private boolean pickupItem(Source source) {
		if(heldItem != null) {
			drop();
		}
		
		heldItem = source;
		heldItem.setActive(false);
		return true;
	}
	
	private void dropItem() {
		if(heldItem == null) {
			return;
		}
		
		heldItem.setActive(true);
		heldItem.setLocation(x, y);
		
		heldItem = null;
	}
}