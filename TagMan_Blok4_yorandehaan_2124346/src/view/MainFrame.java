package view;

import java.awt.Dimension;

import javax.swing.JFrame;

import controller.MainController;

public class MainFrame extends JFrame {
	private MainController controller;

	private ContentPane contentPane;

	public MainFrame(MainController controller) {
		this.controller = controller;
		contentPane = new ContentPane(controller);
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
}
