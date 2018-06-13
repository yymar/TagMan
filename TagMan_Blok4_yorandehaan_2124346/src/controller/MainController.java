package controller;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import model.Dash;
import model.Game;
import model.TagMan;
import view.MainFrame;

public class MainController {
	private TimeController timeController;
	private Game game;
	private MainFrame mainFrame;

	public MainController() {
		this.game = new Game();
		this.timeController = new TimeController(this);
		this.mainFrame = new MainFrame(this, game);

		game.update();
		timeController.addObserver(mainFrame.getTimeView());
		mainFrame.initializeFrame();
	}

	public void move(KeyEvent e) {
		int keyPressed = e.getKeyCode();
		int tagManXpos = (int) game.getTagMan().getPoint().getX();
		int tagManYpos = (int) game.getTagMan().getPoint().getY();
		int tagManWidth = (int) game.getTagMan().getDimension().getWidth();
		int tagManHeigth = (int) game.getTagMan().getDimension().getHeight();
		int getWidth = mainFrame.getContentPane().getPlayView().getWidth();

		if (game.getStartPressed()) {
			if (keyPressed == e.VK_RIGHT && tagManXpos <= getWidth - tagManWidth - (tagManWidth / 4)) {
				checkDashCollision();
				game.getTagMan().moveForwards();
				checkFinished();
			}
			if (keyPressed == e.VK_DOWN && tagManYpos <= 800 - tagManHeigth - (tagManWidth / 4)) {
				checkDashCollision();
				game.getTagMan().moveDownwards();
				checkFinished();
			}
			if (keyPressed == e.VK_UP && tagManYpos >= 0 + (tagManHeigth / 4)) {
				checkDashCollision();
				game.getTagMan().moveUpwards();
				checkFinished();
			}

			if (keyPressed == KeyEvent.VK_NUMPAD3 && tagManXpos <= getWidth - tagManWidth - (tagManWidth / 4)  && tagManYpos <= 800 - tagManHeigth - (tagManWidth / 4)) {
				checkDashCollision();
				game.getTagMan().moveHorizontalyDownwards();
				checkFinished();
			}

			if (keyPressed == KeyEvent.VK_NUMPAD9 && tagManXpos <= getWidth - tagManWidth - (tagManWidth / 4) && tagManYpos >= 0 + (tagManHeigth / 4)) {
				checkDashCollision();
				game.getTagMan().moveHorizontalyUpwards();
				checkFinished();
			}
		}
	}

	public void checkDashCollision() {
		ArrayList<Dash> dashes = game.getDashes();
		for (Dash dash : dashes) {
			Rectangle dashHitbox = dash.getBounds();
			Rectangle tagManHitBox = game.getTagMan().getBounds();
			if (tagManHitBox.intersects(dashHitbox)) {
				game.setCrashed(true);
				System.out.println("COLLISION!");
			}
		}
	}

	public void checkFinished() {
		TagMan tagMan = game.getTagMan();
		Rectangle tagManHitBox = game.getTagMan().getBounds();
		
		Color firstCircleColorFinish = new Color(0, 100, 50);
		Color secondCircleColorFinish = new Color(0, 255, 128);
		Color thirdCircleColorFinish = new Color(170, 255, 170);
		
		Rectangle finish = new Rectangle(1180, 0, 365, 800);
		
		if (tagManHitBox.intersects(finish)) {
			tagMan.setFirstCircle(firstCircleColorFinish);
			tagMan.setSecondCircle(secondCircleColorFinish);
			tagMan.setThirdCircle(thirdCircleColorFinish);
			game.setSucces(true);
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
		return game.getMaxTime();
	}

	public void startAllThreads() {
		// Prevents Threads from starting multiple times.
		if (!game.getStartPressed()) {
			new Thread(timeController).start();
			timeController.startTimer();
			game.setStartPressed(true);
			new Thread(game).start();
		}
	}
	
	public Game getGame() {
		return game;
	}
}
