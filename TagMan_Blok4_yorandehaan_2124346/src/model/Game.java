package model;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

public class Game extends Observable {
	private ArrayList<Dash> dashes;
	private ArrayList<Wall> walls;
	private TagMan tagMan;
	private int amountOfDashes;
	private static final int MAX_TIME = 30;
	private int level;
	private int score;
	private int timerAmount;
	private boolean finished;
	private boolean crashed;

	public Game() {
		tagMan = new TagMan(new Dimension(50, 50), new Point(10, (756 /2) -25));
		dashes = new ArrayList<>();
		walls = new ArrayList<>();
		timerAmount = 30;
		amountOfDashes = 10;
		
		addDashes();
		addWalls();
		
	}

	public void addDashes() {
		int spaceBetweenDashes = 130;
		for (int i = 0; i < amountOfDashes; i++) {
			Dash dash = new Dash(new Dimension(50, 20), new Point(spaceBetweenDashes, 0));
			dashes.add(dash);
			spaceBetweenDashes++;
		}
	}
	
	public void addWalls() {
		for (int i = 0; i < amountOfDashes; i++) {
			Wall wall1 = new Wall(new Dimension(50, 300), new Point(0, 0));
			walls.add(wall1);
		}
	}
	
	// Getters and Setters
	public TagMan getTagMan() {
		return tagMan;
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

	public static int getMaxTime() {
		return MAX_TIME;
	}
}
