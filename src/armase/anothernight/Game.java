package armase.anothernight;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.IOException;

import armase.anothernight.display.Display;
import armase.anothernight.gfx.Assets;
import armase.anothernight.gfx.BackdropManager;
import armase.anothernight.input.KeyManager;
import armase.anothernight.input.MouseManager;
import armase.anothernight.properties.Props;
import armase.anothernight.scobo.ScoboManager;
import armase.anothernight.states.StartupState;
import armase.anothernight.states.State;
import armase.anothernight.utils.Utils;

public class Game implements Runnable {
	private Display display;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	private KeyManager keyManager;
	private MouseManager mouseManager;
	private BackdropManager backdropManager;
	private Handler handler;

	public String title;
	private int width, height;
		
	public Game(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
		
		// Works in IDE but not in JAR, leave for reference
//		try {
//			new AudioPlayer();
//		} catch (UnsupportedAudioFileException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (LineUnavailableException e) {
//			e.printStackTrace();
//		}
	}
	
	private void init() {
		Utils.createGameFolder();
		
		try {
			ScoboManager.initScobo();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		// MouseListener on frame & canvas prevents problems in different environments
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		
		Assets.init();
		handler = new Handler(this);
		backdropManager = new BackdropManager(handler);
		
		State.setState(new StartupState(handler));
	}
	
	public void run() {
		init();
		
		int fps = Props.FPS; // tick&renders per second
		double timePerTick = 1_000_000_000 / fps; // max time given to execute tick&renders to achieve fps
		double delta = 0; // tick&renders to do to keep the pace
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0; // for fps counter output
		
		while(running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1) {
				tick();
				render();
				ticks++;
				delta--;
			}
			
			if(timer >= 1_000_000_000) {
//				System.out.println("Ticks & Frames: " + ticks);
				// TODO : devtool - graphical fps counter
				ticks = 0;
				timer = 0;
			}
		}
		
		stop();
	}
	
	private void tick() {
		keyManager.tick();
		
		if(State.getState() != null)
			State.getState().tick();
	}
	
	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		
		if(bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		
		g = bs.getDrawGraphics();
		g.clearRect(0, 0, width, height); // Clear Screen
		
		// Draw Stuff
		if(State.getState() != null)
			State.getState().render(g);
		
		// End Drawing!
		bs.show();
		g.dispose();
	}
	
	public synchronized void start() {
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start(); // calls this.run()
	}
	
	public synchronized void stop() {
		if(!running)
			return;
		
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	// ### GETTERS & SETTERS
	
	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	public MouseManager getMouseManager() {
		return mouseManager;
	}

	public BackdropManager getBackdropManager() {
		return backdropManager;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}
