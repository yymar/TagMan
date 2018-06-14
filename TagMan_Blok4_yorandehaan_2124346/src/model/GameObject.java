package model;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

public abstract class GameObject {
	private Dimension dimension;
	private Point point;
	
	public GameObject(Dimension dimension, Point point) {
		this.dimension = dimension;
		this.point = point;
	}

	public boolean willCollide(GameObject object, int x, int y) {
		Rectangle playerRectangle = new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		playerRectangle.translate(x, y);
		Rectangle objectRectangle = new Rectangle(object.getX(), object.getY(), object.getWidth(), object.getHeight());
		return playerRectangle.intersects(objectRectangle);
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
