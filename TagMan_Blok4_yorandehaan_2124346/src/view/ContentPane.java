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
		this.setPreferredSize(new Dimension(1300, 800));
		timeView = new TimeView(mainController);
		playView = new PlayView(mainController);
		gameView = new GameView();

		add(playView);
		
		this.setupLeftPanel();
		this.setupTimeView();
	}

	private void setupTimeView() {

	}

	public TimeView getTimeView() {
		return timeView;
	}

	

	public void setupLeftPanel() {
		leftPanel = new JPanel();
		leftPanel.setPreferredSize(new Dimension(100, 0));
		leftPanel.setBackground(timeView.getBackground());
		
		leftPanel.add(gameView);
		leftPanel.add(timeView);
		
		leftPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		this.add(leftPanel, BorderLayout.WEST);
	}
	
}
