import java.awt.Point;

public class Spawner {
	double basicEnemyRate;
	double fastEnemyRate;
	double bigEnemyRate;
	
	double basicEnemySpawn;
	double fastEnemySpawn;
	double bigEnemySpawn;
	
	public float timeElapsedCurve() {
		return (float)Game.elapsedTime/(float)(Game.elapsedTime+300);
	}
	
	public void spawn(Enemy enemy) {
		enemy.position = new Point(Game.WIDTH, Game.random.nextInt(Game.HEIGHT));
		Game.instantiate(enemy);
	}
	
	public void spawn() {
		float difficulty = timeElapsedCurve();

		System.out.println(difficulty);
		
		basicEnemyRate = 0.3 + 0.2*difficulty;
		fastEnemyRate = 0.5*difficulty;
		bigEnemyRate = -0.1 + 0.3*difficulty;

		basicEnemySpawn += basicEnemyRate * Game.deltaTime;
		fastEnemySpawn += fastEnemyRate * Game.deltaTime;
		bigEnemySpawn += bigEnemyRate * Game.deltaTime;
		
		if(basicEnemySpawn > 1) {
			spawn(new BasicEnemy());
			basicEnemySpawn -= Game.random.nextDouble();
		}

		if(fastEnemySpawn > 1) {
			spawn(new FastEnemy());
			fastEnemySpawn -= Game.random.nextDouble();
		}
		
		if(bigEnemySpawn > 1) {
			spawn(new BigEnemy());
			bigEnemySpawn -= Game.random.nextDouble();
		}
	}
}
