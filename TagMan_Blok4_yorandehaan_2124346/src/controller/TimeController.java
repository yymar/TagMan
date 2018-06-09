package controller;

import java.util.Observable;

public class TimeController extends Observable implements Runnable {
	private MainController controller;
	
	
	public TimeController(MainController controller) {
		this.controller = controller;
	}


	@Override
	public void run() {
		countDown();
		notifyObservers();
	}
	
	private void countDown() {
		for(int i = 30; i >= 0; i--) {
			try {
				Thread.sleep(1000);
				System.out.println(i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
