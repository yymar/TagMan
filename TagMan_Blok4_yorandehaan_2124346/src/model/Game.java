package model;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Observable;


public class Game extends Observable implements Runnable {
	private ArrayList<Dash> dashes;
	private ArrayList<Wall> walls;
	private TagMan tagMan;
	private boolean isRunning;
	private static final int AMOUNT_OF_DASHES = 10;
	private static final int MAX_TIME = 30;
	private int level;
	private int score;
	private int timerAmount;
	private boolean startPressed;
	private boolean succes;
	private boolean crashed;
	@SuppressWarnings("unused")
	private boolean gameFinished;
	
	public Game() {
		loadLevel();
	}

	public void loadLevel() {
		if (!getGameFinished()) {
			level = level + 1;

			startPressed = false;
			succes = false;
			crashed = false;
			timerAmount = 30;

			tagMan = new TagMan(new Dimension(50, 50), new Point(5, 375));
			dashes = new ArrayList<>();
			walls = new ArrayList<>();

			createDashes();
			createWalls();
			update();
		}
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

		Wall defaultWallTopLeft = new Wall(new Dimension(defaultWallWidth, defaultWallHeight), new Point(0, 0));
		Wall defaultWallTopRight = new Wall(new Dimension(defaultWallWidth, defaultWallHeight), new Point(1200 - defaultWallWidth, 0));
		Wall defaultWallBottomLeft = new Wall(new Dimension(defaultWallWidth, defaultWallHeight), new Point(0, 800 - defaultWallHeight));
		Wall defaultWallBottomRight = new Wall(new Dimension(defaultWallWidth, defaultWallHeight), new Point(1200 - defaultWallWidth, 800 - defaultWallHeight));

		walls.add(defaultWallTopLeft);
		walls.add(defaultWallTopRight);
		walls.add(defaultWallBottomLeft);
		walls.add(defaultWallBottomRight);
		
		if (level == 2) {
			int wallObstacleWidth = 50;
			int wallObstacleHeigth = defaultWallHeight / 2;
			Wall wallObstacle = new Wall(new Dimension(25, wallObstacleHeigth), new Point(250 - (wallObstacleWidth  / 2), defaultWallHeight));	
			walls.add(wallObstacle);
		}
	}
	
	public boolean tagManCollidesWithDash(int x, int y) {
		for (GameObject object : getDashes()) {
			if (object.willCollide(tagMan, x, y)) {
				setCrashed(true);
				return false;
			}
		}
		return true;
	}

	public void update() {
		this.setChanged();
		this.notifyObservers();
	}
	
	public boolean getGameFinished() {
		if (succes && getLevel() == 2) {
			gameFinished = true;
			return true;
		}
		return false;
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

	public boolean getCrashed() {
		return crashed;
	}

	public void setCrashed(boolean crashed) {
		this.crashed = crashed;
	}
	
	public boolean getSucces() {
		return succes;
	}
	
	public void setSucces(boolean succes) {
		this.succes = succes;
		update();
	}
	

	public void resetTimer(int i) {
		this.timerAmount = i;
	}

	public void startGameThread() {
		isRunning = true;
	}

	@Override
	public void run() {
		try {
			while (isRunning) {
				if (getStartPressed()) {
					if (!getSucces()) {
						if (!getCrashed()) {
							Thread.sleep(1000 / 60);
							for (Dash dash : dashes) {
								if (!dash.getIsMoving()) {
									dash.setIsMoving();
								}
								if (dash.getIsMoving()) {
									if (tagManCollidesWithDash(0, dash.getSpeed())) {
										dash.moveDownwards();
									}
								}
							}
						}
					}
				}
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}
	


