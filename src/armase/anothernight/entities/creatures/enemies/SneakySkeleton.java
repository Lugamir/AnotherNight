package armase.anothernight.entities.creatures.enemies;

import armase.anothernight.Handler;
import armase.anothernight.entities.creatures.Creature;
import armase.anothernight.gfx.Animation;
import armase.anothernight.gfx.Assets;

public class SneakySkeleton extends Creature {

	public SneakySkeleton() {
		super("Sneaky Skeleton", 10, 10, 5, 3);
		isEnemy = true;
		
		animIdle = new Animation(Assets.skeleton_idle);
		animAttack = new Animation(Assets.skeleton_attack);
		animDeath = new Animation(Assets.skeleton_death);
		currentAnim = animIdle;
	}
	
	public SneakySkeleton(Handler handler) {
		super("Sneaky Skeleton", 10, 10, 5, 3, handler);
		isEnemy = true;
		
		animIdle = new Animation(Assets.skeleton_idle);
		animAttack = new Animation(Assets.skeleton_attack);
		animDeath = new Animation(Assets.skeleton_death);
		currentAnim = animIdle;
	}
}
