package controller;

import java.util.Observable;

public class TimeController extends Observable implements Runnable {
	private MainController mainController;
	private boolean isRunning;

	public TimeController(MainController mainController) {
		this.mainController = mainController;
	}

	public void startTimer() {
		isRunning = true;
	}
	
	public void resetTimer() {

	}

	@Override
	public void run() {
		while (isRunning) {
			try {
				Thread.sleep(1000);
				mainController.updateTimerAmount(1);
				if (mainController.getTimeAmount() <= 0) {
					isRunning = false;
				}
				this.setChanged();
				this.notifyObservers(mainController.getTimeAmount());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
