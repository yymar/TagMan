package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
		setBackground(Color.BLACK);
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

		
		
		// Colors them Yellow
		scoreText.setForeground(Color.YELLOW);
		scoreInt.setForeground(Color.YELLOW);
		levelText.setForeground(Color.YELLOW);
		levelInt.setForeground(Color.YELLOW);
		// Centers the labels
		scoreText.setHorizontalAlignment(JLabel.CENTER);
		scoreInt.setHorizontalAlignment(JLabel.CENTER);
		levelText.setHorizontalAlignment(JLabel.CENTER);
		levelInt.setHorizontalAlignment(JLabel.CENTER);
		//Set font size
		scoreText.setFont(smallFont());
		scoreInt.setFont(bigFont());
		levelText.setFont(smallFont());
		levelInt.setFont(bigFont());	
		// Adds the labels to the gameViewPane
		add(scoreText);
		add(scoreInt);
		add(levelText);
		add(levelInt);
	}

	private Font smallFont() {
		return new Font("Helvetica", Font.PLAIN, 12);
	}
	private Font bigFont() {
		return new Font("Helvetica", Font.PLAIN, 28);
	}
}
