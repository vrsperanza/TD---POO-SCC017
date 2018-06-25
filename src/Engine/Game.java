package Engine;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferStrategy;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Enemies.*;
import Interface.*;
import Turrents.*;

/**
 * Main execution class, knows all enemies, turrents and gameObjects.
 * Handles the execution loop and rendering of all GameObjects
 */
public class Game{
	public static HashSet<Enemy> enemies;
	public static HashSet<Turrent> turrents;
	public static Target target;
	public static HashSet<GameObject> gameObjects;
	
	static Spawner spawner;
	
	static Queue<GameObject> toDestroy;
	static Queue<GameObject> toInstantiate;
	static LinkedList<Button> buttons;
	
	static Input input;
	static Random random;

	public static double elapsedTime;
	public static double deltaTime;
	public static double pauseSpeed;
	public static double speed;
	
	final static int WIDTH = 1000;
	final static int HEIGHT = 700;
	
	static JFrame frame;
	static Canvas canvas;
	static BufferStrategy bufferStrategy;
	
	public static int money;
	
	/**
	 * Generates the game's frame and canvas.
	 *  
	 */
	public Game(){
		random = new Random();
		frame = new JFrame("Tower defence");
		
		JPanel panel = (JPanel) frame.getContentPane();
		panel.setPreferredSize(new Dimension(WIDTH-10, HEIGHT-10));
		panel.setLayout(null);
		
		canvas = new Canvas();
		canvas.setBounds(0, 0, WIDTH, HEIGHT);
		canvas.setIgnoreRepaint(true);
		input = new Input(canvas);
		canvas.addMouseListener(input);
		canvas.addKeyListener(input);
		
		panel.add(canvas);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
		
		canvas.createBufferStrategy(2);
		bufferStrategy = canvas.getBufferStrategy();
		canvas.requestFocus();
	}
	
	/**
	 * Runs the game loop, at each iteration, processes the input, enemy spawns, loops all objects and buttons,
	 * destroys and instantiates all prepared objects then restarts loop.
	 */
	public static void run(){
		long lastLoopTime = System.nanoTime();
		while(true){			
			long currLoopTime = System.nanoTime();
			deltaTime = (currLoopTime - lastLoopTime) * 1e-9;
			deltaTime *= speed;
			elapsedTime += deltaTime;
			
			lastLoopTime = currLoopTime;
			
			
			input.process();
			render();

			spawner.spawn();
			
			for(GameObject button : buttons)
				button.loop();
			
			for(GameObject object : gameObjects)
				object.loop();
			
			while(!toDestroy.isEmpty()) {
				GameObject g = toDestroy.poll();
				gameObjects.remove(g);
				g.destroy();
			}
			
			while(!toInstantiate.isEmpty()) {
				GameObject g = toInstantiate.poll();
				gameObjects.add(g);
				g.instantiate();
			}
		}
	}
	
	// Prepares the given GameObject for instantiation
	public static void instantiate(GameObject g) {
		toInstantiate.add(g);
	}
	
	// Prepares the given GameObject for destruction
	public static void destroy(GameObject g) {
		toDestroy.add(g);
	}
	
	// Renders all gameObjects on screen, as well as displaying money, speed and difficulty texts
	private static void render() {
		Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		
		for(GameObject object : gameObjects)
			g.drawImage(object.image, null, object.position.x, object.position.y);
		for(Button button : buttons)
			g.drawImage(button.image, null, button.position.x, button.position.y);

		g.setColor(Color.YELLOW);
		g.drawString("   Money: " + String.valueOf(money), WIDTH-100, 25);
		g.drawString("   Speed: " + String.format("%.02f", speed), WIDTH-100, 50);
		g.drawString("Difficulty: " + String.format("%.02f", Spawner.difficulty), WIDTH-100, 75);
		
		g.dispose();
		bufferStrategy.show();
	}

	// Stops the game for game over.
	public static void over() {
		System.out.println("GAME OVER");
		speed = 0;
		System.out.println("Aperte espaço para reiniciar");
	}
	
	// Starts a new game
	public static void start() {
		elapsedTime = 0;
		deltaTime = 0;
		speed = 1;
		
		enemies = new HashSet<Enemy>();
		turrents = new HashSet<Turrent>();
		gameObjects = new HashSet<GameObject>();
		toDestroy = new LinkedList<GameObject>();
		toInstantiate = new LinkedList<GameObject>();
		buttons = new LinkedList<Button>();
		
		buttons.add(new Button(new Point(20, 20), 0));
		buttons.add(new Button(new Point(90, 20), 1));
		buttons.add(new Button(new Point(160, 20), 2));
		target = new Target();
		target.position = Grid.snap(20, HEIGHT/2);
		instantiate(target);
		
		spawner = new Spawner();
		
		money = 1000;
		
		run();
	}
	
	public static void main(String [] args){
		new Game();
		Game.start();
	}
	
}