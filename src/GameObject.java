import java.awt.Point;
import java.awt.image.BufferedImage;

public abstract class GameObject {
	public Point position;
	public BufferedImage image;

	public static BufferedImage defaultImage() { return null; }
	
	public void instantiate() { }
	
	public void loop(){ }
	
	public void destroy(){ }
	
	public int distanceSquared(GameObject other) {
		return (other.position.x-position.x)*(other.position.x-position.x) + (other.position.y-position.y)*(other.position.y-position.y);
	}
}
