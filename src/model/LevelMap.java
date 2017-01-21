package model;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

public class LevelMap {
	private Dimension dimensions;
	
	private List<Obstacle> obstacles = new ArrayList<>();
	
	public LevelMap(Dimension d, List<Obstacle> obstacles) {
		this(d);
		obstacles.addAll(obstacles);
	}
	
	public LevelMap(Dimension d) {
		this.dimensions = d;
	}
	
	public void addObstacle(Obstacle obstacle) {
		obstacles.add(obstacle);
	}
	
	public Dimension getDimensions() {
		return dimensions;
	}
	
	public List<Obstacle> getObstacles() {
		return obstacles;
	}
}
