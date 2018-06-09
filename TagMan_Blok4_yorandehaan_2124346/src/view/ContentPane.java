package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import controller.MainController;

public class ContentPane extends JPanel {
	private MainController mainController;
	private TimeView timeView;
	private PlayView playView;
	private GameView gameView;
	private JPanel leftPanel;
	
	public ContentPane(MainController controller) {
		this.mainController = controller;
		this.setLayout(new BorderLayout());
		timeView = new TimeView();
		playView = new PlayView();
		gameView = new GameView();

		this.setupLeftPanel();
		this.initializeContentPane();
		this.setupTimeView();
	}

	private void setupTimeView() {

	}

	public TimeView getTimeView() {
		return timeView;
	}

	private void initializeContentPane() {
		this.setBackground(new Color(0, 3, 50));
	}

	public void setupLeftPanel() {
		leftPanel = new JPanel();
		leftPanel.setPreferredSize(new Dimension(100, 0));
		leftPanel.setBackground(timeView.getBackground());
		
		leftPanel.add(gameView);
		leftPanel.add(timeView);
		
		leftPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		this.add(leftPanel, BorderLayout.WEST);
	}
	
}
