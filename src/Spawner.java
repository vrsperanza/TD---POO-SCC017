import java.awt.Point;

public class Spawner {
	double currentSpawnCoolDown = 1;
	double basicEnemyRate;
	double fastEnemyRate;
	
	public float timeElapsedCurve() {
		return (float)Game.elapsedTime/(float)(Game.elapsedTime+300);
	}
	
	public void spawn(Enemy enemy) {
		enemy.position = new Point(Game.WIDTH, Game.random.nextInt(Game.HEIGHT));
		Game.instantiate(enemy);
	}
	
	public void spawn() {
		currentSpawnCoolDown -= Game.deltaTime;
		
		if(currentSpawnCoolDown <= 0) {
			currentSpawnCoolDown += 1;
			
			float difficulty = timeElapsedCurve();

			System.out.println(difficulty);
			
			basicEnemyRate = 0.5 + 0.3*difficulty;
			fastEnemyRate = 0.5*difficulty;
			
			if(Game.random.nextFloat() <= basicEnemyRate)
				spawn(new BasicEnemy());
			

			if(Game.random.nextFloat() <= fastEnemyRate)
				spawn(new FastEnemy());
		}
		
	}
}
