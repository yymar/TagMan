package model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

public class TagMan extends GameObject {
	private Dimension dimension;
	private Point point;
	private Color firstCircle;
	private Color secondCircle;
	private Color thirdCircle;

	private int velocity;
	private int diagonalVelocity;
	private int xPos;
	private int yPos;
	
	public TagMan(Dimension dimension, Point point) {
		super(dimension, point);
		this.dimension = dimension;
		this.point = point;	
		
		this.xPos = getX();
		this.yPos = getY();
		
		this.firstCircle = Color.RED;
		this.secondCircle = new Color(255, 141, 0);
		this.thirdCircle = new Color(255, 200, 0);
		
		this.velocity = 10;
		this.diagonalVelocity = (velocity / 3) * 2;
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
	
	public void moveHorizontalyDownwards() {
		point.setLocation(xPos + diagonalVelocity, yPos + diagonalVelocity);
		xPos = xPos + diagonalVelocity;
		yPos = yPos + diagonalVelocity;
	}
	
	public void moveHorizontalyUpwards() {
		point.setLocation(xPos + diagonalVelocity, yPos - diagonalVelocity);
		xPos = xPos + diagonalVelocity;
		yPos = yPos - diagonalVelocity;
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

	public void setFirstCircle(Color firstCircle) {
		this.firstCircle = firstCircle;
	}

	public void setSecondCircle(Color secondCircle) {
		this.secondCircle = secondCircle;
	}

	public void setThirdCircle(Color thirdCircle) {
		this.thirdCircle = thirdCircle;
	}

	public int getVelocity() {
		return velocity;
	}

	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}
	
	public Rectangle getBounds() { 
		return new Rectangle(getX(), getY(), getWidth(), getHeight());
	}
	
	

}
