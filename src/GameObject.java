import java.awt.Point;
import java.awt.image.BufferedImage;

public abstract class GameObject {
	public Point position;
	public BufferedImage image;

	public static BufferedImage defaultImage() { return null; }
	
	public void instantiate() { }
	
	public void loop(){ }
	
	public void destroy(){ }
}
