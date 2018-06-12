package controller;

import java.awt.event.KeyEvent;

import model.Game;
import view.MainFrame;

public class MainController {
	private TimeController timeController;
	private Game game;
	private MainFrame mainFrame;

	public MainController() {
		this.timeController = new TimeController(this);
		this.game = new Game();
		this.mainFrame = new MainFrame(this);
		
		timeController.addObserver(mainFrame.getTimeView());
		mainFrame.initializeFrame();
	}
	
	public void move(KeyEvent e) {
		int keyPressed = e.getKeyCode();
		
		if (keyPressed == e.VK_RIGHT) {
			System.out.println("forwards");
			game.getTagMan().moveForwards();
		}
		if (keyPressed == e.VK_DOWN) {
			System.out.println("down");
			game.getTagMan().moveDownwards();
		}
		if (keyPressed == e.VK_UP) {
			System.out.println("up");
			game.getTagMan().moveUpwards();
		}

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
		new Thread(timeController).start();
		timeController.startTimer();
	}
	
	public Game getGame() {
		return game;
	}
}
