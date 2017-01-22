package model;

public class Player {
	private static final float RADIUS = 20;
	private static final float TOLERANCE = 10;
	private static final float PICKUP_RANGE = 100;
	
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
			x += time*speed*Math.sin(theta);
			y += time*speed*Math.cos(theta);
		} 
		
		//TODO: collision detection
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
	
	public synchronized boolean isHoldingItem() {
		return heldItem == null;
	}
}