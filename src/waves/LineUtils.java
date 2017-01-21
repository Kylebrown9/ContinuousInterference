package waves;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Line2D;

public class LineUtils {

//	public static void main(String[] args) {
//		System.out.println(lineIntersectsRectangle(new Point(0,0),new Point(5,5),new Rectangle(1,0,10,10)));
//	}
	
	public static boolean lineIntersectsRectangle(Point a, Point b, Rectangle rect) {
		boolean output = false;
		
		float[] xCoords = {rect.x,rect.x+rect.width,rect.x+rect.width,rect.x,rect.x};
		float[] yCoords = {rect.y,rect.y,rect.y+rect.height,rect.y+rect.height,rect.y};
		
		for(int i=0; i<4; i++) {
			output |= Line2D.linesIntersect(a.getX(),a.getY(),b.getX(),b.getY(),
					xCoords[i],yCoords[i],xCoords[i+1],yCoords[i+1]);
		}
		
		return output;
	}
}