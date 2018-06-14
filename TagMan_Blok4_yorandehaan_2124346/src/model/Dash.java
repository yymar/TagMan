package model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;

public class Dash extends GameObject {
	private Dimension dimension;
	private Point point;
	private Color dashColour;
	private Random random;
	private int xPos;
	private int yPos;
	private int speed;
	private boolean isMoving;

	public Dash(Dimension dimension, Point point) {
		super(dimension, point);
		this.dimension = dimension;
		this.point = point;
		this.random = new Random();
		
		// speed always + 1 so it can't be 0.
		this.speed = random.nextInt(4) + 1;

		this.xPos = getX();
		this.yPos = getY();
		this.dashColour = Color.RED;
	}

	// determines if the dash can move, based on a random integer between 0 and 30;
	public void setIsMoving() {
		int chanceOfDrop = random.nextInt(30);
		if (chanceOfDrop == 1) {
			this.isMoving = true;
		}
	}

	public void moveDownwards() {
		point.setLocation(xPos, yPos + speed);
		yPos = yPos + speed;
	}
	
	public boolean getIsMoving() {
		return isMoving;
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
	
	// Returns a rectangle of the dash for collision detection purposes
	public Rectangle getBounds() {
		return new Rectangle(getX(), getY(), getWidth(), getHeight());
	}

	public int getSpeed() {
		return speed;
	}

}
