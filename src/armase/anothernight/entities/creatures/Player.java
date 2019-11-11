package armase.anothernight.entities.creatures;

import armase.anothernight.Handler;
import armase.anothernight.gfx.Animation;
import armase.anothernight.gfx.Assets;

public class Player extends Creature {

	public Player() {
		super("Anni", 50, 50, 10, 10);
		isEnemy = false;
		
		// Animations - set anim speed in ms here
		int animSpeed = 300;
		animIdle = new Animation(animSpeed, Assets.player_idle);
	}
	
	public Player(Handler handler) {
		super("Anni", 50, 50, 10, 10);
		isEnemy = false;
		
		// Animations - set anim speed in ms here
		int animSpeed = 300;
		animIdle = new Animation(animSpeed, Assets.player_idle);
	}
}
