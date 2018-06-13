package controller;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import model.Game;
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
		int getHeight = mainFrame.getContentPane().getPlayView().getHeight();

		if (game.getStartPressed()) {
			if (keyPressed == e.VK_RIGHT && tagManXpos <= getWidth - tagManWidth - (tagManWidth / 4)) {
				game.getTagMan().moveForwards();
			}
			if (keyPressed == e.VK_DOWN && tagManYpos <= 800 - tagManHeigth - (tagManWidth / 4)) {
				game.getTagMan().moveDownwards();
			}
			if (keyPressed == e.VK_UP && tagManYpos >= 0 + (tagManHeigth / 4)) {
				game.getTagMan().moveUpwards();
			}
		}
	}
	
	public void checkFinished() {
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
