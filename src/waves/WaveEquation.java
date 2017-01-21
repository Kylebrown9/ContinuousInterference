package waves;

public class WaveEquation {
	private float h, v;
	
	public WaveEquation(float h, float v) {
		this.h = h;
		this.v = v;
	}
	
	public double evaluate(long t, float r) {
		return MathUtils.sin(h*(t-(r/v)));
	}
}
