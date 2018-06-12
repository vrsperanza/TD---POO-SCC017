import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class AreaTurrent extends GameObject {
	double timeAlive = 0;
	
	public static BufferedImage defaultImage() {
		BufferedImage image = new BufferedImage(20, 20, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g2d = image.createGraphics();
	    g2d.setColor(Color.red);
	    g2d.fill(new Ellipse2D.Float(0, 0, 10, 10));
	    g2d.dispose();
	    return image;		
	}
	
	public AreaTurrent(Point position) {
		this.position = position;
		image = defaultImage();
	}
	
	public void instantiate() {
	    timeAlive = 0;
	}
	
	public void loop() {
		timeAlive += Game.deltaTime;
		Point mousePos = Input.mousePosition;
		if(timeAlive > 0.3 &&
				mousePos.x > position.x &&
				mousePos.x < position.x + image.getWidth() &&
				mousePos.y > position.y &&
				mousePos.y < position.y + image.getHeight()) {
			Game.destroy(this);
		}
	}
	
	public void destroy() {
		
	}
}
