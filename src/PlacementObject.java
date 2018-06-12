public class PlacementObject extends GameObject {	
	static PlacementObject placementObject = null;
	static TurrentType t = null;
	
	public PlacementObject(TurrentType t) {
		if(placementObject != null)
			Game.destroy(placementObject);
		
		placementObject = this;
		position = Input.mousePosition;
		
		switch(t) {
			case BasicTurrent:
				image = Image.toGray(BasicTurrent.defaultImage());
		    break;
			case AreaTurrent:
				image = Image.toGray(AreaTurrent.defaultImage());
				break;
			default:
				break;
		}
	}
	
	public void place() {
		switch(t) {
			case BasicTurrent:
				Game.instantiate(new BasicTurrent(Grid.snap(position)));
		    break;
			case AreaTurrent:
				Game.instantiate(new AreaTurrent(Grid.snap(position)));
				break;
			default:
				break;
		}
		
		if(!Input.shift)
			Game.destroy(this);
	}
	
	public void instantiate() {
		
	}
	
	public void loop() {
		position = Input.mousePosition;
	}
	
	public void destroy() {
		
	}
}
