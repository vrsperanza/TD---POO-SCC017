import java.awt.Point;

public class Spawner {
	double currentSpawnCoolDown = 1;
	public void spawn() {
		currentSpawnCoolDown -= Game.deltaTime;
		if(currentSpawnCoolDown <= 0) {
			currentSpawnCoolDown += 20/Math.sqrt(1+Game.elapsedTime);
			
			Enemy enemy = new BasicEnemy();
			enemy.position = new Point(Game.WIDTH, Game.random.nextInt(Game.HEIGHT));
			Game.instantiate(enemy);
		}
	}
}
