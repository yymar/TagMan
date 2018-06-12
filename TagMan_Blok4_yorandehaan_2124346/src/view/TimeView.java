package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import controller.MainController;
import model.Game;

public class TimeView extends JPanel implements Observer {
	private JLabel secondsText;
	private JLabel secondsInt;
	private MainController mainController;
	private static final int TIMERWIDTH = 50;
	private int timerHeight;
	private int interval;
	private int timerY;

	public TimeView(MainController mainController, Game game) {
		this.mainController = mainController;
		game.addObserver(this);
		this.setPreferredSize(new Dimension(0, 1000));
		this.setBackground(Color.BLACK);
		this.setLayout(new BorderLayout());
		setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
		setupJLabels();
	}


	private void setupJLabels() {
		Border margin = new EmptyBorder(20, 20, 10, 20);

		secondsInt = new JLabel(mainController.getTimeAmount() + "");
		secondsInt.setHorizontalAlignment(JLabel.CENTER);
		secondsInt.setForeground(Color.YELLOW);
		secondsInt.setFont(new Font("Helvetica", Font.PLAIN, 28));
		secondsInt.setBorder(margin);
		add(secondsInt, BorderLayout.NORTH);

		secondsText = new JLabel("seconds");
		secondsText.setHorizontalAlignment(JLabel.CENTER);
		secondsText.setForeground(Color.YELLOW);
		secondsText.setFont(new Font("Helvetica", Font.PLAIN, 12));
		secondsText.setBorder(margin);
		add(secondsText, BorderLayout.SOUTH);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int amountOfSeconds = mainController.getTimeAmount();
		int middleOfPanelWidth = (getWidth() / 2) - (TIMERWIDTH / 2);
		
		int drawHeight = (getHeight() - getHeight() / 8) + timerY * -1;

		timerHeight = getHeight() - (getHeight() / 4);
		interval = timerHeight / mainController.getMaxTime();
		timerY = mainController.getTimeAmount() * interval;

		if (mainController.getTimeAmount() > 15) {
			g.setColor(new Color(0, 200, 254));
			g.fillRect(middleOfPanelWidth, drawHeight, TIMERWIDTH, timerY);
			g.setColor(Color.WHITE);
			g.drawRect(middleOfPanelWidth, drawHeight, TIMERWIDTH, timerY);
		}
		
		if (mainController.getTimeAmount() <= 15) {
			g.setColor(new Color(255, 200, 0));
			g.fillRect(middleOfPanelWidth, drawHeight, TIMERWIDTH, timerY);
			g.setColor(Color.WHITE);
			g.drawRect(middleOfPanelWidth, drawHeight, TIMERWIDTH, timerY);
		}
		
		if (mainController.getTimeAmount() <= 7) {
			g.setColor(new Color(255, 0, 0));
			g.fillRect(middleOfPanelWidth, drawHeight, TIMERWIDTH, timerY);
			g.setColor(Color.WHITE);
			g.drawRect(middleOfPanelWidth, drawHeight, TIMERWIDTH, timerY);
		}
	
	}

	@Override
	public void update(Observable o, Object arg) {
		secondsInt.setText(arg + "");
	}
}
