import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.Queue;

public class Input implements MouseListener{
	Queue<MouseEvent> mousePresses = new LinkedList<MouseEvent>();
	public static Point canvasPosition;
	public static Point mousePosition;
	
	public static boolean shift = false;
	
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
		mousePresses.add(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	
	public void process(){
		mousePosition = MousePosition();
		while(!mousePresses.isEmpty()){
			MouseEvent e = mousePresses.poll();
			if(e.getButton() == MouseEvent.BUTTON1)
				Game.instantiate(new BasicTurrent(Grid.snap(e.getPoint())));
			else
				Game.instantiate(new PlacementObject(TurrentType.BasicTurrent));
		}
	}
	
	private static Point MousePosition() {
		Point mousePosition = MouseInfo.getPointerInfo().getLocation();
		mousePosition.x -= canvasPosition.x;
		mousePosition.y -= canvasPosition.y;
		return mousePosition;
	}
}
