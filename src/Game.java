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


public class Game{
	static HashSet<Enemy> enemies;
	static HashSet<Turrent> turrents;
	static Target target;
	static Spawner spawner;
	
	static HashSet<GameObject> gameObjects;
	static Queue<GameObject> toDestroy;
	static Queue<GameObject> toInstantiate;
	static LinkedList<Button> buttons;
	
	static Input input;
	static Random random;

	static double elapsedTime;
	static double deltaTime;
	static double speed;
	
	final static int WIDTH = 1000;
	final static int HEIGHT = 700;
	
	static JFrame frame;
	static Canvas canvas;
	static BufferStrategy bufferStrategy;
	
	static int money;
	
	public Game(){
		random = new Random();
		frame = new JFrame("Tower defence");
		
		JPanel panel = (JPanel) frame.getContentPane();
		panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
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
	
	public static void instantiate(GameObject g) {
		toInstantiate.add(g);
	}
	
	public static void destroy(GameObject g) {
		toDestroy.add(g);
	}
	
	private static void render() {
		Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		
		for(GameObject object : gameObjects)
			g.drawImage(object.image, null, object.position.x, object.position.y);
		for(Button button : buttons)
			g.drawImage(button.image, null, button.position.x, button.position.y);

		g.setColor(Color.YELLOW);
		g.drawString(String.valueOf(money), WIDTH-50, 25);
		
		g.dispose();
		bufferStrategy.show();
	}

	public static void over() {
		System.out.println("GAME OVER");
		speed = 0;
		System.out.println("Aperte espaço para reiniciar");
	}
	
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