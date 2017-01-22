package engine;

public abstract class UpdateThread extends Thread {
	private boolean running = true;

	public void run() {
		running = true;
		while (running) {
			update();
		}
	}

	public abstract void update();

	public void end() {
		running = false;
	}
}
