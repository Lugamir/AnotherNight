package armase.anothernight.entities.creatures.enemies;

import armase.anothernight.Handler;
import armase.anothernight.entities.creatures.Creature;
import armase.anothernight.gfx.Animation;
import armase.anothernight.gfx.Assets;

public class DangerDoggo extends Creature {

	public DangerDoggo() {
		super("Danger Doggo", 12, 12, 13, 12);
		isEnemy = true;

		// Animations - set anim speed in ms here
		int animSpeed = 300;
		animIdle = new Animation(animSpeed, Assets.doggo_idle);
	}
	
	public DangerDoggo(Handler handler) {
		super("Danger Doggo", 12, 12, 13, 12, handler);

		// Animations - set anim speed in ms here
		int animSpeed = 300;
		animIdle = new Animation(animSpeed, Assets.doggo_idle);
	}
}
