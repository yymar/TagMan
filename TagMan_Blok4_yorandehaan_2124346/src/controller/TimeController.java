package controller;

import java.util.Observable;

public class TimeController extends Observable {
	private MainController controller;

	public TimeController(MainController controller) {
		this.controller = controller;
	}

}
