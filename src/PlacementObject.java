import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class PlacementObject extends GameObject {	
	static PlacementObject placementObject = null;
	
	public PlacementObject(TurrentType t) {
		if(placementObject != null)
			Game.destroy(placementObject);
		
		placementObject = this;
		position = Input.mousePosition;
		
		switch(t) {
			case BasicTurrent:
				image = new BufferedImage(25, 25, BufferedImage.TYPE_3BYTE_BGR);
				Graphics2D g2d = image.createGraphics();
		
			    g2d.setColor(Color.gray);
			    g2d.fill(new Ellipse2D.Float(0, 0, 25, 25));
			    g2d.dispose();
		    break;
			case AreaTurrent:
				break;
			default:
				break;
		}
	}
	
	public void instantiate() {
		
	}
	
	public void loop() {
		position = Input.mousePosition;
	}
	
	public void destroy() {
		
	}
}
