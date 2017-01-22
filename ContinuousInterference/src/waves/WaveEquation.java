package waves;

public class WaveEquation {
	private float h, v;
	
	/**
	 * 
	 * @param h frequency
	 * @param v velocity of wave
	 */
	public WaveEquation(float h, float v) {
		this.h = h;
		this.v = v;
	}
	
	/**
	 * 
	 * @param t time
	 * @param r distance from source
	 * @return
	 */
	public float evaluate(float t, float r) {
		return MathUtils.sin(h*(t-(r/v)));
	}
}
