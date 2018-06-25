package Enemies;
import java.awt.Color;
import java.awt.Graphics2D;

import Engine.Game;
import Engine.GameObject;
import Engine.Grid;
import Turrents.Turrent;

/**
 * Abstract class Enemy, contains health, speed, damage and reward information, as well as the default enemy behaviour
 */
public abstract class Enemy extends GameObject {
	public int health;
	int maxHealth;
	int speed;
	int damage;
	int value;
	
	public int seekRangeSquared = (6*Grid.size)*(6*Grid.size);
	public int harmRangeSquared = (2*Grid.size)*(2*Grid.size);
	double harmCoolDown = 1;
	private double currentHarmCoolDown = 0;
	private boolean inHarmRange = false;
	private Turrent target = null;

	Enemy(){
		image = defaultImage();
	}

	private double walkXAccum = 0;
	private double walkYAccum = 0;
	
	public void walkTo(Turrent turrent) {
		double dirX = turrent.position.x - position.x;
		double dirY = turrent.position.y - position.y;
		double sum = Math.abs(dirX) + Math.abs(dirY);
		dirX /= sum;
		dirY /= sum;
		
		walkXAccum += Game.deltaTime*speed*dirX;
		walkYAccum += Game.deltaTime*speed*dirY;
		
		position.x += (int)walkXAccum;
		position.y += (int)walkYAccum;
		
		walkXAccum -= (int)walkXAccum;
		walkYAccum -= (int)walkYAccum;
	}
	
	@Override
	public void loop() {
		if(health <= 0) {
			Game.destroy(this);
			return;
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
		
		if(!inHarmRange) {
			int closestDistanceSquared = Integer.MAX_VALUE;
			Turrent closestTurrent = null;
			for(Turrent turrent : Game.turrents) {
				if(turrent.distanceSquared(this) < closestDistanceSquared) {
					closestTurrent = turrent;
					closestDistanceSquared = turrent.distanceSquared(this);
				}
			}
			
			if(closestDistanceSquared < seekRangeSquared && closestDistanceSquared > harmRangeSquared) {
				walkTo(closestTurrent);
				
			} else if(closestDistanceSquared <= harmRangeSquared) {
				inHarmRange = true;
				currentHarmCoolDown = harmCoolDown;
				target = closestTurrent;
			} else {
				walkTo(Game.target);
			}
		}
		else {
			currentHarmCoolDown -= Game.deltaTime;
			if(currentHarmCoolDown <= 0 && inHarmRange) {
				currentHarmCoolDown += harmCoolDown;
				
				if(Game.turrents.contains(target)) {
					target.health -= damage;
				}
				else {
					target = null;
					inHarmRange = false;
				}
			}
		}
	}

	@Override
	public void instantiate() {
		Game.enemies.add(this);
	}

	@Override
	public void destroy() {
		Game.money += value;
		Game.enemies.remove(this);
	}
}
