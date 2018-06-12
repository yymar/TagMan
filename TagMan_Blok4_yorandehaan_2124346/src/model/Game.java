package model;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Observable;

public class Game extends Observable implements Runnable{
	private ArrayList<Dash> dashes;
	private ArrayList<Wall> walls;
	private TagMan tagMan;
	private static final int AMOUNT_OF_DASHES = 10;
	private static final int MAX_TIME = 30;
	private int level;
	private int score;
	private int timerAmount;
	private boolean startPressed;
	private boolean finished;
	private boolean crashed;

	public Game() {
		tagMan = new TagMan(new Dimension(50, 50), new Point(5, 375));
		dashes = new ArrayList<>();
		walls = new ArrayList<>();
		level = 1;
		startPressed = false;
		timerAmount = 30;
		
		createDashes();
		createWalls();
	}
	
	public void createDashes() {
		int startX = 150;
		int spaceBetweenDashes = 100;
		int dashHeight = 50;
		int dashWidth = 10;
		
		for (int i = 0; i < AMOUNT_OF_DASHES; i++) {
			Dash dash = new Dash(new Dimension(dashWidth, dashHeight), new Point(startX, 0));
			dashes.add(dash);
			startX += spaceBetweenDashes;
		}
	}
	
	public void createWalls() {
		int defaultWallWidth = 65;
		int defaultWallHeight = 365;
		
		Wall wall1 = new Wall(new Dimension(defaultWallWidth, defaultWallHeight), new Point(0, 0));
		Wall wall2 = new Wall(new Dimension(defaultWallWidth, defaultWallHeight), new Point(1200 - defaultWallWidth, 0));
		Wall wall3 = new Wall(new Dimension(defaultWallWidth, defaultWallHeight), new Point(0, 800  -  defaultWallHeight));
		Wall wall4 = new Wall(new Dimension(defaultWallWidth, defaultWallHeight), new Point(1200 - defaultWallWidth, 800 - defaultWallHeight));
		
		walls.add(wall1);
		walls.add(wall2);
		walls.add(wall3);
		walls.add(wall4);
	}

	public void update() {
		this.setChanged();
		this.notifyObservers();
	}
	
	public void moveDashes() {
		while (startPressed) {
			try {
				Thread.sleep(1000 / 60);
				for (Dash dash : dashes) {
					dash.moveDownwards();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	// Getters and Setters
	public TagMan getTagMan() {
		return tagMan;
	}

	public ArrayList<Wall> getWalls() {
		return walls;
	}
	
	public ArrayList<Dash> getDashes() {
		return dashes;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void updateTimerAmount(int i) {
		this.timerAmount = timerAmount - i;
	}

	public int getTimerAmount() {
		return timerAmount;
	}
	
	public void setTimerAmount(int timerAmount) {
		this.timerAmount = timerAmount;
	}

	public static int getMaxTime() {
		return MAX_TIME;
	}

	public boolean getStartPressed() {
		return startPressed;
	}

	public void setStartPressed(boolean startPressed) {
		this.startPressed = startPressed;
	}

	@Override
	public void run() {
		moveDashes();
	}
	
}
