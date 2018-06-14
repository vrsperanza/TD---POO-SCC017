package Interface;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

import Engine.Game;
import Engine.GameObject;
import Engine.ObjectType;

public class Button extends GameObject {
	int id;
	public Point position;
	public BufferedImage image;
	public boolean pressed = false;
	boolean trigger = false;

	public Button(Point position, int id) {
		this.position = position;
		this.id = id;
		image = defaultImage();
	}
	
	@Override
	public BufferedImage defaultImage() {
		BufferedImage image = new BufferedImage(50, 30, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g2d = image.createGraphics();
	    g2d.setColor(Color.LIGHT_GRAY);
	    g2d.fillRect(0, 0, 50,  30);
	    g2d.dispose();
	    return image;		
	}

	@Override
	public void loop() {
		Point mousePos = Input.mousePosition;
		pressed = trigger;
		trigger = false;
		if(Input.mousePress &&
			mousePos.x > position.x &&
			mousePos.x < position.x + image.getWidth() &&
			mousePos.y > position.y &&
			mousePos.y < position.y + image.getHeight()) {
			pressed = true;
		}
		
		if(pressed) {
			switch(id) {
				case 0:
					Game.instantiate(new PlacementObject(ObjectType.BasicTurrent));
					break;
				case 1:
					Game.instantiate(new PlacementObject(ObjectType.AreaTurrent));
					break;
				case 2:
					Game.instantiate(new PlacementObject(ObjectType.BarricadeTurrent));
					break;
				default:
					System.out.println("Button ID: " + id + " is not mapped inside Button.java");
					break;
			}
		}
	}

	@Override
	public void instantiate() {
		
	}

	@Override
	public void destroy() {
		
	}
}
