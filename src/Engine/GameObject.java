package Engine;
import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 * Generic GameObject class, contains the GameObject's position and current image for rendering.
 * Also contains the default abstract functions loop, instantiate, destroy and defaultImage which must be implemented by any GameObject
 */
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
