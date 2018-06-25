package Engine;
import java.awt.Point;

import Enemies.*;

/**
 * This class handles enemy spawn, every time the spawn function runs, new enemies appear on the map, according to the time elapsed since the beggining of the game.
 */
public class Spawner {
	public static double difficulty;
	double basicEnemySpawn;
	double fastEnemySpawn;
	double bigEnemySpawn = 1;
	double hugeEnemySpawn = 1;
	
	public double timeElapsedCurve() {
		return (double)Game.elapsedTime/(double)(Game.elapsedTime+300);
	}
	
	public void spawn(Enemy enemy) {
		enemy.position = new Point(Game.WIDTH, Game.random.nextInt(Game.HEIGHT));
		Game.instantiate(enemy);
	}
	
	public void spawn() {
		difficulty = timeElapsedCurve();

		basicEnemySpawn += (0.3 + 0.7*difficulty) * Game.deltaTime;
		fastEnemySpawn += (Math.max(0, -0.1 + difficulty)) * Game.deltaTime;
		bigEnemySpawn += (Math.max(0, -0.1 + 0.3*difficulty)) * Game.deltaTime;
		hugeEnemySpawn += (Math.max(0, -0.1 + 0.2*difficulty)) * Game.deltaTime;
		
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
		
		if(hugeEnemySpawn > 1) {
			spawn(new BigEnemy());
			hugeEnemySpawn -= Game.random.nextDouble();
		}
	}
}
