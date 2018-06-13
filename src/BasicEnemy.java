public class BasicEnemy extends Enemy {
	private double currentWalkCoolDown = 0.01;
	private double currentHarmCoolDown = 1;
	
	private boolean inHarmRange = false;
	
	private Turrent target = null;
	
	
	BasicEnemy() {
		super();
	}

	double walkXAccum = 0;
	double walkYAccum = 0;
	
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
	
	public void loop() {
		super.loop();
		

		if(!inHarmRange) {
			currentWalkCoolDown -= Game.deltaTime;
			if(currentWalkCoolDown <= 0) {
				currentWalkCoolDown += walkCoolDown;
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
		}
		else {
			currentHarmCoolDown -= Game.deltaTime;
			if(currentHarmCoolDown <= 0 && inHarmRange) {
				currentHarmCoolDown += harmCoolDown;
				
				if(Game.turrents.contains(target)) {
					target.health -= damage;
					System.out.println(target.health);
				}
				else {
					target = null;
					inHarmRange = false;
				}
			}
		}
	}
}
