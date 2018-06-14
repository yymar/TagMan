package view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import controller.MainController;
import model.Game;

@SuppressWarnings("serial")
public class MainFrame extends JFrame implements Runnable, KeyListener {
	private MainController mainController;
	private ContentPane contentPane;
	public static int FPS = 60;

	public MainFrame(MainController controller, Game game) {
		this.mainController = controller;
		contentPane = new ContentPane(controller, game);
		addKeyListener(this);
		startThread();
	}

	public void initializeFrame() {
		setTitle("TagMan by Yoran de Haan");
		setContentPane(contentPane);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		setFocusable(true);
		requestFocus();
	}

	public TimeView getTimeView() {
		return contentPane.getTimeView();
	}

	public void startThread() {
		new Thread(this).start();
	}

	// Calls the correct methods when KeyInput is given.
	@Override
	public void keyPressed(KeyEvent e) {
		int event = e.getKeyChar();

		if (event == 's' && !mainController.getGame().getStartPressed()) {
			mainController.getGame().setStartPressed(true);
			mainController.startGameThread();
			mainController.startTimerThread();
		}
		if (event == 27) {
			mainController.systemExit();
		}

		if (event == 'l' && mainController.getGame().getSucces()) {
			if (!mainController.getGame().getGameFinished()) {
				mainController.stop();
				mainController.getGame().loadLevel();
				mainController.resetTimer();
			}
		}
		mainController.move(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	public ContentPane getContentPane() {
		return contentPane;
	}

	// This basically repaints the frame 60x per second (60FPS)
	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(1000 / FPS);
				this.repaint();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
