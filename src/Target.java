import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class Target extends Turrent {	
	public BufferedImage defaultImage() {
		BufferedImage image = new BufferedImage(Grid.size, Grid.size, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g2d = image.createGraphics();
	    g2d.setColor(Color.white);
	    g2d.fill(new Ellipse2D.Float(4,4,12,12));
	    g2d.dispose();
	    return image;		
	}
	
	public Target() {
		image = defaultImage();
		
		health = 100;
	}
	
	public void loop() {
		if(health <= 0) {
			Game.destroy(this);
			Game.over();
		}
	}
}