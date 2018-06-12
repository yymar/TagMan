package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import controller.MainController;
import model.Dash;
import model.Wall;

public class PlayView extends JPanel implements Observer {
	private MainController mainController;
	private TagManPainterPlain tagManPainterPlain;

	public PlayView(MainController mainController) {
		this.mainController = mainController;
		this.tagManPainterPlain = new TagManPainterPlain();
		this.setBackground(new Color(0, 3, 50));
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		String welcomeText = "Welcome to TagMan";	
		String introText = "move with arrows or numpad";
		String instructionText = "press S to start";
		
		// Introduction Text
		// draws center of component
		if (!mainController.getGame().getStartPressed()) {
			g.setColor(Color.RED);
			g.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
			g.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);
			// Draw text
			g.setColor(Color.YELLOW);
			this.drawCenteredString(g, welcomeText, new Rectangle(new Dimension(getWidth(), getHeight() - 200)), new Font("Helvetica", Font.PLAIN, 32));
			this.drawCenteredString(g, introText, new Rectangle(new Dimension(getWidth(), getHeight() - 100)), new Font("Helvetica", Font.PLAIN, 32));
			// TODO: LEVEL + VARIABLE
			this.drawCenteredString(g, instructionText, new Rectangle(new Dimension(getWidth(), getHeight() + 100)), new Font("Helvetica", Font.PLAIN, 32));
		}
		
		tagManPainterPlain.paint(g, mainController.getGame().getTagMan());
		paintWalls(g);
		paintDashes(g);
	}

	private void paintDashes(Graphics g) {
		ArrayList<Dash> dashes = mainController.getGame().getDashes();
		int borderMargin = 1;
		for (Dash dash : dashes) {
			g.setColor(Color.WHITE);
			g.fillRect((int) dash.getPoint().getX(), (int) dash.getPoint().getY(), (int) dash.getDimension().getWidth(), (int) dash.getDimension().getHeight());
			g.setColor(dash.getDashColor());
			g.fillRect((int) dash.getPoint().getX() + borderMargin, (int) dash.getPoint().getY() + borderMargin, (int) dash.getDimension().getWidth() - (borderMargin * 2), (int) dash.getDimension().getHeight() - (borderMargin * 2));
		}
	}
	
	private void paintWalls(Graphics g) {
		ArrayList<Wall> walls = mainController.getGame().getWalls();
		int borderMargin = 4;
		for (Wall wall : walls) {
			g.setColor(Color.WHITE);
			g.fillRect((int) wall.getPoint().getX(), (int) wall.getPoint().getY(), (int) wall.getDimension().getWidth(), (int) wall.getDimension().getHeight());
			g.setColor(wall.getWallColour());
			g.fillRect((int) wall.getPoint().getX() + borderMargin, (int) wall.getPoint().getY() + borderMargin, (int) wall.getDimension().getWidth() - (borderMargin * 2), (int) wall.getDimension().getHeight() - (borderMargin * 2));
		
		}
	}
	
	// Method that you can find anywhere ;)
	private void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
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

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
}
