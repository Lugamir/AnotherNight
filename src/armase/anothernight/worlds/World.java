package armase.anothernight.worlds;

import java.awt.Graphics;

import armase.anothernight.Handler;
import armase.anothernight.entities.EntityManager;
import armase.anothernight.entities.creatures.Creature;
import armase.anothernight.entities.creatures.Player;
import armase.anothernight.entities.creatures.enemies.Boss;
import armase.anothernight.entities.creatures.enemies.DangerDoggo;
import armase.anothernight.entities.creatures.enemies.HiddenBug;
import armase.anothernight.entities.creatures.enemies.Mushdrool;
import armase.anothernight.entities.creatures.enemies.SneakySkeleton;
import armase.anothernight.ui.CLI;
import armase.anothernight.ui.GUI;
import armase.anothernight.utils.Utils;

public class World {
	
	private Handler handler;
	private GUI gui;
	private int width, height;
	
	// Entities
	private EntityManager entityManager;
	
	// run specific variables
	Creature player, enemy;
	private final int enemyTypes = 3;
	private int nightCounter;
	
	public World(Handler handler) {
		this.handler = handler;
		this.gui = new GUI(handler);
		
		// Test
		player = new Player(handler);
		gui.setPlayer(player);
		entityManager = new EntityManager(handler, player);
		
		enemy = generateRndEnemy();
		gui.setEnemy(enemy);
		entityManager.addEntity(enemy);
	}
	
	public void tick() {
		handler.getBackdropManager().tick();
		entityManager.tick();
	}
	
	public void render(Graphics g) {
		handler.getBackdropManager().render(g);
		entityManager.render(g);

		// TODO : render UI
	}
	
	private void playRound() {
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
		// TODO : Creature Factory
		switch (Utils.generateRandomMinMax(1, enemyTypes)) {
		case 1:
			return new SneakySkeleton();
		case 2:
			return new Mushdrool();
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
	
	// ### GETTERS & SETTERS

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
}
