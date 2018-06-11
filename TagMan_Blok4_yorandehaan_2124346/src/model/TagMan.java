package model;

import java.awt.Dimension;
import java.awt.Point;

public class TagMan extends GameObject {
	private Dimension dimension;
	private Point point;
	private int velocity;
	private int x;
	private int y;
	
	public TagMan(Dimension dimension, Point point) {
		super(dimension, point);
		this.dimension = dimension;
		this.point = point;	
		
		this.x = (int) point.getX();
		this.y = (int) point.getY();
		
		this.velocity = 10;
	}

	public void moveForwards(int x) {
		point.setLocation(x + velocity, y);
	}

	public void moveUpwards(int y) {
		point.setLocation(x, y + velocity);
	}

	public void moveDownwards(int y) {
		point.setLocation(x, y - velocity);
	}

	public Dimension getDimension() {
		return dimension;
	}

	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

}
