package interfaces;

import java.awt.event.KeyEvent;

public interface EventHandler {
	public void notifyKeyEvent(KeyEvent ke);
	public void notifyClick(float x, float y);
}
