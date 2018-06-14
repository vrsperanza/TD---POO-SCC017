package Enemies;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

import Engine.Grid;

public class HugeEnemy extends Enemy {
	@Override
	public BufferedImage defaultImage() {		
		BufferedImage image = new BufferedImage(2*Grid.size, 2*Grid.size, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g2d = image.createGraphics();
	    g2d.setColor(new Color(255, 100, 0));
	    g2d.fill(new Ellipse2D.Float(0,0,2*Grid.size, 2*Grid.size));
	    g2d.dispose();
	    return image;		
	}
	
	public HugeEnemy() {
		super();
		health = 10000;
		maxHealth = health;
		speed = 30;
		damage = 250;
		value = 200;
		harmRangeSquared = (4*Grid.size)*(4*Grid.size);
	}

	public void loop() {
		super.loop();
	}
}