package armase.anothernight;

import armase.anothernight.entities.creatures.Creature;
import armase.anothernight.entities.creatures.Player;
import armase.anothernight.entities.creatures.enemies.Boss;
import armase.anothernight.entities.creatures.enemies.DangerDoggo;
import armase.anothernight.entities.creatures.enemies.GooeyGhost;
import armase.anothernight.entities.creatures.enemies.HiddenBug;
import armase.anothernight.entities.creatures.enemies.SneakySkeleton;
import armase.anothernight.ui.CLI;
import armase.anothernight.utils.Utils;

// TODO : This file is an example, use it as a template or delete it
// LEAVE IT UNTIL ANOTHER FILE ARRIVES IN THIS PACKAGE (Git is whiny)

public class Game implements Runnable {
	
	/* ### Reference for graphical design
	private Display display;
	private int width, height;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	// States - if possible revert to private
	public State gameState;
	public State menuState;
	
	// Input
	private KeyManager keyManager;
	
	// Handler
	private Handler handler;
	*/

	public String title;
	private final int enemyTypes = 3;
	private int nightCounter;
		
	public Game(String title, int width, int height) {
		this.title = title;
		
		/*
		this.width = width;
		this.height = height;
		keyManager = new KeyManager();
		*/
	}
	
	public void run() {
		// Console Version
		CLI.writeWelcomeMsg();
		CLI.writeRandomMotd();
		Utils.waitInMs(1000);
		
		while(true) {
			CLI.writeMainMenu();
			
			switch (CLI.readConsoleInput()) {
			case "play":
				play();
				break;
			case "scobo":
				CLI.writeScoreboard();
				CLI.enterToContinue();
				break;
			case "info":
				Utils.waitInMs(1000);
				CLI.writeGameInfo();
				Utils.waitInMs(1000);
				CLI.enterToContinue();
				break;
			case "quit":
				Utils.waitInMs(1000);
				CLI.writeExitMsg();
				Utils.waitInMs(1500);
				System.exit(0);
			default:
				CLI.writeBadInput();
				break;
			}
		}
	}

	private void play() {
		Creature player = new Player();
		nightCounter = 1;
		Creature enemy;
		
		Utils.waitInMs(1500);
		CLI.writeJourneyBeginning();
		
		while(player.isAlive() && nightCounter <= 10) {
			Utils.waitInMs(1500);
			CLI.writeNightCount(nightCounter);
			Utils.waitInMs(1500);
			
			if(nightCounter == 10)
				enemy = generateBoss();
			else
				enemy = generateRndEnemy();
			
			CLI.writeCreatureAppearance(enemy);
			
			if(randomEnemyGetsFirstAttack()) {
				CLI.writeEnemyGotFirstMove(enemy);
				CLI.writeCreatureAttacksCreature(enemy, player, enemy.dealDamageToOpponent(player));
			}
			
			while(enemy.isAlive() && player.isAlive()) {
				// TODO : revert buffs after fight
				
				Utils.waitInMs(1500);
				CLI.writeCreatureStatus(player);
				CLI.writeCreatureStatus(enemy);
				CLI.writeFightMenu();
				
				switch(CLI.readConsoleInput()) {
				case "atk":
					CLI.writeCreatureAttacksCreature(player, enemy, player.dealDamageToOpponent(enemy));
					break;
				case "bc":
					CLI.writeCreatureBattleCry(player);
					player.debuffPowerOfOpponent(enemy);
					CLI.writeCreatureDebuffed(enemy);
					break;
				case "su":
					CLI.writeCreatureShieldsUp(player);
					player.buffOwnDefense();
					CLI.writeCreatureBuffed(player);
					break;
				case "info":
					CLI.writeFightMenuInfo();
					CLI.enterToContinue();
					break;
				case "suicide": // for testing
					player.kill();
					break;
				case "instakill": // for testing
					enemy.kill();
					break;
				default:
					CLI.writeCreatureConfused(player);
					break;
				}
				
				Utils.waitInMs(1500);
				
				if(enemy.isAlive())
					CLI.writeCreatureAttacksCreature(enemy, player, enemy.dealDamageToOpponent(player));
				// TODO : default enemy AI, then maybe enemy-specific AI
			}
			
			if(!enemy.isAlive()) {
				if(nightCounter < 10) {
					CLI.writeCreatureDeath(enemy);
					Utils.waitInMs(1500);
					CLI.writeJourneyContinue();
					Utils.waitInMs(1500);
				} else {
					CLI.writeBossDeath(enemy);
					Utils.waitInMs(3000);
				}
			} else if(!player.isAlive()) {
				CLI.writeCreatureDeath(player);
				CLI.writeYouDied();
			}
			
			nightCounter++;
		}
		
		if(nightCounter > 10)
			nightCounter = 10;
		
		Utils.waitInMs(1500);
		CLI.writeScoreOnRunEnd(player, nightCounter);
		Utils.waitInMs(1500);
		CLI.writeAddScore();
		
		if(CLI.readConsoleInput().contentEquals("y")) {
			CLI.writeAskName();
			String name = CLI.readConsoleInput();
			
			if(nightCounter > 10)
				nightCounter = 10;
			String scoreString = CLI.createScoreStringNameNightHp(name, nightCounter, player.getCurrentHp());
			CLI.addScoreToScoreboard(scoreString);
		} else {
			CLI.writeDontSaveScore();
		}
		
		CLI.enterToContinue();
	}
	
	private Creature generateRndEnemy() {
		switch (Utils.generateRandomMinMax(1, enemyTypes)) { // TODO : Creature Factory
		case 1:
			return new SneakySkeleton();
		case 2:
			return new GooeyGhost();
		case 3:
			return new DangerDoggo();
		default:
			return new HiddenBug();
		}
	}
	
	private Creature generateBoss() {
		return new Boss();
	}
	
	private boolean randomEnemyGetsFirstAttack() {
		// Chance 1 out of 3 that enemy attacks first
		switch(Utils.generateRandomMinMax(1, 6)) {
		case 1:
		case 2:
		case 3:
		case 4:
			return false;
		case 5:
		case 6:
			return true;
		default:
			return true;
		}
	}
	
	/*  ### Reference for graphical design
	private void init() {
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		Assets.init();
		
		handler = new Handler(this);
		
		gameState = new GameState(handler);
		menuState = new MenuState(handler);
		State.setState(menuState); // state on startup
	}
		
	private void tick() { // == update buffers
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
		// Clear Screen
		g.clearRect(0, 0, width, height);
		// Draw Here!
		
		if(State.getState() != null)
			State.getState().render(g);
		
		// End Drawing!
		bs.show();
		g.dispose();
	}
	
	##### This block was in run() !!!
	
		init();
	
		int fps = 60; // tick&renders per second
		double timePerTick = 1_000_000_000 / fps; // max amount of time to execute tick&renders to achieve fps
		double delta = 0; // tick&renders to do to keep the pace
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
	
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
				System.out.println("Ticks & Frames: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}
	
		stop();
		
	##### Until this point, yesyes I know, awful code design etc. Write it somewhere I cant't see cuz I already know

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
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	*/
}
