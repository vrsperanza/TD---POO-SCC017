import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class BasicTurrent extends Turrent {
	
	private double currentCoolDown = 1;

	public BasicTurrent() {
		image = defaultImage();
		
		health = 100;
		maxHealth = health;
		regenPerSecond = 1;
		damage = 15;
		coolDown = 1;
		rangeSquared = (6*Grid.size)*(6*Grid.size);
		cost = 50;
	}
	
	@Override
	public BufferedImage defaultImage() {
		BufferedImage image = new BufferedImage(Grid.size, Grid.size, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g2d = image.createGraphics();
	    g2d.setColor(Color.yellow);
	    g2d.fill(new Ellipse2D.Float(0, 0, Grid.size, Grid.size));
	    g2d.dispose();
	    return image;		
	}
	
	@Override
	public void loop() {
		super.loop();
		currentCoolDown -= Game.deltaTime;
		
		if(currentCoolDown <= 0) {
			currentCoolDown += coolDown;
			int closestDistanceSquared = Integer.MAX_VALUE;
			Enemy closestEnemy = null;
			for(Enemy enemy : Game.enemies) {
				if(enemy.distanceSquared(this) < closestDistanceSquared) {
					closestEnemy = enemy;
					closestDistanceSquared = enemy.distanceSquared(this);
				}
			}
			
			if(closestDistanceSquared < rangeSquared)
				closestEnemy.health -= damage;
		}
	}
}
