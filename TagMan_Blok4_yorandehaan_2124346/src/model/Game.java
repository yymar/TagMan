package model;

import java.util.Observable;

public class Game extends Observable {
	private int level;
	private int score;

	
	
	
	
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

}
