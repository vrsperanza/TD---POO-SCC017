public class PlacementObject extends GameObject {	
	static PlacementObject placementObject = null;
	static ObjectType objectType = null;
	
	public PlacementObject(ObjectType objectType) {
		if(placementObject != null)
			Game.destroy(placementObject);
		
		PlacementObject.objectType = objectType;
		placementObject = this;
		position = Input.mousePosition;
		
		switch(objectType) {
			case BasicTurrent:
				image = Image.toGray(BasicTurrent.defaultImage());
		    break;
			case AreaTurrent:
				image = Image.toGray(AreaTurrent.defaultImage());
				break;
			case BarricadeTurrent:
				image = Image.toGray(BarricadeTurrent.defaultImage());
				break;
			case Enemy:
				image = Image.toGray(BasicEnemy.defaultImage());
				break;
			default:
				break;
		}
	}
	
	public void place() {
		for(GameObject g : Game.gameObjects) {
			if(g != this && Grid.intersect(position, g.position)) {
				return;
			}
		}
		
		switch(objectType) {
			case BasicTurrent:
				Game.instantiate(new BasicTurrent(Grid.snap(position)));
		    break;
			case AreaTurrent:
				Game.instantiate(new AreaTurrent(Grid.snap(position)));
				break;
			case BarricadeTurrent:
				Game.instantiate(new BarricadeTurrent(Grid.snap(position)));
				break;
			case Enemy:
				Game.instantiate(new BasicEnemy(Grid.snap(position)));
				break;
			default:
				System.out.println("ObjectType: " + objectType + " is not mapped inside PlacementObject.java");
				break;
		}
		
		if(!Input.shift)
			Game.destroy(this);
	}
	
	public void instantiate() {
		
	}
	
	public void loop() {
		position = Grid.snap(Input.mousePosition);
		if(Input.mousePress)
			place();
		if(Input.rightMousePress) {
			Game.destroy(this);
			placementObject = null;
		}
	}
	
	public void destroy() {
		
	}
}
