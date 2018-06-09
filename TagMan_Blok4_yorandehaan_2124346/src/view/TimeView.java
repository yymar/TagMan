package view;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

public class TimeView extends JPanel implements Observer {

	public TimeView() {
		this.setPreferredSize(new Dimension(0, 300));
		this.setBackground(Color.RED);
	}

	@Override
	public void update(Observable o, Object arg) {

	}
}
