import java.awt.Point;

public abstract class Turrent extends GameObject{
	float rangeSquared;
	double coolDown;
	int damage;
	int health;
	
	public void loop() {
		if(health <= 0)
			Game.destroy(this);
		
		if(Input.rightMousePress) {
			Point mousePos = Input.mousePosition;
			if( mousePos.x > position.x &&
				mousePos.x < position.x + image.getWidth() &&
				mousePos.y > position.y &&
				mousePos.y < position.y + image.getHeight()) {
				Game.destroy(this);
			}
		}
	}
	
	public void instantiate() {
		Game.turrents.add(this);
	}
	
	public void destroy() {
		Game.turrents.remove(this);
	}
}
