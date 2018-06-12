import java.awt.Point;

public class Grid {
	public final static int size = 20;
	public static Point snap(Point p) {
		return new Point(p.x-p.x%size, p.y-p.y%size);
	}
	public static int snap(int val) {
		return val-val%size;
	}
}
