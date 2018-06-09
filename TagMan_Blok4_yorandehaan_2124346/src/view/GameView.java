package view;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

public class GameView extends JPanel implements Observer {
	
	
	public GameView() {
		this.setBackground(Color.ORANGE);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		
	}
}
