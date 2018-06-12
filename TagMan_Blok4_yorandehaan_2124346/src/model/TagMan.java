package model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

public class TagMan extends GameObject {
	private Dimension dimension;
	private Point point;
	private Color firstCircle;
	private Color secondCircle;
	private Color thirdCircle;

	private int velocity;
	private int xPos;
	private int yPos;
	
	public TagMan(Dimension dimension, Point point) {
		super(dimension, point);
		this.dimension = dimension;
		this.point = point;	
		
		this.xPos = (int) point.getX();
		this.yPos = (int) point.getY();
		
		this.firstCircle = Color.RED;
		this.secondCircle = new Color(255, 141, 0);
		this.thirdCircle = new Color(255, 200, 0);
		
		this.velocity = 10;
	}

	
	
	public void moveForwards() {
		point.setLocation(xPos + velocity, yPos);
		xPos = xPos + velocity;
	}

	public void moveUpwards() {
		point.setLocation(xPos, yPos - velocity);
		yPos = yPos - velocity;
	}

	public void moveDownwards() {
		point.setLocation(xPos, yPos + velocity);
		yPos = yPos + velocity;
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

	public Color getFirstCircleColour() {
		return firstCircle;
	}

	public Color getSecondCircleColour() {
		return secondCircle;
	}

	public Color getThirdCircleColour() {
		return thirdCircle;
	}
	

}
