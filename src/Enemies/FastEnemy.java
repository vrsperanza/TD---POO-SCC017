package Enemies;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

import Engine.Grid;

public class FastEnemy extends Enemy {
	@Override
	public BufferedImage defaultImage() {
		int marginX = 3;
		int marginY = 9;
		
		BufferedImage image = new BufferedImage(Grid.size, Grid.size, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g2d = image.createGraphics();
	    g2d.setColor(new Color(255, 100, 0));
	    g2d.fill(new Ellipse2D.Float(marginX,marginY,Grid.size-marginX-marginX-1,Grid.size-marginY-marginY-1));
	    g2d.dispose();
	    return image;		
	}
	
	public FastEnemy() {
		super();
		health = 100;
		maxHealth = health;
		speed = 600;
		damage = 3;
		value = 10;
	}

	public void loop() {
		super.loop();
	}
}