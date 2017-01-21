package interfaces;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface EventHandler {
	public void notifyKeyEvent(KeyEvent ke);
	public void notifyClick(double x, double y);
}
