import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class GameObject {
	public Point position;
	public BufferedImage image;
	public GameObject(int px, int py){
		position = new Point(px, py);
		image = new BufferedImage(50, 50, BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D g2d = image.createGraphics();

	    g2d.setColor(Color.red);
	    g2d.fill(new Ellipse2D.Float(0, 0, 200, 100));
	    g2d.dispose();
	}
	
	public void loop(){
		
	}
}
