package Turrents;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

import Enemies.Enemy;
import Engine.Game;
import Engine.Grid;

/**
 * AreaTurrent is a turrent type, this class defines the turrent's behaviour, image, health, speed, damage and value information.
 * When instantiated, this class generates a AreaTurrent
 */
public class AreaTurrent extends Turrent {	
	private double currentCoolDown = 1;

	public AreaTurrent() {
		image = defaultImage();

		health = 200;
		damage = 10;
		coolDown = 0.5;
		maxHealth = 200;
		regenPerSecond = 2;
		cost = 300;
		
		rangeSquared = (5*Grid.size)*(5*Grid.size);
	}
	
	@Override
	public BufferedImage defaultImage() {
		int marginX = 1;
		int marginY = 1;
		
		BufferedImage image = new BufferedImage(Grid.size, Grid.size, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g2d = image.createGraphics();
	    g2d.setColor(Color.blue);
	    g2d.fill(new Ellipse2D.Float(marginX,marginY,Grid.size-marginX-marginX-1,Grid.size-marginY-marginY-1));
	    g2d.dispose();
	    return image;		
	}
	
	@Override
	public void loop() {
		super.loop();
		currentCoolDown -= Game.deltaTime;
		
		if(currentCoolDown <= 0) {
			currentCoolDown += coolDown;
			for(Enemy enemy : Game.enemies) {
				if(enemy.distanceSquared(this) < rangeSquared) {
					enemy.health -= damage;
				}
			}
		}
	}
}
