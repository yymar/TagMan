package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class TimeView extends JPanel implements Observer {

	private JLabel secondsText;
	
	public TimeView() {
		this.setPreferredSize(new Dimension(0, 1000));
		this.setBackground(Color.RED);
		this.setLayout(new BorderLayout());
		setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
		setupJLabels();
	}

	@Override
	public void update(Observable o, Object arg) {
		
	}
	
	private void setupJLabels() {
		Border margin = new EmptyBorder(20,20,10,20);
		secondsText = new JLabel("seconds");
		secondsText.setHorizontalAlignment(JLabel.CENTER);
		secondsText.setBorder(margin);
		add(secondsText, BorderLayout.SOUTH);
	}
}
