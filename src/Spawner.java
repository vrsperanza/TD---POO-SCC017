import java.awt.Point;
import java.util.Random;

public class Spawner {
	double currentSpawnCoolDown = 1;
	Random random = new Random();
	public void spawn() {
		currentSpawnCoolDown -= Game.deltaTime;
		if(currentSpawnCoolDown <= 0) {
			currentSpawnCoolDown += 50/Math.sqrt(1+Game.elapsedTime);
			
			Enemy enemy = new BasicEnemy();
			enemy.position = new Point(Game.WIDTH, random.nextInt(Game.HEIGHT));
			Game.instantiate(enemy);
		}
	}
}
