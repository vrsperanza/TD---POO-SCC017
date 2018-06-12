import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Input implements MouseListener{
	Queue<Point> mousePresses = new LinkedList<Point>();
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mousePresses.add(new Point(e.getX(), e.getY()));
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	
	public void process(){
		while(!mousePresses.isEmpty()){
			Point p = mousePresses.poll();
			Game.gameObjects.add(new GameObject(p.x, p.y));
		}
	}
	
	
}
