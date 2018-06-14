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
	
	/*
	 * Every class that extends GameObject has it's own dimension and point, because
	 * this class is their parent. This method creates a rectangle for the player
	 * with it's dimension and point. The x and y in the parameters is the amount of
	 * x and y that the player moves with. The translation draws kind of an
	 * invisible playerHitbox. Then it creates a rectangle for the object given
	 * through the parameters of this method. Based on the intersect it returns true
	 * or false.
	 */

	public boolean willCollide(GameObject object, int x, int y) {
		Rectangle playerHitbox = new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		playerHitbox.translate(x, y);
		Rectangle objectHitbox = new Rectangle(object.getX(), object.getY(), object.getWidth(), object.getHeight());
		return playerHitbox.intersects(objectHitbox);
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
