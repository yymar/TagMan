package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.text.html.HTML.Tag;

import controller.MainController;
import model.TagMan;

public class PlayView extends JPanel implements Observer, KeyListener {
	private boolean startPressed;
	private MainController mainController;
	private Color firstCircle;
	private Color secondCircle;
	private Color thirdCircle;
	private char startChar = 's';
	private char nextLevel = 'l';
	
	private int playerX;
	private int playerY;
	private int playerWidth;
	private int playerHeight;

	public PlayView(MainController mainController) {
		this.mainController = mainController;

		this.firstCircle = Color.RED;
		this.secondCircle = Color.ORANGE;
		this.thirdCircle = Color.YELLOW;

		this.addKeyListener(this);
		this.setFocusable(true);
		this.requestFocus();
		this.setBackground(new Color(0, 3, 50));
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		String welcomeText = "Welcome to TagMan";	
		String introText = "move with arrows or numpad";
		String instructionText = "press S to start";

		// Introduction Text
		if (!startPressed) {
			// draws center of component
			g2.setColor(Color.RED);
			g2.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
			g2.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);
			// Draw text
			g2.setColor(Color.YELLOW);
			this.drawCenteredString(g2, welcomeText, new Rectangle(new Dimension(getWidth(), getHeight() - 200)),
					new Font("Helvetica", Font.PLAIN, 32));
			this.drawCenteredString(g2, introText, new Rectangle(new Dimension(getWidth(), getHeight() - 100)),
					new Font("Helvetica", Font.PLAIN, 32));
			// TODO: LEVEL + VARIABLE
			this.drawCenteredString(g2, instructionText, new Rectangle(new Dimension(getWidth(), getHeight() + 100)),
					new Font("Helvetica", Font.PLAIN, 32));
		}
		
		// TODO: DRAW TAGMAN
		g2.setColor(firstCircle);
		g2.fillOval(playerX, playerY, playerWidth, playerHeight);
		g2.setColor(secondCircle);
		g2.fillOval(playerX, playerY, playerWidth, playerHeight);
		g2.setColor(thirdCircle);
		g2.fillOval(playerX, playerY, playerWidth, playerHeight);
	}

	@Override
	public void update(Observable arg0, Object arg1) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int event = e.getKeyChar(); 
		mainController.move(event);
		switch (e.getKeyCode()) {

		case KeyEvent.VK_S:
			if (event == startChar && !startPressed) {
				mainController.startAllThreads();
				startPressed = true;
				break;
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	// Method that you can find anywhere ;)
	private void drawCenteredString(Graphics2D g, String text, Rectangle rect, Font font) {
		// Get the FontMetrics
		FontMetrics metrics = g.getFontMetrics(font);
		// Determine the X coordinate for the text
		int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
		// Determine the Y coordinate for the text (note we add the ascent, as in java
		// 2d 0 is top of the screen)
		int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
		// Set the font
		g.setFont(font);
		// Draw the String
		g.drawString(text, x, y);
	}
}
