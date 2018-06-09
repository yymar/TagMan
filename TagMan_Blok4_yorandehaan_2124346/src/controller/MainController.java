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
	
}
