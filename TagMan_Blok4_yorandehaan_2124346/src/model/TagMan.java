package model;

import java.awt.Dimension;
import java.awt.Point;

public class TagMan extends GameObject {
	private Dimension dimension;
	private Point point;
	
	public TagMan(Dimension dimension, Point point) {
		super(dimension, point);
		this.dimension = dimension;
		this.point = point;
	}

}
