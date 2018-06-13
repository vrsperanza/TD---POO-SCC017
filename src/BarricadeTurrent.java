import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class BarricadeTurrent extends Turrent {	

	public BarricadeTurrent() {
		image = defaultImage();

		health = 300;
		damage = 10;
		coolDown = 1;
		maxHealth = 100;
		regenPerSecond = 3;
		cost = 100;	
	}
	
	@Override
	public BufferedImage defaultImage() {
		BufferedImage image = new BufferedImage(Grid.size, Grid.size, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g2d = image.createGraphics();
	    g2d.setColor(Color.LIGHT_GRAY);
	    g2d.fillRect(1, 1, Grid.size-2, Grid.size-2);
	    g2d.dispose();
	    return image;		
	}
	

	@Override
	public void loop() {
		super.loop();
	}
}
