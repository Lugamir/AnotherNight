package armase.anothernight.entities.creatures.enemies;

import armase.anothernight.Handler;
import armase.anothernight.entities.creatures.Creature;
import armase.anothernight.gfx.Animation;
import armase.anothernight.gfx.Assets;

public class SneakySkeleton extends Creature {

	public SneakySkeleton() {
		super("Sneaky Skeleton", 10, 10, 5, 3);
		isEnemy = true;
		
		// Animations - set anim speed in ms here
		int animSpeed = 300;
		animIdle = new Animation(animSpeed, Assets.skeleton_idle);
		animAttack = new Animation(animSpeed, Assets.skeleton_attack);
		animDeath = new Animation(animSpeed, Assets.skeleton_death);
	}
	
	public SneakySkeleton(Handler handler) {
		super("Sneaky Skeleton", 10, 10, 5, 3, handler);
		isEnemy = true;
		
		// Animations - set anim speed in ms here
		int animSpeed = 300;
		animIdle = new Animation(animSpeed, Assets.skeleton_idle);
		animAttack = new Animation(animSpeed, Assets.skeleton_attack);
		animDeath = new Animation(animSpeed, Assets.skeleton_death);
	}
}
