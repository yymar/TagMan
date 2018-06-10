package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import controller.MainController;

public class TimeView extends JPanel implements Observer {
	private JLabel secondsText;
	private JLabel secondsInt;
	private MainController mainController;

	public TimeView(MainController mainController) {
		this.mainController = mainController;
		this.setPreferredSize(new Dimension(0, 1000));
		this.setBackground(Color.BLACK);
		this.setLayout(new BorderLayout());
		setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
		setupJLabels();
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println(arg);
		secondsInt.setText(arg + "");
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
}
