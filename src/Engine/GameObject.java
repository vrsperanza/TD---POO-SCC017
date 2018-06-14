package Engine;
import java.awt.Point;
import java.awt.image.BufferedImage;

public abstract class GameObject {
	public Point position;
	public BufferedImage image;

	public abstract BufferedImage defaultImage();
	
	public abstract void instantiate();
	
	public abstract void loop();
	
	public abstract void destroy();
	
	public int distanceSquared(GameObject other) {
		return (other.position.x-position.x)*(other.position.x-position.x) + (other.position.y-position.y)*(other.position.y-position.y);
	}
}
