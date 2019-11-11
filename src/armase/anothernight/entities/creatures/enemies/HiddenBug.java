package armase.anothernight.entities.creatures.enemies;

import armase.anothernight.Handler;
import armase.anothernight.entities.creatures.Creature;
import armase.anothernight.gfx.Animation;
import armase.anothernight.gfx.Assets;

public class HiddenBug extends Creature {

	public HiddenBug() {
		super("Hidden Bug", 1, 1, 1, 1);
		isEnemy = true;
		
		// Animations - set anim speed in ms here
		int animSpeed = 300;
		animIdle = new Animation(animSpeed, Assets.mushdrool_idle);
	}
	
	public HiddenBug(Handler handler) {
		super("Hidden Bug", 1, 1, 1, 1, handler);
		isEnemy = true;
		
		// Animations - set anim speed in ms here
		int animSpeed = 300;
		animIdle = new Animation(animSpeed, Assets.mushdrool_idle);
	}
}
