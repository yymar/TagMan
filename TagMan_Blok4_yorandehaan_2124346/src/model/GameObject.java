package model;

import java.awt.Dimension;
import java.awt.Point;

public abstract class GameObject {
	private Dimension dimension;
	private Point point;
	
	public GameObject(Dimension dimension, Point point) {
		this.dimension = dimension;
		this.point = point;
	}

	public int getX() {
		return (int) point.getX();
	}

	public int getY() {
		return (int) point.getY();
	}

	public int getWidth() {
		return (int) dimension.getWidth();
	}

	public int getHeight() {
		return (int) dimension.getHeight();
	}
}
