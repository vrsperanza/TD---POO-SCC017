package Interface;
import java.awt.image.BufferedImage;

import Engine.*;
import Turrents.*;

/**
 * GameObject that follows the mouse in order to orient turrent placement.
 * Once the place function is run this object disappears and places its respective turrent in its position, updating the player's money in the process
 */
public class PlacementObject extends GameObject {	
	static PlacementObject placementObject = null;
	static ObjectType objectType = null;
	static GameObject toPlace = null;
	
	public PlacementObject(ObjectType objectType) {
		if(placementObject != null)
			Game.destroy(placementObject);
		
		PlacementObject.objectType = objectType;
		placementObject = this;
		position = Input.mousePosition;
		
		switch(objectType) {
			case BasicTurrent:
				toPlace = new BasicTurrent();
				break;
			case AreaTurrent:
				toPlace = new AreaTurrent();
				break;
			case BarricadeTurrent:
				toPlace = new BarricadeTurrent();
				break;
			default:
				break;
		}
		image = Image.toGray(toPlace.defaultImage());
	}
	
	public void place() {
		for(GameObject g : Game.gameObjects)
			if(g != this && Grid.intersect(position, g.position))
				return;
				
		toPlace.position = Grid.snap(position);
		
		
		if(toPlace instanceof Turrent) {
			Turrent turrent = (Turrent)toPlace;
			if(Game.money < turrent.cost)
				return;
			
			Game.money -= turrent.cost;
		}
		
		Game.instantiate(toPlace);
		
		Game.destroy(this);
		if(Input.shift)
			Game.instantiate(new PlacementObject(objectType));
	}

	@Override
	public void instantiate() {
		
	}

	@Override
	public void loop() {
		position = Grid.snap(Input.mousePosition);
		if(Input.mousePress)
			place();
		if(Input.rightMousePress) {
			Game.destroy(this);
			placementObject = null;
		}
	}

	@Override
	public void destroy() {
		
	}

	@Override
	public BufferedImage defaultImage() {
		// TODO Auto-generated method stub
		return null;
	}
}
