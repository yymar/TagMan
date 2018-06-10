package view;

import java.awt.Dimension;

import javax.swing.JFrame;

import controller.MainController;

public class MainFrame extends JFrame implements Runnable {
	private MainController mainController;

	private ContentPane contentPane;
    public static int FPS = 60; //TODO: initialize somewhere else

	public MainFrame(MainController controller) {
		this.mainController = controller;
		contentPane = new ContentPane(controller);
		this.startThread();
	}

	public void initializeFrame() {
		this.setTitle("TagMan by Yoran de Haan");
		this.setContentPane(contentPane);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(1300, 800));
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
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
	
}
