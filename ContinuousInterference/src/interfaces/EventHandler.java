package interfaces;

public interface EventHandler {
	public void notifyKeyEvent(int ke);

	public void notifyClick(float x, float y);

	public void notifyMousePos(float x, float y);
}
