import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class BasicTurrent extends GameObject {
	float rangeSquared = (6*Grid.size)*(6*Grid.size);
	double coolDown = 1;
	int damage = 10;
	
	private double currentCoolDown = 1;
	
	public static BufferedImage defaultImage() {
		BufferedImage image = new BufferedImage(Grid.size, Grid.size, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g2d = image.createGraphics();
	    g2d.setColor(Color.yellow);
	    g2d.fill(new Ellipse2D.Float(0, 0, Grid.size, Grid.size));
	    g2d.dispose();
	    return image;		
	}
	
	public BasicTurrent(Point position) {
		this.position = position;
		image = defaultImage();
	}
	
	public void loop() {
		currentCoolDown -= Game.deltaTime;
		
		if(Input.rightMousePress) {
			Point mousePos = Input.mousePosition;
			if( mousePos.x > position.x &&
				mousePos.x < position.x + image.getWidth() &&
				mousePos.y > position.y &&
				mousePos.y < position.y + image.getHeight()) {
				Game.destroy(this);
			}
		}
		
		if(currentCoolDown <= 0) {
			currentCoolDown = coolDown;
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
