package armase.anothernight.entities.creatures.enemies;

import armase.anothernight.Handler;
import armase.anothernight.entities.creatures.Creature;
import armase.anothernight.gfx.Animation;
import armase.anothernight.gfx.Assets;

public class Mushdrool extends Creature {
	
	public Mushdrool() {
		super("Mushdrool", 20, 20, 8, 15);
		isEnemy = true;
		
		// Animations - set anim speed in ms here
		int animSpeed = 300;
		animIdle = new Animation(animSpeed, Assets.mushdrool_idle);
		animAttack = new Animation(animSpeed, Assets.mushdrool_attack);
		animDeath = new Animation(animSpeed, Assets.mushdrool_death);
	}
	
	public Mushdrool(Handler handler) {
		super("Mushdrool", 20, 20, 8, 15, handler);
		isEnemy = true;
		
		// Animations - set anim speed in ms here
		int animSpeed = 300;
		animIdle = new Animation(animSpeed, Assets.mushdrool_idle);
		animAttack = new Animation(animSpeed, Assets.mushdrool_attack);
		animDeath = new Animation(animSpeed, Assets.mushdrool_death);
	}
}
