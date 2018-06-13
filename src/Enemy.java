import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public abstract class Enemy extends GameObject {
	int health;
	int speed;
	int damage;
	int value;
	
	public int seekRangeSquared = (6*Grid.size)*(6*Grid.size);
	public int harmRangeSquared = (2*Grid.size)*(2*Grid.size);
	double harmCoolDown = 1;
	private double currentHarmCoolDown = 0;
	private boolean inHarmRange = false;
	private Turrent target = null;
	
	Graphics2D imageGraphics;

	Enemy(){
		image = defaultImage();
		imageGraphics = image.createGraphics();
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

		imageGraphics.setColor(new Color(255*health/100, 100, 0));
		imageGraphics.fill(new Ellipse2D.Float(4,4,12,12));
		
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
		System.out.println("Money: " + Game.money);
		Game.enemies.remove(this);
	}
}
