package armase.anothernight.entities.creatures.enemies;

import armase.anothernight.Handler;
import armase.anothernight.entities.creatures.Creature;
import armase.anothernight.gfx.Animation;
import armase.anothernight.gfx.Assets;

public class DangerDoggo extends Creature {

	public DangerDoggo() {
		super("Danger Doggo", 12, 12, 13, 12);
		isEnemy = true;

		animIdle = new Animation(animSpeed, Assets.doggo_idle);
		animAttack = new Animation(animSpeed, Assets.doggo_attack);
		animDeath = new Animation(animSpeed, Assets.doggo_death);
		currentAnim = animIdle;
	}
	
	public DangerDoggo(Handler handler) {
		super("Danger Doggo", 12, 12, 13, 12, handler);

		animIdle = new Animation(animSpeed, Assets.doggo_idle);
		animAttack = new Animation(animSpeed, Assets.doggo_attack);
		animDeath = new Animation(animSpeed, Assets.doggo_death);
		currentAnim = animIdle;
	}
}
