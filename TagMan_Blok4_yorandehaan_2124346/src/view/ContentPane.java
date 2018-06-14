package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import controller.MainController;
import model.Game;

@SuppressWarnings("serial")
public class ContentPane extends JPanel {
	private MainController mainController;
	private TimeView timeView;
	private PlayView playView;
	private GameView gameView;
	private JPanel leftPanel;
	
	public ContentPane(MainController controller, Game game) {
		this.mainController = controller;
		setLayout(new BorderLayout());
		// set the preferedSize here, because if you do it in mainFrame it counts the JFrame bars as width and height as well.
		setPreferredSize(new Dimension(1300, 800));
		timeView = new TimeView(mainController);
		playView = new PlayView(mainController, game);
		gameView = new GameView(game);

		add(playView);
		
		this.setupLeftPanel();
	}
	
	// Created a leftPanel to make positioning easier
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

	public TimeView getTimeView() {
		return timeView;
	}

	public PlayView getPlayView() {
		return playView;
	}

	public GameView getGameView() {
		return gameView;
	}

}
