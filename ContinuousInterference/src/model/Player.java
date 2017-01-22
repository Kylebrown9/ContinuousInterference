package model;

public class Player {
<<<<<<< HEAD
	public static final float RADIUS = 20;
	public static final float TOLERANCE = 10;
	public static final float PICKUP_RANGE = 100;
	
=======
	private static final float TOLERANCE = 0.5f;
	private static final float PICKUP_RANGE = 10;

>>>>>>> cdb63bf... Added: game
	private GameModel gM;
	private float x, y;
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
<<<<<<< HEAD
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
=======
		float distance = (float) Math.sqrt((x - tX) * (x - tX) + (y - tY) * (y - tY));

		if (distance > TOLERANCE) {
			x += time * speed * Math.sin(theta - Math.PI/2);
			y += time * speed * Math.cos(theta + Math.PI/2);
		}
		
		if (heldItem != null) {
			heldItem.setLocation(x, y);
		}

		// TODO: collision detection
>>>>>>> cdb63bf... Added: game
	}

	public synchronized void setTargetPoint(float x, float y) {
		this.tX = x;
		this.tY = y;
		this.theta = (float) Math.atan2(this.y - y, this.x - x);
	}
<<<<<<< HEAD
	
	public synchronized float getX() {
		return x;
	}
	
	public synchronized float getY() {
=======

	public synchronized double getX() {
		return x;
	}

	public synchronized double getY() {
>>>>>>> cdb63bf... Added: game
		return y;
	}

	public synchronized void pickup() {
		float dX, dY;
		for (Source s : gM.getCurrentLevel().getSources()) {
			dX = s.getX() - x;
			dY = s.getY() - y;
			if (Math.sqrt(dX * dX + dY * dY) < PICKUP_RANGE) {
				pickupItem(s);
				return;
			}
		}
	}

	public synchronized void drop() {
		dropItem();
	}
<<<<<<< HEAD
	
	public synchronized boolean isHoldingItem() {
		return heldItem == null;
	}
	
=======

>>>>>>> cdb63bf... Added: game
	private boolean pickupItem(Source source) {
		if (heldItem != null) {
			drop();
		}

		heldItem = source;
		//heldItem.setActive(false);
		return true;
	}

	private void dropItem() {
		if (heldItem == null) {
			return;
		}

		//heldItem.setActive(true);
		heldItem.setLocation(x, y);

		heldItem = null;
	}
<<<<<<< HEAD
=======

	public synchronized boolean isHoldingItem() {
		return heldItem == null;
	}
>>>>>>> cdb63bf... Added: game
}