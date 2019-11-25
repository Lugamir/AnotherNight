package armase.anothernight.entities.creatures.enemies;

import armase.anothernight.Handler;
import armase.anothernight.entities.creatures.Creature;
import armase.anothernight.gfx.Animation;
import armase.anothernight.gfx.Assets;

public class Boss extends Creature {

	public Boss() {
		super("Weary Dragon", 25, 100, 17, 15);
		isEnemy = true;

		// Animations - set anim speed in ms here
		int animSpeed = 300;
		animIdle = new Animation(animSpeed, Assets.placeholders);
		animAttack = new Animation(animSpeed, Assets.placeholders);
		animDeath = new Animation(animSpeed, Assets.placeholders);
	}
	
	public Boss(Handler handler) {
		super("Weary Dragon", 25, 100, 17, 15, handler);

		// Animations - set anim speed in ms here
		int animSpeed = 300;
		animIdle = new Animation(animSpeed, Assets.placeholders);
		animAttack = new Animation(animSpeed, Assets.placeholders);
		animDeath = new Animation(animSpeed, Assets.placeholders);
	}
}
