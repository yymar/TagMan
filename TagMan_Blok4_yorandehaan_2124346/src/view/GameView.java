package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameView extends JPanel implements Observer {
	private JLabel scoreText;
	private JLabel scoreInt;
	private JLabel levelText;
	private JLabel levelInt;
	
	public GameView() {
		setBackground(Color.ORANGE);
		setPreferredSize(new Dimension(0, 300));
		setLayout(new GridLayout(4, 1));
		setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		this.setupJLabels();
	}

	@Override
	public void update(Observable arg0, Object arg1) {

	}

	private void setupJLabels() {
		scoreText = new JLabel("score");
		scoreInt = new JLabel();
		levelText = new JLabel("level");
		levelInt = new JLabel();
		
		scoreText.setHorizontalAlignment(JLabel.CENTER);
		scoreInt.setHorizontalAlignment(JLabel.CENTER);
		levelText.setHorizontalAlignment(JLabel.CENTER);
		levelInt.setHorizontalAlignment(JLabel.CENTER);
		
		add(scoreText);
		add(scoreInt);
		add(levelText);
		add(levelInt);
	}
}
