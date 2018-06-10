package controller;

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
	
}
