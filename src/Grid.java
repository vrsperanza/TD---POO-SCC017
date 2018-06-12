import java.awt.Point;

public class Grid {
	public final static int size = 20;
	public static Point snap(Point p) {
		return new Point(p.x-p.x%size, p.y-p.y%size);
	}
	public static Point snap(int x, int y) {
		return new Point(x-x%size, y-y%size);
	}
	public static int snap(int val) {
		return val-val%size;
	}
	
	public static boolean intersect(Point a, Point b) {
		return (snap(a.x) == snap(b.x)) && (snap(a.y) == snap(b.y));
	}
}
