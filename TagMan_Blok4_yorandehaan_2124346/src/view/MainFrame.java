package view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;

import javax.swing.JFrame;

import controller.MainController;
import model.Game;

public class MainFrame extends JFrame implements Runnable, KeyListener {
	private MainController mainController;
	private ContentPane contentPane;
	public static int FPS = 60; 
	private char startChar = 's';

	public MainFrame(MainController controller, Game game) {
		this.mainController = controller;
		contentPane = new ContentPane(controller, game);
		this.addKeyListener(this);
		this.startThread();
	}

	public void initializeFrame() {
		this.setTitle("TagMan by Yoran de Haan");
		this.setContentPane(contentPane);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setFocusable(true);
		this.requestFocus();
	}

	public TimeView getTimeView() {
		return contentPane.getTimeView();
	}

	public void startThread() {
		new Thread(this).start();
	}

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

	@Override
	public void keyPressed(KeyEvent e) {
		int event = e.getKeyChar();

		if (event == 's') {
			mainController.startAllThreads();
		}
		if (event == 27) {
			mainController.systemExit();
		}
		
		mainController.move(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}
}
