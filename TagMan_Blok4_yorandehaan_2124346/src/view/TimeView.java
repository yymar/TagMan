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
	private Color timerColour;

	public TimeView(MainController mainController) {
		this.mainController = mainController;
		this.setPreferredSize(new Dimension(0, 1000));
		this.setBackground(Color.BLACK);
		this.setLayout(new BorderLayout());
		setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
		this.timerColour = Color.CYAN;
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
			g.setColor(getTimerColour());
			g.fillRect(middleOfPanelWidth, drawHeight, TIMERWIDTH, timerY);
			g.setColor(Color.WHITE);
			g.drawRect(middleOfPanelWidth, drawHeight, TIMERWIDTH, timerY);
		}

		if (mainController.getTimeAmount() <= 15) {
			g.setColor(getTimerColour());
			g.fillRect(middleOfPanelWidth, drawHeight, TIMERWIDTH, timerY);
			g.setColor(Color.WHITE);
			g.drawRect(middleOfPanelWidth, drawHeight, TIMERWIDTH, timerY);
		}

		if (mainController.getTimeAmount() <= 7) {
			g.setColor(getTimerColour());
			g.fillRect(middleOfPanelWidth, drawHeight, TIMERWIDTH, timerY);
			g.setColor(Color.WHITE);
			g.drawRect(middleOfPanelWidth, drawHeight, TIMERWIDTH, timerY);
		}

	}

	public Color getTimerColour() {
		if (mainController.getTimeAmount() <= 15) {
			return new Color(255, 200, 0);
		}
		if (mainController.getTimeAmount() <= 7) {
			return Color.RED;
		}
		return Color.CYAN;
	}

	public void resetTimer() {
		timerHeight = getHeight() - (getHeight() / 4);
		interval = timerHeight / mainController.getMaxTime();
		timerY = mainController.getTimeAmount() * interval;
		secondsInt.setText(mainController.getTimeAmount() + "");
		setTimerColour(Color.CYAN);
	}

	public void setTimerColour(Color timerColour) {
		this.timerColour = timerColour;
	}

	@Override
	public void update(Observable o, Object arg) {
		secondsInt.setText(arg + "");
	}
}
