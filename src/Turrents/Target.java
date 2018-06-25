package Turrents;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

import Engine.Game;
import Engine.Grid;

/**
 * Target is a turrent type, this class defines the turrent's behaviour, image, health, speed, damage and value information.
 * When instantiated, this class generates a Target
 * 
 * For the game to run there must be a Target present for the enemies to seek when nothing else is in range,
 * this is the enemies's ultimate goal and is what must be protected.
 */
public class Target extends Turrent {	
	public BufferedImage defaultImage() {
		BufferedImage image = new BufferedImage(Grid.size, Grid.size, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g2d = image.createGraphics();
	    g2d.setColor(Color.white);
	    g2d.fill(new Ellipse2D.Float(3,3,Grid.size-7,Grid.size-7));
	    g2d.dispose();
	    return image;		
	}
	
	public Target() {
		image = defaultImage();
		
		health = 100;
		maxHealth = health;
		regenPerSecond = 1;
	}
	
	public void loop() {
		if(health <= 0)
			Game.over();
		super.loop();
	}
}