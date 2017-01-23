package model;

import java.awt.Rectangle;

public class Player {
	public static final float RADIUS = 5;
	private static final float TOLERANCE = 0.5f;
	private static final float PICKUP_RANGE = 10;

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
		tX = initX;
		tY = initY;
		this.speed = speed;
	}

	float lastTime = 0;

	public synchronized void update(float time) {
		float deltaTime = time - lastTime;
		this.theta = (float) Math.atan2(y - tY, x - tX);
		float distance = (float) Math.sqrt((x - tX) * (x - tX) + (y - tY) * (y - tY));

		if (distance > TOLERANCE) {
			float moveDistance = deltaTime * speed;
			moveDistance = Math.min(distance, moveDistance);
			float xMove = (float) (moveDistance * Math.sin(theta - Math.PI / 2));
			float yMove = (float) (moveDistance * Math.cos(theta + Math.PI / 2));
			tryMove(xMove, yMove);
		}

		if (heldItem != null) {
			heldItem.setLocation(x, y);
		}

		lastTime = time;
	}

	/**
	 * @param dy
	 *            amount to move player, delta x
	 */
	private void tryMove(float dx, float dy) {
		Level curLevel = gM.getCurrentLevel();

		if (curLevel == null) {
			this.x += dx;
			this.y += dy;
			return;
		}

		for (Obstacle obs : curLevel.getObstacles()) {
			if (obs.getActive()) {
				Rectangle obstacleRect = new Rectangle(obs.getRect());
				obstacleRect.grow((int) RADIUS * 2 / 3, (int) RADIUS * 2 / 3);
				if (obstacleRect.contains((int) x + dx, (int) y + dy)) {
					return;
				}
			}
		}

		this.x += dx;
		this.y += dy;

	}

	public synchronized void setTargetPoint(float x, float y) {
		this.tX = x;
		this.tY = y;
	}

	public synchronized float getX() {
		return x;
	}

	public synchronized float getY() {
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
//		dropItem();
	}

	public synchronized boolean isHoldingItem() {
		return heldItem == null;
	}

	private boolean pickupItem(Source source) {
		if (heldItem != null) {
			drop();
		}

		heldItem = source;
		// heldItem.setActive(false);
		return true;
	}

	private void dropItem() {
		if (heldItem == null) {
			return;
		}

		// heldItem.setActive(true);
		heldItem.setLocation(x, y);

		heldItem = null;
	}
}