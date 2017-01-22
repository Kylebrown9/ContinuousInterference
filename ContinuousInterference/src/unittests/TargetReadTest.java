package unittests;

import java.awt.Rectangle;

import model.Level;
import model.Obstacle;
import model.Source;
import model.Target;

public class TargetReadTest extends Test {

	public TargetReadTest() {
		super("TargetTest");
	}

	@Override
	public boolean getOutcome() {
		return test1();
	}
	
	public boolean test1() {
		Level level = new Level(80,90,0);
		level.addSource(new Source(0,0,1,1));
		Obstacle o = new Obstacle(new Rectangle(10,-5,10,10),false);
		Target t = new Target(level,"chan1",30,0,1,0.1f);
		
		level.addObstacle(o);
		level.addTarget(t);
		
		for(float f=0; f<100; f+=0.01f) {
			t.update(f);
		}
		
//		System.out.println("Test1(A): " + t.getChannel().getOutput());
		
		
		return true;
	}

}