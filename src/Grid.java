import java.awt.Point;

public class Grid {
	final static int gridSize = 20;
	public static Point snap(Point p) {
		return new Point(p.x-p.x%gridSize, p.y-p.y%gridSize);
	}
	public static int snap(int val) {
		return val-val%gridSize;
	}
}
