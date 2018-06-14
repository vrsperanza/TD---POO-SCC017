package Interface;
import java.awt.Canvas;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.Queue;

import Engine.Game;
import Engine.ObjectType;

public class Input implements MouseListener, KeyListener{
	Queue<MouseEvent> mousePresses = new LinkedList<MouseEvent>();
	Queue<KeyEvent> keyPresses = new LinkedList<KeyEvent>();
	Queue<KeyEvent> keyReleases = new LinkedList<KeyEvent>();
	public static Point canvasPosition;
	public static Point mousePosition;

	public static boolean shift = false;
	public static boolean mousePress = false;
	public static boolean rightMousePress = false;
	public static Canvas canvas;
	public Input(Canvas canvas){
		Input.canvas = canvas;
	}
	
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
	
	@Override
	public void keyPressed(KeyEvent e) {
		keyPresses.add(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keyReleases.add(e);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	public void process(){
		mousePosition = MousePosition();
		mousePress = false;
		rightMousePress = false;
		while(!mousePresses.isEmpty()){
			MouseEvent e = mousePresses.poll();
			switch(e.getButton()){
				case MouseEvent.BUTTON1:
					mousePress = true;
				break;
				case MouseEvent.BUTTON3:
					rightMousePress = true;
				break;
			}
		}

		while(!keyPresses.isEmpty()){
			KeyEvent e = keyPresses.poll();
			switch(e.getKeyCode()){
				case KeyEvent.VK_SHIFT:
					shift = true;
				break;

				case KeyEvent.VK_UP:
					Game.speed *= 1.1;
					System.out.println("Speed: " + Game.speed);
				break;
				case KeyEvent.VK_DOWN:
					Game.speed /= 1.1;
					System.out.println("Speed: " + Game.speed);
				break;

				case KeyEvent.VK_1:
					Game.instantiate(new PlacementObject(ObjectType.BasicTurrent));
				break;
				case KeyEvent.VK_2:
					Game.instantiate(new PlacementObject(ObjectType.AreaTurrent));
				break;
				case KeyEvent.VK_3:
					Game.instantiate(new PlacementObject(ObjectType.BarricadeTurrent));
				break;

				case KeyEvent.VK_SPACE:
					Game.start();
				break;

				case KeyEvent.VK_P:
					double temp = Game.speed;
					Game.speed = Game.pauseSpeed;
					Game.pauseSpeed = temp;
				break;
				
				case KeyEvent.VK_F1:
					Game.money += 100;
				break;
				
				case KeyEvent.VK_F2:
					Game.elapsedTime += 60;
				break;
			}
		}
		
		while(!keyReleases.isEmpty()){
			KeyEvent e = keyReleases.poll();
			switch(e.getKeyCode()){
				case KeyEvent.VK_SHIFT:
					shift = false;
				break;
			}
		}
	}
	
	private static Point MousePosition() {
		Point mousePosition = MouseInfo.getPointerInfo().getLocation();
		Point canvasPosition = canvas.getLocationOnScreen();
		mousePosition.x -= canvasPosition.x;
		mousePosition.y -= canvasPosition.y;
		return mousePosition;
	}

}
