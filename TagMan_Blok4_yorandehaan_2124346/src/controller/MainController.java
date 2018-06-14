package controller;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import model.Game;
import model.GameObject;
import model.TagMan;
import view.MainFrame;

public class MainController {
	private TimeController timeController;
	private Game game;
	private MainFrame mainFrame;
	private Thread gameThread;
	private Thread timeControllerThread;
	
	public MainController() {
		this.game = new Game();
		this.timeController = new TimeController(this);
		this.mainFrame = new MainFrame(this, game);
		
		this.timeControllerThread = new Thread(timeController);
		
		game.update();
		timeController.addObserver(mainFrame.getTimeView());
		mainFrame.initializeFrame();
	}
	
	/*
	 * Move method checks if the player is allowed to move. It gets the KeyEvent e from the MainFrame.
	 * First it checks if start is pressed 
	 * Secondly it checks if the game hasn't succeeded yet
	 * Thirdly it checks if the tagMan hasn't crashed yet in game
	 * Then I basically coded every what it's supposed to do with every KeyEvent (move up down etc.)
	 */
	
	public void move(KeyEvent e) {
		int keyPressed = e.getKeyCode();
		TagMan tagMan = game.getTagMan();
		int tagManXpos = (int) game.getTagMan().getPoint().getX();
		int tagManYpos = (int) game.getTagMan().getPoint().getY();
		int tagManWidth = (int) game.getTagMan().getDimension().getWidth();
		int tagManHeigth = (int) game.getTagMan().getDimension().getHeight();
		int getWidth = mainFrame.getContentPane().getPlayView().getWidth();

		if (game.getStartPressed()) {
			if (!game.getSucces()) {
				if (!game.getCrashed()) {
					if ((keyPressed == KeyEvent.VK_RIGHT || keyPressed == KeyEvent.VK_NUMPAD6) && tagManXpos <= getWidth - tagManWidth - (tagManWidth / 4)) {
						if (!collidesWithWalls(tagMan.getVelocity(), 0)) {
							if (!collidesWithDashes(tagMan.getVelocity(), 0)) {
								tagMan.moveForwards();
								checkFinished();
							}
						}
					}

					if ((keyPressed == KeyEvent.VK_DOWN || keyPressed == KeyEvent.VK_NUMPAD2) && tagManYpos <= 800 - tagManHeigth - (tagManWidth / 4)) {
						if (!collidesWithWalls(0, tagMan.getVelocity())) {
							if (!collidesWithDashes(0, tagMan.getVelocity())) {
								tagMan.moveDownwards();
								checkFinished();
							}
						}
					}

					if ((keyPressed == KeyEvent.VK_UP || keyPressed == KeyEvent.VK_NUMPAD8) && tagManYpos >= 0 + (tagManHeigth / 4)) {
						if (!collidesWithWalls(0, tagMan.getVelocity() * -1)) {
							if (!collidesWithDashes(0, tagMan.getVelocity() * -1)) {
								tagMan.moveUpwards();
								checkFinished();
							}
						}
					}

					if (keyPressed == KeyEvent.VK_NUMPAD3 && tagManXpos <= getWidth - tagManWidth - (tagManWidth / 4)  && tagManYpos <= 800 - tagManHeigth - (tagManWidth / 4)) {
						if (!collidesWithWalls(tagMan.getDiagonalVelocity(), tagMan.getDiagonalVelocity())) {
							if (!collidesWithWalls(tagMan.getDiagonalVelocity(), tagMan.getDiagonalVelocity())) {
								tagMan.moveDiagonalyDownwards();
								checkFinished();
							}
						}
					}

				if (keyPressed == KeyEvent.VK_NUMPAD9 && tagManXpos <= getWidth - tagManWidth - (tagManWidth / 4) && tagManYpos >= 0 + (tagManHeigth / 4)) {
					if (!collidesWithWalls(tagMan.getVelocity(), tagMan.getDiagonalVelocity() * -1)) {
						if (!collidesWithDashes(tagMan.getVelocity(), tagMan.getDiagonalVelocity() * -1))
							tagMan.moveDiogonalyUpwards();
						checkFinished();
						}
					}
				}
			}
		}
	}

	// Checks for collision with walls.
	public boolean collidesWithWalls(int x, int y) {
		for (GameObject object : game.getWalls()) {
			if (game.getTagMan().willCollide(object, x, y)) {
				return true;
			}
		}
		return false;
	}

	// Checks for collision with dashes.
	public boolean collidesWithDashes(int x, int y) {
		for (GameObject object : game.getDashes()) {
			if (game.getTagMan().willCollide(object, x, y)) {
				game.setCrashed(true);
				stop();
				return true;
			}
		}
		return false;
	}
	
	/*
	 * Checks if the tagMan is in the finish area, if so it sets success to true and it set the color to green.
	 */
	
	public void checkFinished() {
		TagMan tagMan = game.getTagMan();
		Rectangle tagManHitBox = game.getTagMan().getBounds();
		
		Color firstCircleColorFinish = new Color(0, 100, 50);
		Color secondCircleColorFinish = new Color(0, 255, 128);
		Color thirdCircleColorFinish = new Color(170, 255, 170);
		
		Rectangle finish = new Rectangle(1180, 0, 365, 800);
		
		if (tagManHitBox.intersects(finish)) {
			game.setSucces(true);
			game.setScore(game.getScore() + getTimeAmount());
			game.update();
			
			tagMan.setFirstCircle(firstCircleColorFinish);
			tagMan.setSecondCircle(secondCircleColorFinish);
			tagMan.setThirdCircle(thirdCircleColorFinish);
		}
	}
	
	public void systemExit() {
		System.exit(0);
	}

	public void resetTimer() {
		game.resetTimer(getMaxTime());
		mainFrame.getTimeView().resetTimer();
	}

	public void startGameThread() {
		this.gameThread = new Thread(game);
		gameThread.start();
		game.startGameThread();
	}

	public void startTimerThread() {
		this.timeControllerThread = new Thread(timeController);
		timeControllerThread.start();
		timeController.startTimer();
	}
	
	// Interrupts threads if the tagMan crashed or if the tagMan finished
	public void stop() {
		if (game.getCrashed() || game.getSucces()) {
			timeControllerThread.interrupt();
			gameThread.interrupt();
		}
	}

	public void updateTimerAmount(int i) {
		game.updateTimerAmount(i);
	}

	public int getTimeAmount() {
		return game.getTimerAmount();
	}

	public int getMaxTime() {
		return Game.getMaxTime();
	}

	public Game getGame() {
		return game;
	}
}
