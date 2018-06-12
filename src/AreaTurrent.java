import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class AreaTurrent extends GameObject {
	float rangeSquared = (4*Grid.size)*(4*Grid.size);
	double coolDown = 1;
	int damage = 10;
	
	private double currentCoolDown = 1;
	
	public static BufferedImage defaultImage() {
		BufferedImage image = new BufferedImage(Grid.size, Grid.size, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g2d = image.createGraphics();
	    g2d.setColor(Color.blue);
	    g2d.fill(new Ellipse2D.Float(0,0,Grid.size,Grid.size));
	    g2d.dispose();
	    return image;		
	}
	
	public AreaTurrent(Point position) {
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
			for(Enemy enemy : Game.enemies) {
				if(enemy.distanceSquared(this) < rangeSquared) {
					enemy.health -= damage;
				}
			}
		}
	}
}
