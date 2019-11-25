package armase.anothernight;

import armase.anothernight.entities.creatures.Creature;
import armase.anothernight.entities.creatures.Player;
import armase.anothernight.entities.creatures.enemies.Boss;
import armase.anothernight.entities.creatures.enemies.DangerDoggo;
import armase.anothernight.entities.creatures.enemies.HiddenBug;
import armase.anothernight.entities.creatures.enemies.Mushdrool;
import armase.anothernight.entities.creatures.enemies.SneakySkeleton;
import armase.anothernight.ui.CLI;
import armase.anothernight.utils.Utils;

public class ConsoleVersion {
	
	private int nightCounter = 0;
	private final int enemyTypes = 3;
	private CLI cli;

	public ConsoleVersion() {
		cli = new CLI(System.console());
		start();
	}
	
	private void start() {
		cli.writeWelcomeMsg();
		cli.writeRandomMotd();
		Utils.waitInMs(1000);
		
		while(true) {
			cli.writeMainMenu();
			
			switch (cli.readConsoleInput()) {
			case "play":
				playRound();
				break;
			case "scobo":
				cli.writeScoreboard();
				cli.enterToContinue();
				break;
			case "info":
				Utils.waitInMs(1000);
				cli.writeGameInfo();
				Utils.waitInMs(1000);
				cli.enterToContinue();
				break;
			case "quit":
				Utils.waitInMs(1000);
				cli.writeExitMsg();
				Utils.waitInMs(1500);
				return;
			default:
				cli.writeBadInput();
				break;
			}
		}
	}
	
	private void playRound() {
		Creature player = new Player();
		nightCounter = 1;
		Creature enemy;
		
		Utils.waitInMs(1500);
		cli.writeJourneyBeginning();
		
		while(player.isAlive() && nightCounter <= 10) {
			Utils.waitInMs(1500);
			cli.writeNightCount(nightCounter);
			Utils.waitInMs(1500);
			
			if(nightCounter == 10)
				enemy = generateBoss();
			else
				enemy = generateRndEnemy();
			
			cli.writeCreatureAppearance(enemy);
			
			if(randomEnemyGetsFirstAttack()) {
				cli.writeEnemyGotFirstMove(enemy);
				cli.writeCreatureAttacksCreature(enemy, player, enemy.dealDamageToOpponent(player));
			}
			
			while(enemy.isAlive() && player.isAlive()) {
				// TODO : revert buffs after fight
				
				Utils.waitInMs(1500);
				cli.writeCreatureStatus(player);
				cli.writeCreatureStatus(enemy);
				cli.writeFightMenu();
				
				switch(cli.readConsoleInput()) {
				case "atk":
					cli.writeCreatureAttacksCreature(player, enemy, player.dealDamageToOpponent(enemy));
					break;
				case "bc":
					cli.writeCreatureBattleCry(player);
					player.debuffPowerOfOpponent(enemy);
					cli.writeCreatureDebuffed(enemy);
					break;
				case "su":
					cli.writeCreatureShieldsUp(player);
					player.buffOwnDefense();
					cli.writeCreatureBuffed(player);
					break;
				case "info":
					cli.writeFightMenuInfo();
					cli.enterToContinue();
					break;
				case "suicide": // for testing
					player.kill();
					break;
				case "instakill": // for testing
					enemy.kill();
					break;
				default:
					cli.writeCreatureConfused(player);
					break;
				}
				
				Utils.waitInMs(1500);
				
				if(enemy.isAlive())
					cli.writeCreatureAttacksCreature(enemy, player, enemy.dealDamageToOpponent(player));
				// TODO : default enemy AI, then maybe enemy-specific AI
			}
			
			if(!enemy.isAlive()) {
				if(nightCounter < 10) {
					cli.writeCreatureDeath(enemy);
					Utils.waitInMs(1500);
					cli.writeJourneyContinue();
					Utils.waitInMs(1500);
				} else {
					cli.writeBossDeath(enemy);
					Utils.waitInMs(3000);
				}
			} else if(!player.isAlive()) {
				cli.writeCreatureDeath(player);
				cli.writeYouDied();
			}
			
			nightCounter++;
		}
		
		if(nightCounter > 10)
			nightCounter = 10;
		
		Utils.waitInMs(1500);
		cli.writeScoreOnRunEnd(player, nightCounter);
		Utils.waitInMs(1500);
		cli.writeAddScore();
		
		if(cli.readConsoleInput().contentEquals("y")) {
			cli.writeAskName();
			String name = cli.readConsoleInput();
			
			if(nightCounter > 10)
				nightCounter = 10;
			String scoreString = cli.createScoreStringNameNightHp(name, nightCounter, player.getCurrentHp());
			cli.addScoreToScoreboard(scoreString);
		} else {
			cli.writeDontSaveScore();
		}
		
		cli.enterToContinue();
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
}
