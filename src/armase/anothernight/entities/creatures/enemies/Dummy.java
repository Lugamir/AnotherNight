package armase.anothernight.entities.creatures.enemies;

import armase.anothernight.Handler;
import armase.anothernight.entities.creatures.Creature;
import armase.anothernight.gfx.Animation;
import armase.anothernight.gfx.Assets;

public class Dummy extends Creature {

	public Dummy() {
		super("Fred the Dummy", 100, 100, 0, 12);
		isEnemy = true;
		
		animIdle = new Animation(Assets.dummy_all);
		animAttack = new Animation(Assets.dummy_all);
		animDeath = new Animation(Assets.dummy_all);
		currentAnim = animIdle;
	}
	
	public Dummy(Handler handler) {
		super("Fred the Dummy", 100, 100, 0, 12, handler);
		isEnemy = true;
		
		animIdle = new Animation(Assets.dummy_all);
		animAttack = new Animation(Assets.dummy_all);
		animDeath = new Animation(Assets.dummy_all);
		currentAnim = animIdle;
	}
}
