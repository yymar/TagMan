package model;

import java.util.Observable;

public class Game extends Observable {
	private static final int MAX_TIME = 30;
	private int level;
	private int score;
	private int timerAmount;
	private boolean finished;
	private boolean crashed;

	public Game() {
		timerAmount = 30;
	}

	// Getters and Setters
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
