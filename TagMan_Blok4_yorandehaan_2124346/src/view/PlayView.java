package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import controller.MainController;
import model.Dash;
import model.Game;
import model.Wall;

@SuppressWarnings("serial")
public class PlayView extends JPanel implements Observer {
	private MainController mainController;
	private TagManPainterPlain tagManPainterPlain;

	public PlayView(MainController mainController, Game game) {
		this.mainController = mainController;
		game.addObserver(this);
		this.tagManPainterPlain = new TagManPainterPlain();
		this.setBackground(new Color(0, 3, 50));
		
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Introduction Text
		String welcomeText = "Welcome to TagMan";
		String introText = "move with arrows or numpad";
		String levelText = "LEVEL " + mainController.getGame().getLevel();
		String instructionText = "press S to start";
		// Hit text
		String tagManHitText = "TAGMAN IS HIT";
		String gameOver = "GAMEOVER";
		String yourScore = "Your score: " + mainController.getGame().getScore();
		String exitString = "hit ESC to exit";
		// Finished text
		String finished = "Finished !";
		String yourScoreFinish = "your score: " + mainController.getGame().getScore();
		String continueGame = "hit L to continue";
		// End game text
		String youWon = "YEAH TagMan Reached the end";
		String totalScore = "Total score: " + mainController.getGame().getScore();

		tagManPainterPlain.paint(g, mainController.getGame().getTagMan());
		paintWalls(g);
		paintDashes(g);

		// Paint text on frame
		g.setColor(Color.YELLOW);

		// Welcome Text
		if (!mainController.getGame().getStartPressed()) {
			if (mainController.getGame().getLevel() == 1) {
				drawCenteredString(g, welcomeText, new Rectangle(new Dimension(getWidth(), getHeight() - 200)), getGameFont());
			}
			
			drawCenteredString(g, introText, new Rectangle(new Dimension(getWidth(), getHeight() - 100)), getGameFont());
			drawCenteredString(g, levelText , new Rectangle(new Dimension(getWidth(), getHeight())), getGameFont());
			drawCenteredString(g, instructionText, new Rectangle(new Dimension(getWidth(), getHeight() + 100)), getGameFont());
		}
		
		// Crash Text
		if (mainController.getGame().getCrashed()) {
			drawCenteredString(g, tagManHitText, new Rectangle(new Dimension(getWidth(), getHeight()  -200)), getGameFont());
			drawCenteredString(g, gameOver, new Rectangle(new Dimension(getWidth(), getHeight() - 100)), getGameFont());
			drawCenteredString(g, yourScore, new Rectangle(new Dimension(getWidth(), getHeight())), getGameFont());
			drawCenteredString(g, exitString, new Rectangle(new Dimension(getWidth(), getHeight() + 100)), getGameFont());
		}
		
		// Finished Text
		if (mainController.getGame().getSucces() && !mainController.getGame().getGameFinished()) {
			drawCenteredString(g, finished, new Rectangle(new Dimension(getWidth(), getHeight()  - 200)),getGameFont());
			drawCenteredString(g, yourScoreFinish, new Rectangle(new Dimension(getWidth(), getHeight()  - 100)), getGameFont());
			drawCenteredString(g, continueGame, new Rectangle(new Dimension(getWidth(), getHeight()  + 100)), getGameFont());
		}
		
		// Win Text
		if (mainController.getGame().getSucces() && mainController.getGame().getGameFinished()) {
			drawCenteredString(g, youWon, new Rectangle(new Dimension(getWidth(), getHeight() - 200)), getGameFont());
			drawCenteredString(g, gameOver, new Rectangle(new Dimension(getWidth(), getHeight() - 100)), getGameFont());
			drawCenteredString(g, totalScore, new Rectangle(new Dimension(getWidth(), getHeight())), getGameFont());
			drawCenteredString(g, exitString, new Rectangle(new Dimension(getWidth(), getHeight() + 100)), getGameFont());
		}
	}

	
	// Returns Game Font to decrease the drawCenteredString methods parameter length.
	private Font getGameFont() {
		return new Font("Helvetica", Font.PLAIN, 32);
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
	public void update(Observable o, Object arg) {

	}
}
