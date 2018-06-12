package model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

public class Dash extends GameObject {
	private Dimension dimension;
	private Point point;
	private Color dashColour;

	public Dash(Dimension dimension, Point point) {
		super(dimension, point);
		this.dimension = dimension;
		this.point = point;
		
		this.dashColour = Color.RED;
	}

	public Color getDashColor() {
		return dashColour;
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
