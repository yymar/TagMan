package model;

import java.awt.Dimension;
import java.awt.Point;

public class Wall extends GameObject {
	private Dimension dimension;
	private Point point;

	public Wall(Dimension dimension, Point point) {
		super(dimension, point);
		this.dimension = dimension;
		this.point = point;
	}
}
