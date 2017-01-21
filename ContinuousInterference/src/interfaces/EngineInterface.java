package interfaces;

import model.GameModel;

public interface EngineInterface {
	public GameModel getModel();
	public EventHandler getHandle();
	public void start();
	public void stop();
}