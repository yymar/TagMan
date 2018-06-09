package app;

import controller.MainController;

public class MainClass {

	public static void main(String[] args) {
		new Thread(new Runnable() {
			public void run() {
				new MainController();
			}
		}).start();
	}

}
