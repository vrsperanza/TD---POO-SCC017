import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.HashSet;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Game implements Runnable{
	static HashSet<GameObject> gameObjects;
	
	static Input input;
	
	static double deltaTime = 0;
	
	final int WIDTH = 1000;
	final int HEIGHT = 700;
	
	JFrame frame;
	Canvas canvas;
	BufferStrategy bufferStrategy;
	
	public Game(){
		frame = new JFrame("Basic Game");
		
		JPanel panel = (JPanel) frame.getContentPane();
		panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		panel.setLayout(null);
		
		canvas = new Canvas();
		canvas.setBounds(0, 0, WIDTH, HEIGHT);
		canvas.setIgnoreRepaint(true);
		input = new Input();
		canvas.addMouseListener(input);
		
		panel.add(canvas);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
		
		canvas.createBufferStrategy(2);
		bufferStrategy = canvas.getBufferStrategy();
		
		canvas.requestFocus();
	}
	
	public void run(){
		long lastLoopTime = System.nanoTime();
		
		
		while(true){
			long currLoopTime = System.nanoTime();
			deltaTime = (currLoopTime - lastLoopTime);;
			lastLoopTime = currLoopTime;
			
			input.process();
			render();
			for(GameObject object : gameObjects)
				object.loop();
		}
	}
	
	private void render() {
		Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		g.clearRect(0, 0, WIDTH, HEIGHT);
		render(g);
		g.dispose();
		bufferStrategy.show();
	}
	
	
	protected void render(Graphics2D g){
		for(GameObject object : gameObjects)
			g.drawImage(object.image, null, object.position.x, object.position.y);
	}
	
	public static void main(String [] args){
		gameObjects = new HashSet<GameObject>();
		Game game = new Game();
		game.run();
	}

}