package view;

import java.awt.Color;
import java.awt.Graphics;

import model.TagMan;

public class TagManPainterPlain {

	public void paint(Graphics g, TagMan tagMan) {
		int scale = 15 / 2;
		g.setColor(tagMan.getFirstCircleColour());
		g.fillOval((int) tagMan.getPoint().getX(), (int) tagMan.getPoint().getY(), (int) tagMan.getDimension().getWidth(), (int) tagMan.getDimension().getHeight());
		g.setColor(tagMan.getSecondCircleColour());
		g.fillOval((int) tagMan.getPoint().getX() + scale / 2, (int) tagMan.getPoint().getY() + scale / 2, (int) tagMan.getDimension().getWidth() - scale, (int) tagMan.getDimension().getHeight() - scale);
		scale *= 3;
		g.setColor(tagMan.getThirdCircleColour());
		g.fillOval((int) tagMan.getPoint().getX() + scale / 2, (int) tagMan.getPoint().getY() + scale / 2, (int) tagMan.getDimension().getWidth() - scale, (int) tagMan.getDimension().getHeight() - scale);
	}
}
