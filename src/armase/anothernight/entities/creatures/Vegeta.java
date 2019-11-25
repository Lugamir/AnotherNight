package armase.anothernight.entities.creatures;

import armase.anothernight.Handler;
import armase.anothernight.gfx.Animation;
import armase.anothernight.gfx.Assets;

public class Vegeta extends Creature {

	public Vegeta(String name, int currentHp, int maxHp, int power, int defense) {
		super("Vegeta", 9001, 9001, 9001, 9001);
		isEnemy = false;
		
		// Animations - set anim speed in ms here
		int animSpeed = 300;
		animIdle = new Animation(animSpeed, Assets.player_idle);
		animAttack = new Animation(animSpeed, Assets.player_attack);
		animDeath = new Animation(animSpeed, Assets.player_death);
	}
	
	public Vegeta(Handler handler) {
		super("Vegeta", 9001, 9001, 9001, 9001);
		isEnemy = false;
		
		// Animations - set anim speed in ms here
		int animSpeed = 300;
		animIdle = new Animation(animSpeed, Assets.player_idle);
		animAttack = new Animation(animSpeed, Assets.player_attack);
		animDeath = new Animation(animSpeed, Assets.player_death);
	}

}
