package Turrents;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import Engine.*;
import Interface.*;

/**
 * Abstract class Turrent, contains range, coolDown, damage, regeneration and cost information, as well as the default turrent behaviour.
 * Default turrents cost an amount to be placed and defend from nearby enemies, being destroyed once they suffered enough damage.
 */
public abstract class Turrent extends GameObject{
	public int health;
	
	float rangeSquared;
	double coolDown;
	int damage;
	int maxHealth;
	double regenPerSecond;
	public int cost;
	
	private double healthAddAccum;

	@Override
	public void loop() {
		if(health <= 0)
			Game.destroy(this);
		
		healthAddAccum += regenPerSecond*Game.deltaTime;
		health += (int)healthAddAccum;
		healthAddAccum -= (int)healthAddAccum;
		health = Math.min(health, maxHealth);
		
		if(Input.rightMousePress) {
			Point mousePos = Input.mousePosition;
			if( mousePos.x > position.x &&
				mousePos.x < position.x + image.getWidth() &&
				mousePos.y > position.y &&
				mousePos.y < position.y + image.getHeight()) {
				
				Game.money += cost/2;
				Game.destroy(this);
			}
		}

		image = this.defaultImage();
		if(health < maxHealth) {			
			Graphics2D graphics = image.createGraphics();

			graphics.setColor(Color.DARK_GRAY);
			graphics.fillRect(0, Grid.size-6, Grid.size, 5);
			
			graphics.setColor(new Color(0, 153, 0));
			graphics.fillRect(0, Grid.size-6, Grid.size * health/maxHealth, 5);
			
			graphics.dispose();
		}
	}

	@Override
	public void instantiate() {
		Game.turrents.add(this);
	}

	@Override
	public void destroy() {
		Game.turrents.remove(this);
	}
}
