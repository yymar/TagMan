package model;

import java.util.Observable;

public class Game extends Observable {
	private int level;
	private int score;
	private int timerAmount;
//	TODO: booleans with failure succes etc. 

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
}
