package engine;

public abstract class UpdateThread extends Thread {
	private boolean running = true;

	public void run() {
		// XXX: Hacky hack, just removed to get rid of compiler errors.
//		running = true;
//		while (running) {
//			update();
//		}
	}

	public abstract void update(float delta);

	public void end() {
		running = false;
	}
}
