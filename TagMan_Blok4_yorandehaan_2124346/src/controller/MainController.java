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
					if (keyPressed == KeyEvent.VK_RIGHT && tagManXpos <= getWidth - tagManWidth - (tagManWidth / 4)) {
						if (!collidesWithWalls(tagMan.getVelocity(), 0)) {
							if (!collidesWithDashes(tagMan.getVelocity(), 0)) {
								tagMan.moveForwards();
								checkFinished();
							}
						}
					}

					if (keyPressed == KeyEvent.VK_DOWN && tagManYpos <= 800 - tagManHeigth - (tagManWidth / 4)) {
						if (!collidesWithWalls(0, tagMan.getVelocity())) {
							if (!collidesWithDashes(0, tagMan.getVelocity())) {
								tagMan.moveDownwards();
								checkFinished();
							}
						}
					}

					if (keyPressed == KeyEvent.VK_UP && tagManYpos >= 0 + (tagManHeigth / 4)) {
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

	public boolean collidesWithWalls(int x, int y) {
		for (GameObject object : game.getWalls()) {
			if (game.getTagMan().willCollide(object, x, y)) {
				return true;
			}
		}
		return false;
	}

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

	public void checkFinished() {
		TagMan tagMan = game.getTagMan();
		Rectangle tagManHitBox = game.getTagMan().getBounds();
		
		Color firstCircleColorFinish = new Color(0, 100, 50);
		Color secondCircleColorFinish = new Color(0, 255, 128);
		Color thirdCircleColorFinish = new Color(170, 255, 170);
		
		Rectangle finish = new Rectangle(1180, 0, 365, 800);
		
		if (tagManHitBox.intersects(finish)) {
			game.setSucces(true);
			game.setScore(getTimeAmount());
			game.update();
			
			tagMan.setFirstCircle(firstCircleColorFinish);
			tagMan.setSecondCircle(secondCircleColorFinish);
			tagMan.setThirdCircle(thirdCircleColorFinish);
		}
	}
	
	public void systemExit() {
		System.exit(0);
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
	
	public void resetTimer() {
        game.resetTimer(getMaxTime());
        mainFrame.getTimeView().resetTimer();
        game.update();
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

	public void stop() {
		if (game.getCrashed() || game.getSucces()) {
			timeControllerThread.interrupt();
			gameThread.interrupt();
		}
	}

	public Game getGame() {
		return game;	
	}
}
