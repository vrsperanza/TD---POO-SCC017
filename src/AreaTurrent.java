import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class AreaTurrent extends Turrent {	
	private double currentCoolDown = 1;

	public AreaTurrent() {
		image = defaultImage();

		health = 100;
		damage = 10;
		coolDown = 1;
		maxHealth = 100;
		regenPerSecond = 1;
		
		rangeSquared = (4*Grid.size)*(4*Grid.size);
		
		cost = 200;
	}
	
	@Override
	public BufferedImage defaultImage() {
		BufferedImage image = new BufferedImage(Grid.size, Grid.size, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g2d = image.createGraphics();
	    g2d.setColor(Color.blue);
	    g2d.fill(new Ellipse2D.Float(0,0,Grid.size,Grid.size));
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
