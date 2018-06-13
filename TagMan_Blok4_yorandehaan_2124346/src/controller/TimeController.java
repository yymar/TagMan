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

	@Override
	public void run() {
		while (isRunning) {
			try {
				if (mainController.getGame().getStartPressed()) {
					if (!mainController.getGame().getSucces()) {
						if (!mainController.getGame().getCrashed()) {
							Thread.sleep(1000);
							mainController.updateTimerAmount(1);
							if (mainController.getTimeAmount() <= 0) {
								isRunning = false;
							}
						}
					}
				}
				this.setChanged();
				this.notifyObservers(mainController.getTimeAmount());
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}
}
