import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class BasicEnemy extends Enemy {
	@Override
	public BufferedImage defaultImage() {
		BufferedImage image = new BufferedImage(Grid.size, Grid.size, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g2d = image.createGraphics();
	    g2d.setColor(new Color(255, 100, 0));
	    g2d.fill(new Ellipse2D.Float(4,4,12,12));
	    g2d.dispose();
	    return image;		
	}
	
	BasicEnemy() {
		super();
		health = 100;
		speed = 50;
		damage = 5;
		value = 10;
	}

	public void loop() {
		super.loop();
	}
}