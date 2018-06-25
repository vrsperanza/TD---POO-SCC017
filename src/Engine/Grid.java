package Engine;
import java.awt.Point;

/**
 * Grid helper methods
 */
public class Grid {
	public final static int size = 20;
	
	// Snap the point p to grid with grid.size
	public static Point snap(Point p) {
		return new Point(p.x-p.x%size, p.y-p.y%size);
	}
	// Snap the coordinate pair x, y to grid with grid.size
	public static Point snap(int x, int y) {
		return new Point(x-x%size, y-y%size);
	}
	
	// Snap the value to grid with grid.size
	public static int snap(int val) {
		return val-val%size;
	}
	
	// Checks if the two points lie on the same grid spot
	public static boolean intersect(Point a, Point b) {
		return (snap(a.x) == snap(b.x)) && (snap(a.y) == snap(b.y));
	}
}
