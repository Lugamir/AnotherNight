package armase.anothernight.ui;

import armase.anothernight.Handler;
import armase.anothernight.entities.creatures.Creature;

public class GUI {
	
	private Handler handler;

	private Creature player;
	private int playerPosX;
	private int playerPosY;
	private Creature enemy;
	private int enemyPosX;
	private int enemyPosY;
	
	public GUI(Handler handler) {
		this.handler = handler;
		
		playerPosX = handler.getWidth() - 240;
		playerPosY = handler.getHeight() / 10 * 5;
		enemyPosX = handler.getWidth() / 3 - 240;
		enemyPosY =  handler.getHeight() / 10 * 5;
	}
	
	public void setPlayer(Creature player) {
		this.player = player;
		this.player.setPosition(playerPosX, playerPosY);
	}
	
	public void removePlayer() {
		player = null;
	}

	public void setEnemy(Creature enemy) {
		this.enemy = enemy;
		this.enemy.setPosition(enemyPosX, enemyPosY);
	}
	
	public void removeEnemy() {
		enemy = null;
	}
}
