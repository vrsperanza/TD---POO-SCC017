import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class BarricadeTurrent extends Turrent {	

	public BarricadeTurrent() {
		image = defaultImage();

		health = 300;
		damage = 0;
		coolDown = 1;
		maxHealth = health;
		regenPerSecond = 2;
		rangeSquared = (4*Grid.size)*(4*Grid.size);
	}
	
	@Override
	public BufferedImage defaultImage() {
		BufferedImage image = new BufferedImage(Grid.size, Grid.size, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g2d = image.createGraphics();
	    g2d.setColor(Color.CYAN);
	    g2d.fillRect(1, 1, Grid.size-3, Grid.size-3);
	    g2d.dispose();
	    return image;		
	}
	

	@Override
	public void loop() {
		super.loop();
	}
}
