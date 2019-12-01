package armase.anothernight.entities.creatures.enemies;

import armase.anothernight.Handler;
import armase.anothernight.entities.creatures.Creature;
import armase.anothernight.gfx.Animation;
import armase.anothernight.gfx.Assets;

public class HiddenBug extends Creature {

	public HiddenBug() {
		super("Hidden Bug", 1, 1, 1, 1);
		isEnemy = true;
		
		animIdle = new Animation(animSpeed, Assets.placeholders);
		animAttack = new Animation(animSpeed, Assets.placeholders);
		animDeath = new Animation(animSpeed, Assets.placeholders);
		currentAnim = animIdle;
	}
	
	public HiddenBug(Handler handler) {
		super("Hidden Bug", 1, 1, 1, 1, handler);
		isEnemy = true;
		
		animIdle = new Animation(animSpeed, Assets.placeholders);
		animAttack = new Animation(animSpeed, Assets.placeholders);
		animDeath = new Animation(animSpeed, Assets.placeholders);
		currentAnim = animIdle;
	}
}
