package Enemies;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

import Engine.Grid;


/**
 * BigEnemy is a enemy type, this class defines the enemy's its image, health, speed, damage and value information.
 * When instantiated, this class generates a BigEnemy
 */
public class BigEnemy extends Enemy {
	@Override
	public BufferedImage defaultImage() {		
		BufferedImage image = new BufferedImage((int)(1.5*Grid.size), (int)(1.5*Grid.size), BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g2d = image.createGraphics();
	    g2d.setColor(new Color(255, 100, 0));
	    g2d.fill(new Ellipse2D.Float(0,0,(int)(1.5*Grid.size),(int)(1.5*Grid.size)));
	    g2d.dispose();
	    return image;		
	}
	
	public BigEnemy() {
		super();
		health = 2000;
		maxHealth = health;
		speed = 50;
		damage = 100;
		value = 100;
		harmRangeSquared = (3*Grid.size)*(3*Grid.size);
	}

	public void loop() {
		super.loop();
	}
}