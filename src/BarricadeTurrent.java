import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class BarricadeTurrent extends Turrent {	
	int maxHealth;
	double healthAddAccum;
	double regenPerSecond;
	
	public static BufferedImage defaultImage() {
		BufferedImage image = new BufferedImage(Grid.size, Grid.size, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g2d = image.createGraphics();
	    g2d.setColor(Color.CYAN);
	    g2d.fillRect(1, 1, Grid.size-2, Grid.size-2);
	    g2d.dispose();
	    return image;		
	}
	
	public BarricadeTurrent(Point position) {
		this.position = position;
		image = defaultImage();

		health = 300;
		damage = 0;
		coolDown = 1;
		maxHealth = health;
		healthAddAccum = 0;
		regenPerSecond = 2;
		rangeSquared = (4*Grid.size)*(4*Grid.size);
	}
	
	public void loop() {
		super.loop();
		
		healthAddAccum += regenPerSecond*Game.deltaTime;
		health += (int)healthAddAccum;
		healthAddAccum -= (int)healthAddAccum;
		health = Math.min(health, maxHealth);
	}
}
