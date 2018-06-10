package model;

import java.awt.Dimension;
import java.awt.Point;

public abstract class GameObject {
	private Dimension dimension;
	private Point point;
	
	// TODO: INTERSECTS AND TRANSLATE FOR COLLISION DETECTION
	public GameObject(Dimension dimension, Point point) {
		this.dimension = dimension;
		this.point = point;
	}
}
